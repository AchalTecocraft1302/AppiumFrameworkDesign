package appiumproject.testcases;

import appiumproject.pageOjects.FormPage;
import appiumproject.pageOjects.ProductCatalogue;
import appiumproject.testutils.SuperBaseClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test03_DDTByDataProvider extends SuperBaseClass {

	@BeforeMethod(alwaysRun = true)
	public void goHomePage() throws InterruptedException {

		//BeforeMethod is used for go to Homepage
		System.out.println("******************* BeforeMethod() start ****************");
		FormPage formPage = new FormPage(driver);
		formPage.setActivity();
		System.out.println("******************* BeforeMethod() finished ****************");
	}

	@Test(dataProvider = "getData",groups = { "Smoke" })
	public void DDTByDataProviderTest03(String country,String name,String gender) {
		
		System.out.println("******************* General Store App Form submit by @DataProvider ****************");
		System.out.println("******************* DDTByDataProviderTest03() is start  ****************");
		   
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			
			FormPage frmPage = new FormPage(driver); //create object of formpage 
			frmPage.countrySelection(country);  //country select from dropdown
			frmPage.setNameField(name); //Enter name in field
			frmPage.setGender(gender); //select Female gender
			
			ProductCatalogue prdCatalogue = frmPage.letsShopButtonclick(); //Implement Page object file for Product Catalogue page with actions

			System.out.println("*******************  DDTByDataProviderTest03() is finished ****************");

     
	}

	@DataProvider
	public Object[][] getData(){

		return new Object[][] { {"Aruba","Achal","Female"} };


	}

}
