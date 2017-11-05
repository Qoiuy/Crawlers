package com.itjuzi.entity;

import java.util.Date;

public class CrawerCompanyInnoItjuzi {
    private Integer id;

    private String name;

    private String fullName;

    private String companyIndustry;

    private String companyAddress;

    private String companyUrl;

    private String companyFinance;

    private String companyScale;

    private String companyTags;

    private Date companyEstablishedTime;

    private String companyOperationState;

    private String crawlerUrl;

    private String urlmd5;

    private Date addtime;

    private String companySummary;

    private String companyDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public String getCompanyIndustry() {
        return companyIndustry;
    }

    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry == null ? null : companyIndustry.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl == null ? null : companyUrl.trim();
    }

    public String getCompanyFinance() {
        return companyFinance;
    }

    public void setCompanyFinance(String companyFinance) {
        this.companyFinance = companyFinance == null ? null : companyFinance.trim();
    }

    public String getCompanyScale() {
        return companyScale;
    }

    public void setCompanyScale(String companyScale) {
        this.companyScale = companyScale == null ? null : companyScale.trim();
    }

    public String getCompanyTags() {
        return companyTags;
    }

    public void setCompanyTags(String companyTags) {
        this.companyTags = companyTags == null ? null : companyTags.trim();
    }

    public Date getCompanyEstablishedTime() {
        return companyEstablishedTime;
    }

    public void setCompanyEstablishedTime(Date companyEstablishedTime) {
        this.companyEstablishedTime = companyEstablishedTime;
    }

    public String getCompanyOperationState() {
        return companyOperationState;
    }

    public void setCompanyOperationState(String companyOperationState) {
        this.companyOperationState = companyOperationState == null ? null : companyOperationState.trim();
    }

    public String getCrawlerUrl() {
        return crawlerUrl;
    }

    public void setCrawlerUrl(String crawlerUrl) {
        this.crawlerUrl = crawlerUrl == null ? null : crawlerUrl.trim();
    }

    public String getUrlmd5() {
        return urlmd5;
    }

    public void setUrlmd5(String urlmd5) {
        this.urlmd5 = urlmd5 == null ? null : urlmd5.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getCompanySummary() {
        return companySummary;
    }

    public void setCompanySummary(String companySummary) {
        this.companySummary = companySummary == null ? null : companySummary.trim();
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc == null ? null : companyDesc.trim();
    }

    @Override
    public String toString() {
        return "CrawerCompanyInnoItjuzi{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", companyIndustry='" + companyIndustry + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyUrl='" + companyUrl + '\'' +
                ", companyFinance='" + companyFinance + '\'' +
                ", companyScale='" + companyScale + '\'' +
                ", companyTags='" + companyTags + '\'' +
                ", companyEstablishedTime=" + companyEstablishedTime +
                ", companyOperationState='" + companyOperationState + '\'' +
                ", crawlerUrl='" + crawlerUrl + '\'' +
                ", urlmd5='" + urlmd5 + '\'' +
                ", addtime=" + addtime +
                ", companySummary='" + companySummary + '\'' +
                ", companyDesc='" + companyDesc + '\'' +
                '}';
    }
}