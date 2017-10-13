package com.quick.zhilian.model;

import org.apache.commons.codec.digest.DigestUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl({"http://jobs.zhaopin.com/*.htm?*"})
@HelpUrl({"http://sou.zhaopin.com/jobs/searchresult.ashx?*"})
public class ZhilianJobInfo
        implements AfterExtractor {

    @ExtractBy("//h1/text()")
    private String title = "";

    @ExtractBy("//html/body/div[6]/div[1]/ul/li[1]/strong/text()")
    private String salary = "";

    @ExtractBy("//html/body/div[5]/div[1]/div[1]/h2/a/text()")
    private String company = "";

    @ExtractBy("//html/body/div[6]/div[1]/div[1]/div/div[1]/allText()")
    private String description = "";

    private String source = "zhilian.com";

    @ExtractByUrl
    private String url = "";

    private String urlMd5 = "";

    @ExtractBy("//html/body/div[6]/div[1]/ul/li[2]/strong/a/text()")
    private String dizhi = "";

    @ExtractBy("//html/body/div[6]/div[1]/ul/li[5]/strong/text()")
    private String qualifications = "";

    @ExtractBy("//html/body/div[6]/div[2]/div[1]/ul/li[3]/strong/a/text()")
    private String companycategory = "";

    @ExtractBy("//html/body/div[6]/div[2]/div[1]/ul/li[1]/strong/text()")
    private String companyscale = "";

    @ExtractBy("//html/body/div[6]/div[2]/div[1]/ul/li[2]/strong/text()")
    private String companytype = "";

    @ExtractBy("//html/body/div[6]/div[2]/div[1]/ul/li[4]/strong/text()")
    private String companyaddress;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        if (description != null)
            this.description = description;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
        this.urlMd5 = DigestUtils.md5Hex(url);
    }

    public String getSalary() {
        return this.salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getUrlMd5() {
        return this.urlMd5;
    }

    public void setUrlMd5(String urlMd5) {
        this.urlMd5 = urlMd5;
    }

    public String getDizhi() {
        return this.dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    public String getQualifications() {
        return this.qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getCompanycategory() {
        return this.companycategory;
    }

    public void setCompanycategory(String companycategory) {
        this.companycategory = companycategory;
    }

    public String getCompanyscale() {
        return this.companyscale;
    }

    public void setCompanyscale(String companyscale) {
        this.companyscale = companyscale;
    }

    public String getCompanytype() {
        return this.companytype;
    }

    public void setCompanytype(String companytype) {
        this.companytype = companytype;
    }

    public String getCompanyaddress() {
        return this.companyaddress;
    }

    public void setCompanyaddress(String companyaddress) {
        this.companyaddress = companyaddress;
    }

    public String toString() {
        return "JobInfo{title='" + this.title + '\'' + ", salary='" + this.salary + '\'' + ", company='" + this.company + '\'' + ", description='" + this.description + '\'' + ", source='" + this.source + '\'' + ", url='" + this.url + '\'' + '}';
    }

    public void afterProcess(Page page) {
    }
}