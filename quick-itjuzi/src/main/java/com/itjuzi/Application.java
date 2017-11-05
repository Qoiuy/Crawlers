package com.itjuzi;

import com.itjuzi.crawler4httpclient.CrawlerOrangeByHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created with IDEA
 * User: vector
 * Data: 2017/11/3
 * Time: 9:31
 * Description:
 */
@SpringBootApplication
public class Application {

    public final static int LIST_START = 0;

    @Resource
    private CrawlerOrangeByHttpClient crawlerOrangeByHttpClient;


    @PostConstruct
    public void crawelr() throws IOException {
        crawlerOrangeByHttpClient.run();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
