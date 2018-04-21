package com.zgw.core.course.entity;

import com.zgw.orm.BaseEntity;

public class CourseSection extends BaseEntity {
    private Long courseId;
    private Long parentId;
    private String name;
    private Integer sort;
    private String time;
    private Integer onsale;
    private String videoUrl;
    private String brief;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getOnsale() {
        return onsale;
    }

    public void setOnsale(Integer onsale) {
        this.onsale = onsale;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Override
    public String toString() {
        return "CourseSection{" +
                "courseId=" + courseId +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                ", time='" + time + '\'' +
                ", onsale=" + onsale +
                ", videoUrl='" + videoUrl + '\'' +
                ", brief='" + brief + '\'' +
                '}';
    }
}
