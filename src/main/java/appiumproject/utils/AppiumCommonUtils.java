package appiumproject.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
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

public class AppiumCommonUtils {

   AppiumDriver driver ;  //This grandparent class so AppiumDriver is also parent driver
    public AppiumCommonUtils(AppiumDriver driver) {

        this.driver = this.driver;
    }
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



}
