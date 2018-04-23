package com.zgw.core.user.dao;

import com.zgw.core.user.entity.UserCollections;
import com.zgw.page.TailPage;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUserCollectionsDao{

        /**
         *根据id获取
         **/
        public UserCollections getById(Long id);

        public List<String> queryAllCourseSubClassify();

        public List<UserCollections> queryAllByCourseSubCLassify(String courseSubClassify,TailPage<UserCollections> page);

        /**
         *获取所有
         **/
        public List<UserCollections> queryAll(UserCollections queryEntity);

        /**
         *获取总数量
         **/
        public Integer getTotalItemsCount(UserCollections queryEntity);

        /**
         *分页获取
         **/
        public List<UserCollections> queryPage(UserCollections queryEntity , TailPage<UserCollections> page);

        /**
         *创建新记录
         **/
        public void create(UserCollections entity);

        /**
         *创建新记录
         **/
        public void createSelectivity(UserCollections entity);

        /**
         *根据id更新
         **/
        public void update(UserCollections entity);

        /**
         *根据id选择性更新自动
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
