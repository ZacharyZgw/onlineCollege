package com.zgw.core.user.entity;

import com.zgw.orm.BaseEntity;

public class UserCollections extends BaseEntity {
    private Long userId;
    /*
    * 1:代表课程
    * */
    private Long classify;
    private Long objectId;
    private String tips;

    private String name;
    /*课程分类，如java*/
    private String courseSubClassify;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getClassify() {
        return classify;
    }

    public void setClassify(Long classify) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", courseSubClassify='" + courseSubClassify + '\'' +
                '}';
    }
}
