package com.zgw.core.user.service.impl;

import com.zgw.core.user.dao.IUserCollectionsDao;
import com.zgw.core.user.entity.UserCollections;
import com.zgw.core.user.service.IUserCollectionService;
import com.zgw.page.TailPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCollectionServiceImpl implements IUserCollectionService{

    @Autowired
    IUserCollectionsDao userCollectionsDao;
    @Override
    public UserCollections getById(Long id) {
        return userCollectionsDao.getById(id);
    }

    @Override
    public List<String> queryAllCourseSubClassify() {
        return userCollectionsDao.queryAllCourseSubClassify();
    }


    @Override
    public TailPage<UserCollections> queryAllByCourseSubCLassify(String courseSubClassify, TailPage<UserCollections> page) {
        UserCollections userCollections = new UserCollections();
        userCollections.setCourseSubClassify(courseSubClassify);
        Integer totalCount = userCollectionsDao.getTotalItemsCount(userCollections);
        List<UserCollections> result = userCollectionsDao.queryAllByCourseSubCLassify(courseSubClassify, page);
        page.setItemsTotalCount(totalCount);
        page.setItems(result);
        return page;
    }

    @Override
    public List<UserCollections> queryAll(UserCollections queryEntity) {
        return userCollectionsDao.queryAll(queryEntity);
    }

    @Override
    public TailPage<UserCollections> queryPage(UserCollections queryEntity, TailPage<UserCollections> page) {
        Integer totalCount = userCollectionsDao.getTotalItemsCount(queryEntity);
        List<UserCollections> userCollections = userCollectionsDao.queryPage(queryEntity, page);
        page.setItemsTotalCount(totalCount);
        page.setItems(userCollections);
        return page;
    }

    @Override
    public void createSelectivity(UserCollections entity) {
        userCollectionsDao.createSelectivity(entity);

    }

    @Override
    public void update(UserCollections entity) {
        userCollectionsDao.update(entity);

    }

    @Override
    public void updateSelectivity(UserCollections entity) {
        userCollectionsDao.updateSelectivity(entity);
    }

    @Override
    public void delete(UserCollections entity) {
        userCollectionsDao.delete(entity);
    }

    @Override
    public void deleteLogic(UserCollections entity) {
        userCollectionsDao.deleteLogic(entity);
    }
}
