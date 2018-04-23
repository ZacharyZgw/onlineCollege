package com.zgw.vo;

import com.zgw.core.user.entity.UserCourseSection;
import com.zgw.core.user.entity.UserCourseSectionDto;

import java.util.ArrayList;
import java.util.List;

public class UserCourseSectionVo extends UserCourseSectionDto {
     private List<String> courseClassifyList = new ArrayList<>();


    public List<String> getCourseClassifyList() {
        return courseClassifyList;
    }

    public void setCourseClassifyList(List<String> courseClassifyList) {
        this.courseClassifyList = courseClassifyList;
    }
}
