package com.zgw.core.course.entity;

import com.zgw.orm.BaseEntity;

public class CourseComment extends BaseEntity {
    private String username;
    private String toUsername;
    private Integer courseId;
    private Integer sectionId;
    private String sectionTitle;
    private String content;
    private Integer refId;
    private String refContent;
    private Integer type;
    private String header;
    private String courseName;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public String getRefContent() {
        return refContent;
    }

    public void setRefContent(String refContent) {
        this.refContent = refContent;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }



    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "CourseComment{" +
                "username='" + username + '\'' +
                ", toUsername='" + toUsername + '\'' +
                ", courseId=" + courseId +
                ", sectionId=" + sectionId +
                ", sectionTitle='" + sectionTitle + '\'' +
                ", content='" + content + '\'' +
                ", refId=" + refId +
                ", refContent='" + refContent + '\'' +
                ", type=" + type +
                ", header='" + header + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
