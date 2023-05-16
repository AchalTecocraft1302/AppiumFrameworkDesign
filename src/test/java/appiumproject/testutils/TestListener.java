package appiumproject.testutils;

import com.aventstack.extentreports.App;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
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
        try {
        driver = (AppiumDriver) iTestResult.getTestClass().getRealClass().getField("driver").get(iTestResult.getInstance());

        }catch (Exception e){
        e.printStackTrace();
        }

     try {
       test.addScreenCaptureFromPath(getScreenshotPath(iTestResult.getMethod().getMethodName(), driver),iTestResult.getMethod().getMethodName());
         System.out.println("******************  Listener second try block ****************");
     }catch (Exception e1){
        e1.printStackTrace();
     }
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
