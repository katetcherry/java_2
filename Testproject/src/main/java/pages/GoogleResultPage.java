package pages;

import elements.Button;
import elements.Input;
import elements.ResultInput;
import elements.ResultMagnifierButton;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GoogleResultPage {
  RemoteWebDriver driver;

  private ResultMagnifierButton btn_resultMagnifierButton() {
    return new ResultMagnifierButton(By.xpath("//div[@id='searchform']//button[contains(@jsname,'Tg7LZd')]"), driver);
  }

  public GoogleResultPage click_searchResult() {
    btn_resultMagnifierButton().click();
    return this;

  }

  private ResultInput inp_resultInput() {
    return new ResultInput(By.xpath("//div[@id='searchform']//input[@name='q']"), driver);
  }

  public GoogleResultPage searchInputField(String searchKeys) {
    inp_resultInput().sendKeys(searchKeys);
    driver.findElement(By.xpath("//body")).click();
    return this;
  }

  public GoogleResultPage(RemoteWebDriver driver) {
    this.driver = driver;
  }
}
