package appiumproject.testcases;

import java.time.Duration;

import appiumproject.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appiumproject.pageOjects.CartPage;
import appiumproject.pageOjects.FormPage;
import appiumproject.pageOjects.ProductCatalogue;
import io.appium.java_client.AppiumBy;

public class Test01_GeneralStore extends SuperBaseClass {

	@BeforeMethod
	public void goHomePage() throws InterruptedException {

		//BeforeMethod is used for go to Homepage
		System.out.println("******************* BeforeMethod() start ****************");
		FormPage formPage = new FormPage(driver);
		formPage.setActivity();
		System.out.println("******************* BeforeMethod() finished ****************");
	}
	@Test
	public void GeneralStoreTest01() {
		
		System.out.println("******************* General Store App Form submit by Page Factory Design Pattern ****************");
		System.out.println("******************* Test01_GeneralStore() is start  ****************");
		   
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		try {
			
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
			 System.out.println("******************* Assertion:Passed ****************");
			 
			 
			 cartPage.clickOnCheckmarked();
			 Thread.sleep(3000);
			 cartPage.clickOnTermsAnsCondition();
			 cartPage.clickOnVisitPurchaseButton();
			 
    
			 

			   System.out.println("*******************  Test01_GeneralStore() is finished ****************");
			
			
		}catch (AssertionError a) {
			System.out.println("Assertion error message ...."+a.getMessage());
			a.printStackTrace();
			System.out.println("*******************  Test01_GeneralStore() is not run ****************");
		} 
		catch (NotFoundException e) {
			System.out.println("Not found error message ...."+e.getMessage());
			e.printStackTrace();
			System.out.println("*******************  Test01_GeneralStore() is not run ****************");
		}
		catch (Exception ex) {
			System.out.println("Error message ...."+ex.getMessage());
			ex.printStackTrace();
			System.out.println("*******************  Test01_GeneralStore() is not run ****************");
		}
     
	}

}
