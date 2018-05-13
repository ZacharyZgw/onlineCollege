package com.zgw.core.course.dao;

import com.zgw.core.course.entity.CourseSection;
import com.zgw.page.TailPage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseSectionDao {
    public Long getFirstSectionId(Long courseId);
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
