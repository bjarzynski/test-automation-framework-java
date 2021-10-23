package utils;

import java.io.File;

public class FileUtils {

  private FileUtils() {

  }

  public static String constructPath(String[] dirs) {
    return String.join(File.separator, dirs);
  }

  public static String addToPath(String path, String newDir) {
    return path + File.separator + newDir;
  }

  public static String getDirFromProjectPath(String path) {
    return getProjectPath() + File.separator + path;
  }

  private static String getProjectPath() {
    return System.getProperty("user.dir");
  }
}
