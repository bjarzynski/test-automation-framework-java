package base;

import configuration.EnvironmentVariables;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import utils.browsers.Browser;
import utils.browsers.DriverFactory;

@Listeners(TestListener.class)
public class TestBase {
  protected static WebDriver driver;

  @BeforeTest
  public void beforeTestSetup() {
    setUpDriver();
  }

  private void setUpDriver() {
    String browserName = System.getenv(EnvironmentVariables.BROWSER_KEY);
    switch (Browser.fromString(browserName)) {
      case CHROME -> driver = DriverFactory.createChromeDriver();
      case EDGE -> driver = DriverFactory.createEdgeDriver();
      case FIREFOX -> driver = DriverFactory.createFirefoxDriver();
      default -> throw new UnsupportedOperationException();
    }
  }
}
