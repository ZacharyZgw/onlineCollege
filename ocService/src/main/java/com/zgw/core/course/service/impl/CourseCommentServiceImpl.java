package com.zgw.core.course.service.impl;

import com.zgw.core.course.dao.ICourseCommentDao;
import com.zgw.core.course.entity.CourseComment;
import com.zgw.core.course.service.ICourseCommentService;
import com.zgw.page.TailPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseCommentServiceImpl implements ICourseCommentService {

    @Autowired
    private ICourseCommentDao courseCommentDao;
    @Override
    public CourseComment getById(Long id) {
        return courseCommentDao.getById(id);
    }

    @Override
    public List<CourseComment> queryAll(CourseComment queryEntity) {
        return courseCommentDao.queryAll(queryEntity);
    }

    @Override
    public TailPage<CourseComment> queryPage(CourseComment queryEntity, TailPage<CourseComment> page) {
        Integer totalItemCount = courseCommentDao.getTotalItemsCount(queryEntity);
        List<CourseComment> courseComments = courseCommentDao.queryPage(queryEntity,page);
        page.setItemsTotalCount(totalItemCount);
        page.setItems(courseComments);
        return page;

    }

    @Override
    public TailPage<CourseComment> queryMyQAItemsPage(CourseComment queryEntity, TailPage<CourseComment> page) {
        Integer totalItemCount = courseCommentDao.getTotalItemsCount(queryEntity);
        List<CourseComment> items = courseCommentDao.queryMyQAItemsPage(queryEntity,page);
        page.setItemsTotalCount(totalItemCount);
        page.setItems(items);
        return page;
    }

    @Override
    public void create(CourseComment entity) {
        courseCommentDao.create(entity);

    }

    @Override
    public void createSelectivity(CourseComment entity) {
        courseCommentDao.createSelectivity(entity);
    }

    @Override
    public void update(CourseComment entity) {
        courseCommentDao.update(entity);

    }

    @Override
    public void updateSelectivity(CourseComment entity) {
        courseCommentDao.updateSelectivity(entity);
    }

    @Override
    public void delete(CourseComment entity) {
        courseCommentDao.delete(entity);
    }

    @Override
    public void deleteLogic(CourseComment entity) {
        courseCommentDao.deleteLogic(entity);
    }
}
