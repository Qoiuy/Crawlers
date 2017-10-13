package com.quick.zhilian.pipeline;

import com.quick.zhilian.model.ZhilianJobInfo;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * Created by wei on 2017/1/9.
 */
public class ZhilianModelPipeline implements PageModelPipeline<ZhilianJobInfo> {


    public void process(ZhilianJobInfo zhilianJobInfo, Task task) {
        // save info to db
        System.out.println(zhilianJobInfo);
    }
}
