package com.zgw.core.course.service.impl;

import com.zgw.core.course.dao.ICourseSectionDao;
import com.zgw.core.course.entity.CourseSection;
import com.zgw.core.course.service.ICourseSectionService;
import com.zgw.page.TailPage;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseSectionServiceImpl implements ICourseSectionService {

    @Autowired
    private ICourseSectionDao courseSectionDao;

    @Override
    public Long getFirstSectionId(Long courseId) {
        return courseSectionDao.getFirstSectionId(courseId);
    }

    @Override
    public CourseSection getById(Long id) {
        return courseSectionDao.selectCourseSectionById(id);
    }

    @Override
    public CourseSection getByCourseId(Long courseId) {
        return courseSectionDao.selectCourseSectionByCourseId(courseId);
    }

    @Override
    public List<CourseSection> queryAll(CourseSection queryEntity) {
        List<CourseSection> courseSectionList = courseSectionDao.selectAll(queryEntity);
        return courseSectionList;
    }

    @Override
    public Integer getMaxSort(Long courseId) {
        return courseSectionDao.getMaxSort(courseId);
    }

    @Override
    public TailPage<CourseSection> queryPage(CourseSection courseSection, TailPage<CourseSection> page) {
        int totalItemCount = courseSectionDao.getTotalItemsCount();
        List<CourseSection> courseSectionList= courseSectionDao.queryPage(courseSection,page);
        page.setItemsTotalCount(totalItemCount);
        page.setItems(courseSectionList);
        return page;


    }

    @Override
    public CourseSection getSortSectionMax(CourseSection curCourseSection) {
        return courseSectionDao.getSortSectionMax(curCourseSection);
    }

    @Override
    public CourseSection getSortSectionMin(CourseSection curCourseSection) {
        return courseSectionDao.getSortSectionMin(curCourseSection);
    }

    @Override
    public void createSelectivity(CourseSection entity) {
        this.courseSectionDao.createSelectivity(entity);
    }

    @Override
    public void createList(List<CourseSection> entityList) {
        this.courseSectionDao.createList(entityList);
    }

    @Override
    public void updateSelectivity(CourseSection entity) {
        this.courseSectionDao.updateSelectivity(entity);
    }

    @Override
    public void delete(CourseSection entity) {
        this.courseSectionDao.delete(entity);
    }

    @Override
    public void deleteLogic(CourseSection entity) {
        this.courseSectionDao.deleteLogic(entity);
    }

    @Override
    public void deleteByCourseId(CourseSection entity) {
        this.courseSectionDao.deleteByCourseId(entity);
    }

    @Override
    public void deleteLogicByCourseId(CourseSection entity) {
        this.courseSectionDao.deleteLogicByCourseId(entity);
    }
}
