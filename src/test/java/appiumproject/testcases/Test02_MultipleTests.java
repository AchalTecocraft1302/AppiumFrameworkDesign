package appiumproject.testcases;

import java.time.Duration;
import org.openqa.selenium.NotFoundException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appiumproject.pageOjects.CartPage;
import appiumproject.pageOjects.FormPage;
import appiumproject.pageOjects.ProductCatalogue;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Test02_MultipleTests extends SuperBaseClass {
	
	public Activity activity ;
	
	//******************* Part- 3 :Test Strategy and TestNG.xml ****************
	@BeforeMethod
	public void goHomePage() throws InterruptedException {
		
		//BeforeMethod is used for go to Homepage
		System.out.println("******************* BeforeMethod() start ****************");
		
		 activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.SplashActivity");
		 
		 System.out.println("******************* get Package before start activity ****************"+activity.getAppPackage());
		 System.out.println("******************* get Activity before start activity ****************"+activity.getAppActivity());
		 
		 driver.startActivity(activity); //start activity 
		 System.out.println("******************* start activity run ****************");
		 
		 activity.getAppWaitActivity();
		 activity.setAppWaitActivity("com.androidsample.generalstore.MainActivity");
		 System.out.println("******************* set App wait Activity run ****************");
		 
		 System.out.println("******************* get Package after start activity ****************"+activity.getAppPackage());
		 System.out.println("******************* get Activity after start activity ****************"+activity.getAppActivity());
		 
		 System.out.println("******************* BeforeMethod() finished ****************");
		 
	}
	
	@Test (priority=1)
	public void VerifyNameValidation_Test01() {
		
	     
		
		System.out.println("******************* First Test is start ****************");
		System.out.println("******************* VerifyNameValidation_Test01() is start  ****************");
		   
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		try {
			
			FormPage frmPage = new FormPage(driver); //create object of formpage 
			frmPage.countrySelection("Australia");  //country select from dropdown
			frmPage.setGender("Female"); //select Female gender
			frmPage.letsShopButtonclick(); //Implement Page object file for Product Catalogue page with actions
			
			
			 String toastMessage  = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")).getAttribute("text");
			Assert.assertEquals(toastMessage,"Please enter your name");
			System.out.println("************** Assertion:Passed 'Please enter your name' toast is displayed *********************");
			
	

			System.out.println("*******************  VerifyNameValidation_Test01() is finished ****************");
			
			
		}catch (AssertionError a) {
			System.out.println("Assertion error message ...."+a.getMessage());
			a.printStackTrace();
			System.out.println("*******************  VerifyNameValidation_Test01() is not run: Assertion:Failed ****************");
		} 
		catch (Exception ex) {
			System.out.println("Error message ...."+ex.getMessage());
			ex.printStackTrace();
			System.out.println("******************* VerifyNameValidation_Test01() is not run ****************");
		}
     
	}
	
	
	@Test (priority=0)
	public void FormSubmit_Test02() {
		
		System.out.println("******************* Second Test is start ****************");
		System.out.println("******************* FormSubmit_Test02() is start  ****************");
		   
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		try {
			
			FormPage frmPage = new FormPage(driver); //create object of formpage 
			frmPage.countrySelection("Australia");  //country select from dropdown
			frmPage.setGender("Female"); //select Female gender
			frmPage.setNameField("Achal");
			frmPage.letsShopButtonclick(); //Implement Page object file for Product Catalogue page with actions
			System.out.println("******************* FormSubmit_Test02() is finished ****************");
			
			
		}catch (AssertionError a) {
			System.out.println("Assertion error message ...."+a.getMessage());
			a.printStackTrace();
			System.out.println("*******************  FormSubmit_Test02() is not run: Assertion:Failed ****************");
		} 
		catch (Exception ex) {
			System.out.println("Error message ...."+ex.getMessage());
			ex.printStackTrace();
			System.out.println("*******************  FormSubmit_Test02() is not run ****************");
		}
     
	}

}
