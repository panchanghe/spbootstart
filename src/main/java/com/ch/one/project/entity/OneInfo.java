package com.ch.one.project.entity;

import java.io.Serializable;

public class OneInfo implements Serializable {
    private Integer id;

    private String ana;

    private String imgUrl;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAna() {
        return ana;
    }

    public void setAna(String ana) {
        this.ana = ana == null ? null : ana.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }
}