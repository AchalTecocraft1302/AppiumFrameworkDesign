package appiumproject.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AppiumCommonUtils {

   AppiumDriver driver ;  //This grandparent class so AppiumDriver is also parent driver
    public AppiumCommonUtils(AndroidDriver driver) {

        this.driver = driver;
    }
    public Double getStringToDouble(String stringtext) {

        Double convertedDouble = Double.parseDouble(stringtext.substring(1));
        System.out.println("******************* AppiumCommonUtils: getStringToDouble() method call for String is into Double *******************");
        return convertedDouble;
    }

    public void waitForElementByAttributeContains(WebElement element , String attibute, String value) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(element,attibute,value));
        System.out.println("******************* AppiumCommonUtils: Wait method is call *******************");
    }



}
