package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountLogoutPage extends BasePage
{

	public AccountLogoutPage(WebDriver driver) {
		super(driver);
		
	}
	
	//Elements
	@FindBy(xpath="//a[normalize-space()='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Account Logout']")
	WebElement txtTitle;
	
	//Actions
	public void clickContinue() {
		btnContinue.click();
	}
	
	public boolean isAccountLogoutPageExists() {
		try{
			return txtTitle.isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}

}
