package Common;

public class CommonMethods
{
  /**
   * Scrolls to provided element
   * true for scroll element to top of page
   * false for scroll element to bottom of page
   *
   * @param el     - WebElement
   * @param driver - WebDriver
   */
  public static void scrollIntoView(WebElement el, RemoteWebDriver driver, boolean... top) {
    if (el == null) {
      return;
    }
    boolean t = top.length > 0 && top[0];
    try {
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(" + t + ");", el);
      //to scroll element from under Webportal header:
      if (t) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(" + 0 + "," + -350 + ");");
      }
/*			if (!el.isDisplayed()) {
				javascriptExecutor.executeScript("window.scrollBy(" + 0 + "," + 550 + ");");
			}*/
    } catch (
            StaleElementReferenceException ignored) {
    }

  }
