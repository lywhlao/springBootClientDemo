package com.example.demo.service.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * @Author: laojiaqi
 * @Date: 2019-10-24 21:14
 * @Description:
 */
public class SeleniumTest {

    public static void main(String[] args) {
//        System.setProperty("webdriver.chrome.driver", "/Users/laojiaqi/IdeaProjects/springBootDemo/src/main/resources/driver/chromedriver");
        //Initiating your chromedriver
        WebDriver driver=new FirefoxDriver();

//Applied wait time
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//maximize window
        driver.manage().window().maximize();

//open browser with desried URL
        driver.get("https://www.baidu.com");

//closing the browser
        driver.close();
    }
}
