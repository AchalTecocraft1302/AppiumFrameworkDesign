package appiumproject.testutils;

import appiumproject.utils.AppiumCommonUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager extends AppiumCommonUtils {

    public static ExtentReports extentReports = new ExtentReports();

    public static ExtentReports createExtentReports(){
        ExtentSparkReporter reporter = new ExtentSparkReporter(projectdir +"/src/test/java/appiumproject/testreports/ExtentHtmlReport.html");
        reporter.config().setReportName("Sample Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Project", "General Store");
        extentReports.setSystemInfo("QA Name", "Achal");
        return extentReports;

    }

}
