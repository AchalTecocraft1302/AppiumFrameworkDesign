package appiumproject.testcases;

import java.time.Duration;

import appiumproject.testutils.SuperBaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appiumproject.pageOjects.CartPage;
import appiumproject.pageOjects.FormPage;
import appiumproject.pageOjects.ProductCatalogue;

public class Test01_GeneralStore extends SuperBaseClass {

	@BeforeMethod(alwaysRun = true)
	public void goHomePage() throws InterruptedException {

		//BeforeMethod is used for go to Homepage
		info("BeforeMethod() start");
		FormPage formPage = new FormPage(driver);
		formPage.setActivity();
		info("BeforeMethod() finished");
	}
	@Test(groups = { "Smoke" })
	public void GeneralStoreTest01() throws InterruptedException {

		System.out.println("***** General Store App Form submit by Page Factory Design Pattern *****");
		info("***** Test01: GeneralStoreTest01() is start *****");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));



			FormPage frmPage = new FormPage(driver); //create object of formpage 
			frmPage.countrySelection("Australia");  //country select from dropdown
			frmPage.setNameField("Achal"); //Enter name in field
			frmPage.setGender("Female"); //select Female gender
			
			ProductCatalogue prdCatalogue = frmPage.letsShopButtonclick(); //Implement Page object file for Product Catalogue page with actions
			
			//ProductCatalogue prdCatalogue = new ProductCatalogue(driver); //create object seperatly of product catologue page
			prdCatalogue.clickOnAddToCartByIndex(0); //click on first product
			prdCatalogue.clickOnAddToCartByIndex(0); //click on second product
			
			CartPage cartPage = prdCatalogue.clickOnCartIcon(); //Implement Page object file for cart page with actions
			// CartPage cartPage = new CartPage(driver); //create object seperatly of cart page page

			//Wait method call
			cartPage.cartPageTitleWait();

			//Simple Wait
			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			//wait.until(ExpectedConditions.attributeContains(cartPage.cartPageTitle,"text","Cart"));

			//cartPage.cartPageTitleWait(); //Wait for 'Cart' page load
			 double ProductSum = cartPage.getProductSum(); //two product sum and store into double
			 double ProductPurchaseAmount = cartPage.getTotalPurchaseAmountDisplayed(); //get purchase amount and store in double
			 
			 Assert.assertEquals(ProductSum,ProductPurchaseAmount);
		     info("Assertion:Passed");
			 
			 
			 cartPage.clickOnCheckmarked();
			 Thread.sleep(3000);
			 cartPage.clickOnTermsAnsCondition();
			 cartPage.clickOnVisitPurchaseButton();

		     info("***** Test01: GeneralStoreTest01() is finished *****");

     
	}

}
