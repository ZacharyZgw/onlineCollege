package com.zgw.controller;


import com.zgw.core.consts.entity.SiteCarousel;
import com.zgw.core.consts.service.ISiteCarouselService;
import com.zgw.page.TailPage;
import com.zgw.web.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;

@Controller
@RequestMapping("/carousel")
public class SiteCarouselController {

    @Autowired
    private ISiteCarouselService siteCarouselService;

    @RequestMapping("/queryPage")
    public String queryPage(SiteCarousel entity, TailPage<SiteCarousel> page, Model model){
        page.setPageSize(5);
        page = siteCarouselService.queryPage(entity,page);
        model.addAttribute("curNav","carousel");
        model.addAttribute("page",page);
        model.addAttribute("queryEntity",entity);
        return "cms/carousel/pagelist";
    }
    @RequestMapping("/toMerge")
    public String toMerge(SiteCarousel entity, Model model){
        model.addAttribute("curNav","carousel");
        if (null != entity.getId()){
            entity = this.siteCarouselService.getById(entity.getId());
        }
        model.addAttribute("entity",entity);
        return "cms/carousel/merge";
    }

    @RequestMapping(value = "/doMerge")
    public String doMerge(SiteCarousel entity,@RequestParam MultipartFile pictureImg){
        if (!pictureImg.isEmpty()){
            String imgPath = "res/i/"+pictureImg.getOriginalFilename();
            entity.setPicture(imgPath);
        }
        if (null != entity.getId()){
            //更新操作
            this.siteCarouselService.updateSelectivity(entity);
        }else {
            this.siteCarouselService.createSelectivity(entity);
        }
        return "redirect:/carousel/queryPage";
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public String delete(SiteCarousel entity){
        this.siteCarouselService.deleteLogic(entity);
        return new JsonView().toString();
    }

}
