package appiumproject.testcases.testutils;

import appiumproject.testcases.SuperBaseClass;
import appiumproject.utils.AppiumCommonUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsManager extends AppiumCommonUtils {
    public static ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReports() {
        String Extentreportdir = System.getProperty("user.dir")+"/src/test/java/appiumproject/testreports/extent-report.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(Extentreportdir);
        reporter.config().setReportName("Achal- Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Blog Name", "Appium Frameword");
        extentReports.setSystemInfo("Author", "Achal");
        return extentReports;
    }
}
