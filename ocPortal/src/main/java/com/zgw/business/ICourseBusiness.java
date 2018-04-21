package com.zgw.business;

import com.zgw.core.course.entity.CourseSection;
import com.zgw.vo.CourseSectionVo;

import java.util.List;

public interface ICourseBusiness {

    public List<CourseSectionVo> getCourseSection(Long courseId);
}
