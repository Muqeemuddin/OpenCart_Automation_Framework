package testCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseTestClass;

public class TC005_LG_ApplicationSession extends BaseTestClass{
	
	
	
	@Test(groups= {"Sanity", "Master"})
	public void verify_application_session() throws IOException, InterruptedException {
		loginSetup();
		Thread.sleep(5000);
		driver.close();
		setup("Windows","Chrome");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		Assert.assertTrue(hp.isLogoutDispalyed());
	}
}
