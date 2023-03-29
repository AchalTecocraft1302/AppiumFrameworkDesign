package appiumproject.testcases;

import com.google.common.collect.ImmutableMap;
import com.testinium.deviceinformation.DeviceInfo;
import com.testinium.deviceinformation.DeviceInfoImpl;
import com.testinium.deviceinformation.device.DeviceType;
import com.testinium.deviceinformation.model.Device;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class BaseClass {
	
	public AndroidDriver driver ;
	// public static AppiumDriver driver = null;
	public static AppiumDriverLocalService service = null;
	public static AppiumServiceBuilder builder ;
	public static DeviceInfo deviceInfo ;
	public static Device device ;
	public String projectdir = System.getProperty("user.dir");
	public File app= new File( projectdir +"/src/test/resources/apps/General-Store.apk");  //E-commerce app
	//File app= new File("C:/Users/Achal Trivedi/eclipse-workspace/AppiumUdemyDemo/src/test/resources/apps/ApiDemos-debug.apk"); //APIDEMOS app


	@BeforeTest
	public void Setup() throws Exception {
		System.out.println("****************** Baseclass: BeforeTest is Start ******************");

		startAppium(); //start appium server
		getAutoCapabilites(); //get automatically capabilities by call method

		System.out.println("****************** BaseClass setup is successfully run ****************");
	}

	@AfterTest
	public void teardown() {
	
		System.out.println("******* Teardown is start ********");
		service.close();
		System.out.println("******* Appium Server Closed *******");

	}
	
	@Test
	public void test() {

		System.out.println("Baseclass Test is run....");
	}

	public void startAppium(){

		System.out.println("*********** startAppium() is Start ******************");
		//Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub");
		builder.withArgument(() -> "--port", "4723");
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"debug");
		builder.build();

		//Start the Appium server with the builder
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		System.out.println("**********************Appium Server Started via Java ************************************************");

	}
	public void getAutoCapabilites(){

		try {
			DesiredCapabilities cap = new DesiredCapabilities();

			deviceInfo = new DeviceInfoImpl(DeviceType.ANDROID);
			deviceInfo.anyDeviceConnected();
			device = deviceInfo.getFirstDevice();

			System.out.println("Platform Name - " + device.getDeviceProductName());
			System.out.println("Device UDID   - " + device.getUniqueDeviceID());
			System.out.println("Product Verison - " + device.getProductVersion());
			System.out.println("Model Number   - " + device.getModelNumber());
			System.out.println("Buld Verison   - " + device.getBuildVersion());

			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, device.getDeviceProductName());
			System.out.println("******************  get Platform name ****************");
			cap.setCapability(MobileCapabilityType.UDID, device.getUniqueDeviceID());
			System.out.println("******************  get UDID ****************");
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.getProductVersion());
			System.out.println("******************  get Platform Verison ****************");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, device.getModelNumber());
			System.out.println("******************  get device name (Model Number) ****************");

			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);

			cap.setCapability("automationName", "UiAutomator2");
			cap.setCapability(MobileCapabilityType.APP, app.getPath());

			cap.setCapability("autoGrantPermissions", true);
			cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);

			//URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AndroidDriver(service, cap); //call service here after appium server start by service.start()

			//implicitwait
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			System.out.println("******************  capabilities are successfully taken ****************");

		} catch (Exception e) {

			System.out.println("Error cause message ...." + e.getMessage());
			e.printStackTrace();
			System.out.println("******************  Device is not connected or please check your device ****************");
			// TODO: handle exception
		}
	}

}

