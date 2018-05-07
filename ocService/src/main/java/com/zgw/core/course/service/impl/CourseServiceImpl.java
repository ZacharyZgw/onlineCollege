package com.zgw.core.course.service.impl;

import com.zgw.core.course.dao.ICourseDao;
import com.zgw.core.course.entity.Course;
import com.zgw.core.course.entity.CourseQueryDto;
import com.zgw.core.course.service.ICourseService;
import com.zgw.page.TailPage;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICourseDao courseDao;
    @Override
    public Course getById(Long id) {
        return courseDao.getById(id);
    }

    @Override
    public Course getByCourseId(Long courseId) {
        return courseDao.getByCourseId(courseId);
    }

    @Override
    public List<String> getAllCourseClassify(String userName) {
        return courseDao.getClassifyByUserName(userName);
    }

    @Override
    public TailPage<Course> queryPageByTeacher(Course queryEntity, TailPage<Course> page) {
        Integer totalCount=courseDao.getTotalItemsCount(queryEntity);
        List<Course> result=courseDao.queryPageByTeacher(queryEntity,page);
        page.setItemsTotalCount(totalCount);
        page.setItems(result);
        return page;
    }


    @Override
    public List<Course> queryList(CourseQueryDto queryEntity) {
        return courseDao.queryList(queryEntity);
    }

    @Override
    public List<Course> getFiveCourses(Course queryEntity) {
        return courseDao.getFiveCourses(queryEntity);
    }

    @Override
    public TailPage<Course> queryPage(Course queryEntity, TailPage<Course> page) {
        Integer itemCount = courseDao.getTotalItemsCount(queryEntity);
        List<Course> courseList =
                (List<Course>) courseDao.queryPage(queryEntity,page);
        page.setItemsTotalCount(itemCount);
        page.setItems(courseList);
        return page;
    }

    @Override
    public void createSelectivity(Course entity) {
        courseDao.createSelectivity(entity);
    }

    @Override
    public void updateSelectivity(Course entity) {
        courseDao.updateSelectivity(entity);
    }

    @Override
    public void delete(Course entity) {
        courseDao.delete(entity);
    }

    @Override
    public void deleteLogic(Course entity) {
        courseDao.deleteLogic(entity);
    }
}
