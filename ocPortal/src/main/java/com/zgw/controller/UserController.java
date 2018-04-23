package com.zgw.controller;

import com.zgw.core.auth.domain.AuthUser;
import com.zgw.core.auth.service.IAuthUserService;
import com.zgw.core.user.entity.UserCollections;
import com.zgw.core.user.entity.UserCourseSection;
import com.zgw.core.user.entity.UserCourseSectionDto;
import com.zgw.core.user.service.IUserCollectionService;
import com.zgw.core.user.service.IUserCourseSectionService;
import com.zgw.page.TailPage;
import com.zgw.vo.UserCollectionsVo;
import com.zgw.vo.UserCourseSectionVo;
import com.zgw.web.SessionContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/userInfo")
    public String userInfo(Model model){
        Long curUserId = SessionContext.getUserId();
        AuthUser user =authUserService.getById(curUserId);
        if (null != user){
            model.addAttribute("user",user);
        }
        return "user/userInfo";
    }

    @RequestMapping("/course")
    public String home(String classify,Model model, TailPage<UserCourseSectionDto> page){
        AuthUser user = authUserService.getById(SessionContext.getUserId());
        model.addAttribute("user",user);
        model.addAttribute("curnav","course");

        /*
        * 用户学习进度
        * */
        List<String> courseClassifyList = userCourseSectionService.queryAllCourseSubClassify();
        model.addAttribute("courseClassifyList",courseClassifyList);
        UserCourseSection userCourseSection = new UserCourseSection();
        userCourseSection.setUserId(SessionContext.getUserId());
        if (null != classify || StringUtils.isNotEmpty(classify)){
            userCourseSection.setCourseSubClassify(classify);
            page = userCourseSectionService.queryPageBycourseSubClassify(userCourseSection,page);
        }else {
            page = userCourseSectionService.queryPage(userCourseSection,page);
        }
        model.addAttribute("page",page);
        return "user/userCourse";
    }

    /*
    * 我的收藏
    * */
    @RequestMapping("/collections")
    public String collection(String classify, Model model, TailPage<UserCollections> page){
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
        return "user/collections";
    }




}
