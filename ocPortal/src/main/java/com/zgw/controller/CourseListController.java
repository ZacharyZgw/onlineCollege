package com.zgw.controller;

import com.zgw.business.IPortalBusiness;
import com.zgw.core.consts.entity.Classify;
import com.zgw.core.consts.service.IClassifyService;
import com.zgw.core.course.CourseEnum;
import com.zgw.core.course.entity.Course;
import com.zgw.core.course.service.ICourseService;
import com.zgw.page.TailPage;
import com.zgw.vo.ClassifyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseListController {

    @Autowired
    private IClassifyService classifyService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IPortalBusiness portalBusiness;
    /*@RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model,TailPage<Course> page){
        String currentCode="-1";
        String currentSubCode = "-2";

        //加载所有课程分类
        Map<String,ClassifyVo> classifyMap = portalBusiness.queryAllClassifyMap();
        //加载一级分类
        List<ClassifyVo> classifyList =
                new ArrayList<>();
        for (ClassifyVo classifyVo:classifyMap.values()
             ) {
            classifyList.add(classifyVo);
        }
        model.addAttribute("classifyList",classifyList);

        //当前分类(一开始加载所有二级分类)
        List<Classify> allSubClassifyList = new ArrayList<>();
        for (ClassifyVo classifyVo:classifyMap.values()
             ) {
            allSubClassifyList.addAll(classifyVo.getSubClassifyList());
        }
        model.addAttribute("allSubClassifyList",allSubClassifyList);
        Course queryEntity = new Course();
        //分页参数
        queryEntity.setOnsale(CourseEnum.ONSALE.value());
        page.descSortField("id");
        page = this.courseService.queryPage(queryEntity, page);
        model.addAttribute("page", page);
        return "courseList";
    }*/

    @RequestMapping(value = "/list")
    public String list(String c, String sort, TailPage<Course> page,Model model){
        String currentCode= "-1";
        String currentSubCode = "-2";

        //加载所有课程分类
        Map<String,ClassifyVo> classifyMap = portalBusiness.queryAllClassifyMap();

        //加载一级分类
        List<ClassifyVo> classifyList =
                new ArrayList<>();
        for (ClassifyVo classifyVo:classifyMap.values()
                ) {
            classifyList.add(classifyVo);
        }

        model.addAttribute("classifyList",classifyList);
        System.out.println("***************1111111111"+c+"******************************************************************");
        Classify currentClassify =classifyService.getByCode(c);
        System.out.println("***************2222222222"+currentClassify+"******************************************************************");
        if (null == currentClassify){//没有此分类
            //获取所有二级分类
            List<Classify> currentSubClassify = new ArrayList<>();
            for (ClassifyVo vo:classifyMap.values()
                 ) {
                currentSubClassify.addAll(vo.getSubClassifyList());
            }
            model.addAttribute("currentSubClassify",currentSubClassify);
        }else {
            if (!"0".endsWith(currentClassify.getParentCode())){//当前是二级分类
                currentSubCode = currentClassify.getCode();
                currentCode = currentClassify.getParentCode();
                model.addAttribute("currentSubClassify",classifyMap.get(currentClassify.getParentCode()).getSubClassifyList());
            }else {//当前是一级分类
                currentCode = currentClassify.getCode();
                model.addAttribute("currentSubClassify",classifyMap.get(currentClassify.getCode()).getSubClassifyList());

            }

        }
        model.addAttribute("currentCode",currentCode);
        model.addAttribute("currentSubCode",currentSubCode);
        //分页排序数据
        //分页的分类参数
        Course queryEntity = new Course();
        if(!"-1".equals(currentCode)){
            queryEntity.setClassify(currentCode);
        }
        if(!"-2".equals(currentSubCode)){
            queryEntity.setSubClassify(currentSubCode);
        }
        //排序参数
        if("pop".equals(sort)){//最热
            page.descSortField("studyCount");
        }else{
            sort = "last";
            page.descSortField("id");
        }
        model.addAttribute("sort", sort);

        //分页参数
        queryEntity.setOnsale(CourseEnum.ONSALE.value());
        page = this.courseService.queryPage(queryEntity, page);
        model.addAttribute("page", page);

        return "fenlei";
    }
}
