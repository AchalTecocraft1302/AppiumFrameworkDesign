package appiumproject.testcases;

import java.io.File;
import java.time.Duration;

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
	// public static AppiumDriver driver = null;


	//public static IOSDriver mobiledriver;
	
	@BeforeClass
	public void Setup() throws Exception {


        System.out.println("*********** SuperBaseclass: BeforeTest is Start ******************");
        service = startAppiumServer();
		getCapabilities();
		//URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver(service,cap); //call service here after appium server start by service.start()

      //API DEMOS APP  
   	 //File app= new File("C:/Users/Achal Trivedi/eclipse-workspace/AppiumUdemyDemo/src/test/resources/apps/ApiDemos-debug.apk");
   	 

       System.out.println("********************** Project directory:-" +projectdir);


	
		
     try {
            

     			
     	    	//implicitwait 
     	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
     				
     		    System.out.println("******************  SuperBaseClass setup is successfully run ****************"); 
     		    
     } catch (Exception e) {

     	System.out.println("Error cause message ...."+e.getMessage());
     	e.printStackTrace();
     	System.out.println("******************  Device is not connected or please check your device ****************"); 
     	// TODO: handle exception
     }	 
     
    
	}

	@AfterClass
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

