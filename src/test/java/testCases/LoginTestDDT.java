package testCases;

import PageObjects.HomePage;
import PageObjects.MyAccountPage;
import PageObjects.loginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testBase.BaseClass;
import utilities.DataProviders;

public class LoginTestDDT extends BaseClass {

    @Test(dataProvider="loginData",dataProviderClass = DataProviders.class,groups = "DataDriven")
    public void loginValidationWithDP(String email,String password) {
        logger.info("****Starting execution*****");
        try {

            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("****Clicked*****");
            hp.clickLogin();

            loginPage lp = new loginPage(driver);
            lp.setEmailAddress(email);
            lp.setPassword(password);
            lp.clickLogin();

            MyAccountPage arp = new MyAccountPage(driver);
            Boolean myAccountStatus=arp.MyAccountIsDisplayed();

            if(myAccountStatus.equals(true)){
                arp.clickLogout();
                Assert.assertTrue(true);
            }
            else{
                Assert.fail();
            }
        } catch (Exception e) {
            logger.error("Test failed");
            logger.debug("debug");
            Assert.fail();
        }
        logger.info("****Complete execution*****");
    }
}
