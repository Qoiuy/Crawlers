package com.itjuzi.crawler4httpclient;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.itjuzi.entity.CrawerCompanyInnoItjuzi;
import com.itjuzi.entity.CrawerCompanyInnoItjuziSeed;
import com.itjuzi.mapper.CrawerCompanyInnoItjuziMapper;
import com.itjuzi.mapper.CrawerCompanyInnoItjuziSeedMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IDEA
 * User: vector
 * Data: 2017/11/2
 * Time: 11:44
 * Description:
 */
@Component
public class CrawlerOrangeByHttpClient {


    @Value("${crawler.total.node}")
    private int totalNodes;

    @Value("${crawler.current.node}")
    private int currentNode;

    @Value("${crawler.url.total.page}")
    private int totalPage;

    @Value("${crawler.username}")
    private String userName;

    @Value("${crawler.password}")
    private String password;


    @Autowired
    private CrawerCompanyInnoItjuziSeedMapper seedMapper;

    @Autowired
    private CrawerCompanyInnoItjuziMapper juziMapper;

    public static WebClient webClient = new WebClient();

//    /**
//     * 根据totalNodes和currentNode 判断当前节点需爬哪些种子
//     */
//    public List<CrawerCompanyInnoItjuziSeed> buildCurWaitCrawlerSeeds() {
//        List<CrawerCompanyInnoItjuziSeed> seeds = seedMapper.selectListOrderById();
//        int pageSize = seeds.size() / totalNodes;
//        int start = currentNode - 1;
//        List<CrawerCompanyInnoItjuziSeed> waitCrawlerSeeds = new ArrayList<>();
//        if (currentNode == totalNodes) {
//            waitCrawlerSeeds = seeds.subList(start * pageSize, seeds.size());
//        } else {
//            waitCrawlerSeeds = seeds.subList(start * pageSize, pageSize * currentNode);
//        }
//        return waitCrawlerSeeds;
//
//    }


