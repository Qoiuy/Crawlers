package com.inno.tree;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class CrawlerUseHttpclient {
    public static void main(String[] args) throws IOException {
        // 公司列表
//        getCompanyList();

        String str[] = {"6368839152178930679", "6771030035931876966", "1654075881820993462", "2198001895727819183", "17005284700625355601", "2144759685388773199", "14581904392320709619", "2560552285065536437", "12030336207748215289", "17360333989400542532"};
        for(int i=0;i<str.length;i++) {
            //公司详情
            getCompanyDetail(str[i]);
        }
//
        
    }

    private static void getCompanyDetail(String s) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.innotree.cn/inno/company/"+s+".html");
        httpGet.setHeader("Accept","application/json,text/javascript,*/*;q=0.01");
        httpGet.setHeader("Accept-Encoding","gzip,deflate,br");
        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        httpGet.setHeader("Connection","keep-alive");
        httpGet.setHeader("Cookie","_user_identify_=bcc1c471-b2d8-3a36-bfa8-fb20b06f68e2;uID=450394;sID=2a48b3d753f6d08a1a4bf456440cc1f3;JSESSIONID=aaaMaWLicGHPz6AA-OX9v;Hm_lvt_37854ae85b75cf05012d4d71db2a355a=1509366682,1509440093;Hm_lpvt_37854ae85b75cf05012d4d71db2a355a=1509440135");
        httpGet.setHeader("Host","www.innotree.cn");
        httpGet.setHeader("Referer","https//www.innotree.cn/inno/database/totalDatabase");
        httpGet.setHeader("User-Agent","Mozilla/5.0(WindowsNT10.0;Win64;x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/60.0.3112.90Safari/537.36");
        httpGet.setHeader("X-Requested-With","XMLHttpRequest");

        CloseableHttpResponse execute = httpclient.execute(httpGet);
        String result = IOUtils.toString(execute.getEntity().getContent());
        Document parse = Jsoup.parse(result);
        Element select = parse.select("body > div > div.de_170822_con > div.mech_170525_nav > table > tbody > tr > td:nth-child(2) > h3").get(0);
        System.out.println(select.text());
    }

    static void getCompanyList() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        int pageNo = 3;

        HttpGet httpGet = new HttpGet("https://www.innotree.cn/inno/search/ajax/getCompanySearchResultV2?query=&areaName=&rounds=&st="+pageNo+"&ps=10&sEdate=-1&sFdate=1&sRound=-1&chainName=");


        httpGet.setHeader("Accept","application/json,text/javascript,*/*;q=0.01");
        httpGet.setHeader("Accept-Encoding","gzip,deflate,br");
        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        httpGet.setHeader("Connection","keep-alive");
        httpGet.setHeader("Cookie","_user_identify_=bcc1c471-b2d8-3a36-bfa8-fb20b06f68e2;uID=450394;sID=2a48b3d753f6d08a1a4bf456440cc1f3;JSESSIONID=aaaMaWLicGHPz6AA-OX9v;Hm_lvt_37854ae85b75cf05012d4d71db2a355a=1509366682,1509440093;Hm_lpvt_37854ae85b75cf05012d4d71db2a355a=1509440135");
        httpGet.setHeader("Host","www.innotree.cn");
        httpGet.setHeader("Referer","https//www.innotree.cn/inno/database/totalDatabase");
        httpGet.setHeader("User-Agent","Mozilla/5.0(WindowsNT10.0;Win64;x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/60.0.3112.90Safari/537.36");
        httpGet.setHeader("X-Requested-With","XMLHttpRequest");

        CloseableHttpResponse execute = httpclient.execute(httpGet);
        System.out.println(IOUtils.toString(execute.getEntity().getContent()));
    }
}