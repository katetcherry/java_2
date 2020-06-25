package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public abstract class WebComponent<T extends WebComponent<T>> {
  WebElement element;
  RemoteWebDriver driver;

  WebComponent(By method, RemoteWebDriver driver) {
    this.driver = driver;
    this.element = driver.findElement(method);
  }

  public void waitUntilInvisible(Long sec) {

    WebDriverWait time = new WebDriverWait(driver, sec);
    try {
      time.until(ExpectedConditions.invisibilityOf(element));
    } catch (Exception ignored) {

    }


  }

  public void waitUntilClickable(Long sec) {

    WebDriverWait time = new WebDriverWait(driver, sec);
    try {
      time.until(ExpectedConditions.elementToBeClickable(element));
    } catch (Exception ignored) {

    }
  }
}
