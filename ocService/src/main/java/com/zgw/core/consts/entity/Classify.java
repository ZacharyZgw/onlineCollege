package com.zgw.core.consts.entity;

import com.zgw.orm.BaseEntity;

public class Classify extends BaseEntity {
    private String name;
    private String code;
    private String parentCode;
    private Integer sort;
    private Integer clevel;

    public Integer getClevel() {
        return clevel;
    }

    public void setClevel(Integer clevel) {
        this.clevel = clevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
