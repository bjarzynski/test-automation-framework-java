package utils.files;

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
}
