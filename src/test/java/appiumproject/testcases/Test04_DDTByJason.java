package appiumproject.testcases;

import appiumproject.pageOjects.FormPage;
import appiumproject.pageOjects.ProductCatalogue;
import appiumproject.utils.AppiumCommonUtils;
import org.openqa.selenium.NotFoundException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class Test04_DDTByJason extends SuperBaseClass {

	@BeforeMethod
	public void goHomePage() throws InterruptedException {

		//BeforeMethod is used for go to Homepage
		System.out.println("******************* BeforeMethod() start ****************");
		FormPage formPage = new FormPage(driver);
		formPage.setActivity();
	}

	@Test(dataProvider = "getData")
	public void DDTByJasonFile04(HashMap<String ,String> input) {
		
		System.out.println("******************* Part:4 General Store App Form submit by Json File ****************");
		System.out.println("******************* DDTByJsonFile04() is start  ****************");
		   
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		

		try {
			
			FormPage frmPage = new FormPage(driver); //create object of formpage 
			frmPage.countrySelection(input.get("country"));  //country select from dropdown
			System.out.println("******************* Country is selected ****************");
			frmPage.setNameField(input.get("name")); //Enter name in field
			System.out.println("******************* Name is entered ****************");
			frmPage.setGender(input.get("gender")); //select Female gender
			System.out.println("******************* Gender is selected ****************");
			
			ProductCatalogue prdCatalogue = frmPage.letsShopButtonclick(); //Implement Page object file for Product Catalogue page with actions

			System.out.println("*******************  DDTByJsonFile04() is finished ****************");
			
			
		}catch (AssertionError a) {
			System.out.println("Assertion error message ...."+a.getMessage());
			a.printStackTrace();
			System.out.println("******************* DDTByJsonFile04() is not run ****************");
		} 
		catch (NotFoundException e) {
			System.out.println("Not found error message ...."+e.getMessage());
			e.printStackTrace();
			System.out.println("******************* DDTByJsonFile04() is not run ****************");
		}
		catch (Exception ex) {
			System.out.println("Error message ...."+ex.getMessage());
			ex.printStackTrace();
			System.out.println("******************* DDTByJsonFile04() is not run ****************");
		}
     
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		System.out.println("*******************  getData() is run ****************");

		//create object in superbase class ,use reference variable to call method
		List<HashMap<String ,String>> data = commonUtils.getJsonData(System.getProperty("user.dir")+"/src/test/java/appiumproject/testdata/generalstore.json");
		return new Object[][] { {data.get(0)},{data.get(1)} };
	}


}
