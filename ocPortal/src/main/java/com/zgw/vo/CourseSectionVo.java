package com.zgw.vo;

import com.zgw.core.course.entity.CourseSection;

import java.util.ArrayList;
import java.util.List;

public class CourseSectionVo extends CourseSection {
    private List<CourseSection> subSectionList = new ArrayList<>();

    public List<CourseSection> getSubSectionList() {
        return subSectionList;
    }

    public void setSubSectionList(List<CourseSection> subSectionList) {
        this.subSectionList = subSectionList;
    }
}
