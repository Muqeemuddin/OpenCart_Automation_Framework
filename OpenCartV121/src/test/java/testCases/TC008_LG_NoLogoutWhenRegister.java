package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseTestClass;

public class TC008_LG_NoLogoutWhenRegister extends BaseTestClass {
	
	@Test(groups= {"Sanity", "Master"})
	public void verify_no_logout_when_register() {
		try {
			logger.info("********* Test TC008_LG_NoLogoutWhenRegister started ********");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("clicked on MyAccount......");
			hp.clickRegister();
			logger.info("clicked on register........");
			AccountRegistrationPage accRegister = new AccountRegistrationPage(driver);
			logger.info("Validating test......");
			if(!accRegister.isLogoutDispalyed()) {
				logger.info("Test Passed.....");
				Assert.assertTrue(true);
			}else {
				logger.info("Test failed......");
				Assert.assertTrue(false);
			}
		}catch(Exception e) {
			logger.info("Exception occured......");
			Assert.fail();
		}
	}

}
