package testCases;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseTestClass;

public class TC001_AccountResgistrationTest extends BaseTestClass{
	
	
	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration() {
		logger.info("**** Starting TC001_AccountResgistrationTest *****");
		try {
			
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on myAccount link....");
			hp.clickRegister();
			logger.info("Clicked on Register link....");
			
			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
			logger.info("Providing customer details....");
			regPage.setFirstName(randomString());
			regPage.setLastNAme(randomString());
			regPage.setEmail(randomString() + "@example.com");
			regPage.setPassword("js@1234");
			regPage.setConfirmPassword("js@1234");
			regPage.setSubscribe("Yes");
			regPage.setTelephone(randomNumber());
			regPage.checkPolicy();
			regPage.clickContinue();
			logger.info("Form submitted.....");
			String confmsg = regPage.getConfirmationMessage();
			if(confmsg.equals("Your Account Has Been Created!")) {
				logger.info("Received confirmation message....");
				AssertJUnit.assertTrue(true);
			}else {
				logger.error("Test failed....");
				logger.debug("Debug Logs...");
				AssertJUnit.assertTrue(false);
			}
			logger.info("Test case Validated.....");
		}catch(Exception e) {
			
			AssertJUnit.fail();
		}
		
		logger.info("**** Ending of TC001_AccountResgistrationTest *****");
		
		
		
	}
	
	
	
	

}
