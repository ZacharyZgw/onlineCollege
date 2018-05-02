package com.zgw.controller;

import com.zgw.business.ICourseBusiness;
import com.zgw.core.auth.domain.AuthUser;
import com.zgw.core.auth.service.IAuthUserService;
import com.zgw.core.course.entity.Course;
import com.zgw.core.course.entity.CourseQueryDto;
import com.zgw.core.course.entity.CourseSection;
import com.zgw.core.course.service.ICourseCommentService;
import com.zgw.core.course.service.ICourseSectionService;
import com.zgw.core.course.service.ICourseService;
import com.zgw.core.user.entity.UserCourseSection;
import com.zgw.core.user.service.IUserCourseSectionService;
import com.zgw.vo.CourseSectionVo;
import com.zgw.web.JsonView;
import com.zgw.web.SessionContext;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private ICourseSectionService courseSectionService;

    @Autowired
    private IUserCourseSectionService userCourseSectionService;

    @Autowired
    private ICourseCommentService courseCommentService;

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
        model.addAttribute("firstSectionId",courseSectionService.getFirstSectionId(courseId));
        /*当前学习的章节*/
        UserCourseSection userCourseSection = new UserCourseSection();
        userCourseSection.setCourseId(courseId);
        userCourseSection.setUserId(SessionContext.getUserId());
        userCourseSection = this.userCourseSectionService.queryLatest(userCourseSection);
        if (null != userCourseSection){
            CourseSection currentCourseSection =
                    courseSectionService.getById(userCourseSection.getSectionId());
            model.addAttribute("currentUserCourseSection",currentCourseSection);
        }
        return "courseinfo";


    }
    @RequestMapping(value = "/video/{sectionId}",method = RequestMethod.GET)
    public String video(@PathVariable("sectionId") Long sectionId,Model model){
        if (null == sectionId){
            return "error/404";
        }
        CourseSection courseSection = courseSectionService.getById(sectionId);
        if (null == courseSection){
            return "error/404";
        }

        /*获取课程章节*/
        List<CourseSectionVo> courseSectionList = courseBusiness.getCourseSection(courseSection.getCourseId());
        Course course = courseService.getByCourseId(courseSection.getCourseId());
        model.addAttribute("courseSectionList",courseSectionList);
        model.addAttribute("courseSection",courseSection);
        model.addAttribute("courseName",course.getName());

        //学习记录
        UserCourseSection userCourseSection = new UserCourseSection();
        userCourseSection.setUserId(SessionContext.getUserId());
        userCourseSection.setCourseId(courseSection.getCourseId());
        userCourseSection.setSectionId(courseSection.getId());
        UserCourseSection result = this.userCourseSectionService.queryLatest(userCourseSection);
        if (null == result){
            //新建学习记录
            userCourseSection.setCourseStatus(0);
            userCourseSection.setSectionStatus(0);
            userCourseSection.setCourseSubClassify(course.getSubClassify());
            userCourseSection.setCreateUser(SessionContext.getUsername());
            userCourseSectionService.createSelectivity(userCourseSection);
        }else {
            userCourseSectionService.updateSelectivity(userCourseSection);
        }
        return "video";
    }

    /*
    * AJax请求用户学习进度
    * */
    @RequestMapping("/getCurLearnInfo")
    @ResponseBody
    public String getCurLearnInfo(){
        JsonView jv = new JsonView();
        if (SessionContext.isLogin()){
            UserCourseSection userCourseSection = new UserCourseSection();
            userCourseSection.setUserId(SessionContext.getUserId());
            userCourseSection= userCourseSectionService.queryLatest(userCourseSection);
            if (null != userCourseSection){
                JSONObject jsonObject = new JSONObject();
                CourseSection curCourseSection = this.courseSectionService.getById(userCourseSection.getSectionId());
                if (null != curCourseSection){
                    jsonObject.put("curCourseSection",curCourseSection);
                }
                Course curCourse = this.courseService.getByCourseId(userCourseSection.getCourseId());
                if (null != curCourse){
                    jsonObject.put("curCourse",curCourse);
                }
                jv.setData(jsonObject);


            }
        }
        return jv.toString();
    }

}
