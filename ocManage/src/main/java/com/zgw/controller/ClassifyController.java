package com.zgw.controller;

import com.zgw.business.IPortalBusiness;
import com.zgw.core.consts.entity.Classify;
import com.zgw.core.consts.service.IClassifyService;
import com.zgw.page.TailPage;
import com.zgw.vo.ConstsClassifyVO;
import com.zgw.web.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/classify")
public class ClassifyController {

    @Autowired
    private IClassifyService classifyService;

    @Autowired
    private IPortalBusiness portalBusiness;

    @RequestMapping("/getById")
    @ResponseBody
    public String getById(Long id){
        return JsonView.render(this.classifyService.getById(id)).toString();
    }

    @RequestMapping("/index")
    public String queryClassify(Classify entity, Model model, TailPage<Classify> page){
        model.addAttribute("curNav","classify");
        Map<String,ConstsClassifyVO> classifyMap = this.portalBusiness.queryAllClassifyMap();
        //所有一级分类
        List<ConstsClassifyVO> classifyList = new ArrayList<>();
        for (ConstsClassifyVO vo:classifyMap.values()
             ) {
            classifyList.add(vo);

        }
        model.addAttribute("classifys",classifyList);
        //对应的二级分类
        List<Classify> subClassifyList = new ArrayList<>();
        for (ConstsClassifyVO vo:classifyMap.values()
             ) {
            subClassifyList.addAll(vo.getSubClassifyList());
        }
        model.addAttribute("subClassifys",subClassifyList);
        return "cms/classify/classifyIndex";
    }
    @RequestMapping("/doMerge")
    @ResponseBody
    public String doMerge(Classify entity){
        if (null ==entity.getId()){
            Classify tmpEntity = this.classifyService.getByCode(entity.getCode());
            if (null != tmpEntity){
                return JsonView.render(1,"此分类已经存在").toString();
            }
            this.classifyService.createSelectivity(entity);
        }else {
            this.classifyService.updateSelectivity(entity);
        }
        return new JsonView().toString();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Classify entity){
       this.classifyService.deleteLogic(entity);
       return new JsonView().toString();
    }
}
