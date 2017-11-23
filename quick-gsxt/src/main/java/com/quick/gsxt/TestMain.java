package com.quick.gsxt;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created with IDEA
 * User: vector
 * Data: 2017/11/21
 * Time: 16:26
 * Description:
 */
public class TestMain {
    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://blog.csdn.net/ma524654165/article/details/76983929");
//        Thread.sleep(10000);
//        WebElement firstClick = driver.findElement(By.cssSelector("#captcha > div > div.geetest_btn > div.geetest_radar_btn > div.geetest_radar_tip"));
//        firstClick.click();
        Thread.sleep(3000);

        WebElement element = driver.findElement(By.xpath("//*[@id=\"article_details\"]/div[1]/h1/span/a"));

        ScreenShoter(element, "D:test.png");
        // 拖动
        WebElement begin = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div/div[2]/div[2]/div[2]"));
        Thread.sleep(3000);

        // 根据像素处理需要滑动的距离



    }

    public static void ScreenShoter(WebElement driver,String filePathName) {
        File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShotFile, new File(filePathName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
