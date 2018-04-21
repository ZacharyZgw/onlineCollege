package com.zgw.core.user.service;

import com.zgw.core.user.entity.UserCollections;
import com.zgw.page.TailPage;

import java.util.List;

public interface IUserCollectionService {
    /**
     *根据id获取
     **/
    public UserCollections getById(Long id);

    public List<UserCollections> queryAllByCourseSubClassify(String courseSubClassify);

    /**
     *获取所有
     **/
    public List<UserCollections> queryAll(UserCollections queryEntity);

    /**
     *分页获取
     **/
    public TailPage<UserCollections> queryPage(UserCollections queryEntity , TailPage<UserCollections> page);

    /**
     *创建
     **/
    public void createSelectivity(UserCollections entity);

    /**
     *根据id更新
     **/
    public void update(UserCollections entity);

    /**
     *根据id 进行可选性更新
     **/
    public void updateSelectivity(UserCollections entity);

    /**
     *物理删除
     **/
    public void delete(UserCollections entity);

    /**
     *逻辑删除
     **/
    public void deleteLogic(UserCollections entity);
}
