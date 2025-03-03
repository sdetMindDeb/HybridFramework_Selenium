package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

    public AccountRegistrationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement phone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement cnfpassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkdPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//*[@id=\"content\"]/h1")
    WebElement cnfrmMsg;


    public void setFirstName(String fName){
        firstName.sendKeys(fName);
    }

    public void setLastName(String lName){
        lastName.sendKeys(lName);
    }

    public void setEmail(String emailAdd){
        email.sendKeys(emailAdd);
    }

    public void setPhone(String emailAdd){
        phone.sendKeys(emailAdd);
    }

    public void setpassword(String pass){
        password.sendKeys(pass);
    }

    public void setCnfpassword(String pass){
        cnfpassword.sendKeys(pass);
    }

    public void checkPolicy(){
        chkdPolicy.click();
    }

    public void continuepage(){
        btnContinue.click();
    }

    public String getConfirmationPage() {
        return cnfrmMsg.getText();
    }
}
