package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;

public class TC006_LG_BrowseBack extends BaseTestClass {
	
	@Test(groups= {"Sanity", "Master"})
	public void verify_browseBack() {
		try {
			loginSetup();
			MyAccountPage accPage = new MyAccountPage(driver);
			accPage.clickLogout();
			logger.info("clicked logout......");
			driver.navigate().back();
			logger.info("clicked back button.....");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickMyAccountDetails();
			logger.info("clicked myAccount detials.....");
			String actualTitle = driver.getTitle();
			Assert.assertEquals(actualTitle, "Account Login");
		}catch(Exception e) {
			logger.info("exception occured.......");
			Assert.fail();
		}
		
		
		
	}

}
