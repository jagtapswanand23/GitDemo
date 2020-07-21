package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public WebDriver driver;
    By btn=By.cssSelector("[type='submit']");//href*(Contains) means sign_in text is present from the link

    @FindBy(how = How.CSS, using="input[id='user_email']")
    public WebElement username;

    @FindBy(how = How.CSS, using="input[id='user_password']")
    public WebElement password;

    @FindBy(how=How.CSS, using="div[class='alert alert-danger']")
    public WebElement invalidusernamepassword;


    By forgotpassword = By.cssSelector("a[class='link-below-button']");

    public ForgotPassword forgot(){
        driver.findElement(forgotpassword).click();
        return new ForgotPassword(driver);  // it will create a new object and also return that object. this to minimise the code

    }

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement loginbtn(){
        return driver.findElement(btn);
    }

    public WebElement invalidusernamepassword() {
        return driver.findElement(By.cssSelector("div[class='alert alert-danger']"));
    }
}
