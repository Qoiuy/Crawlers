package com.itjuzi;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IDEA
 * User: vector
 * Data: 2017/11/2
 * Time: 10:43
 * Description:
 */
public class TestMain {
    public static void main(String[] args) throws Exception {

//        File file = new File("C:\\Users\\bd2\\Desktop\\content.html");
//
//        Document parse = Jsoup.parse(file, "utf-8");
//        CrawerCompanyInnoItjuzi juzi = new CrawerCompanyInnoItjuzi();
//        String name = parse.select("body > div.thewrap > div:nth-child(6) > div > div > div.on-edit-hide > div.rowhead.feedback-btn-parent > div.picinfo > div.line-title > span > h1").text();
//        juzi.setName(name.substring(0, name.lastIndexOf("(")));
//        String tz = parse.select("body > div.thewrap > div:nth-child(6) > div > div > div.on-edit-hide > div.rowhead.feedback-btn-parent > div.picinfo > div.line-title > span > h1 > span").text();
//        juzi.setCompanyFinance(tz.replace("(","").replace(")",""));
//        juzi.setCompanySummary(parse.select("body > div.thewrap > div:nth-child(6) > div > div > div.on-edit-hide > div.rowhead.feedback-btn-parent > div.picinfo > div:nth-child(2) > h2").text());
//        juzi.setCompanyIndustry(parse.select("body > div.thewrap > div:nth-child(6) > div > div > div.on-edit-hide > div.rowhead.feedback-btn-parent > div.picinfo > div:nth-child(3) > span.scope.c-gray-aset").text());
//        juzi.setCompanyAddress(parse.select("body > div.thewrap > div:nth-child(6) > div > div > div.on-edit-hide > div.rowhead.feedback-btn-parent > div.picinfo > div:nth-child(3) > span.loca.c-gray-aset").text());
//        juzi.setCompanyUrl(parse.select("body > div.thewrap > div:nth-child(6) > div > div > div.on-edit-hide > div.rowhead.feedback-btn-parent > div.picinfo > div.link-line > a.weblink").text());
//        Elements select = parse.select("body > div.thewrap > div:nth-child(6) > div > div > div.rowfoot.feedback-btn-parent.position-relative > div.tagset.dbi.c-gray-aset > a");
//        String tags = "";
//        for (Element item : select) {
//            tags += item.text() + ";";
//        }
//        juzi.setCompanyTags(tags);
//        juzi.setCompanyDesc(parse.select("body > div.thewrap > div.boxed.invest-info.company-info > div.main > div.sec.ugc-block-item.bgpink > div.block-inc-info.on-edit-hide > div:nth-child(2) > div").text());
//        String fullNameTmp = parse.select("body > div.thewrap > div.boxed.invest-info.company-info > div.main > div.sec.ugc-block-item.bgpink > div.block-inc-info.on-edit-hide > div:nth-child(3) > div > div:nth-child(1) > h2").text();
//        juzi.setFullName(fullNameTmp.replace("公司全称：",""));
//        String dateStr = parse.select("body > div.thewrap > div.boxed.invest-info.company-info > div.main > div.sec.ugc-block-item.bgpink > div.block-inc-info.on-edit-hide > div:nth-child(3) > div > div:nth-child(2) > h2:nth-child(1)").text();
//        Date date = DateUtils.parseDate(dateStr.replace("成立时间：",""), "yyyy.hh");
//        juzi.setCompanyEstablishedTime(date);
//        String gmTmp = parse.select("body > div.thewrap > div.boxed.invest-info.company-info > div.main > div.sec.ugc-block-item.bgpink > div.block-inc-info.on-edit-hide > div:nth-child(3) > div > div:nth-child(2) > h2:nth-child(2)").text();
//        juzi.setCompanyScale(gmTmp.replace("公司规模：",""));
//        juzi.setCompanyOperationState(parse.select("body > div.thewrap > div.boxed.invest-info.company-info > div.main > div.sec.ugc-block-item.bgpink > div.block-inc-info.on-edit-hide > div:nth-child(3) > div > div:nth-child(3) > span.tag.green").text());
//        juzi.setCrawlerUrl("https://www.itjuzi.com/company/12454165");
//        juzi.setUrlmd5(DigestUtils.md5Hex(juzi.getCrawlerUrl()));
//        System.out.println(juzi);

        int total = 3;
        int current = 2;

        String str[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9","10","11","12"};
        List<String> list = Arrays.asList(str);
        int pageSize = str.length / total;
        for(int i=1;i<=total;i++) {
            int start = i - 1;
            if (i == total) {
                System.out.println(list.subList(start*pageSize, list.size()));
            }else{
                System.out.println(list.subList(start*pageSize,pageSize*i));
            }

        }

//
//        System.out.println(list.subList(1,4));
    }
}
