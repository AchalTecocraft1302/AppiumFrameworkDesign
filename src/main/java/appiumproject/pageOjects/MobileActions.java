package appiumproject.pageOjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class MobileActions {
	AndroidDriver driver ;
	
	public MobileActions(AndroidDriver driver) {
		
			this.driver = driver;
			PageFactory.initElements(driver,this);
		
	}
	
	public void scrollToCountry(String text) {
		
		 driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))")).click();
		  System.out.println("****** List is scrolled Australia is selected from list ********");
	
	}
  
	public Double getStringToDouble(String stringtext) {

		 Double convertedDouble = Double.parseDouble(stringtext.substring(1));
		 System.out.println("****** String is converted into Double ,take substring after first char($ sign removed) ********");
		 return convertedDouble;
	}
	
	public void longPressAction(WebElement element) throws InterruptedException {
		
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",ImmutableMap.of("elementId",((WebElement) element),"duration",2000));
		 System.out.println("******************* longpress run run ****************");
		 Thread.sleep(3000);
		 //RemoteWebElment is not cast with @FindBy so it change to WebElement
	}
	
}
