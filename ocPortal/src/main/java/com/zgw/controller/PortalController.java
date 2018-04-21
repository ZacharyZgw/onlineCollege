package com.zgw.controller;

import com.zgw.business.IPortalBusiness;
import com.zgw.core.consts.entity.SiteCarousel;
import com.zgw.core.consts.service.ISiteCarouselService;
import com.zgw.core.course.CourseEnum;
import com.zgw.core.course.entity.Course;
import com.zgw.core.course.entity.CourseQueryDto;
import com.zgw.core.course.service.ICourseService;
import com.zgw.vo.ClassifyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class PortalController {
    @Autowired
    private ISiteCarouselService siteCarouselService;

    @Autowired
    private IPortalBusiness portalBusiness;

    @Autowired
    private ICourseService courseService;

    @RequestMapping("/return")
    public void return1(HttpServletResponse resp){
        try {
            resp.getWriter().print(123);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
    public String toIndex(Model model){
        //加载轮播图
        List<SiteCarousel> siteCarouselList =siteCarouselService.queryCarousels(4);
        model.addAttribute("siteCarouselList",siteCarouselList);

        //获取一级分类，设置课程推荐
        List<ClassifyVo> classifyVoList = portalBusiness.queryAllClassify();
        portalBusiness.prepareRecomdCourses(classifyVoList);
        model.addAttribute("classify",classifyVoList);

        //加载5门实战课程，根据weight进行排序
        CourseQueryDto queryDto = new CourseQueryDto();
        queryDto.setCount(10);
        queryDto.setFree(CourseEnum.FREE_NOT.value());
        queryDto.setOnsale(CourseEnum.ONSALE.value());
        queryDto.descSortField("weight");
        List<Course> actionCourseList = courseService.queryList(queryDto);
        model.addAttribute("actionCourseList",actionCourseList);

        //加载免费课程
        queryDto.setFree(CourseEnum.FREE.value());//非免费的：实战课
        List<Course> freeCourseList = this.courseService.queryList(queryDto);
        model.addAttribute("freeCourseList", freeCourseList);
        return "index";
    }

}
