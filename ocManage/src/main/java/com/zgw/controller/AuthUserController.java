package com.zgw.controller;

import com.zgw.core.auth.domain.AuthUser;
import com.zgw.core.auth.service.IAuthUserService;
import com.zgw.page.TailPage;
import com.zgw.web.JsonView;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class AuthUserController {

    @Autowired
    private IAuthUserService authUserService;

    @RequestMapping("/getById")
    @ResponseBody
    public String getById(Long id){
        AuthUser user = this.authUserService.getById(id);
        return JsonView.render(user).toString();
    }

    @RequestMapping("/userPageList")
    public String userPageList(AuthUser entity, TailPage<AuthUser> page,
                               Model model){
        model.addAttribute("curNav","user");

        if (StringUtils.isNotEmpty(entity.getUsername())){
            entity.setUsername(entity.getUsername().trim());
        }else {
            entity.setUsername(null);
        }
        if (Integer.valueOf(-1).equals(entity.getStatus())){
            entity.setStatus(null);
        }
        page = this.authUserService.queryPage(entity,page);
        model.addAttribute("page",page);
        model.addAttribute("queryEntity",entity);
        return "cms/user/userPageList";
    }

    @RequestMapping("/doMerge")
    @ResponseBody
    public String doMerge(AuthUser entity){
        //不更新的字段
        entity.setUsername(null);
        entity.setRealname(null);
        this.authUserService.updateSelectivity(entity);
        return new JsonView(0).toString();
    }
}
