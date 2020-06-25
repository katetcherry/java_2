package com.google;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FirstTest {
  @Test
  public void FirstTest() {
    System.setProperty("webdriver.chrome.driver", "/Users/k.tereshchenko/Automation/seleniumTool/chromedriver.exe");
    SoftAssert softAssert = new SoftAssert();
    ChromeDriver driver = new ChromeDriver();
    driver.get("https://google.com");
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    softAssert.assertTrue(driver.getCurrentUrl().equals("https://gogle.com"), "address doesn't equals");
    driver.quit();
    softAssert.assertAll();


  }
}



