package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseTestClass;

public class TC007_LG_NoLogoutBeforeLogin extends BaseTestClass {
	
	@Test(groups= {"Sanity", "Master"})
	public void verify_no_logout_before_login() {
		try {
			logger.info("********* TC007_LG_NoLogoutBeforeLogin started ************");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("clicked on myAccount.......");
			if(hp.isLogoutDispalyed()) {
				logger.info("Test failed.......");
				Assert.assertTrue(false);
			}else {
				logger.info("Test passed.......");
				Assert.assertTrue(true);
			}
		}catch(Exception e) {
			logger.info("Exception occured.....");
			Assert.fail();
		}
		
		
		
	}

}
