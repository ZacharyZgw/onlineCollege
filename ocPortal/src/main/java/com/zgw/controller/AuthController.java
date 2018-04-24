package com.zgw.controller;


import com.zgw.core.auth.domain.AuthUser;
import com.zgw.core.auth.service.IAuthUserService;
import com.zgw.util.EncryptUtil;
import com.zgw.web.HttpHelper;
import com.zgw.web.JsonView;
import com.zgw.web.SessionContext;
import jxl.common.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IAuthUserService authUserService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletRequest request){

        String requestPath = request.getHeader("Referer");
        logger.error("requesturl*********************",requestPath);
        if (SessionContext.isLogin()){
            return "redirect:"+requestPath;//返回之前的路径
        }else {
            SessionContext.setAttribute(request,"urlLast",requestPath);
            return "auth/loginV2";
        }

    }

    @RequestMapping(value = "/doAjaxLogin",method = RequestMethod.POST)
    @ResponseBody
    public String doAjaxLogin(AuthUser user,String identiryCode,Integer rememberMe,
                              HttpServletRequest request,HttpServletResponse response){
        logger.debug("identiryCode",identiryCode);
        logger.debug("authUser",user);
        logger.debug("rememberMe",rememberMe);
        if (identiryCode != null &&
                !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))){
            return JsonView.render(2);
        }
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),
                EncryptUtil.encodedByMD5(user.getPassword()));
        try{
            if (rememberMe != null && rememberMe ==1){
                token.setRememberMe(true);
            }
            currentUser.login(token);//shiro登录
            return new JsonView().toString();
        }catch (AuthenticationException ex){
            return JsonView.render(1);
        }

    }

    @RequestMapping(value = "/doAjaxRegister",method = RequestMethod.POST)
    @ResponseBody
    public String doAjaxRegister(AuthUser user,String identiryCode,HttpServletRequest request,
                                 HttpServletResponse response){
        if (identiryCode != null &&
                !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))){
            return JsonView.render(2,"验证码错误");
        }
        AuthUser tmpUser = authUserService.getByUsername(user.getUsername());
        if (tmpUser != null){
            return JsonView.render(1,"用户名已经被注册");
        }else {
            user.setPassword(EncryptUtil.encodedByMD5(user.getPassword()));
            authUserService.createSelectivity(user);
            return new JsonView().toString();
        }


    }

    @RequestMapping("/register")
    public String register(){
        return "auth/register";
    }
    @RequestMapping(value = "/doRegister",method = RequestMethod.POST)
    @ResponseBody
    public String doRegister(AuthUser user,String identiryCode,
                             HttpServletRequest request,
                             HttpServletResponse response){
        if (identiryCode != null &&
                !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))) {
            return JsonView.render(2);
        }
        AuthUser temUser = authUserService.getByUsername(user.getUsername());
        if (temUser != null){
            return JsonView.render(1);
        }else {
            user.setPassword(EncryptUtil.encodedByMD5(user.getPassword()));
            authUserService.createSelectivity(user);
            return JsonView.render(0);
        }
    }

    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin(AuthUser user, Integer rememberMe,String identryCode,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          Model model){

        logger.error("rememberMe+*&*^$%^&^&&&&&&&&&&&&&&&&&&&"+String.valueOf(rememberMe));
        logger.error("user"+user.toString());
        logger.error("urlLast",SessionContext.getAttribute(request,"urlLast").toString());
        Boolean isRememberMe=false;
        if (null != rememberMe && rememberMe == 1){
            isRememberMe=true;
        }
        String urlLast = SessionContext.getAttribute(request,"urlLast").toString();
        logger.error("urlLast#$%^&**^#$%^&*#$%^",urlLast);
        if (SessionContext.getAuthUser() != null){
            //用户已经登录
            return "redirect:"+urlLast;
        }
        if (identryCode != null &&
                !identryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))){
                model.addAttribute("errcode",1);
                return "/auth/loginV2";
        }
        UsernamePasswordToken token =
                new UsernamePasswordToken(user.getUsername(),
                        EncryptUtil.encodedByMD5(user.getPassword()),isRememberMe );

        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);//shiro实现登录
            HttpHelper.redirectHttpUrl(request,response,urlLast);
            return null;
        }catch (AuthenticationException ex){
            model.addAttribute("errcode",2);
            return "/auth/loginV2";
        }

    }
    @RequestMapping("/logout")
    public String doLogout(HttpServletRequest request){
        String requestPath = request.getHeader("Referer");
        SessionContext.shiroLogout();
        return "redirect:"+requestPath;

    }


}
