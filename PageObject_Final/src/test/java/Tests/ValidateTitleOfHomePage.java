package Tests;

import org.apache.logging.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LoginLandingPage;
import resources.Base;

import java.io.IOException;

public class ValidateTitleOfHomePage extends Base {
    public WebDriver driver;   // this will execute test in parallel mode , run the driver in local mode
    public static Logger log = LogManager.getLogger(Test.class.getName());
    LoginLandingPage l;
    @BeforeTest
    public void setup() throws IOException {
        driver = initialiseDrier();
        log.info("driver initialse");
        driver.get(prop.getProperty("url"));
        try {
            driver.findElement(By.cssSelector("div[class*=\"close-button\"]")).click();
        }catch (NoSuchElementException e) {
            e.printStackTrace();}
      log.info("url is open");
    }

    @Test
    public void test_titleOfPage() {
        l = new LoginLandingPage(driver);
        Assert.assertEquals(l.pagetitle().getText(), "FEATURED COURSES");
        log.info("title verified");
    }

    @Test
    public void test_headerOfPage() {
        l = new LoginLandingPage(driver);
        Assert.assertEquals(l.getheader().getText(), "AN ACADEMY TO LEARN EVERYTHING ABOUT TESTING");
        log.info("title verified");
    }

    @AfterTest
    public void browserquit(){
        driver.close();
    }
}
