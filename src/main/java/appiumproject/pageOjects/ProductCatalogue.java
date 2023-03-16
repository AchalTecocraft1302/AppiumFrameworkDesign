package appiumproject.pageOjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;


public class ProductCatalogue extends MobileActions{

	AndroidDriver driver ;
	public ProductCatalogue(AndroidDriver driver) {
		    
		    super(driver);
			this.driver = driver;
			PageFactory.initElements(driver,this);
		
	}
	
	//driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
	@FindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addtocart ;
	
	//driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	@FindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement carticon ;
	
	
	
	public void clickOnAddToCartByIndex(int i) throws InterruptedException {
		addtocart.get(i).click();;
		System.out.println("******************* Clicked product ****************");
		
	}
	
	public CartPage clickOnCartIcon() {
		carticon.click();
		 System.out.println("*******************  Clicked on cart button: Go to Cart screen ****************");
        return new CartPage(driver);
	}
	
	

}
