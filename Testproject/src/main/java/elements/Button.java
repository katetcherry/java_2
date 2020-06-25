package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Button extends WebComponent<Button> {
  public Button (By method, RemoteWebDriver driver){
  super(method, driver);
  }
  public void click() {
    element.click();
  }

}
