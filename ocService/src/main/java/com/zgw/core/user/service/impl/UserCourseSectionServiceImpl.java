package com.zgw.core.user.service.impl;

import com.zgw.core.user.dao.IUserCourseSectionDao;
import com.zgw.core.user.entity.UserCourseSection;
import com.zgw.core.user.entity.UserCourseSectionDto;
import com.zgw.core.user.service.IUserCourseSectionService;
import com.zgw.page.TailPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserCourseSectionServiceImpl implements IUserCourseSectionService {

    @Autowired
    private IUserCourseSectionDao userCourseSectionDao;
    @Override
    public UserCourseSection getById(Long id) {
        return userCourseSectionDao.getById(id);
    }

    @Override
    public List<String> queryAllCourseSubClassify() {
        return userCourseSectionDao.queryAllCourseSubClassify();
    }

    @Override
    public List<UserCourseSection> queryAll(UserCourseSection queryEntity) {
        return userCourseSectionDao.queryAll(queryEntity);
    }

    @Override
    public UserCourseSection queryLatest(UserCourseSection queryEntity) {
        return userCourseSectionDao.queryLatest(queryEntity);
    }

    @Override
    public TailPage<UserCourseSectionDto> queryPage(UserCourseSection queryEntity, TailPage<UserCourseSectionDto> page) {
        Integer totalCount = userCourseSectionDao.getTotalItemsCount(queryEntity);
        List<UserCourseSectionDto> list = userCourseSectionDao.queryPage(queryEntity, page);
        page.setItemsTotalCount(totalCount);
        page.setItems(list);
        return page;
    }

    @Override
    public TailPage<UserCourseSectionDto> queryPageBycourseSubClassify(UserCourseSection queryEntity, TailPage<UserCourseSectionDto> page) {
        Integer totalCount = userCourseSectionDao.getTotalItemsCount(queryEntity);
        List<UserCourseSectionDto> result = userCourseSectionDao.queryPageBycourseSubClassify(queryEntity, page);
        page.setItems(result);
        page.setItemsTotalCount(totalCount);
        return page;
    }

    @Override
    public void create(UserCourseSection entity){
        userCourseSectionDao.create(entity);
    }

    @Override
    public void createSelectivity(UserCourseSection entity) {
        userCourseSectionDao.createSelectivity(entity);
    }

    @Override
    public void update(UserCourseSection entity) {
        userCourseSectionDao.update(entity);
    }

    @Override
    public void updateSelectivity(UserCourseSection entity) {
        userCourseSectionDao.updateSelectivity(entity);
    }

    @Override
    public void delete(UserCourseSection entity) {
        userCourseSectionDao.delete(entity);
    }

    @Override
    public void deleteLogic(UserCourseSection entity) {
        userCourseSectionDao.deleteLogic(entity);
    }
}
