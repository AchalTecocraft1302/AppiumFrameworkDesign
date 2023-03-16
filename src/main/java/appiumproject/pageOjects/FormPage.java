package appiumproject.pageOjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;


public class FormPage extends MobileActions{

	AndroidDriver driver ;
	public FormPage(AndroidDriver driver) {
		    
		    super(driver);
			this.driver = driver;
			PageFactory.initElements(driver,this);
		
	}

	
	
	//driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Enter name here']")).sendKeys("Achal");
	@FindBy(xpath = "//android.widget.EditText[@text='Enter name here']")
	private WebElement nameField;
	
	 //driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
	@FindBy(xpath = "//android.widget.RadioButton[@text='Female']")
	private WebElement femaleGender;
	
	@FindBy(xpath = "//android.widget.RadioButton[@text='Male']")
	private WebElement maleGender;
	
//	 driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Afghanistan']")).click();
	@FindBy(xpath = "//android.widget.TextView[@text='Afghanistan']")
	private WebElement countryField;
   
//	   driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.Button")).click();
	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.Button")
	private WebElement letShopButton;
	
	
	
	
	public void countrySelection(String country) {
		
		countryField.click();
		System.out.println("****** Clicked on country dropdown list ********");
	    scrollToCountry(country);
		
	}
	
	
	public void setNameField(String name) {
		
		nameField.sendKeys(name);
		driver.hideKeyboard();
		System.out.println("****** Entered name in 'Your Name' field ********");
		 System.out.println("****** Keyboard is hide ********");
	}
	
	public void setGender(String gender) {
		
		if(gender.contains("Female")) {
		     femaleGender.click();
		     System.out.println("****** Gender 'Female' is selected ********");
		}    
		else 
			maleGender.click();
	}
	
	public ProductCatalogue letsShopButtonclick() {
		
		letShopButton.click();
		System.out.println("****** Clicked on 'Let's shop' to submit form ********");
		//Implement Page object file for Product Catalogue page with actions
		//return as product catalogue page object
		return new ProductCatalogue(driver);
	}

}
