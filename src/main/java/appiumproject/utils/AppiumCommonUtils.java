package appiumproject.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testinium.deviceinformation.DeviceInfo;
import com.testinium.deviceinformation.DeviceInfoImpl;
import com.testinium.deviceinformation.device.DeviceType;
import com.testinium.deviceinformation.exception.DeviceNotFoundException;
import com.testinium.deviceinformation.model.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.opentelemetry.sdk.trace.internal.data.ExceptionEventData;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public abstract class AppiumCommonUtils {

    public static String projectdir = System.getProperty("user.dir");
    public AppiumDriverLocalService service;
    public AppiumServiceBuilder builder;
    public DesiredCapabilities cap;
    public DeviceInfo deviceInfo ;
    public Device device ;
    public AppiumDriver driver ;  //This grandparent class so AppiumDriver is also parent driver
    public WebDriverWait wait ;
    public File app ;
    public Properties properties;

    //Initialize Log4j instance
    private static final Logger Log = LogManager.getLogger(AppiumCommonUtils.class);

    //Info Level Logs
    public static void info(String message) {
        Log.info(message);
    }

    //Warn Level Logs
    public static void warn(String message) {
        Log.warn(message);
    }

    //Error Level Logs
    public static void error(String message) {
        Log.error("\u001B[133m"+message+"\u001B[133m");
        //"/u001B[31m"
    }

    //Fatal Level Logs
    public static void fatal(String message) {
        Log.fatal(message);
    }

    //Debug Level Logs
    public static void debug(String message) {
        Log.debug(message);
    }



    //E-COMMERCE APP

   
    public Double getStringToDouble(String stringtext) {

        Double convertedDouble = Double.parseDouble(stringtext.substring(1));
        info("getStringToDouble() method call for String into Double");
        return convertedDouble;
    }
    public List<HashMap<String ,String>> getJsonData(String JasonFilePath) throws IOException {

        info("getJsonData() method is call");
        //convert Json file into Json string
        String Jsoncontent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/src/test/java/appiumproject/testdata/generalstore.json"), StandardCharsets.UTF_8);

        //Convert Json String to HashMap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String ,String>> data = mapper.readValue(Jsoncontent,new TypeReference<List<HashMap<String ,String>>>(){});
        info("getJsonData(): method return json data successfully");
        return data;

    }
    //wait Method 1
    public WebElement waitAttributeContains(WebElement element, String atrribute, String value){

        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.attributeContains(element,atrribute,value));
        info("Wait: waitAttributeContains is run");
        return element;
    }
    //wait method 2
    public WebElement waitTextToBePresentInElement(WebElement element, String text){

        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.textToBePresentInElement(element,text));
        info("Wait: TextToBePresentInElement is run");

        return element;
    }
    //wait method 3
    public WebElement waitVisibiltyOfElement(WebElement element){

        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
        info("Wait: visibilityOfElement is run");
        return element;
    }

    public AppiumDriverLocalService startAppiumServer(String ipaddress,String basepath,String port,String loglevel){

        try {
            info("startAppiumServer() method is call");
            //Build the Appium service
            builder = new AppiumServiceBuilder();
            //npm main.js file attached beacuase jenkins get error
            builder.withAppiumJS(new File("C:\\Users\\Achal Trivedi\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"));
            builder.withIPAddress(ipaddress);
            builder.withArgument(() -> "--port", port);
            builder.withArgument(GeneralServerFlag.BASEPATH, basepath);
            builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
            builder.withArgument(GeneralServerFlag.LOG_LEVEL,loglevel);
            builder.build();

            //Start the Appium server with the builder
            service = AppiumDriverLocalService.buildService(builder);
            service.start();
            info("Appium Server Started via Java");

        }catch (Exception e){
            error("***** Appium server is not started : "+e.getMessage());
           // System.out.println(" ***** Appium server is not started :- "+e.getMessage());
        }
        return service;
    }

    public void getCapabilities(File app) throws DeviceNotFoundException, IOException {

       try {
           info("getCapabilities() method is call");
           cap = new DesiredCapabilities();
           deviceInfo = new DeviceInfoImpl(DeviceType.ANDROID);
           deviceInfo.anyDeviceConnected();
           device = deviceInfo.getFirstDevice();

           info("Platform Name - " + device.getDeviceProductName());
           info("Device UDID   - " + device.getUniqueDeviceID());
           info("Product Verison - " + device.getProductVersion());
           info("Model Number   - " + device.getModelNumber());
           info("Buld Verison   - " + device.getBuildVersion());

           cap.setCapability(MobileCapabilityType.PLATFORM_NAME,device.getDeviceProductName());
           cap.setCapability(MobileCapabilityType.UDID,device.getUniqueDeviceID());
           cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,device.getProductVersion());
           cap.setCapability(MobileCapabilityType.DEVICE_NAME,device.getModelNumber());

           cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,60);

           cap.setCapability("automationName", "UiAutomator2");
           cap.setCapability(MobileCapabilityType.APP,app.getPath());

           cap.setCapability("autoGrantPermissions", true);
           cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);

           info("Capabilities are successfully taken");
       }catch (Exception e){
           error("***** Capabilities not get, check device adb connection:- "+e.getMessage());
       }


    }
    public String getScreenshotPath(String testcasename, AppiumDriver driver) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = projectdir + "/src/test/java/appiumproject/testreports/"+testcasename+".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }

    public File setAPKname(String apkname){

        try {
            app = new File( projectdir +"/src/test/resources/apps/"+apkname+".apk");
            info("Set APK file name is:- "+apkname);

            //General-Store
        }catch (Exception e){
            error("***** APK file is not set, check filename or present or not:- "+e.getMessage());
        }
        return app;
    }

    public static void disableWarning() {
        //How to hide warning "Illegal reflective access"
        warn("--- System warning is disable ---");
        System.err.close();
        System.setErr(System.out);
    }

    public void setupPropertiesLoad() throws IOException {
        properties = new Properties();
        FileInputStream fis = new FileInputStream(projectdir+"/src/main/java/appiumproject/resources/data.properties");
        properties.load(fis);
        info("Properties file is loaded by setupPropertiesLoad()");
    }

}
