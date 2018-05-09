package com.zgw.core.consts.service;

import com.zgw.core.consts.entity.Classify;

import java.util.List;

public interface IClassifyService {
    /**
     *根据id获取
     **/
    public Classify getById(Long id);

    /**
     *获取所有
     **/
    public List<Classify> queryAll();

    /**
     * 根据code获取
     */
    public Classify getByCode(String code);

    /**
     *根据条件动态获取
     **/
    public List<Classify> queryByCondition(Classify queryEntity);

    /**
     * 查询记录数
     */
    public int queryAllItemsCount();

    public void createSelectivity(Classify entity);
    public void updateSelectivity(Classify enttity);
    public void delete(Classify entity);
    public void deleteLogic(Classify entity);
}
