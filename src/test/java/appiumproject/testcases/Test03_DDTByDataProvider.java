package appiumproject.testcases;

import appiumproject.pageOjects.CartPage;
import appiumproject.pageOjects.FormPage;
import appiumproject.pageOjects.ProductCatalogue;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.NotFoundException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test03_DDTByDataProvider extends SuperBaseClass {

	@BeforeMethod
	public void goHomePage() throws InterruptedException {

		//BeforeMethod is used for go to Homepage
		System.out.println("******************* BeforeMethod() start ****************");
		FormPage formPage = new FormPage(driver);
		formPage.setActivity();
	}

	@Test(dataProvider = "getData")
	public void DDTByDataProviderTest03(String country,String name,String gender) {
		
		System.out.println("******************* General Store App Form submit by @DataProvider ****************");
		System.out.println("******************* DDTByDataProviderTest03() is start  ****************");
		   
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		

		try {
			
			FormPage frmPage = new FormPage(driver); //create object of formpage 
			frmPage.countrySelection(country);  //country select from dropdown
			frmPage.setNameField(name); //Enter name in field
			frmPage.setGender(gender); //select Female gender
			
			ProductCatalogue prdCatalogue = frmPage.letsShopButtonclick(); //Implement Page object file for Product Catalogue page with actions

			System.out.println("*******************  DDTByDataProviderTest03() is finished ****************");
			
			
		}catch (AssertionError a) {
			// TODO: handle exception
			System.out.println("Assertion error message ...."+a.getMessage());
			a.printStackTrace();
			System.out.println("*******************  DDTByDataProviderTest03() is not run ****************");
		} 
		catch (NotFoundException e) {
			// TODO: handle exception
			System.out.println("Not found error message ...."+e.getMessage());
			e.printStackTrace();
			System.out.println("*******************  DDTByDataProviderTest03() is not run ****************");
		}
		catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Error message ...."+ex.getMessage());
			ex.printStackTrace();
			System.out.println("*******************  DDTByDataProviderTest03() is not run ****************");
		}
     
	}

	@DataProvider
	public Object[][] getData(){

		return new Object[][] { {"Aruba","Achal","Female"},{"Bahamas","kumar","Male"} };


	}

}
