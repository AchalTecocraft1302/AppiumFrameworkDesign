package appiumproject.pageOjects;

import java.util.List;

import appiumproject.utils.AndroidActions;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;


public class CartPage extends AndroidActions {

	AndroidDriver driver ;
	public CartPage(AndroidDriver driver) {
		    
		    super(driver);
			this.driver = driver;
			PageFactory.initElements(new AppiumFieldDecorator(driver) ,this);
		
	}
	
	 //List<WebElement> productprice = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productprice ;
	
	//driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalpurchaseamount;
	
	//driver.findElement(By.className("android.widget.CheckBox")).click();
	@AndroidFindBy(className =  "android.widget.CheckBox")
	private WebElement checkmarkedElement ;
	
	//driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Please read our terms of conditions']"));
	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement terms ;
	
	//driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='CLOSE']"));
	@AndroidFindBy(id = "android:id/button1")
	private WebElement close ;

	//driver.findElement(By.xpath("//android.widget.Button[@text='Visit to the website to complete purchase']")).click();
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Visit to the website to complete purchase']")
	private WebElement submitPurchasButton  ;

	//WebElement cartTitlElement = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Cart']"));
	@AndroidFindBy(xpath  = "//android.widget.TextView[@text='Cart']")
	private WebElement cartPageTitle ;
	
	
	public double getProductSum() {
		     
		   
		    double sumOftwoProduct = 0;
		    for (WebElement singleproduct : productprice) {
		    	          
		    	          //step by step converting string to double
		    	          String singleprice = singleproduct.getText(); //convert web element into string
		    	          Double dpriceDouble = getStringToDouble(singleprice);  //formatted string by function in MobileAction
		    	         // Double dpriceDouble = Double.parseDouble(singleprice.substring(1)); //convert string into double
		    	          sumOftwoProduct = sumOftwoProduct + dpriceDouble;  //sum of products in available cart
		    	          System.out.println(singleprice+"---> After every iteration,total price is:- "+sumOftwoProduct);		
			}
		    return sumOftwoProduct;
	}
	
	public Double getTotalPurchaseAmountDisplayed() {
		     //One line - convert string to double by method and return double as well (beacuse method return double)
		    return getStringToDouble(totalpurchaseamount.getText());
	}
	
	public void clickOnCheckmarked() {
		 
		checkmarkedElement.click();
		 System.out.println("******************* click on checkmarked ****************");
	}
	
	
	public void clickOnTermsAnsCondition() throws InterruptedException {
		
		
		longPressAction(terms); // long press on terms and condtion
        close.click();
      System.out.println("******************* Closed Terms and condition popup ****************");
	}
	public void clickOnVisitPurchaseButton() {

		submitPurchasButton.click();
		System.out.println("******************* click on 'visit the website to purchase' button ****************");
	}

	public void cartPageTitleWait(){

		waitAttributeContains(cartPageTitle, "text", "Cart"); //Wait method 1
		//waitTextToBePresentInElement(cartPageTitle, "Cart");  //Wait method 2
		//waitVisibiltyOfElement(cartPageTitle); //Wait method 3
		System.out.println("******************* CartPage: Wait method is called ****************");
	}

	
}

//String totalString = totalpurchaseamount.getText();
//Double totalPurchaseAmountDouble = Double.parseDouble(totalString.substring(1));
//System.out.println("******************* Total Purchase Amount is displayed:- " +totalPurchaseAmountDouble);
//return totalPurchaseAmountDouble;