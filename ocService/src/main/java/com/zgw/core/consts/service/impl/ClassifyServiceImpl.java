package com.zgw.core.consts.service.impl;

import com.zgw.core.consts.dao.IClassifyDao;
import com.zgw.core.consts.entity.Classify;
import com.zgw.core.consts.service.IClassifyService;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassifyServiceImpl implements IClassifyService {

    @Autowired
    private IClassifyDao classifyDao;
    @Override
    public Classify getById(Long id) {
        return classifyDao.selectById(id);
    }

    @Override
    public List<Classify> queryAll() {
        return classifyDao.selectAll();
    }

    @Override
    public Classify getByCode(String code) {
        if (org.apache.commons.lang.StringUtils.isEmpty(code)){
            return null;
        }
        Classify classify = new Classify();
        classify.setCode(code);
        List<Classify> classifies = classifyDao.selectByCondition(classify);
        if (CollectionUtils.isNotEmpty(classifies)){
            return classifies.get(0);
        }
        return null;
    }

    @Override
    public List<Classify> queryByCondition(Classify queryEntity) {
        return classifyDao.selectByCondition(queryEntity);
    }

    @Override
    public int queryAllItemsCount() {
        return classifyDao.getTotalItemsCount();
    }

    @Override
    public void createSelectivity(Classify entity) {
        this.classifyDao.createSelectivity(entity);
    }

    @Override
    public void updateSelectivity(Classify entity) {
        this.classifyDao.updateSelectivity(entity);
    }

    @Override
    public void delete(Classify entity) {
        this.classifyDao.delete(entity);
    }

    @Override
    public void deleteLogic(Classify entity) {
        this.classifyDao.deleteLogic(entity);
    }
}
