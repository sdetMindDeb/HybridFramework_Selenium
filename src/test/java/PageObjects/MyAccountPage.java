package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

    public MyAccountPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//h2[text()='My Account']")
    WebElement MyAccountText;

    @FindBy(xpath = "//*[@id=\"column-right\"]/div/a[13]")
    WebElement linkLogout;

    public String getTextMyAccount(){
        return MyAccountText.getText();
    }

    public Boolean MyAccountIsDisplayed(){
        return MyAccountText.isDisplayed();
    }

    public void clickLogout(){
         linkLogout.click();
    }


}
