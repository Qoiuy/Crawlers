package com.inno.tree;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerUseHttpclient {


    public static void main(String[] args) throws IOException {
        /**
         * 抓取公司列表
         */
        List<String> lists = getCompanyList();
        System.out.println(lists);


        /**
         * 根据公司Id抓取详情页，结果对象见 CrawerCompanyInnoTree
         */
        CrawerCompanyInnoTree crawerCompanyInnoTree = new CrawerCompanyInnoTree("10245516517745398582",getCompanyDetail("10245516517745398582"));
        System.out.println(crawerCompanyInnoTree);
    }


    public static String getRelationlist(String companId, String type) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String url = "";
        if ("product".equals(type)) {
            url = "https://www.innotree.cn/inno/company/ajax/projectlist?compId=" + companId;
        } else {
            url = "https://www.innotree.cn/inno/company/ajax/relationlist?compId=" + companId;
        }
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("accept", "application/json,*/*");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Cookie", "_user_identify_=bcc1c471-b2d8-3a36-bfa8-fb20b06f68e2;uID=450394;sID=2a48b3d753f6d08a1a4bf456440cc1f3;JSESSIONID=aaa5kBah4PDP7aAuJs19v;Hm_lvt_37854ae85b75cf05012d4d71db2a355a=1509366682,1509440093,1509444022,1509501317;Hm_lpvt_37854ae85b75cf05012d4d71db2a355a=1509504558");
        httpGet.setHeader("Host", "www.innotree.cn");
        httpGet.setHeader("Referer", "https://www.innotree.cn/inno/company/8291112702946569349.html");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36");
        CloseableHttpResponse execute = httpclient.execute(httpGet);
        return IOUtils.toString(execute.getEntity().getContent());
    }

    /**
     * 根据网页ID获取网页内容
     *
     * @param s
     * @return
     * @throws IOException
     */
    private static String getCompanyDetail(String s) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.innotree.cn/inno/company/" + s + ".html");
        httpGet.setHeader("Accept", "application/json,text/javascript,*/*;q=0.01");
        httpGet.setHeader("Accept-Encoding", "gzip,deflate,br");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Cookie", "_user_identify_=bcc1c471-b2d8-3a36-bfa8-fb20b06f68e2;uID=450394;sID=2a48b3d753f6d08a1a4bf456440cc1f3;JSESSIONID=aaaMaWLicGHPz6AA-OX9v;Hm_lvt_37854ae85b75cf05012d4d71db2a355a=1509366682,1509440093;Hm_lpvt_37854ae85b75cf05012d4d71db2a355a=1509440135");
        httpGet.setHeader("Host", "www.innotree.cn");
        httpGet.setHeader("Referer", "https//www.innotree.cn/inno/database/totalDatabase");
        httpGet.setHeader("User-Agent", "Mozilla/5.0(WindowsNT10.0;Win64;x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/60.0.3112.90Safari/537.36");
        httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
        CloseableHttpResponse execute = httpclient.execute(httpGet);
        return IOUtils.toString(execute.getEntity().getContent());
    }

    static List<String> getCompanyList() throws IOException {
        String tjjson = "{\"code\":0,\"msg\":\"OK\",\"data\":{\"areaList\":[{\"id\":\"301\",\"name\":\"北京市\"},{\"id\":\"302\",\"name\":\"天津市\"},{\"id\":\"303\",\"name\":\"上海市\"},{\"id\":\"304\",\"name\":\"重庆市\"},{\"id\":\"305\",\"name\":\"河北省\"},{\"id\":\"306\",\"name\":\"山西省\"},{\"id\":\"307\",\"name\":\"内蒙古自治区\"},{\"id\":\"308\",\"name\":\"辽宁省\"},{\"id\":\"309\",\"name\":\"吉林省\"},{\"id\":\"310\",\"name\":\"黑龙江省\"},{\"id\":\"311\",\"name\":\"江苏省\"},{\"id\":\"312\",\"name\":\"浙江省\"},{\"id\":\"313\",\"name\":\"安徽省\"},{\"id\":\"314\",\"name\":\"福建省\"},{\"id\":\"315\",\"name\":\"江西省\"},{\"id\":\"316\",\"name\":\"山东省\"},{\"id\":\"317\",\"name\":\"河南省\"},{\"id\":\"318\",\"name\":\"湖北省\"},{\"id\":\"319\",\"name\":\"湖南省\"},{\"id\":\"320\",\"name\":\"广东省\"},{\"id\":\"321\",\"name\":\"广西壮族自治区\"},{\"id\":\"322\",\"name\":\"海南省\"},{\"id\":\"323\",\"name\":\"四川省\"},{\"id\":\"324\",\"name\":\"贵州省\"},{\"id\":\"325\",\"name\":\"云南省\"},{\"id\":\"326\",\"name\":\"西藏自治区\"},{\"id\":\"327\",\"name\":\"陕西省\"},{\"id\":\"328\",\"name\":\"甘肃省\"},{\"id\":\"329\",\"name\":\"青海省\"},{\"id\":\"330\",\"name\":\"宁夏回族自治区\"},{\"id\":\"331\",\"name\":\"新疆维吾尔自治区\"}],\"roundList\":[{\"id\":\"1-2\",\"name\":\"种子期\"},{\"id\":\"3-4-5-6-7-8\",\"name\":\"早期\"},{\"id\":\"9-10-11-12-13-14\",\"name\":\"成长期\"},{\"id\":\"15-16-17-18-19\",\"name\":\"成熟期\"},{\"id\":\"20-21\",\"name\":\"新三板\"},{\"id\":\"30\",\"name\":\"PIPE\"},{\"id\":\"40\",\"name\":\"IPO上市及以后\"},{\"id\":\"50\",\"name\":\"战略并购\"},{\"id\":\"60\",\"name\":\"战略投资\"}]}}";
        JSONObject jsonObject = JSONObject.parseObject(tjjson);
        JSONArray areaList = jsonObject.getJSONObject("data").getJSONArray("areaList");
        JSONArray roundList = jsonObject.getJSONObject("data").getJSONArray("roundList");
        List<String> allComIds = new ArrayList<String>();
        for (int i = 0; i < areaList.size(); i++) {
            String area = areaList.getJSONObject(i).getString("name");
            List<String> perSearchList = new ArrayList<String>();
            for (int j = 0; j < roundList.size(); j++) {
                String round = roundList.getJSONObject(j).getString("id");
                CloseableHttpClient httpclient = HttpClients.createDefault();
                String url = "https://www.innotree.cn/inno/search/ajax/getCompanySearchResultV2" +
                        "?query=&areaName=" + area + "&rounds=" + round + "&st=1&ps=10&sEdate=-1&sFdate=1&sRound=-1&chainName=";
                CloseableHttpResponse execute = httpclient.execute(getCachedGet(url));
                JSONObject jsonObject1 = JSONObject.parseObject(IOUtils.toString(execute.getEntity().getContent()));
                // 总条数
                Integer total = jsonObject1.getJSONObject("data").getJSONObject("company").getInteger("count");

                if (total > 0) {
                    JSONArray jsonArray = jsonObject1.getJSONObject("data").getJSONObject("company").getJSONArray("infos");
                    for (int x = 0; i < jsonArray.size(); i++) {
                        perSearchList.add(jsonArray.getJSONObject(x).getString("ncid"));
                    }
                    int pageCount = total / 10;
                    if (total % 10 > 0) {
                        pageCount += 1;
                    }
                    List<String> repsList = new ArrayList<String>();
                    for (int y = 2; y <= pageCount; y++) {
                        url = "https://www.innotree.cn/inno/search/ajax/getCompanySearchResultV2" +
                                "?query=&areaName=" + area + "&rounds=" + round + "&st=" + y + "&ps=10&sEdate=-1&sFdate=1&sRound=-1&chainName=";
                        CloseableHttpClient subHttpclient = HttpClients.createDefault();
                        CloseableHttpResponse subExecu = subHttpclient.execute(getCachedGet(url));
                        repsList = getRepsList(IOUtils.toString(subExecu.getEntity().getContent()));
                    }
                    perSearchList.addAll(repsList);
                }
            }
            allComIds.addAll(perSearchList);
        }
        return allComIds;
    }

    public static HttpGet getCachedGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept", "application/json,text/javascript,*/*;q=0.01");
        httpGet.setHeader("Accept-Encoding", "gzip,deflate,br");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Cookie", "_user_identify_=bcc1c471-b2d8-3a36-bfa8-fb20b06f68e2;uID=450394;sID=2a48b3d753f6d08a1a4bf456440cc1f3;JSESSIONID=aaaMaWLicGHPz6AA-OX9v;Hm_lvt_37854ae85b75cf05012d4d71db2a355a=1509366682,1509440093;Hm_lpvt_37854ae85b75cf05012d4d71db2a355a=1509440135");
        httpGet.setHeader("Host", "www.innotree.cn");
        httpGet.setHeader("Referer", "https//www.innotree.cn/inno/database/totalDatabase");
        httpGet.setHeader("User-Agent", "Mozilla/5.0(WindowsNT10.0;Win64;x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/60.0.3112.90Safari/537.36");
        httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
        return httpGet;
    }

    public static List<String> getRepsList(String reps) {
        List<String> result = new ArrayList<String>();
        JSONObject subObj = JSONObject.parseObject(reps);
        JSONArray subJa = subObj.getJSONObject("data").getJSONObject("company").getJSONArray("infos");
        for (int i = 0; i < subJa.size(); i++) {
            System.out.println(subJa.getJSONObject(i).getString("ncid"));
            result.add(subJa.getJSONObject(i).getString("ncid"));
        }
        return result;
    }
}