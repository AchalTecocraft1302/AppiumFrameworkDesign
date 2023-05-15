package appiumproject.testutils;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import appiumproject.testutils.logs.Log;
import appiumproject.utils.AppiumCommonUtils;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;
import com.testinium.deviceinformation.DeviceInfo;
import com.testinium.deviceinformation.DeviceInfoImpl;
import com.testinium.deviceinformation.device.DeviceType;
import com.testinium.deviceinformation.model.Device;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.testng.annotations.*;

public class SuperBaseClass extends AppiumCommonUtils{
	
	public AndroidDriver driver ;



	@BeforeClass(alwaysRun = true)
	public void Setup() throws Exception {
		try {
			Log.info("*********** SuperBaseclass: BeforeTest is Start ******************");
			System.out.println("*********** SuperBaseclass: BeforeTest is Start ******************");

			setupPropertiesLoad();

			String ipaddress = System.getProperty("ipaddress") != null ?  System.getProperty("ipaddress") : properties.getProperty("ipaddress");
			String basepath = properties.getProperty("basepath");
			String port = properties.getProperty("port");
			String loglevel = System.getProperty("loglevel") != null ? System.getProperty("loglevel") : properties.getProperty("loglevel");

			service = startAppiumServer(ipaddress,basepath,port,loglevel);

			app = setAPKname("General-Store"); //ApiDemos-debug

			getCapabilities(app);

		     //URL url = new URL("http://127.0.0.1:4723/wd/hub");
		    driver = new AndroidDriver(service,cap); //call service here after appium server start by service.start()
			// implicitwait
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			disableWarning(); //Hide warning "Illegal reflective access"

			System.out.println("******************  SuperBaseClass setup is successfully run ****************");

     } catch (Exception e) {

     	System.out.println("SuperBaseclass error message is ...."+e.getMessage());
     	e.printStackTrace();
     	System.out.println("******************  SuperBaseClass setup is not run ****************");
     }	 
     
    
	}

	@AfterClass(alwaysRun = true)
	public void teardown() {
	
		System.out.println("******* Teardown is start ********");
		service.close();
		System.out.println("******* Appium Server Closed *******");
		 
	
	}
	
//	@Test
//	public void test() {
//
//		System.out.println("SuperBaseclass Test is run....");
//
//	}

	
}

//Socket Hung Up issue command
//adb uninstall io.appium.uiautomator2.server
//adb uninstall io.appium.uiautomator2.server.test