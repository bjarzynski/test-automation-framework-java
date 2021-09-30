package utils.browsers;

import java.io.File;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.files.FileUtils;

public class DriverFactory {
  private static final String[] DRIVER_DIRS = {"src", "main", "resources", "drivers"};
  private static final String CHROME_DRIVER_FILE_NAME = "chromedriver.exe";
  private static final String GECKO_DRIVER_FILE_NAME = "geckodriver.exe";
  private static final String EDGE_DRIVER_FILE_NAME = "msedgedriver.exe";
  private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
  private static final String GECKO_DRIVER_PROPERTY = "webdriver.gecko.driver";
  private static final String EDGE_DRIVER_PROPERTY = "webdriver.edge.driver";

  private DriverFactory() {

  }

  public static ChromeDriver createChromeDriver() {
    setDriverProperty(CHROME_DRIVER_PROPERTY, CHROME_DRIVER_FILE_NAME);
    return new ChromeDriver();
  }

  public static FirefoxDriver createFirefoxDriver() {
    setDriverProperty(GECKO_DRIVER_PROPERTY, GECKO_DRIVER_FILE_NAME);
    return new FirefoxDriver();
  }

  public static EdgeDriver createEdgeDriver() {
    setDriverProperty(EDGE_DRIVER_PROPERTY, EDGE_DRIVER_FILE_NAME);
    return new EdgeDriver();
  }

  private static void setDriverProperty(String propertyName, String driverFileName) {
    String driverAbsolutePath = FileUtils.addToPath(getDriversAbsolutePath(), driverFileName);
    System.setProperty(propertyName, driverAbsolutePath);
  }

  private static String getDriversAbsolutePath() {
    return System.getProperty("user.dir") + File.separator + FileUtils.constructPath(DRIVER_DIRS);
  }
}
