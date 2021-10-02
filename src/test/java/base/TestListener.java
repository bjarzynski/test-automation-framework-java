package base;

import java.util.Arrays;
import java.util.Set;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
  private static final Logger logger = Logger.getLogger(TestListener.class);

  @Override
  public void onTestStart(ITestResult result) {
    ITestListener.super.onTestStart(result);
    logger.info("Starting test: " + result.getName() + Arrays.toString(result.getParameters()));
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    ITestListener.super.onTestSuccess(result);
    logger.info("PASSED: " + result.getName() + Arrays.toString(result.getParameters()));
  }

  @Override
  public void onTestFailure(ITestResult result) {
    ITestListener.super.onTestFailure(result);
    logger.info("FAILED: " + result.getName() + Arrays.toString(result.getParameters()));
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    ITestListener.super.onTestSkipped(result);
    logger.info("SKIPPED: " + result.getName() + Arrays.toString(result.getParameters()));
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    logger.info("Test within success percentage: "
            + result.getName() + Arrays.toString(result.getParameters()));
  }

  @Override
  public void onTestFailedWithTimeout(ITestResult result) {
    ITestListener.super.onTestFailedWithTimeout(result);
    logger.info("FAILED with timeout: "
            + result.getName() + Arrays.toString(result.getParameters()));
  }

  @Override
  public void onStart(ITestContext context) {
    ITestListener.super.onStart(context);
    logger.info("\n\n-------------STARTING EXECUTION-------------\n");
  }

  @Override
  public void onFinish(ITestContext context) {
    logger.info(getExecutionSummary(context));
  }

  private String getExecutionSummary(ITestContext context) {
    ITestListener.super.onFinish(context);
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

  private String getTestNamesWithParameters(Set<ITestResult> testResultsSet) {
    StringBuilder testNamesWithParameters = new StringBuilder();
    testResultsSet.forEach(t -> testNamesWithParameters
            .append(t.getMethod().getMethodName())
            .append(Arrays.toString(t.getParameters()))
            .append("\n"));
    return testNamesWithParameters.toString();
  }
}
