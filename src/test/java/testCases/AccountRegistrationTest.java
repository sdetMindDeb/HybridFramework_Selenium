package testCases;

import PageObjects.AccountRegistrationPage;
import PageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.BaseClass;

public class AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void RegisterAccount() {

        logger.info("****Starting execution*****");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("****Clicked*****");
            hp.clickRegister();

            AccountRegistrationPage arp = new AccountRegistrationPage(driver);
            arp.setFirstName(randomString());
            arp.setLastName(randomString());
            String email = randomString() + "@gmail.com";
            arp.setEmail(email);
            System.out.println("Email address=>" + email);
            arp.setPhone(randomNumeric());

            String password = randomAlphaNumeric();
            arp.setpassword(password);
            arp.setCnfpassword(password);
            System.out.println("Password" + password);
            arp.checkPolicy();
            arp.continuepage();
            logger.info("****Validating expected message*****");

            String confirnMessage = arp.getConfirmationPage();
            Assert.assertEquals(confirnMessage, "Your Account Has Been Created!");
        } catch (Exception e) {
            logger.error("Test failed");
            logger.debug("debug");
            Assert.fail();
        }
        logger.info("****Complete execution*****");
    }
}
