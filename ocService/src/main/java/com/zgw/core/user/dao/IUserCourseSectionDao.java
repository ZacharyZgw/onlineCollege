package com.zgw.core.user.dao;

import com.zgw.core.user.entity.UserCourseSection;
import com.zgw.core.user.entity.UserCourseSectionDto;
import com.zgw.page.TailPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserCourseSectionDao {
    /**
     *根据id获取
     **/
    public UserCourseSection getById(Long id);
    public List<String> queryAllCourseSubClassify();
    public List<UserCourseSectionDto> queryPageBycourseSubClassify(@Param("param1") UserCourseSection queryEntity,@Param("param2") TailPage<UserCourseSectionDto> page);
    /**
     *获取所有
     **/
    public List<UserCourseSection> queryAll(UserCourseSection queryEntity);
    /**
     * 获取最新的学习记录
     */
    public UserCourseSection queryLatest(UserCourseSection queryEntity);

    /**
     *获取总数量
     **/
    public Integer getTotalItemsCount(@Param("param1") UserCourseSection queryEntity);

    /**
     *分页获取
     **/
    public List<UserCourseSectionDto> queryPage(UserCourseSection queryEntity , TailPage<UserCourseSectionDto> page);

    /**
     *创建新记录
     **/
    public void createSelectivity(UserCourseSection entity);
    public void create(UserCourseSection entity);
    /**
     *根据id更新
     **/
    public void update(UserCourseSection entity);

    /**
     *根据id选择性更新自动
     **/
    public void updateSelectivity(UserCourseSection entity);

    /**
     *物理删除
     **/
    public void delete(UserCourseSection entity);

    /**
     *逻辑删除
     **/
    public void deleteLogic(UserCourseSection entity);



}
