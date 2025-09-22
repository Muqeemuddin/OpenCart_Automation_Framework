package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountLogoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;

public class TC004_LogoutTest extends BaseTestClass {
	
	

	
	@Test(groups= {"Sanity","Master"})
	public void verify_account_logout() {
		try {
			loginSetup();
			logger.info("*********** TC004_LogoutTest started ***********");
			MyAccountPage myAccPage = new MyAccountPage(driver);
			myAccPage.clickLogout();
			logger.info("clicked on logout.......");
			AccountLogoutPage accLogoutPage = new AccountLogoutPage(driver);
			if(accLogoutPage.isAccountLogoutPageExists()) {
				logger.info("Validating Test.....");
				Assert.assertTrue(true);
				logger.info("Test Passed......");
			}else {
				logger.info("Test failed........");
				Assert.assertTrue(false);
			}
		}catch(Exception e) {
			logger.info("Exception Occured......");
			Assert.fail();
		}
		
		
	}
	
	@Test(groups= {"Sanity", "Master"},dependsOnMethods = {"verify_account_logout"})
	public void verify_account_logout_HomePage() {
		
		try{
			logger.info("******* TC004_LogoutTest HomePage started ********");
			AccountLogoutPage accLogoutPage = new AccountLogoutPage(driver);
			accLogoutPage.clickContinue();
			logger.info("clicked on continue.......");
			HomePage hp = new HomePage(driver);
			Assert.assertEquals(hp.getTitleText(), "Your Store");
		}catch(Exception e) {
			logger.info("Test failed to go back to HomePage.......");
			Assert.fail();
		}
		
	}
	
	
}
