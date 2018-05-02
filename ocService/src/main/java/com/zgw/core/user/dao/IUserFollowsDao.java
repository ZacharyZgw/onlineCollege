package com.zgw.core.user.dao;

import com.zgw.core.user.entity.UserFollowStudyRecord;
import com.zgw.core.user.entity.UserFollows;
import com.zgw.page.TailPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUserFollowsDao {
    /**
     *根据id获取
     **/
    public UserFollows getById(Long id);

    /**
     *获取所有
     **/
    public List<UserFollows> queryAll(UserFollows queryEntity);

    /**
     *获取总数量
     **/
    public Integer getTotalItemsCount(@Param("param1") UserFollows queryEntity);

    /**
     *分页获取
     **/
    public List<UserFollows> queryPage(@Param("param1") UserFollows queryEntity ,@Param("param2") TailPage<UserFollows> page);

    /**
     *获取总数量
     **/
    public Integer getFollowStudyRecordCount(UserFollowStudyRecord queryEntity);

    /**
     *分页获取
     **/
    public List<UserFollowStudyRecord> queryFollowStudyRecord(UserFollowStudyRecord queryEntity , TailPage<UserFollowStudyRecord> page);

    /**
     *创建新记录
     **/
    public void createSelectivity(UserFollows entity);
    public void create(UserFollows entity);
    /**
     *根据id更新
     **/
    public void update(UserFollows entity);

    /**
     *根据id选择性更新自动
     **/
    public void updateSelectivity(UserFollows entity);

    /**
     *物理删除
     **/
    public void delete(UserFollows entity);

    /**
     *逻辑删除
     **/
    public void deleteLogic(UserFollows entity);


}
