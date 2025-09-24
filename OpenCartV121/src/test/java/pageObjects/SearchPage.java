package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage{

	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	//Elements 
	
	@FindBy(xpath="//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']//h4")
	List<WebElement> txtItemHeadings;
	
	@FindBy(xpath="//p[normalize-space()='There is no product that matches the search criteria.']")
	WebElement txtNoProductMatch;
	
	@FindBy(xpath="//label[normalize-space()='Search Criteria']")
	WebElement txtSearchCriteria;
	
	@FindBy(xpath="//input[@id='input-search']")
	WebElement txtSearchCriteriaBar;
	
	//Actions
	public List<String> getItemHeadings(){
		List<String> headings = new ArrayList<>();
		for(WebElement element:txtItemHeadings) {
			headings.add(element.getText());
		}
		return headings;
	}
	
	public String getNoProductMatchText() {
		return txtNoProductMatch.getText();
	}
	
	public String getSearchCriteriaText() {
		return txtSearchCriteria.getText();
	}
	
	public boolean isSearchCriteriaBarDisplayed() {
		return txtSearchCriteriaBar.isDisplayed();
	}
	
	public void setSearchCriteriaBar(String searchItem) {
		txtSearchCriteriaBar.sendKeys(searchItem);
	}
}
