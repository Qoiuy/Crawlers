package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Iterator;
import java.util.Set;

/**
 * @Author: wangxc
 * @GitHub: https://github.com/vector4wang
 * @CSDN: http://blog.csdn.net/qqhjqs?viewmode=contents
 * @BLOG: http://vector4wang.tk
 * @wxid: BMHJQS
 */
public class TestMain {
    public static void main(String[] args) throws InterruptedException {

//        System.setProperty("webdriver.chrome.driver", "D:\\develop\\gitspace\\Crawlers\\quick-selenium\\src\\main\\resources\\chromedriver.exe");
        /**
         * 火狐用的是39.0.3版本
         */
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.baidu.com");
        WebElement element = driver.findElement(By.cssSelector("#kw"));
        element.sendKeys("vector4wang");
        WebElement btn = driver.findElement(By.cssSelector("#su"));
        btn.click();
        Thread.sleep(2000);
        WebElement firstEle = driver.findElement(By.cssSelector("#\\31 > h3 > a"));
        firstEle.click();

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

        Thread.sleep(15000); // 时间等的依个人而定
        WebElement desc = driver.findElement(By.cssSelector("#Pl_Official_Headerv6__1 > div > div > div.shadow > div.pf_intro"));
        System.out.println(desc.getText());

        driver.switchTo().window(current_handle);
        driver.close();

    }
}
