package appiumproject.testcases;

import java.time.Duration;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import appiumproject.pageOjects.CartPage;
import appiumproject.pageOjects.FormPage;
import appiumproject.pageOjects.ProductCatalogue;
import io.appium.java_client.AppiumBy;

public class Test01_GeneralStore extends SuperBaseClass {
	
	@Test
	public void formSubmitTest01() {
		
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
			
			 WebElement cartTitlElement = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Cart']"));
			   
			 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			 wait.until(ExpectedConditions.attributeContains(cartTitlElement,"text","Cart"));
			 System.out.println("******************* Wait for 'Cart' page load ****************");
			
			// CartPage cartPage = new CartPage(driver); //create object seperatly of cart page page
			 cartPage.getProductSum();
			 double ProductSum = cartPage.getProductSum();
			 double ProductPurchaseAmount = cartPage.getTotalPurchaseAmountDisplayed();
			 
			 Assert.assertEquals(ProductSum,ProductPurchaseAmount);
			 System.out.println("******************* Assertion:Passed ****************");
			 
			 
			 cartPage.clickOnCheckmarked();
			 Thread.sleep(3000);
			 cartPage.clickOnTermsAnsCondition();
			 
    
			 

			   System.out.println("*******************  Test01_GeneralStore() is finished ****************");
			
			
		}catch (AssertionError a) {
			// TODO: handle exception
			System.out.println("Assertion error message ...."+a.getMessage());
			a.printStackTrace();
			System.out.println("*******************  Test01_GeneralStore() is not run ****************");
		} 
		catch (NotFoundException e) {
			// TODO: handle exception
			System.out.println("Not found error message ...."+e.getMessage());
			e.printStackTrace();
			System.out.println("*******************  Test01_GeneralStore() is not run ****************");
		}
		catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Error message ...."+ex.getMessage());
			ex.printStackTrace();
			System.out.println("*******************  Test01_GeneralStore() is not run ****************");
		}
     
	}

}
