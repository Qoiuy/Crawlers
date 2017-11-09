package com.itjuzi.entity;

import java.util.Date;

public class CrawerCompanyInnoItjuziSeed {
    private Integer id;

    private String url;

    private Boolean isCrawler;

    private Date addTime;

    private Date updateTime;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Boolean getIsCrawler() {
        return isCrawler;
    }

    public void setIsCrawler(Boolean isCrawler) {
        this.isCrawler = isCrawler;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getCrawler() {
        return isCrawler;
    }

    public void setCrawler(Boolean crawler) {
        isCrawler = crawler;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CrawerCompanyInnoItjuziSeed{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", isCrawler=" + isCrawler +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", type='" + type + '\'' +
                '}';
    }
}