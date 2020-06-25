package com.the_internet.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GoogleResultPage;
import pages.GoogleSearchPage;

public class EmailFieldTest {
  ChromeDriver driver;

  @BeforeMethod
  public void start() {
    System.setProperty("webdriver.chrome.driver", "/Users/k.tereshchenko/Automation/seleniumTool/chromedriver.exe");
    driver = new ChromeDriver();
    driver.get("https:///the-internet.herokuapp.com/forgot_password");

  }

  @Test
  public void emailfieldTest() {

    WebElement input = driver.findElement(By.xpath("//div[@class='row']//input[@id='email']"));
    input.sendKeys("abcdef1@gmail.com");
    driver.findElement(By.xpath("//body")).click();

    WebElement button = driver.findElement(By.xpath("//button[@id='form_submit']"));
    WebDriverWait time = new WebDriverWait(driver, 3L);
    try {
      time.until(ExpectedConditions.elementToBeClickable(button));
    } catch (Exception e) {
      Assert.fail("button not found");
    }
    button.click();
  }

  //@Test
  //public void searchtest_PO() {
  //new GoogleSearchPage(driver).searchInputField("cat").click_search();}

  //@Test
 // public void resulttest_PO() {
   // new GoogleSearchPage(driver).searchInputField("summer").click_search();
  //  new GoogleResultPage(driver).searchInputField("winter").click_searchResult();}

  @AfterMethod
  public void finish() {
    driver.quit();
  }

}
