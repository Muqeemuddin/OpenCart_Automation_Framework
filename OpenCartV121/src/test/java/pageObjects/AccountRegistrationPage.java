package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		
	}
	
	//Elements
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//label[normalize-space()='Yes']")
	WebElement btnSubscribeYes;
	
	@FindBy(xpath="//label[normalize-space()='No']")
	WebElement btnSubscribeNo;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkdPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement successMessage;
	
	@FindBy(xpath="//div[@class='list-group']/a")
	List<WebElement> optionsList;
		
	//Actions
	public void setFirstName(String fName) {
		txtFirstName.sendKeys(fName);
	}
	
	public void setLastNAme(String lName) {
		txtLastName.sendKeys(lName);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String telephone) {
		txtTelephone.sendKeys(telephone);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void setConfirmPassword(String confirmPassword) {
		txtConfirmPassword.sendKeys(confirmPassword);
	}
	
	public void setSubscribe(String str) {
		if(str.equals("Yes")) {
			btnSubscribeYes.click();
		}else {
			btnSubscribeNo.click();
		}
	}
	
	public void checkPolicy() {
		chkdPolicy.click();
	}
	
	public void clickContinue() {
		btnContinue.click();
	}
	
	public String getConfirmationMessage() {
		try {
			return successMessage.getText();
		}catch(Exception e) {
			return e.getMessage();
		}
		
	}
	
	public boolean isLogoutDispalyed() {
		for(WebElement element:optionsList) {
			if(element.getText().equals("Logout")) {
				return true;
			}
		}
		return false;
	}

}
