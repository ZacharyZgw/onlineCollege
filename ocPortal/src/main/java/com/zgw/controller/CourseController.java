package com.zgw.controller;

import com.zgw.business.ICourseBusiness;
import com.zgw.core.auth.domain.AuthUser;
import com.zgw.core.auth.service.IAuthUserService;
import com.zgw.core.course.entity.Course;
import com.zgw.core.course.entity.CourseQueryDto;
import com.zgw.core.course.entity.CourseSection;
import com.zgw.core.course.service.ICourseService;
import com.zgw.vo.CourseSectionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IAuthUserService authUserService;

    @Autowired
    private ICourseBusiness courseBusiness;

    @RequestMapping("/to")
    public String to(){
        return "courseinfo";
    }
    @RequestMapping("/learn/{courseId}")
    public String learn(@PathVariable("courseId") Long courseId, Model model){
        if (null == courseId){
            return "error/404";
        }
        //获取课程
        Course course = courseService.getByCourseId(courseId);
        System.out.println("**************************************"+course+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        if (null == course){
            return "error/404";
        }
        /*课程分类定位*/
        String classify = course.getClassifyName();
        String subClassify = course.getSubClassifyName();
        String courseName = course.getName();
        model.addAttribute("classifyName",classify);
        model.addAttribute("subClassify",subClassify);
        model.addAttribute("courseName",courseName);

        /*获取讲师信息*/
        AuthUser courseTeacher = authUserService.getByUsername(course.getUsername());
        model.addAttribute("courseTeacher",courseTeacher);

        /*获取课程章节*/
        List<CourseSectionVo> courseSectionList = courseBusiness.getCourseSection(courseId);

        model.addAttribute("course",course);
        model.addAttribute("courseSectionList",courseSectionList);

        /*获取相关推荐课程*/
        CourseQueryDto queryEntity = new CourseQueryDto();
        queryEntity.descSortField("weight");
        queryEntity.setCount(5);//5门推荐课程
        queryEntity.setSubClassify(course.getSubClassify());
        List<Course> recomdCourseList = this.courseService.queryList(queryEntity);
        model.addAttribute("recomdCourseList", recomdCourseList);

        /*当前学习的章节*/
        return "courseinfo";


    }

}
