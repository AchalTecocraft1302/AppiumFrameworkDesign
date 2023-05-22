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
		info("BeforeMethod() start");
		FormPage formPage = new FormPage(driver);
		formPage.setActivity();
		info("BeforeMethod() finished");

	}

	@Test(dataProvider = "getData",groups = { "Smoke" })
	public void DDTByDataProviderTest03(String country,String name,String gender) {
		
		info("***** General Store App Form submit by @DataProvider *****");
		info("***** Test03: DDTByDataProviderTest03() is start *****");
		   
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			
			FormPage frmPage = new FormPage(driver); //create object of formpage 
			frmPage.countrySelection(country);  //country select from dropdown
			frmPage.setNameField(name); //Enter name in field
			frmPage.setGender(gender); //select Female gender
			
			ProductCatalogue prdCatalogue = frmPage.letsShopButtonclick(); //Implement Page object file for Product Catalogue page with actions

			info("***** Test03: DDTByDataProviderTest03() is finished *****");

     
	}

	@DataProvider
	public Object[][] getData(){

		return new Object[][] { {"Aruba","Achal","Female"} };


	}

}
