package com.zgw.core.consts.dao;

import com.zgw.core.consts.entity.Classify;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IClassifyDao {
    public Classify selectById(@Param("id") Long id);
    public List<Classify> selectAll();
    public List<Classify> selectByCondition(Classify classify);
    public Integer getTotalItemsCount();
    public Classify selectByCode(@Param("code") String code);
    public void createSelectivity(Classify classify);
    public void updateSelectivity(Classify classify);
    public void delete(Classify classify);
    public void deleteLogic(Classify classify);

}
