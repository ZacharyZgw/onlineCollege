package com.zgw.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {

    @RequestMapping("/getSearch")
    public  String getSearch(){
        return "search";
    }
}
