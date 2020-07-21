package resources;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    public WebDriver driver;   // this object will be accessible for entire the test
    public Properties prop;  // this object will be accessible for entire the test

    public WebDriver initialiseDrier() throws IOException {
        prop = new Properties();
        // System.getProperty('user.dir") gives
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
        prop.load(fis);
        /* mvn test -Dbrowser=chrome   this command in mvn will run the tests in Chrome browser
         we don't need the add browser value in Properties file */

     //   String browserName=System.getProperty("browser");  // it will take browser information from mvn command also help for launch through Jenkins
        // no need to write below code to launch browser
        String browserName = prop.getProperty("browser");   // it will take browser information and it taken for dynamic path of drivers
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       return driver;
    }

    public String getscreenshot(String testcaseName, WebDriver driver) throws IOException  //testcaseName we get from Listeners
    {
        TakesScreenshot ts=(TakesScreenshot)driver;
        File src=ts.getScreenshotAs(OutputType.FILE);
        String destinationFile=System.getProperty("user.dir")+"\\reports\\"+testcaseName+".png";
        FileUtils.copyFile(src, new File(destinationFile));
        return destinationFile;  // this method expects path of caputre screenshot
    }



}
