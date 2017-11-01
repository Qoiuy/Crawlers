package com.inno.tree;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrawerCompanyInnoTree {

    private long id;
    private String name;
    private String fullName;
    private String companyDesc;
    private String companyAddress;
    private String companyUrl;
    private String companyProduct;
    private String companyFinance;
    private String companyAllFinances;
    private String companyTags;
    private Date companyRegistrateTime;
    private String companyRegistrateCapital;
    private String companyRegistrateAddress;
    private String corporateRepresentative;
    private String officialContact;
    private String companyRelationData;
    private String crawlerUrl;
    private String urlmd5;

    // 公司详情页代码
    public CrawerCompanyInnoTree(String companyId,String companyDetail) {
        Document document = Jsoup.parse(companyDetail);
        this.name = document.select("body > div > div.de_170822_con > div.mech_170525_nav > table > tbody > tr > td:nth-child(2) > h3").text();
        this.fullName = document.select("body > div > div.de_170822_con > div:nth-child(3) > div.de_170822_d01_d > table > tbody > tr:nth-child(1) > td:nth-child(2) > span").text();
        this.companyDesc = document.select("body > div > div.de_170822_con > div:nth-child(3) > div.de_170822_d01_d02 > p").text();
        this.companyAddress = document.select("body > div > div.de_170822_con > div.mech_170525_nav > table > tbody > tr > td:nth-child(2) > div.mech_170822_nav_d02 > a.mech_170822_nav_d02_a01").text();
        this.companyUrl = document.select("body > div > div.de_170822_con > div.mech_170525_nav > table > tbody > tr > td:nth-child(2) > div.mech_170822_nav_d02 > a.mech_170822_nav_d02_a02").text();
        try {
            this.companyProduct = CrawlerUseHttpclient.getRelationlist(companyId,"product");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.companyFinance = document.select("body > div > div.de_170822_con > div.mech_170525_nav > table > tbody > tr > td:nth-child(2) > h3 > span").text();
        Element financeTable = document.select("body > div > div.de_170822_con > div:nth-child(5) > div > table").get(0);
        List<Finance> finances = new ArrayList<Finance>();
        Elements trs = financeTable.select("tr");
        for (Element tr : trs) {
            Finance finance = new Finance();
            Elements td = tr.select("td");
            finance.setTime(td.get(0).text());
            finance.setFinance(td.get(1).text());
            finance.setInvestor(td.get(3).text());
            finance.setMoney(td.get(2).text());
            finances.add(finance);
        }
        this.companyAllFinances = JSON.toJSONString(finances);
        Element tagDiv = document.select("body > div > div.de_170822_con > div.mech_170525_nav > div.mech_170525_nav_d01").get(0);
        Elements tagSpan = tagDiv.select("span");
        List<String> tagList = new ArrayList<String>();
        for (Element tag : tagSpan) {
            tagList.add(tag.text());
        }
        this.companyTags = StringUtils.join(tagList,";");
        try {
            this.companyRegistrateTime =
                    DateUtils.parseDate(document.select("body > div > div.de_170822_con > div:nth-child(3) > div.de_170822_d01_d > table > tbody > tr:nth-child(3) > td:nth-child(2) > span").get(0).text(),"yyyy-hh-mm");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.companyRegistrateCapital = document.select("body > div > div.de_170822_con > div:nth-child(3) > div.de_170822_d01_d > table > tbody > tr:nth-child(1) > td:nth-child(4) > span").get(0).text();
        this.companyRegistrateAddress = document.select("body > div > div.de_170822_con > div:nth-child(3) > div.de_170822_d01_d > table > tbody > tr:nth-child(2) > td:nth-child(2) > span").get(0).text();
        this.corporateRepresentative = document.select("body > div > div.de_170822_con > div:nth-child(3) > div.de_170822_d01_d > table > tbody > tr:nth-child(2) > td:nth-child(4) > span").get(0).text();
        this.officialContact = document.select("body > div > div.de_170822_con > div:nth-child(3) > div.de_170822_d01_d > table > tbody > tr:nth-child(3) > td:nth-child(4) > span").get(0).text();
        try {
            this.companyRelationData = CrawlerUseHttpclient.getRelationlist(companyId,"company");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.crawlerUrl = companyId;
        this.urlmd5 = DigestUtils.md5Hex(companyId);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getCompanyProduct() {
        return companyProduct;
    }

    public void setCompanyProduct(String companyProduct) {
        this.companyProduct = companyProduct;
    }

    public String getCompanyFinance() {
        return companyFinance;
    }

    public void setCompanyFinance(String companyFinance) {
        this.companyFinance = companyFinance;
    }

    public String getCompanyAllFinances() {
        return companyAllFinances;
    }

    public void setCompanyAllFinances(String companyAllFinances) {
        this.companyAllFinances = companyAllFinances;
    }

    public String getCompanyTags() {
        return companyTags;
    }

    public void setCompanyTags(String companyTags) {
        this.companyTags = companyTags;
    }

    public Date getCompanyRegistrateTime() {
        return companyRegistrateTime;
    }

    public void setCompanyRegistrateTime(Date companyRegistrateTime) {
        this.companyRegistrateTime = companyRegistrateTime;
    }

    public String getCompanyRegistrateCapital() {
        return companyRegistrateCapital;
    }

    public void setCompanyRegistrateCapital(String companyRegistrateCapital) {
        this.companyRegistrateCapital = companyRegistrateCapital;
    }

    public String getCompanyRegistrateAddress() {
        return companyRegistrateAddress;
    }

    public void setCompanyRegistrateAddress(String companyRegistrateAddress) {
        this.companyRegistrateAddress = companyRegistrateAddress;
    }

    public String getCorporateRepresentative() {
        return corporateRepresentative;
    }

    public void setCorporateRepresentative(String corporateRepresentative) {
        this.corporateRepresentative = corporateRepresentative;
    }

    public String getOfficialContact() {
        return officialContact;
    }

    public void setOfficialContact(String officialContact) {
        this.officialContact = officialContact;
    }

    public String getCompanyRelationData() {
        return companyRelationData;
    }

    public void setCompanyRelationData(String companyRelationData) {
        this.companyRelationData = companyRelationData;
    }

    public String getCrawlerUrl() {
        return crawlerUrl;
    }

    public void setCrawlerUrl(String crawlerUrl) {
        this.crawlerUrl = crawlerUrl;
    }

    public String getUrlmd5() {
        return urlmd5;
    }

    public void setUrlmd5(String urlmd5) {
        this.urlmd5 = urlmd5;
    }

    @Override
    public String toString() {
        return "CrawerCompanyInnoTree{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", companyDesc='" + companyDesc + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyUrl='" + companyUrl + '\'' +
                ", companyProduct='" + companyProduct + '\'' +
                ", companyFinance='" + companyFinance + '\'' +
                ", companyAllFinances='" + companyAllFinances + '\'' +
                ", companyTags='" + companyTags + '\'' +
                ", companyRegistrateTime=" + companyRegistrateTime +
                ", companyRegistrateCapital='" + companyRegistrateCapital + '\'' +
                ", companyRegistrateAddress='" + companyRegistrateAddress + '\'' +
                ", corporateRepresentative='" + corporateRepresentative + '\'' +
                ", officialContact='" + officialContact + '\'' +
                ", companyRelationData='" + companyRelationData + '\'' +
                ", crawlerUrl='" + crawlerUrl + '\'' +
                ", urlmd5='" + urlmd5 + '\'' +
                '}';
    }
}
