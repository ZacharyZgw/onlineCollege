package com.zgw.business;

import com.zgw.vo.ClassifyVo;

import java.util.List;
import java.util.Map;

public interface IPortalBusiness {

    /**
     * 获取所有，包括一级分类&二级分类
     */
    List<ClassifyVo> queryAllClassify();

    /**
     * 获取所有分类
     */
    Map<String,ClassifyVo> queryAllClassifyMap();

    /**
     * 为分类设置课程推荐
     */
    void prepareRecomdCourses(List<ClassifyVo> classifyVoList);
}
