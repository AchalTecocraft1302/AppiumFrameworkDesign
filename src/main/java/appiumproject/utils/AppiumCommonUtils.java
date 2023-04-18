package appiumproject.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public abstract class AppiumCommonUtils {

    public AppiumDriver driver ;  //This grandparent class so AppiumDriver is also parent driver
    public WebDriverWait wait ;
   
    public Double getStringToDouble(String stringtext) {

        Double convertedDouble = Double.parseDouble(stringtext.substring(1));
        System.out.println("******************* AppiumCommonUtils: getStringToDouble() method call for String is into Double *******************");
        return convertedDouble;
    }
    public List<HashMap<String ,String>> getJsonData(String JasonFilePath) throws IOException {

        System.out.println("*******************  getJsonData() is run ****************");
        //convert Json file into Json string
        String Jsoncontent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/src/test/java/appiumproject/testdata/generalstore.json"), StandardCharsets.UTF_8);

        //Convert Json String to HashMap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String ,String>> data = mapper.readValue(Jsoncontent,new TypeReference<List<HashMap<String ,String>>>(){});
        System.out.println("******************* getJsonData(): return json data ****************");
        return data;

    }
    //wait Method 1
    public WebElement waitAttributeContains(WebElement element, String atrribute, String value){

        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.attributeContains(element,atrribute,value));
        System.out.println("******************* Wait: waitAttributeContains is run ****************");
        return element;
    }
    //wait method 2
    public WebElement waitTextToBePresentInElement(WebElement element, String text){

        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.textToBePresentInElement(element,text));
        System.out.println("******************* Wait: TextToBePresentInElement is run ****************");
        return element;
    }
    //wait method 3
    public WebElement waitVisibiltyOfElement(WebElement element){

        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
        System.out.println("******************* Wait: visibilityOfElement is run ****************");
        return element;
    }

}
