package com.zgw.core.course.dao;

import com.zgw.core.course.entity.CourseSection;
import com.zgw.page.TailPage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseSectionDao {
    public CourseSection selectCourseSectionById(Long id);
    public CourseSection selectCourseSectionByCourseId(Long courseId);
    public List<CourseSection> selectAll(CourseSection queryEntity);
    public Integer getMaxSort(Long courseId);
    public List<CourseSection> queryPage(CourseSection courseSection, TailPage<CourseSection> page);
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
    public Integer getTotalItemsCount();
}
