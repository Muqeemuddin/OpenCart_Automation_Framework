package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;
import utilities.DataProviderUtil;

public class TC003_LoginDataDrivenTest extends BaseTestClass{
	
	@Test(dataProvider="dpLoginData", dataProviderClass=DataProviderUtil.class, groups="DataDriven")
	public void verify_loginDDT(String email, String pwd, String expectedResult) {
		logger.info("****** Test TC003_LoginDataDrivenTest Begin *****");
		try {
			
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on MyAccount.....");
		hp.clickLogin();
		logger.info("clicked on login.....");
		
		//LoginPage
		LoginPage lgnPage = new LoginPage(driver);
		logger.info("passing login credentials.....");
		lgnPage.setEmail(String.valueOf(email));
		lgnPage.setPassword(String.valueOf(pwd));
		lgnPage.clickLogin();
		logger.info("clicked login button.....");
		
		//MyAccountPAge
		MyAccountPage myAccPage = new MyAccountPage(driver);
		boolean targetPage = myAccPage.isMyAccountPageExists();
		
		if(expectedResult.equalsIgnoreCase("valid")) {
			if(targetPage) {
				Assert.assertTrue(true); // test pass
				myAccPage.clickLogout();
				logger.info("user logged in - Test Passed.....");
			}else {
				logger.info("valid credentials - Test failed.....");
				Assert.assertTrue(false); // test fail
			}
		}else {
			if(targetPage) {
				myAccPage.clickLogout();
				logger.info("invalid credentials, user logged in - test failed.....");
				Assert.assertTrue(false); // test fail
				
			}else {
				Assert.assertTrue(true);// test pass (negative test)
				logger.info("invalid credentials, user didn't login - test passed.....");
			}
			
		}
		}catch(Exception e) {
			logger.info("Exception occured.....");
			Assert.fail();
		}
		
		logger.info("***** Test TC003_LoginDataDrivenTest Completed *******");
		
	}
	
}
