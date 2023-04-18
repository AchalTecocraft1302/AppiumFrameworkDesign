package testutils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public static ExtentReports getReporterObjects() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/src/test/java/appiumproject/testreports/extent-report.html");
        reporter.config().setReportName("Achal- Sample Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Blog Name", "Appium FrameWork Design");
        extentReports.setSystemInfo("Author", "Achal");
        return extentReports;
    }
}
