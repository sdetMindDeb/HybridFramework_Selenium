package testCases;

import PageObjects.HomePage;
import PageObjects.MyAccountPage;
import PageObjects.loginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.BaseClass;

public class LoginPageTest extends BaseClass {

    @Test(groups = {"Regression","Master"})
    public void loginValidation() {

        logger.info("****Starting execution*****");
        try {

            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("****Clicked*****");
            hp.clickLogin();

            loginPage lp = new loginPage(driver);
            lp.setEmailAddress(pr.getProperty("EmailAddress"));
            lp.setPassword(pr.getProperty("Password"));
            lp.clickLogin();

            MyAccountPage arp = new MyAccountPage(driver);
            String myAccountText = arp.getTextMyAccount();
            Assert.assertEquals(myAccountText, "My Account");
        } catch (Exception e) {
            logger.error("Test failed");
            logger.debug("debug");
            Assert.fail();
        }
        logger.info("****Complete execution*****");
    }
}
