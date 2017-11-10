package com.baidu.zhaopin;

import com.baidu.entity.AppointCompanyDetail;
import com.baidu.util.JdbcUtil;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created with IDEA
 * User: vector
 * Data: 2017/11/10
 * Time: 13:40
 * Description:
 */
public class CrawlerService {

    final static String HOST_URL = "http://zhaopin.baidu.com";
    final static WebClient webClient = new WebClient();

    public static void main(String[] args) throws IOException {
        List<String> list = IOUtils.readLines(new FileInputStream(new File("D:\\company\\weiwei\\company_100.txt")));
        for (String companyName : list) {
            if (!isExist(companyName)) {
                try {
                    crawlerBySelenium(companyName);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("未抓取： " + companyName);
                }
            }
        }

    }

    public static void crawlerCompany(String key) throws IOException {

        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36");
        HtmlPage page = webClient.getPage("http://zhaopin.baidu.com/quanzhi");
        HtmlElement domNode = (HtmlElement) page.querySelector("#hd-kw");
        domNode.type(key);
        HtmlElement btn = (HtmlElement) page.querySelector("#hd-su");
        page = btn.click();
        Document parse = Jsoup.parse(page.getWebResponse().getContentAsString());
        Elements element = parse.select("#feed-list > a");
        if (element.size() <= 0) {
            return;
        }
        String targetUrl = element.get(0).attr("href");
        System.out.println(HOST_URL + targetUrl);
        Page targetPage = webClient.getPage(HOST_URL + targetUrl);
        Document targetDoc = Jsoup.parse(targetPage.getWebResponse().getContentAsString());
        Element comDetail = targetDoc.select("body > div.main > div.page > div.clearfix > div.right.width-right > div.rightbar > div > div.details").get(0);
        Elements p = comDetail.select("p");
        AppointCompanyDetail appointCompanyDetail = new AppointCompanyDetail();
        appointCompanyDetail.setName(key);
        for (Element item : p) {
            String txt = item.text();
            if (txt.contains("公司性质：")) {
                appointCompanyDetail.setCompanyNature(txt.replace("公司性质：", ""));
            }
            if (txt.contains("公司规模：")) {
                appointCompanyDetail.setCompanyScale(txt.replace("公司规模：", ""));
            }
            if (txt.contains("公司类型：")) {
                appointCompanyDetail.setCompanyCategory(txt.replace("公司类型：", ""));
            }
            if (txt.contains("工作地点：")) {
                appointCompanyDetail.setCompanyAddress(txt.replace("工作地点：", "").replace("[查看地图]",""));
            }
            if (txt.contains("联系方式：")) {
                appointCompanyDetail.setCompanyContact(txt.replace("联系方式：", ""));
            }
        }
        System.out.println(appointCompanyDetail);
        save(appointCompanyDetail);

    }

    public static void save(AppointCompanyDetail appointCompanyDetail) {
//
//        String sql = "INSERT INTO `appoint_company_detail` (`name`, `company_nature`, `company_scale`, `company_category`, `company_address`, `company_contact`) " +
//                "VALUES (?, ?, ?, ?, ?, ?)";
//        //创建填充参数的list
//        List<Object> paramList = new ArrayList<Object>();
//        //填充参数
//        paramList.add(appointCompanyDetail.getName());
//        paramList.add(appointCompanyDetail.getCompanyNature());
//        paramList.add(appointCompanyDetail.getCompanyScale());
//        paramList.add(appointCompanyDetail.getCompanyCategory());
//        paramList.add(appointCompanyDetail.getCompanyAddress());
//        paramList.add(appointCompanyDetail.getCompanyContact());
//
//        JdbcUtil jdbcUtil = null;
//        boolean bool = false;
//        try {
//            jdbcUtil = new JdbcUtil();
//            jdbcUtil.getConnection(); // 获取数据库链接
//            bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
//        } catch (SQLException e) {
//            System.out.println("执行更新操作抛出异常！");
//            e.printStackTrace();
//        } finally {
//            if (jdbcUtil != null) {
//                jdbcUtil.releaseConn(); // 一定要释放资源
//            }
//        }
//        System.out.println("执行更新的结果："+bool);
    }

    public static boolean isExist(String company) {
        boolean flag = true;
        JdbcUtil jdbcUtil = new JdbcUtil();
        jdbcUtil.getConnection();
        try {
            List<Map<String, Object>> result = jdbcUtil.findResult(
                    "select * from appoint_company_detail where `name` = '" + company + "'", null);
            if (result == null || result.size() <=0) {
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.releaseConn();
        }
        return flag;
    }

    public static void crawlerBySelenium(String name) {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://zhaopin.baidu.com/");
        WebElement key = driver.findElement(By.cssSelector("#hd-kw"));
        key.sendKeys(name);

        WebElement btn = driver.findElement(By.cssSelector("#hd-su"));
        btn.click();
        WebElement element = driver.findElement(By.cssSelector("#feed-list > a:nth-child(1)"));
        element.click();
        String current_handle = driver.getWindowHandle();
        // 处理新窗口打开
        Set<String> all_handles = driver.getWindowHandles();
        Iterator<String> it = all_handles.iterator();
        String handle = null;
        while(it.hasNext()){
            handle = it.next();
            if(current_handle==handle) continue;
            //跳入新窗口,并获得新窗口的driver - newWindow
            driver = driver.switchTo().window(handle);
        }

        WebElement element1 = driver.findElement(By.cssSelector("body > div.main > div.page > div.clearfix > div.right.width-right > div.rightbar > div > div.details"));
        List<WebElement> p = element1.findElements(By.cssSelector("p"));
        AppointCompanyDetail appointCompanyDetail = new AppointCompanyDetail();
        appointCompanyDetail.setName(name);
        for (WebElement item : p) {
            String txt = item.getText();
            if (txt.contains("公司性质：")) {
                appointCompanyDetail.setCompanyNature(txt.replace("公司性质：", ""));
            }
            if (txt.contains("公司规模：")) {
                appointCompanyDetail.setCompanyScale(txt.replace("公司规模：", ""));
            }
            if (txt.contains("公司类型：")) {
                appointCompanyDetail.setCompanyCategory(txt.replace("公司类型：", ""));
            }
            if (txt.contains("工作地点：")) {
                appointCompanyDetail.setCompanyAddress(txt.replace("工作地点：", "").replace("[查看地图]",""));
            }
            if (txt.contains("联系方式：")) {
                appointCompanyDetail.setCompanyContact(txt.replace("联系方式：", ""));
            }
            if (txt.contains("联系邮箱：")) {
                appointCompanyDetail.setCompanyContact(txt.replace("联系邮箱：", ""));
            }
        }
        System.out.println(appointCompanyDetail);
        save(appointCompanyDetail);
        driver.close();
    }
}
