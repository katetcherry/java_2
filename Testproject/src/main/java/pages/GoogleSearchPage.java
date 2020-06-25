package pages;

import elements.Button;
import elements.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GoogleSearchPage {
 RemoteWebDriver  driver;
private Button btn_searchButton() {
  return new Button(By.xpath("(//div[@id='searchform']//center/input[contains(@name,'btnK')])[1]"), driver);
}
public GoogleSearchPage click_search() {
  driver.findElement(By.xpath("//body")).click();
  btn_searchButton().waitUntilClickable(15L);
  btn_searchButton().click();
  btn_searchButton().waitUntilInvisible(3L);
return this;

}
private Input inp_searchInput() {
  return new Input(By.xpath("//div[@id='searchform']//input[@name='q']"), driver);
}
public GoogleSearchPage searchInputField(String searchKeys) {
  inp_searchInput().sendKeys(searchKeys);
  return this;
}

public GoogleSearchPage(RemoteWebDriver driver)
{this.driver = driver;

}
}
