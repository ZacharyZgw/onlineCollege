package com.zgw.core.consts.dao;

import com.zgw.core.consts.entity.SiteCarousel;
import com.zgw.page.TailPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ISiteCarouselDao {
    public SiteCarousel selectSiteCarouselById(@Param("id") Long id);
    public List<SiteCarousel> selectAllSiteCarousel(@Param("count") int count);

    /**
     *获取所有
     **/
    //public List<SiteCarousel> queryAll(SiteCarousel queryEntity);

    /**
     *获取总数量
     **/
    public Integer getTotalItemsCount(SiteCarousel queryEntity);

    /**
     *分页获取
     **/
    public List<SiteCarousel> queryPage(@Param("param1") SiteCarousel queryEntity, @Param("param2") TailPage<SiteCarousel> page);

    /**
     *创建新记录
     **/
    public void create(SiteCarousel entity);

    /**
     * 创建新记录
     */
    public void createSelectivity(SiteCarousel entity);

    /**
     *根据id更新
     **/
    public void update(SiteCarousel entity);

    /**
     *根据id选择性更新自动
     **/
    public void updateSelectivity(SiteCarousel entity);

    /**
     *物理删除
     **/
    public void delete(SiteCarousel entity);

    /**
     *逻辑删除
     **/
    public void deleteLogic(SiteCarousel entity);

}
