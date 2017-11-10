package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created with IDEA
 * User: vector
 * Data: 2017/11/10
 * Time: 10:32
 * Description: 使用selenium抓领英的数据，没想到这么简单
 */
public class LinkedinTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.linkedin.com/uas/login");
        Thread.sleep(3000);

        WebElement name = driver.findElement(By.cssSelector("#session_key-login"));
        name.sendKeys("******");
        WebElement pwd = driver.findElement(By.cssSelector("#session_password-login"));
        pwd.sendKeys("*******");
        WebElement btn = driver.findElement(By.cssSelector("#btn-primary"));
        btn.click();

        Thread.sleep(10000);
        WebElement nameEle = driver.findElement(By.cssSelector("a[data-control-name='identity_welcome_message']"));
        System.out.println("welcome " + nameEle.getText());

        driver.get("https://www.linkedin.com/in/vector-wang-ab042a10a/");
        Thread.sleep(3000);

        System.out.println(driver.findElement(By.cssSelector("h2[class='pv-top-card-section__headline Sans-19px-black-85%']")).getText());

        driver.quit();

    }
}
