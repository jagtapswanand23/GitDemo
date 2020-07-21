package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginLandingPage {
    public WebDriver driver;
    private By signin = By.cssSelector("a[href*='sign_in']");   //href*(Contains) means sign_in text is present from the link

    private By title = By.cssSelector("div[class='text-center']");
    private By navbar = By.cssSelector("ul[class='nav navbar-nav navbar-right']");

    private By header = By.cssSelector("div[class*='video-banner'] h3");  //declaring private and method as public is example of Encapsulation


    public LoginLandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage login() {

        driver.findElement(signin).click();
        LoginPage lp = new LoginPage(driver);  //creating object of login class
        return lp; // retuning the obeject of login class to access the methods of that class

    }

    public WebElement pagetitle() {

        return driver.findElement(title);
    }

    public WebElement NBar() {

        return driver.findElement(navbar);
    }

    public WebElement getheader() {

        return driver.findElement(header);
    }
}
