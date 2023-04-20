package appiumproject.testutils;

import appiumproject.testcases.SuperBaseClass;
import appiumproject.utils.AppiumCommonUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager extends AppiumCommonUtils {

    public static ExtentReports extentReports = new ExtentReports();

    public static ExtentReports createExtentReports(){
        ExtentSparkReporter reporter = new ExtentSparkReporter(projectdir +"/src/test/java/appiumproject/testreports/extent-report.html");
        reporter.config().setReportName("Sample Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Blog Name", "SW Test Academy");
        extentReports.setSystemInfo("Author", "Onur Baskirt");
        return extentReports;

    }

}
