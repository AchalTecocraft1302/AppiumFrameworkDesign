package appiumproject.testcases;

import appiumproject.pageOjects.FormPage;
import appiumproject.pageOjects.ProductCatalogue;
import appiumproject.testutils.SuperBaseClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class Test04_DDTByJason extends SuperBaseClass {

	@BeforeMethod(alwaysRun = true)
	public void goHomePage() throws InterruptedException {

		//BeforeMethod is used for go to Homepage
		System.out.println("******************* BeforeMethod() start ****************");
		FormPage formPage = new FormPage(driver);
		formPage.setActivity();
		System.out.println("******************* BeforeMethod() finished ****************");
	}

	@Test(dataProvider = "getDataProvider",groups = { "Sanity" })
	public void DDTByJasonFile04(HashMap<String ,String> input) {
		
		System.out.println("******************* Part:4 General Store App Form submit by Json File ****************");
		System.out.println("******************* DDTByJsonFile04() is start  ****************");
		   
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			
			FormPage frmPage = new FormPage(driver); //create object of formpage 
			frmPage.countrySelection(input.get("country"));  //country select from dropdown
			System.out.println("******************* Country is selected ****************");
			frmPage.setNameField(input.get("name")); //Enter name in field
			System.out.println("******************* Name is entered ****************");
			frmPage.setGender(input.get("gender")); //select Female gender
			System.out.println("******************* Gender is selected ****************");
			
			ProductCatalogue prdCatalogue = frmPage.letsShopButtonclick(); //Implement Page object file for Product Catalogue page with actions

			System.out.println("*******************  DDTByJsonFile04() is finished ****************");

     
	}

	@DataProvider
	public Object[][] getDataProvider() throws IOException {

		System.out.println("******************* getDataProvider() method is run ****************");

		//create object in superbase class ,use reference variable to call method
		List<HashMap<String ,String>> data = getJsonData(System.getProperty("user.dir")+"/src/test/java/appiumproject/testdata/generalstore.json");
		return new Object[][] { {data.get(0)},{data.get(1)} };
	}


}
