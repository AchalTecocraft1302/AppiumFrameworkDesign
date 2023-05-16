package appiumproject.testutils;

import java.time.Duration;

import appiumproject.utils.AppiumCommonUtils;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

public class SuperBaseClass extends AppiumCommonUtils{
	
	public AndroidDriver driver ;



	@BeforeClass(alwaysRun = true)
	public void Setup() throws Exception {
		try {
			error("SuperBaseclass: Setup() is Start");


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

			info("SuperBaseClass setup is successfully run");

     } catch (Exception e) {
        error("***** SuperBaseclass: Setup() is not run.."+e.getMessage());
     	e.printStackTrace();
     }	 
     
    
	}

	@AfterClass(alwaysRun = true)
	public void teardown() {

		try {
			info("Teardown is start");
			service.close();
			info("Appium server is closed");
		}catch (Exception e){
			error("Appium server is not closing:- "+e.getMessage());
		}

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