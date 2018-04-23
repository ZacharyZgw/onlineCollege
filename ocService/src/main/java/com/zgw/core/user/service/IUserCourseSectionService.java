package com.zgw.core.user.service;

import com.zgw.core.user.entity.UserCourseSection;
import com.zgw.core.user.entity.UserCourseSectionDto;
import com.zgw.page.TailPage;

import java.util.List;

public interface IUserCourseSectionService {
    /**
     *根据id获取
     **/
    public UserCourseSection getById(Long id);
    public List<String> queryAllCourseSubClassify();
    /**
     *获取所有
     **/
    public List<UserCourseSection> queryAll(UserCourseSection queryEntity);

    /**
     * 获取最新的
     */
    public UserCourseSection queryLatest(UserCourseSection queryEntity);

    /**
     *分页获取
     **/
    public TailPage<UserCourseSectionDto> queryPage(UserCourseSection queryEntity , TailPage<UserCourseSectionDto> page);

    public TailPage<UserCourseSectionDto> queryPageBycourseSubClassify(UserCourseSection queryEntity,TailPage<UserCourseSectionDto> page);
    /**
     *创建
     **/
    public void createSelectivity(UserCourseSection entity);
    public void create(UserCourseSection entity);
    /**
     *根据id更新
     **/
    public void update(UserCourseSection entity);

    /**
     *根据id 进行可选性更新
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
