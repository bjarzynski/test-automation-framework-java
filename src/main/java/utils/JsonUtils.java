package utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;

public class JsonUtils {

  private JsonUtils() {

  }

  public static JsonArray readJsonArrayFromFile(String path) {
    try {
      JsonReader jsonReader = new JsonReader(new FileReader(path));
      JsonElement element = JsonParser.parseReader(jsonReader);
      return element.getAsJsonArray();
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("JSON file does not exist in path " + path);
    }
  }

  public static <T> T[] readJsonArrayFromFileMapped(String path, Class<T> classToMap) {
    try {
      JsonReader jsonReader = new JsonReader(new FileReader(path));
      Class<?> classToMapArray = Array.newInstance(classToMap, 0).getClass();
      Gson gson = new Gson();
      return gson.fromJson(jsonReader, classToMapArray);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("JSON file does not exist in path " + path);
    }
  }
}
