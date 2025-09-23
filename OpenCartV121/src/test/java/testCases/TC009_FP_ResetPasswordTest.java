package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ForgotPasswordPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseTestClass;

public class TC009_FP_ResetPasswordTest extends BaseTestClass{
	
	@Test(groups = {"Sanity","Master"})
	public void verify_password_reset() {
		try {
			logger.info("********** Test TC009_FP_ResetPasswordTest started **********");
			HomePage hp= new HomePage(driver);
			hp.clickMyAccount();
			logger.info("clicked on MyAccount......");
			hp.clickLogin();
			logger.info("clicked on login.......");
			LoginPage lp = new LoginPage(driver);
			lp.clickForgottenPass();
			logger.info("clicked on forgotten password link......");
			String actualTitle = driver.getTitle();
			Assert.assertEquals(actualTitle, "Forgot Your Password?"); // ER1
			ForgotPasswordPage forgotPassPage = new ForgotPasswordPage(driver);
			forgotPassPage.setEmail(prop.getProperty("email"));
			logger.info("Set the email for password reset link......");
			forgotPassPage.clickContinue();
			logger.info("clicked continue......");
			try {
				String actualMessage = lp.getSuccessMessage();
				logger.info("Validating test case......");
				if(actualMessage.equals("An email with a confirmation link has been sent your email address.")) { // ER2
					logger.info("Test passed.......");
					Assert.assertTrue(true);
				}else {
					logger.info("Test failed.......");
					Assert.fail();
				}
			}catch(Exception e) {
				logger.info("Unregistered email address...... ");
				Assert.fail();
			}
			
			WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
			newTab.get("https://accounts.google.com/v3/signin/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&dsh=S-1290768552%3A1758657111494095&ifkv=AfYwgwX7oIgzeNNQnwJN0ChbWCAUB8485VMPDPdMmONsLd4EMVYFLBWUFuai7QApddWzu9svWc-NYw&rip=1&sacu=1&service=mail&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
			
			
			
		}catch(Exception e) {
			logger.info("exception occured......");
			Assert.fail();
		}
		
	}

}
