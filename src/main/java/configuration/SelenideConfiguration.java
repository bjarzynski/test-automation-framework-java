package configuration;

import com.codeborne.selenide.Configuration;
import enums.EnvironmentVariable;

public class SelenideConfiguration {

  private SelenideConfiguration() {

  }

  public static void setUpSelenide() {
    Configuration.browser = EnvironmentVariables.getStringValue(EnvironmentVariable.BROWSER);
    Configuration.baseUrl = EnvironmentVariables.getStringValue(EnvironmentVariable.BASE_URL);
    Configuration.timeout = EnvironmentVariables.getLongValue(EnvironmentVariable.TIMEOUT);
    Configuration.headless = EnvironmentVariables.getBooleanValue(EnvironmentVariable.HEADLESS);
    Configuration.startMaximized = true;
  }
}
