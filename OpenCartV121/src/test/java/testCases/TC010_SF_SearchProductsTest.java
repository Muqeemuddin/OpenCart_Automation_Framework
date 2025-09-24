package testCases;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseTestClass;

public class TC010_SF_SearchProductsTest extends BaseTestClass{
	private static final String NO_PRODUCT_MATCH = "There is no product that matches the search criteria.";

	public List<String> searchProduct(String searchItem){
		HomePage hp = new HomePage(driver);
		hp.clearSearchBar();
		hp.setSearchItem(searchItem);
		logger.info("set the search item....");
		hp.clickSearchButton();
		logger.info("clicked the search button......");
		SearchPage sp = new SearchPage(driver);
		List<String> headings = sp.getItemHeadings();
		return headings;
	}
	@Test
	public void verify_search_of_existing_product() {
		try {
			logger.info("****** TestCase verify_search_of_existing_product started *********** ");
			List<String> headings = searchProduct("iMac");
			logger.info("validating results......");
			Assert.assertTrue(headings.contains("iMac"), "Expected search results to contain 'iMac'");
		}catch(Exception e) {
			logger.error("Exception occured.....",e);
			throw e;
		}
		
	}
	
	@Test
	public void verify_search_of_non_existing_product() {
		try {
			logger.info("********* TestCase verify_search_of_non_existing_product started *********");
			HomePage hp = new HomePage(driver);
			hp.clearSearchBar();
			hp.setSearchItem("fitbit");
			logger.info("Set the non existing search item.......");
			hp.clickSearchButton();
			logger.info("clicked the search button........");
			SearchPage sp = new SearchPage(driver);
			String actualMsg = sp.getNoProductMatchText();
			logger.info("Validating results.......");
			Assert.assertTrue(actualMsg.equals(NO_PRODUCT_MATCH), "Expected no results message not displayed.");			
		}catch(Exception e) {
			logger.error("Exception occured.....",e);
			throw e;
		}
	}
	
	@Test
	public void verify_search_with_no_product() {
		try {
			logger.info("******** TestCase verify_search_with_no_product started **********");
			HomePage hp = new HomePage(driver);
			hp.clearSearchBar();
			hp.clickSearchButton();
			logger.info("clicked the search button.......");
			SearchPage sp = new SearchPage(driver);
			String actualMsg = sp.getNoProductMatchText();
			logger.info("Validating results.......");
			Assert.assertTrue(actualMsg.equals(NO_PRODUCT_MATCH), "Expected no results message not displayed.");
		}catch(Exception e) {
			logger.error("Exception occured.....",e);
			throw e;
		}
	}
	
	@Test
	public void verify_search_of_existing_product_with_login() {
		try {
			logger.info("****** TestCase verify_search_of_existing_product_with_login started ********** ");
			loginSetup();
			List<String> headings = searchProduct("iMac");
			logger.info("validating results......");
			Assert.assertTrue(headings.contains("iMac"), "Expected search results to contain 'iMac'");
		}catch(Exception e) {
			logger.error("Exception occured.....",e);
			throw e;
		}
	}
	
	@Test
	public void verify_search_for_multiple_products() {
		try {
			logger.info("******* TestCase verify_search_for_multiple_products started **********");
			List<String> headings = searchProduct("Mac");
			logger.info("validating results......");
			Assert.assertTrue(headings.size()>1, "Expected search results to contain more than one item.");
			
		}catch(Exception e) {
			logger.error("Exception occured.....",e);
			throw e;
		}
	}
	
	@Test
	public void verify_search_page_with_no_product() {
		try {
			logger.info("********* TestCase verify_search_page_with_no_product started **********");
			searchProduct("");
			SearchPage sp = new SearchPage(driver);
			Assert.assertTrue(sp.getSearchCriteriaText().equals("Search Criteria"), "Expected Search Criteria not displayed.");
			Assert.assertTrue(sp.isSearchCriteriaBarDisplayed(), "Expected Search Criteria Bar not displayed.");
		}catch(Exception e) {
			logger.error("Exception occured.....",e);
			throw e;
		}
	}
	
	@Test
	public void verify_search_using_search_criteria() {
		try {
			logger.info("********** TestCase verify_search_using_search_criteria started *********");
			searchProduct("");
			SearchPage sp = new SearchPage(driver);
			sp.setSearchCriteriaBar("iMac");
		}catch(Exception e) {
			logger.error("Exception occured.....",e);
			throw e;
		}
	}
	
	

}
