package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage extends BasePage{

    public loginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement emailAddress;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement Login;

    public void setEmailAddress(String email){
        emailAddress.sendKeys(email);
    }

    public void setPassword(String passwordText){
        password.sendKeys(passwordText);
    }
    public void clickLogin(){
        Login.click();
    }
}
