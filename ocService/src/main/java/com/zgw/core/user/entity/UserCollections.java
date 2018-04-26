package com.zgw.core.user.entity;

import com.zgw.orm.BaseEntity;

public class UserCollections extends BaseEntity {
    private Long userId;
    /*
    * 1:代表课程
    * */
    private Integer classify;
    private Long objectId;
    private String tips;

    //private String courseName;
    /*课程分类，如java*/
    private String courseSubClassify;
    //private String coursePicture;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }


    public String getCourseSubClassify() {
        return courseSubClassify;
    }

    public void setCourseSubClassify(String courseSubClassify) {
        this.courseSubClassify = courseSubClassify;
    }

    @Override
    public String toString() {
        return "UserCollections{" +
                "userId=" + userId +
                ", classify=" + classify +
                ", objectId=" + objectId +
                ", tips='" + tips + '\'' +
                ", courseSubClassify='" + courseSubClassify + '\'' +
                '}';
    }
}
