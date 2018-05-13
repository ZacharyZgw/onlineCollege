package com.zgw.controller;

import com.zgw.core.auth.domain.AuthUser;
import com.zgw.core.auth.service.IAuthUserService;
import com.zgw.core.course.entity.Course;
import com.zgw.core.course.service.ICourseService;
import com.zgw.core.user.entity.*;
import com.zgw.core.user.service.IUserCollectionService;
import com.zgw.core.user.service.IUserCourseSectionService;
import com.zgw.core.user.service.IUserFollowsService;
import com.zgw.page.TailPage;
import com.zgw.web.JsonView;
import com.zgw.web.SessionContext;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController{

    @Autowired
    private IAuthUserService authUserService;

    @Autowired
    private IUserCourseSectionService userCourseSectionService;

    @Autowired
    private IUserCollectionService collectionService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IUserFollowsService userFollowsService;

    @RequestMapping("/teacher/{teacherId}")
    public String showTeacher(@PathVariable("teacherId") Long id,Model model,String classify,TailPage<Course> page){
        AuthUser teacher = this.authUserService.getById(id);
        if (null != teacher){
            model.addAttribute("teacher",teacher);
            String userName = teacher.getUsername();
            List<String> courseClassify=this.courseService.getAllCourseClassify(userName);
            if (CollectionUtils.isNotEmpty(courseClassify)){
                model.addAttribute("courseClassify",courseClassify);
            }
            Course course = new Course();
            course.setUsername(userName);
            if (null != classify && StringUtils.isNotEmpty(classify)){
                course.setSubClassify(classify);
                model.addAttribute("classify",classify);
                page = courseService.queryPageByTeacher(course,page);
            }else {
                page = courseService.queryPageByTeacher(course,page);
            }
            model.addAttribute("page",page);

        }else {
            return "error/404";
        }


        return "user/teacherInfo";
    }

    @RequestMapping("/userInfo")
    public String userInfo(Model model){
        Long curUserId = SessionContext.getUserId();
        AuthUser user =authUserService.getById(curUserId);
        if (null != user){
            model.addAttribute("user",user);
        }
        return "user/userInfo";
    }
    @RequestMapping("/update")
    @ResponseBody
    public String update(AuthUser user){
        user.setId(SessionContext.getUserId());
        this.authUserService.updateSelectivity(user);
        return JsonView.render(0).toString();
    }
    @RequestMapping("/course")
    public String home(String classify,Model model, TailPage<UserCourseSectionDto> page){
        page.setPageSize(2);
        AuthUser user = authUserService.getById(SessionContext.getUserId());
        model.addAttribute("user",user);
        model.addAttribute("curnav","course");
        List<String> courseClassifyList = new ArrayList<>();
        /*
        * 用户学习进度
        * */
        courseClassifyList = userCourseSectionService.queryAllCourseSubClassify();
        model.addAttribute("courseClassifyList",courseClassifyList);
        UserCourseSection userCourseSection = new UserCourseSection();
        userCourseSection.setUserId(SessionContext.getUserId());
        if (null != classify || StringUtils.isNotEmpty(classify)){
            model.addAttribute("classify",classify);
            userCourseSection.setCourseSubClassify(classify);
            page = userCourseSectionService.queryPageBycourseSubClassify(userCourseSection,page);
        }else {
            page = userCourseSectionService.queryPageBycourseSubClassify(userCourseSection,page);
        }
        model.addAttribute("page",page);
        return "user/userCourse";
    }

    /*
    * 我的收藏
    * */
    @RequestMapping("/collections")
    public String collection(String classify, Model model, TailPage<UserCollectionsDto> page){
        AuthUser user = authUserService.getById(SessionContext.getUserId());
        model.addAttribute("user",user);
        model.addAttribute("curnav","collections");
        List<String> courseClassifyList = collectionService.queryAllCourseSubClassify();
        model.addAttribute("courseClassifyList",courseClassifyList);
        UserCollections userCollections = new UserCollections();
        userCollections.setUserId(SessionContext.getUserId());
        if (null != classify || StringUtils.isNotEmpty(classify)){
            userCollections.setCourseSubClassify(classify);
            page = collectionService.queryPage(userCollections,page);
        }else {
            page = collectionService.queryPage(userCollections,page);
        }
        model.addAttribute("page",page);
        System.out.println("cvbrhjngvmkyj,ljghfrcdvbhcvnjmkncfevgrbhnjmytfcfvgbnhjmurfdcvgbnhjm"+page.getPageTotalCount());

        return "user/userCollections";
    }

    @RequestMapping("/follows")
    public String follows(Model model , TailPage<UserFollows> page){
        AuthUser user = authUserService.getById(SessionContext.getUserId());
        model.addAttribute("user",user);
        model.addAttribute("curnav","follows");
        UserFollows userFollows = new UserFollows();
        userFollows.setUserId(SessionContext.getUserId());
        page = this.userFollowsService.queryPage(userFollows,page);
        if (null != page){
            model.addAttribute("page",page);
        }
        return "user/userFollows";

    }





}
