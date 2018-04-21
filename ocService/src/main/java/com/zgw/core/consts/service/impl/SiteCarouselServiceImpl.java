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


}
