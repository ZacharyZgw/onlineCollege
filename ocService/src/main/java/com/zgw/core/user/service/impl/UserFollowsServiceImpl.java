package com.zgw.core.user.service.impl;

import com.zgw.core.user.dao.IUserFollowsDao;
import com.zgw.core.user.entity.UserFollowStudyRecord;
import com.zgw.core.user.entity.UserFollows;
import com.zgw.core.user.service.IUserFollowsService;
import com.zgw.page.TailPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserFollowsServiceImpl implements IUserFollowsService {

    @Autowired
    private IUserFollowsDao userFollowsDao;
    @Override
    public UserFollows getById(Long id) {
        return userFollowsDao.getById(id);
    }

    @Override
    public List<UserFollows> queryAll(UserFollows queryEntity) {
        return userFollowsDao.queryAll(queryEntity);
    }

    @Override
    public TailPage<UserFollows> queryPage(UserFollows queryEntity, TailPage<UserFollows> page) {
        Integer totalCount = userFollowsDao.getTotalItemsCount(queryEntity);
        List<UserFollows> list = userFollowsDao.queryPage(queryEntity, page);
        page.setItemsTotalCount(totalCount);
        page.setItems(list);
        return page;
    }

    @Override
    public TailPage<UserFollowStudyRecord> queryUserFollowStudyRecordPage(UserFollowStudyRecord queryEntity, TailPage<UserFollowStudyRecord> page) {
        Integer itemsTotalCount = userFollowsDao.getFollowStudyRecordCount(queryEntity);
        List<UserFollowStudyRecord> items = userFollowsDao.queryFollowStudyRecord(queryEntity,page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }

    @Override
    public void createSelectivity(UserFollows entity) {
        userFollowsDao.createSelectivity(entity);
    }

    @Override
    public void create(UserFollows entiry) {
        userFollowsDao.create(entiry);
    }

    @Override
    public void update(UserFollows entity) {
        userFollowsDao.update(entity);
    }

    @Override
    public void updateSelectivity(UserFollows entity) {
        userFollowsDao.updateSelectivity(entity);
    }

    @Override
    public void delete(UserFollows entity) {
        userFollowsDao.delete(entity);
    }

    @Override
    public void deleteLogic(UserFollows entity) {
        userFollowsDao.deleteLogic(entity);
    }
}
