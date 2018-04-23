package com.zgw.controller;


import com.zgw.core.auth.domain.AuthUser;
import com.zgw.core.course.CourseEnum;
import com.zgw.core.course.service.ICourseService;
import com.zgw.core.user.entity.UserCollections;
import com.zgw.core.user.service.IUserCollectionService;
import com.zgw.web.JsonView;
import com.zgw.web.SessionContext;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/collections")
public class CollectionsController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IUserCollectionService userCollectionService;

    @RequestMapping("/doCollection")
    @ResponseBody
    public String doCollection(Long courseId){
        Long userId = SessionContext.getUserId();
        UserCollections userCollections = new UserCollections();
        userCollections.setUserId(userId);
        userCollections.setClassify(CourseEnum.COLLECTION_CLASSIFY_COURSE.value());
        userCollections.setCourseSubClassify(courseService.getByCourseId(courseId).getSubClassify());
        userCollections.setObjectId(courseId);
        List<UserCollections> userCollectionsList = this.userCollectionService.queryAll(userCollections);
        if (CollectionUtils.isNotEmpty(userCollectionsList)){
            //说明用户已经收藏,执行取消收藏
            userCollectionService.delete(userCollectionsList.get(0));
            return JsonView.render(0,"取消收藏成功").toString();
        }else {
            this.userCollectionService.createSelectivity(userCollections);
            return JsonView.render(1,"已经成功收藏").toString();
        }
    }

    @RequestMapping(value = "/isCollection")
    @ResponseBody
    public String isCollection(Long courseId){
        //获取当前用户
        Long curUserId = SessionContext.getUserId();
        UserCollections userCollections = new UserCollections();

        userCollections.setUserId(curUserId);
        userCollections.setClassify(CourseEnum.COLLECTION_CLASSIFY_COURSE.value());//课程收藏
        userCollections.setObjectId(courseId);
        userCollections.setCourseSubClassify(this.courseService.getByCourseId(courseId).getSubClassify());
        List<UserCollections> list = this.userCollectionService.queryAll(userCollections);

        if(CollectionUtils.isNotEmpty(list)){//已经收藏
            return new JsonView(1,"已经收藏").toString();
        }else{
            return new JsonView(0,"未收藏").toString();
        }
    }
}
