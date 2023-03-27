package appiumproject.utils;

import io.appium.java_client.android.Activity;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AndroidActions extends AppiumCommonUtils{
	public Activity activity ;
	AndroidDriver driver ;

	
	public AndroidActions(AndroidDriver driver) {

		super(driver);
		this.driver = driver;
	}
	
	public void scrollToCountry(String text) {
		
		 driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))")).click();
		  System.out.println("****** List is scrolled and item is selected from list ********");
	
	}

	public void longPressAction(WebElement element) throws InterruptedException {
		
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",ImmutableMap.of("elementId",((WebElement) element),"duration",2000));
		 System.out.println("******************* longpress action run ****************");
		 Thread.sleep(3000);
		 //RemoteWebElment is not cast with @FindBy so it change to WebElement
	}


}
