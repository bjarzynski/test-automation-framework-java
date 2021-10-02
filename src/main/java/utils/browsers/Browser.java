package utils.browsers;

public enum Browser {
  CHROME("chrome"),
  EDGE("edge"),
  FIREFOX("firefox");

  private String browserName;

  Browser(String browserName) {
    this.browserName = browserName;
  }

  public static Browser fromString(String text) {
    for (Browser b : Browser.values()) {
      if (b.browserName.equalsIgnoreCase(text)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Browser name " + text + " not found");
  }
}
