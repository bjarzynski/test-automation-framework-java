package base;

import com.google.gson.JsonArray;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.IntStream;
import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;
import utils.FileUtils;
import utils.JsonUtils;

public class TestDataJsonProvider {
  private static final String[] TEST_DATA_DIRS = {"src", "test", "resources", "data"};
  private static final String JSON_DIR = "json";
  private static final String JSON_FILE_EXTENSION = ".json";

  @DataProvider(name = "jsonFile")
  public static Object[] provideDataFromJsonFile(ITestNGMethod testMethod) {
    String jsonFilePath = getJsonFileAbsolutePath(testMethod);
    Method method = testMethod.getConstructorOrMethod().getMethod();
    Optional<Class<?>> classToMap = getClassToMapFromAnnotation(method);
    if (classToMap.isPresent()) {
      return JsonUtils.readJsonArrayFromFileMapped(jsonFilePath, classToMap.get());
    } else {
      return getJsonObjectsArrayFromFile(jsonFilePath);
    }
  }

  private static String getJsonFileAbsolutePath(ITestNGMethod testMethod) {
    String testDataDirPath = FileUtils.constructPath(TEST_DATA_DIRS);
    String jsonTestDataDirPath = FileUtils.addToPath(testDataDirPath, JSON_DIR);
    String filePath = getJsonFilePath(testMethod);
    return FileUtils.getDirFromProjectPath(FileUtils.addToPath(jsonTestDataDirPath, filePath));
  }

  private static String getJsonFilePath(ITestNGMethod testMethod) {
    Method method = testMethod.getConstructorOrMethod().getMethod();
    if (method.isAnnotationPresent(DataProviderDetails.class)) {
      String customFilePath = method.getAnnotation(DataProviderDetails.class).filePath();
      if (!customFilePath.isEmpty()) {
        return customFilePath;
      }
    }
    return getJsonFileDefaultPath(testMethod);
  }

  private static Optional<Class<?>> getClassToMapFromAnnotation(Method testMethod) {
    if (testMethod.isAnnotationPresent(DataProviderDetails.class)) {
      DataProviderDetails dataProviderDetails = testMethod.getAnnotation(DataProviderDetails.class);
      if (!dataProviderDetails.classToMap().getSimpleName().equals("void")) {
        return Optional.of(dataProviderDetails.classToMap());
      }
    }
    return Optional.empty();
  }

  private static String getJsonFileDefaultPath(ITestNGMethod testMethod) {
    Method method = testMethod.getConstructorOrMethod().getMethod();
    String jsonFileDefaultName = method.getName() + JSON_FILE_EXTENSION;
    return FileUtils.addToPath(testMethod.getRealClass().getSimpleName(), jsonFileDefaultName);
  }

  private static Object[] getJsonObjectsArrayFromFile(String jsonFilePath) {
    JsonArray jsonArray = JsonUtils.readJsonArrayFromFile(jsonFilePath);
    return getJsonObjectsArray(jsonArray);
  }

  private static Object[] getJsonObjectsArray(JsonArray jsonArray) {
    return IntStream.range(0, jsonArray.size())
            .mapToObj(i -> jsonArray.get(i).getAsJsonObject())
            .toArray();
  }
}