package com.zgw.vo;

import com.zgw.core.consts.entity.Classify;
import com.zgw.core.course.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class ClassifyVo extends Classify {
    private List<Classify> subClassifyList = new ArrayList<>();
    private List<Course> recomdCourseList ;

    public List<Classify> getSubClassifyList() {
        return subClassifyList;
    }

    public void setSubClassifyList(List<Classify> subClassifyList) {
        this.subClassifyList = subClassifyList;
    }

    public List<Course> getRecomdCourseList() {
        return recomdCourseList;
    }

    public void setRecomdCourseList(List<Course> recomdCourseList) {
        this.recomdCourseList = recomdCourseList;
    }
}
