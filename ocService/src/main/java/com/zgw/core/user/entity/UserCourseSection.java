package com.zgw.core.user.entity;

import com.zgw.orm.BaseEntity;

import java.util.Date;

public class UserCourseSection extends BaseEntity {
    private Long userId;
    private Long courseId;
    /*
    * 课程学习状态0:学习中，1学习结束
    * */
    private Integer courseStatus;

    private Long sectionId;

    /*
    * 章节学习状态:0学习中，1学习结束
    * */
    private Integer sectionStatus;

    private String sectionTime;

    private Integer rate;
    private String courseSubClassify;

    public String getCourseSubClassify() {
        return courseSubClassify;
    }

    public void setCourseSubClassify(String courseSubClassify) {
        this.courseSubClassify = courseSubClassify;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(Integer courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getSectionStatus() {
        return sectionStatus;
    }

    public void setSectionStatus(Integer sectionStatus) {
        this.sectionStatus = sectionStatus;
    }

    public String getSectionTime() {
        return sectionTime;
    }

    public void setSectionTime(String sectionTime) {
        this.sectionTime = sectionTime;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "UserCourseSection{" +
                "userId=" + userId +
                ", courseId=" + courseId +
                ", courseStatus=" + courseStatus +
                ", sectionId=" + sectionId +
                ", sectionStatus=" + sectionStatus +
                ", sectionTime='" + sectionTime + '\'' +
                ", rate=" + rate +
                '}';
    }
}
