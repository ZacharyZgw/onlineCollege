package com.zgw.core.course.service;

import com.zgw.core.course.entity.Course;
import com.zgw.core.course.entity.CourseQueryDto;
import com.zgw.page.TailPage;

import java.util.List;

public interface ICourseService {

    /**
     *根据id获取
     **/
    public Course getById(Long id);

    public Course getByCourseId(Long courseId);

    /**
     *获取所有
     **/
    public List<Course> queryList(CourseQueryDto queryEntity);

    /**
     * 获取课程
     */
    public List<Course> getFiveCourses(Course queryEntity);

    /**
     *分页获取
     **/
    public TailPage<Course> queryPage(Course queryEntity , TailPage<Course> page);

    /**
     *创建
     **/
    public void createSelectivity(Course entity);

    /**
     *根据id 进行可选性更新
     **/
    public void updateSelectivity(Course entity);

    /**
     *物理删除
     **/
    public void delete(Course entity);

    /**
     *逻辑删除
     **/
    public void deleteLogic(Course entity);



}
