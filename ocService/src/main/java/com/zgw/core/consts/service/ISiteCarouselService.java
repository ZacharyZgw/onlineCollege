package com.zgw.core.consts.service;

import com.zgw.core.consts.entity.SiteCarousel;
import com.zgw.page.TailPage;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ISiteCarouselService {

    /**
     *根据id获取
     **/
    public SiteCarousel getById(Long id);

    /**
     *获取所有
     **/
    public List<SiteCarousel> queryCarousels(Integer count);

    /**
     *分页获取
     **//*
    public TailPage<SiteCarousel> queryPage(SiteCarousel queryEntity , TailPage<SiteCarousel> page);

    *//**
     *创建
     **//*
    public void create(SiteCarousel entity);

    *//**
     * 创建新记录
     *//*
    public void createSelectivity(SiteCarousel entity);

    *//**
     *根据id更新
     **//*
    public void update(SiteCarousel entity);

    *//**
     *根据id 进行可选性更新
     **//*
    public void updateSelectivity(SiteCarousel entity);

    *//**
     *物理删除
     **//*
    public void delete(SiteCarousel entity);

    *//**
     *逻辑删除
     **//*
    public void deleteLogic(SiteCarousel entity);
*/
}
