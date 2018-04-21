package com.zgw.core.consts.dao;

import com.zgw.core.consts.entity.SiteCarousel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ISiteCarouselDao {
    public SiteCarousel selectSiteCarouselById(@Param("id") Long id);
    public List<SiteCarousel> selectAllSiteCarousel(@Param("count") int count);
}
