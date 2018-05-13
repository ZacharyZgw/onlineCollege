package com.zgw.core.course.service;

import com.zgw.core.course.entity.CourseSection;
import com.zgw.page.TailPage;

import java.util.List;

public interface ICourseSectionService {
    public Long getFirstSectionId(Long courseId);
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
    /**
     *创建新记录
     **/
    public void createSelectivity(CourseSection entity);

    /**
     * 批量创建
     */
    public void createList(List<CourseSection> entityList);
    public void updateSelectivity(CourseSection entity);
    /**
     *物理删除
     **/
    public void delete(CourseSection entity);

    /**
     *逻辑删除
     **/
    public void deleteLogic(CourseSection entity);

    /**
     *物理删除课程对应的章节
     **/
    public void deleteByCourseId(CourseSection entity);

    /**
     *逻辑删除课程对应的章节
     **/
    public void deleteLogicByCourseId(CourseSection entity);
}
