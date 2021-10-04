package base;

import java.util.Arrays;
import java.util.Set;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class TestLogUtils {

  public static String getExecutionSummary(ITestContext context) {
    StringBuilder executionSummary = new StringBuilder();
    executionSummary.append("\n\n-------------EXECUTION FINISHED-------------\n")
            .append("All tests: ").append(context.getAllTestMethods().length).append(", ")
            .append("Failures: ").append(context.getFailedTests().size()).append(", ")
            .append("Skips: ").append(context.getSkippedTests().size()).append(", ")
            .append("Passes: ").append(context.getPassedTests().size());
    if (context.getFailedTests().size() > 0) {
      executionSummary.append("\n\nFailed tests: \n")
              .append(getTestNamesWithParameters(context.getFailedTests().getAllResults()));
    }
    if (context.getSkippedTests().size() > 0) {
      executionSummary.append("\nSkipped tests: ")
              .append(getTestNamesWithParameters(context.getSkippedTests().getAllResults()));
    }
    return executionSummary.toString();
  }

  private static String getTestNamesWithParameters(Set<ITestResult> testResultsSet) {
    StringBuilder testNamesWithParameters = new StringBuilder();
    testResultsSet.forEach(t -> testNamesWithParameters
            .append(t.getMethod().getMethodName())
            .append(Arrays.toString(t.getParameters()))
            .append("\n"));
    return testNamesWithParameters.toString();
  }
}
