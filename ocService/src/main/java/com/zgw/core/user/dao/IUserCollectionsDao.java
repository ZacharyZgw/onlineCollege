package com.zgw.core.user.dao;

import com.zgw.core.user.entity.UserCollections;
import com.zgw.core.user.entity.UserCollectionsDto;
import com.zgw.page.TailPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserCollectionsDao{

        /**
         *根据id获取
         **/
        public UserCollections getById(Long id);
        public UserCollections getByEntity(@Param("param1") UserCollections entity);
        public List<String> queryAllCourseSubClassify();

        public List<UserCollectionsDto> queryAllByCourseSubCLassify(String courseSubClassify,TailPage<UserCollectionsDto> page);

        /**
         *获取所有
         **/
        public List<UserCollections> selectAll(UserCollections queryEntity);

        /**
         *获取总数量
         **/
        public Integer getTotalItemsCount(UserCollections queryEntity);

        /**
         *分页获取
         **/
        public List<UserCollectionsDto> queryPage(@Param("param1") UserCollections queryEntity , @Param("param2") TailPage<UserCollectionsDto> page);


        public void create(UserCollections entity);


        public void createSelectivity(UserCollections entity);


        public void update(UserCollections entity);


        public void updateSelectivity(UserCollections entity);


        public void delete(UserCollections entity);


        public void deleteLogic(UserCollections entity);
}
