package com.quick.zhilian;

import com.quick.zhilian.model.ZhilianJobInfo;
import com.quick.zhilian.pipeline.ZhilianModelPipeline;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IDEA
 * User: vector
 * Data: 2017/10/13
 * Time: 18:00
 * Description:
 */
public class Crawler {
    public static void main(String[] args) {
        // IP代理池
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        try {
            List<Proxy> proxies = buildProxyIP();
            System.out.println("请求代理IP： " + proxies);
            httpClientDownloader.setProxyProvider(new SimpleProxyProvider(proxies));
        } catch (IOException e) {
            e.printStackTrace();
        }

        OOSpider.create(Site.me()
                .setSleepTime(5)
                .setRetrySleepTime(10)
                .setCycleRetryTimes(3),
                new ZhilianModelPipeline(),ZhilianJobInfo.class)
                .addUrl("http://sou.zhaopin.com/jobs/searchresult.ashx?jl=765&bj=7002000&sj=463")
                .thread(60)
                .setDownloader(httpClientDownloader)
                .run();
    }


    /**
     * 不错的免费代理IP站点
     * www.89ip.cn
     *
     * @return
     */
    private static List<Proxy> buildProxyIP() throws IOException {
        Document parse = Jsoup.parse(new URL("http://www.89ip.cn/tiqv.php?sxb=&tqsl=50&ports=&ktip=&xl=on&submit=%CC%E1++%C8%A1"), 5000);
        String pattern = "(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+):(\\d+)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(parse.toString());
        List<Proxy> proxies = new ArrayList<Proxy>();
        while (m.find()) {
            String[] group = m.group().split(":");
            int prot = Integer.parseInt(group[1]);
            proxies.add(new Proxy(group[0], prot));
        }
        return proxies;
    }
}
