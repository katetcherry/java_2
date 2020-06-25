package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ResultMagnifierButton extends WebComponent<Input> {
  public ResultMagnifierButton (By method, RemoteWebDriver driver){
    super(method, driver);
  }
  public void click() {
    element.click();
  }
}
