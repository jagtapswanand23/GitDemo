package Tests;

import org.apache.logging.log4j.*;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.ForgotPassword;
import pageObjects.LoginLandingPage;
import pageObjects.LoginPage;
import resources.Base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HomePageTest extends Base {
    public WebDriver driver;   // this will execute test in parallel mode , run the driver in local mode
    public static Logger log = LogManager.getLogger(Test.class.getName());
    @BeforeTest
    public void setup() throws IOException {
        driver = initialiseDrier();
        //driver.get(prop.getProperty("url"));
        log.info("test");

    }

    @Test(dataProvider = "test_getdata")
    public void test_homepage(String uname, String pwd) throws IOException, InterruptedException {
        driver.get(prop.getProperty("url"));
//        driver=initialiseDrier();
//        driver.get("http://www.qaclickacademy.com/");
        LoginLandingPage l = new LoginLandingPage(driver);
        LoginPage lp=l.login();   // test have two pages. we will configure the passord
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

        //LoginPage lp = new LoginPage(driver);

        boolean a = lp.username.isDisplayed();
        boolean b = lp.password.isDisplayed();
        assertThat("Username textbox is not able to find", a, is(true));
        assertThat("Password textbox is not able to find", b, is(true));

        try {
            lp.username.sendKeys(uname);
            lp.password.sendKeys(pwd);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        Thread.sleep(5000);

       /* lp.loginbtn().click();   currently commenting because of captcha */

         ForgotPassword fp= lp.forgot();   // Click on forgotpassword link. in this class we write down forgot object . so we dont need to create object of login class.
        fp.emailaddress.sendKeys("test");
        fp.sendme.click();

        //        WebDriverWait wait = new WebDriverWait(driver, 30);
//        wait.until(ExpectedConditions.visibilityOfElementLocated((By) lp.invalidusernamepassword()));

      /*  Thread.sleep(5000);
        assertThat("Error message is not displayed", lp.invalidusernamepassword.isDisplayed(), is(true));   //it will verify the error message is displayed
        assertThat("Error message is not displayed", lp.invalidusernamepassword.getText(), equalTo("Invalid email or password")); // error message text testing
       */


        // System.out.println(lp.invalidusernamepassword.getText());
    }

    @DataProvider
    public Object[][] test_getdata() {
        Object[][] data = new Object[2][2];
        data[0][0] = "jayagtswanand@gmail.com";
        data[0][1] = "test";

        data[1][0] = "sayali@gmail.com";
        data[1][1] = "test";

        return data;
    }

    @AfterTest
    public void browserquit(){
        driver.close();
    }

}
