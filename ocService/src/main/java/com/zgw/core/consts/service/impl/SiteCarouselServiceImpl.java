package com.zgw.core.consts.service.impl;

import com.zgw.core.consts.dao.ISiteCarouselDao;
import com.zgw.core.consts.entity.SiteCarousel;
import com.zgw.core.consts.service.ISiteCarouselService;
import com.zgw.page.TailPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SiteCarouselServiceImpl implements ISiteCarouselService {

    @Autowired
    private ISiteCarouselDao siteCarouselDao;

    @Override
    public SiteCarousel getById(Long id) {
        return siteCarouselDao.selectSiteCarouselById(id);
    }

    @Override
    public List<SiteCarousel> queryCarousels(Integer count) {
        List<SiteCarousel> siteCarouselList = siteCarouselDao.selectAllSiteCarousel(count);
        return siteCarouselList;
    }

    @Override
    public TailPage<SiteCarousel> queryPage(SiteCarousel queryEntity, TailPage<SiteCarousel> page) {
        Integer totalCount = this.siteCarouselDao.getTotalItemsCount(queryEntity);
        List<SiteCarousel> result = this.siteCarouselDao.queryPage(queryEntity, page);
        page.setItemsTotalCount(totalCount);
        page.setItems(result);
        return page;
    }

    @Override
    public void create(SiteCarousel entity) {
        this.siteCarouselDao.create(entity);

    }

    @Override
    public void createSelectivity(SiteCarousel entity) {
        this.siteCarouselDao.createSelectivity(entity);
    }

    @Override
    public void update(SiteCarousel entity) {
        this.siteCarouselDao.update(entity);
    }

    @Override
    public void updateSelectivity(SiteCarousel entity) {
        this.siteCarouselDao.updateSelectivity(entity);
    }

    @Override
    public void delete(SiteCarousel entity) {
        this.siteCarouselDao.delete(entity);
    }

    @Override
    public void deleteLogic(SiteCarousel entity) {
        this.siteCarouselDao.deleteLogic(entity);
    }


}
