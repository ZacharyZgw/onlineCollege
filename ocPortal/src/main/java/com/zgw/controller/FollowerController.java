package com.zgw.controller;


import com.zgw.core.user.entity.UserFollows;
import com.zgw.core.user.service.IUserFollowsService;
import com.zgw.web.JsonView;
import com.zgw.web.SessionContext;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/follow")
public class FollowerController {

    @Autowired
    private IUserFollowsService userFollowsService;

    @RequestMapping("/doFollow")
    @ResponseBody
    public String doFollow(Long followId){
        UserFollows userFollows = new UserFollows();
        userFollows.setUserId(SessionContext.getUserId());
        if (null != followId){
            userFollows.setFollowId(followId);
        }
        List<UserFollows> tmpResult = this.userFollowsService.queryAll(userFollows);
        if (CollectionUtils.isNotEmpty(tmpResult)){
            //说明已经关注
            userFollowsService.delete(tmpResult.get(0));
            return JsonView.render(0,"取消关注成功").toString();
        }else {
            //说明没有关注
            this.userFollowsService.createSelectivity(userFollows);
            return JsonView.render(1,"关注成功").toString();
        }

    }

    @RequestMapping("/isFollow")
    @ResponseBody
    public String isFollow(Long followId){
        UserFollows userFollows = new UserFollows();
        userFollows.setUserId(SessionContext.getUserId());
        if (null != followId){
            userFollows.setFollowId(followId);
        }
        List<UserFollows> tmpResult = this.userFollowsService.queryAll(userFollows);
        if (CollectionUtils.isNotEmpty(tmpResult)){
            //已经关注
            return JsonView.render(1).toString();
        }else {
            return JsonView.render(0).toString();
        }

    }
}
