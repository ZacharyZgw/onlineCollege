package com.zgw.vo;

import com.zgw.core.course.entity.CourseSection;

import java.util.ArrayList;
import java.util.List;

public class CourseSectionVO extends CourseSection {
    private List<CourseSection> sections = new ArrayList<CourseSection>();

    public List<CourseSection> getSections() {
        return sections;
    }

    public void setSections(List<CourseSection> sections) {
        this.sections = sections;
    }
}
