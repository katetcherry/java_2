package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ResultInput extends WebComponent<Input> {
  public ResultInput (By method, RemoteWebDriver driver) {
    super(method, driver);
  }
  public void sendKeys(String searchKeys) {
    element.sendKeys(searchKeys);
  }
}
