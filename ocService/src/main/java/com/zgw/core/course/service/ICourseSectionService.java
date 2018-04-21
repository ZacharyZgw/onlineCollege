package com.zgw.core.course.service;

import com.zgw.core.course.entity.CourseSection;
import com.zgw.page.TailPage;

import java.util.List;

public interface ICourseSectionService {
    public CourseSection getById(Long id);
    public CourseSection getByCourseId(Long courseId);
    public List<CourseSection> queryAll(CourseSection queryEntity);
    /**
     * 获取课程章最大的sort
     * */
    public Integer getMaxSort(Long courseId);
    public TailPage<CourseSection> queryPage(CourseSection courseSection,TailPage<CourseSection> page);
    /**
     * 比当前sort大的，正序排序的第一个
     * @param curCourseSection
     * @return
     */
    public CourseSection getSortSectionMax(CourseSection curCourseSection);

    /**
     * 比当前sort小的，倒序排序的第一个
     * @param curCourseSection
     * @return
     */
    public CourseSection getSortSectionMin(CourseSection curCourseSection);
}
