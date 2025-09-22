package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		
		super(driver);
		
	}
	
	// Elements
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	@FindBy(xpath="//a[normalize-space()='Logout']")
	WebElement lnkLogout;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='My Account']")
	WebElement lnkMyAccountDetials;
	
	@FindBy(xpath="//li[@class='dropdown open']/ul/li")
	List<WebElement> elements;
	// Actions
	public void clickMyAccount() {
		lnkMyAccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
	
	public String getTitleText() {
		return driver.getTitle();
	}
	
	public boolean isLogoutDispalyed() {
		for(WebElement element:elements) {
			if(element.getText().equals("Logout")) {
				return true;
			}
		}
		return false;
	}
	
	public void clickMyAccountDetails() {
		lnkMyAccountDetials.click();
	}
}
