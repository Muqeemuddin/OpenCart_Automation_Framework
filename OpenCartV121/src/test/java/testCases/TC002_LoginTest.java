package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;

public class TC002_LoginTest extends BaseTestClass{
	
	@Test(groups={"sanity","Master"})
	public void verify_account_login() {
		
		try {
			logger.info("******Test TC002_LoginTest began ******");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("clicked on MyAccount.....");
			hp.clickLogin();
			logger.info("clicked on login.....");
			
			LoginPage lgnPage = new LoginPage(driver);
			logger.info("passing login credentials.....");
			lgnPage.setEmail(prop.getProperty("email"));
			lgnPage.setPassword(prop.getProperty("password"));
			lgnPage.clickLogin();
			logger.info("clicked login button.....");
			
			MyAccountPage myAccPage = new MyAccountPage(driver);
			if(myAccPage.isMyAccountPageExists()) {
				logger.info("Validating Test....");
				Assert.assertTrue(true);
				logger.info("Test Passed.....");
			}else {
				logger.info("Test failed....");
				Assert.assertTrue(false);
			}
		}catch(Exception e) {
			logger.error("Exception occured....");
			Assert.fail();
		}
		
		logger.info("***** Test TC002_LoginTest Completed ******* ");
		
		
	}
}
