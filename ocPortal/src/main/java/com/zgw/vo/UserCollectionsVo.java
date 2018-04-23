package com.zgw.vo;

import com.zgw.core.user.entity.UserCollections;

import java.util.ArrayList;
import java.util.List;

public class UserCollectionsVo extends UserCollections {
    private String coursePicture;
    private List<String> courseClassifyList = new ArrayList<>();

    public String getCoursePicture() {
        return coursePicture;
    }

    public void setCoursePicture(String coursePicture) {
        this.coursePicture = coursePicture;
    }

    public List<String> getCourseClassifyList() {
        return courseClassifyList;
    }

    public void setCourseClassifyList(List<String> courseClassifyList) {
        this.courseClassifyList = courseClassifyList;
    }
}