    public void run() throws IOException {
        while (true) {
            try {
                CrawerCompanyInnoItjuziSeed seed = seedMapper.selectOneSeed();
                System.out.println("抓取URL： " + seed.getUrl());
                crawlerContent(seed);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void crawlerByClient(int start) throws IOException {
        /**
         * 获取正确的webclient
         */
        getLoginedClient();


        for (int i = start; i <= totalPage; i++) {
            try {
                HtmlPage page = webClient.getPage("https://www.itjuzi.com/company?page=" + i);
                int count = write2File(page.getWebResponse().getContentAsString());
                if (count <= 0) {
                    getLoginedClient();
                }
            } catch (Exception conectExce) {
                System.out.println("链接超时");
                getLoginedClient();
            }

        }
    }

    /**
     * 是否为WAF验证页面
     *
     * @param html
     * @return
     */
    public static boolean isWAFPage(String html) {
        if (StringUtils.isEmpty(html)) {
            return true;
        }
        Document parse = Jsoup.parse(html);
        if (parse.title().equals("WAF验证页面")) {
            return true;
        } else {
            return false;
        }
    }


    public void getLoginedClient() throws IOException {
        HtmlPage loginPage = null;
        String html = "";
        do {
            try {
                loginPage = login();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("切换代理中...");
            }

            if (loginPage != null) {
                html = loginPage.getWebResponse().getContentAsString();
            }

        } while (isWAFPage(html));
    }

    public HtmlPage login() throws IOException {

        webClient.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36");
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setTimeout(5000);
//        ProxyConfig proxyConfig = webClient.getOptions().getProxyConfig();
//        proxyConfig.setProxyHost(ProxyUtil.proxyIP);
//        proxyConfig.setProxyPort(ProxyUtil.proxyPort);
//        webClient.addRequestHeader("Proxy-Authorization", ProxyUtil.getAuthHeader());

        HtmlPage page = webClient.getPage("https://www.itjuzi.com/user/login");
        HtmlElement create_account_email = (HtmlElement) page.getElementById("create_account_email");
        create_account_email.focus();
        create_account_email.type(userName);
        HtmlElement create_account_password = (HtmlElement) page.getElementById("create_account_password");
        create_account_password.focus();
        create_account_password.type(password);
        HtmlElement login_btn = (HtmlElement) page.getElementById("login_btn");
        page = login_btn.click();
        return page;
    }

    public int write2File(String html) throws IOException {
        Document parse = Jsoup.parse(html);
        Elements select = parse.select("body > div.thewrap > div:nth-child(4) > div.main > div:nth-child(3) > div > ul.list-main-icnset.company-list-ul > li");
        List<String> companyUrl = new ArrayList<>();
        for (Element item : select) {
            String href = item.select("i.cell.pic a").attr("href");
            if (!StringUtils.isEmpty(href)) {
                companyUrl.add(href);
                CrawerCompanyInnoItjuziSeed seed = new CrawerCompanyInnoItjuziSeed();
                seed.setUrl(href);
                saveSeed(seed);
            }
        }
        return companyUrl.size();
    }

    public void saveSeed(CrawerCompanyInnoItjuziSeed seed) {
        if (seedMapper.countByurl(seed.getUrl()) <= 0) {
            seedMapper.insert(seed);
        }
    }


    /**
     * 根据公司seed抓取列表
     */
    public void crawlerContent(CrawerCompanyInnoItjuziSeed seed) throws IOException {
        getLoginedClient();
//        for (CrawerCompanyInnoItjuziSeed item : seeds) {
            try {
                Page page = webClient.getPage(seed.getUrl());
                CrawerCompanyInnoItjuzi crawerCompanyInnoItjuzi = buildContent(seed.getUrl(), page.getWebResponse().getContentAsString());
                updateSeed(seed);
                juziMapper.insert(crawerCompanyInnoItjuzi);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("切换代理中...");
            }
//        }
    }

    private void updateSeed(CrawerCompanyInnoItjuziSeed item) {
        item.setIsCrawler(true);
        seedMapper.updateByPrimaryKey(item);
    }

    public CrawerCompanyInnoItjuzi buildContent(String url, String html) {
        Document parse = Jsoup.parse(html);
        CrawerCompanyInnoItjuzi juzi = new CrawerCompanyInnoItjuzi();
        String name = parse.select("body > div.thewrap > div:nth-child(6) > div > div > div.on-edit-hide > div.rowhead.feedback-btn-parent > div.picinfo > div.line-title > span > h1").text();
        juzi.setName(name.substring(0, name.lastIndexOf("(")));
        String tz = parse.select("body > div.thewrap > div:nth-child(6) > div > div > div.on-edit-hide > div.rowhead.feedback-btn-parent > div.picinfo > div.line-title > span > h1 > span").text();
        juzi.setCompanyFinance(tz.replace("(", "").replace(")", ""));
        juzi.setCompanySummary(parse.select("body > div.thewrap > div:nth-child(6) > div > div > div.on-edit-hide > div.rowhead.feedback-btn-parent > div.picinfo > div:nth-child(2) > h2").text());
        juzi.setCompanyIndustry(parse.select("body > div.thewrap > div:nth-child(6) > div > div > div.on-edit-hide > div.rowhead.feedback-btn-parent > div.picinfo > div:nth-child(3) > span.scope.c-gray-aset").text());
        juzi.setCompanyAddress(parse.select("body > div.thewrap > div:nth-child(6) > div > div > div.on-edit-hide > div.rowhead.feedback-btn-parent > div.picinfo > div:nth-child(3) > span.loca.c-gray-aset").text());
        juzi.setCompanyUrl(parse.select("body > div.thewrap > div:nth-child(6) > div > div > div.on-edit-hide > div.rowhead.feedback-btn-parent > div.picinfo > div.link-line > a.weblink").text());
        Elements select = parse.select("body > div.thewrap > div:nth-child(6) > div > div > div.rowfoot.feedback-btn-parent.position-relative > div.tagset.dbi.c-gray-aset > a");
        String tags = "";
        for (Element item : select) {
            tags += item.text() + ";";
        }
        juzi.setCompanyTags(tags);
        juzi.setCompanyDesc(parse.select("body > div.thewrap > div.boxed.invest-info.company-info > div.main > div.sec.ugc-block-item.bgpink > div.block-inc-info.on-edit-hide > div:nth-child(2) > div").text());
        String fullNameTmp = parse.select("body > div.thewrap > div.boxed.invest-info.company-info > div.main > div.sec.ugc-block-item.bgpink > div.block-inc-info.on-edit-hide > div:nth-child(3) > div > div:nth-child(1) > h2").text();
        juzi.setFullName(fullNameTmp.replace("公司全称：", ""));
        String dateStr = parse.select("body > div.thewrap > div.boxed.invest-info.company-info > div.main > div.sec.ugc-block-item.bgpink > div.block-inc-info.on-edit-hide > div:nth-child(3) > div > div:nth-child(2) > h2:nth-child(1)").text();

        Date date = null;
        try {
            date = DateUtils.parseDate(dateStr.replace("成立时间：", ""), "yyyy.hh");
            juzi.setCompanyEstablishedTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String gmTmp = parse.select("body > div.thewrap > div.boxed.invest-info.company-info > div.main > div.sec.ugc-block-item.bgpink > div.block-inc-info.on-edit-hide > div:nth-child(3) > div > div:nth-child(2) > h2:nth-child(2)").text();
        juzi.setCompanyScale(gmTmp.replace("公司规模：", ""));
        juzi.setCompanyOperationState(parse.select("body > div.thewrap > div.boxed.invest-info.company-info > div.main > div.sec.ugc-block-item.bgpink > div.block-inc-info.on-edit-hide > div:nth-child(3) > div > div:nth-child(3) > span.tag.green").text());
        juzi.setCrawlerUrl(url);
        juzi.setUrlmd5(DigestUtils.md5Hex(juzi.getCrawlerUrl()));
        return juzi;
    }
}
