package com.zgw.core.user.entity;

import com.zgw.orm.BaseEntity;

public class UserFollows extends BaseEntity {
    /**
     *用户id
     **/
    private Long userId;

    /**
     *关注的用户id
     **/
    private Long followId;

    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }

    public Long getFollowId(){
        return followId;
    }
    public void setFollowId(Long followId){
        this.followId = followId;
    }

    @Override
    public String toString() {
        return "UserFollows{" +
                "userId=" + userId +
                ", followId=" + followId +
                '}';
    }
}
