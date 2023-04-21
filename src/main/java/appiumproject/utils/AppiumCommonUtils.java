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
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public abstract class AppiumCommonUtils {

    public static String projectdir = System.getProperty("user.dir");
    public AppiumDriverLocalService service;
    public AppiumServiceBuilder builder;
    public DesiredCapabilities cap;
    public DeviceInfo deviceInfo ;
    public Device device ;
    public AppiumDriver driver ;  //This grandparent class so AppiumDriver is also parent driver
    public WebDriverWait wait ;

    //E-COMMERCE APP
    public File app= new File( projectdir +"/src/test/resources/apps/General-Store.apk");
   
    public Double getStringToDouble(String stringtext) {

        Double convertedDouble = Double.parseDouble(stringtext.substring(1));
        System.out.println("******************* AppiumCommonUtils: getStringToDouble() method call for String is into Double *******************");
        return convertedDouble;
    }
    public List<HashMap<String ,String>> getJsonData(String JasonFilePath) throws IOException {

        System.out.println("*******************  getJsonData() is run ****************");
        //convert Json file into Json string
        String Jsoncontent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/src/test/java/appiumproject/testdata/generalstore.json"), StandardCharsets.UTF_8);

        //Convert Json String to HashMap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String ,String>> data = mapper.readValue(Jsoncontent,new TypeReference<List<HashMap<String ,String>>>(){});
        System.out.println("******************* getJsonData(): return json data ****************");
        return data;

    }
    //wait Method 1
    public WebElement waitAttributeContains(WebElement element, String atrribute, String value){

        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.attributeContains(element,atrribute,value));
        System.out.println("******************* Wait: waitAttributeContains is run ****************");
        return element;
    }
    //wait method 2
    public WebElement waitTextToBePresentInElement(WebElement element, String text){

        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.textToBePresentInElement(element,text));
        System.out.println("******************* Wait: TextToBePresentInElement is run ****************");
        return element;
    }
    //wait method 3
    public WebElement waitVisibiltyOfElement(WebElement element){

        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
        System.out.println("******************* Wait: visibilityOfElement is run ****************");
        return element;
    }

    public AppiumDriverLocalService startAppiumServer(){

        //Build the Appium service
        builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.withArgument(() -> "--port", "4723");
        builder.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub");
        //127.0.0.1 is the  localhost normally resolves to the IPv4  127.0.0.1
        builder.usingPort(4723); //Appium default port
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL,"debug");
        builder.build();

        //Start the Appium server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        System.out.println("**********************Appium Server Started via Java ************************************************");
        return service;
    }

    public void getCapabilities() throws DeviceNotFoundException, IOException {
        cap = new DesiredCapabilities();
        deviceInfo = new DeviceInfoImpl(DeviceType.ANDROID);
        deviceInfo.anyDeviceConnected();
        device = deviceInfo.getFirstDevice();

        System.out.println("Platform Name - " + device.getDeviceProductName());
        System.out.println("Device UDID   - " + device.getUniqueDeviceID());
        System.out.println("Product Verison - " + device.getProductVersion());
        System.out.println("Model Number   - " + device.getModelNumber());
        System.out.println("Buld Verison   - " + device.getBuildVersion());



        cap.setCapability(MobileCapabilityType.PLATFORM_NAME,device.getDeviceProductName());
        System.out.println("******************  get Platform name ****************");
        cap.setCapability(MobileCapabilityType.UDID,device.getUniqueDeviceID());
        System.out.println("******************  get UDID ****************");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,device.getProductVersion());
        System.out.println("******************  get Platform Verison ****************");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME,device.getModelNumber());
        System.out.println("******************  get device name (Model Number) ****************");

        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,60);

        cap.setCapability("automationName", "UiAutomator2");
        cap.setCapability(MobileCapabilityType.APP,app.getPath());

        cap.setCapability("autoGrantPermissions", true);
        cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);




        System.out.println("******************  capabilities are successfully taken ****************");

    }
    public String getScreenshotPath(String testcasename, AppiumDriver driver) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = projectdir + "/src/test/java/appiumproject/testreports/"+testcasename+".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }
}
