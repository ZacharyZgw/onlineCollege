package com.zgw.controller;

import com.zgw.web.SessionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ManageController {

    @RequestMapping(value = {"/","/index"})
    public String index(){
        if (!SessionContext.isLogin()){
            return "auth/login";
        }else{
            return "cms/index";
        }
    }

}
