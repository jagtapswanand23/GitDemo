package Tests;

import org.apache.logging.log4j.*;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LoginLandingPage;
import resources.Base;

import java.io.IOException;

public class validateNavigationBar extends Base {
    public WebDriver driver;   // this will execute test in parallel mode , run the driver in local mode
    public static Logger log = LogManager.getLogger(Test.class.getName());
    @BeforeTest
    public void setup() throws IOException {
        driver = initialiseDrier();
        driver.get(prop.getProperty("url"));

    }

    @Test
    public void test_navigationbar() {
        LoginLandingPage l = new LoginLandingPage(driver);
        Assert.assertTrue(l.NBar().isDisplayed());
       log.info("test");
    }

    @AfterTest
    public void browserquit(){
          driver.close();
    }
}
