package appiumproject.testcases;

import java.time.Duration;

import appiumproject.testutils.SuperBaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appiumproject.pageOjects.FormPage;
import io.appium.java_client.AppiumBy;

public class Test02_MultipleTests extends SuperBaseClass {
	

	
	//******************* Part- 3 :Test Strategy and TestNG.xml ****************
	@BeforeMethod(alwaysRun = true)
	public void goHomePage() throws InterruptedException {

		//BeforeMethod is used for go to Homepage
		System.out.println("******************* BeforeMethod() start ****************");
		FormPage formPage = new FormPage(driver);
		formPage.setActivity();
		System.out.println("******************* BeforeMethod() finished ****************");
	}
	
	@Test (priority=0,groups = { "Smoke" })
	public void VerifyNameValidation_Test01() {
		
	     
		
		System.out.println("******************* First Test is start ****************");
		System.out.println("******************* VerifyNameValidation_Test01() is start  ****************");
		   
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			FormPage frmPage = new FormPage(driver); //create object of formpage 
			frmPage.countrySelection("Australia");  //country select from dropdown
			frmPage.setGender("Female"); //select Female gender
			frmPage.letsShopButtonclick(); //Implement Page object file for Product Catalogue page with actions
			
			
			 String toastMessage  = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")).getAttribute("text");
			Assert.assertEquals(toastMessage,"Please enter your nameeeeeee");

			System.out.println("************** Assertion:Passed 'Please enter your name' toast is displayed *********************");
			System.out.println("*******************  VerifyNameValidation_Test01() is finished ****************");

     
	}
	
	
	@Test (priority=1,groups = { "Sanity" })
	public void FormSubmit_Test02() throws InterruptedException {
		
		System.out.println("******************* Second Test is start ****************");
		System.out.println("******************* FormSubmit_Test02() is start  ****************");
		   
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

			FormPage frmPage = new FormPage(driver); //create object of formpage
			frmPage.countrySelection("Australia");  //country select from dropdown
			System.out.println("******************* 'Australia' Country is selected ****************");
			frmPage.setNameField("Achal"); //Enter name in field
			frmPage.setGender("Female"); //select Female gender
			frmPage.letsShopButtonclick();
			System.out.println("******************* FormSubmit_Test02() is finished ****************");

	}

}
