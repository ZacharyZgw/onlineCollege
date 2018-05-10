package com.zgw.controller;


import com.zgw.core.auth.domain.AuthUser;
import com.zgw.core.auth.service.IAuthUserService;
import com.zgw.util.EncryptUtil;
import com.zgw.web.HttpHelper;
import com.zgw.web.SessionContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthUserService authUserService;

    @RequestMapping(value = {"/login"})
    public String login(){
        if (SessionContext.isLogin()){
            return "redirect:/cms/index";
        }else {
            return "auth/login";
        }
    }

    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin(AuthUser user,
                          String identiryCode,
                          Integer rememberMe , Model model,
                          HttpServletRequest request,
                          HttpServletResponse response){
        if (null != identiryCode &&
                !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))){
            model.addAttribute("errcode",1);
            return "auth/login";
        }
        boolean isRememberMe = false;
        if (null != rememberMe && rememberMe==1){
            isRememberMe = true;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),
                EncryptUtil.encodedByMD5(user.getPassword()),isRememberMe);
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);//shiro实现登录
            //HttpHelper.redirectHttpUrl(request,response,"redirect:/index");
            return "redirect:/cms/index";
        }catch (AuthenticationException ex){
            model.addAttribute("errcode",2);
            return "/auth/login";
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        SessionContext.shiroLogout();
        return "redirect:/index";
    }

}
