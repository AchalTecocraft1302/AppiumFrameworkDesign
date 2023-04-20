package appiumproject.testcases.testutils;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends ExtentReportsManager implements ITestListener {

    ExtentTest test ;

    @Override
    public void onFinish(ITestContext iTestContext) {
        extentReports.flush();
    }
    @Override
    public void onTestStart(ITestResult iTestResult) {
      test = extentReports.createTest(iTestResult.getMethod().getMethodName());
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        test.log(Status.PASS, "Test Passed");
    }
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        test.fail(iTestResult.getThrowable());

    }
    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }


}
