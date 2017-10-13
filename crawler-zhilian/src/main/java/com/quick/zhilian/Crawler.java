package com.quick.zhilian;

import com.quick.zhilian.model.ZhilianJobInfo;
import com.quick.zhilian.pipeline.ZhilianModelPipeline;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

/**
 * Created with IDEA
 * User: vector
 * Data: 2017/10/13
 * Time: 18:00
 * Description:
 */
public class Crawler {
    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(5),new ZhilianModelPipeline(),ZhilianJobInfo.class)
                .addUrl("http://sou.zhaopin.com/jobs/searchresult.ashx?jl=765&bj=7002000&sj=463").thread(60).run();
    }
}
