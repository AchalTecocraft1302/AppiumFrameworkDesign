package appiumproject.testutils;

import appiumproject.testcases.testutils.ExtentReportsManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Objects;

public class TestListener extends ExtentManager implements ITestListener {
    ExtentReports extentReports = ExtentManager.createExtentReports();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult iTestResult) {
     test = extentReports.createTest(iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
     test.log(Status.PASS, "Test is Passed");
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

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extentReports.flush();
    }

}
