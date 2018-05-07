package com.zgw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cms")
public class cmsController {

    @RequestMapping(value = {"/","/index"})
    public String cmsIndex(Model model){
        model.addAttribute("curNav","home");
        return "cms/index";
    }
}
