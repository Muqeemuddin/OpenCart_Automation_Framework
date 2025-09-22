package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import pageObjects.HomePage;
import pageObjects.LoginPage;

public class BaseTestClass {
	
	public static WebDriver driver;
	public static Logger logger; // Log4j
	public Properties prop;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String browser) throws IOException {
		
		//Loading config.properties file
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		prop = new Properties();
		prop.load(file);
		logger = LogManager.getLogger(this.getClass());
		if(prop.getProperty("execution_env").equals("remote")) {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			switch(os.toLowerCase()) {
			case "windows" : capabilities.setPlatform(Platform.WIN11); break;
			case "mac" : capabilities.setPlatform(Platform.MAC); break;
			case "linux": capabilities.setPlatform(Platform.LINUX); break;
			default : logger.info("Invalid OS"); 
			}
			
			switch(browser.toLowerCase()) {
			case "chrome" : capabilities.setBrowserName("chrome"); break;
			case "edge" :  capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox" :  capabilities.setBrowserName("firefox"); break;
			case "safari" :  capabilities.setBrowserName("safari"); break;
			default: logger.info("Invalid Browser...."); return;
			}
			
			// add /wd/hub to the grid URL
			driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities);
			
		}
		if(prop.getProperty("execution_env").equals("local")) {
			switch(browser.toLowerCase()) {
			case "chrome" : driver = new ChromeDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			case "safari" : driver = new SafariDriver(); break;
			default: logger.info("Invalid Browser...."); return;
			}
		}
		
		
		//driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("appURL"));
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() {
		
		driver.quit();
		
	}
	
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber() {
		return RandomStringUtils.randomNumeric(10);
	}
	
	public String captureScreen(String testName) {
		LocalDateTime now = LocalDateTime.now();
		String timeStamp = now.format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
		
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File sourceFile =takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir")+ "//screenshots//" + testName + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
	
	public void loginSetup() {
		logger.info("******** Logging in to test Logout *********");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on MyAccount.....");
		hp.clickLogin();
		logger.info("clicked on login.....");
		
		LoginPage lgnPage = new LoginPage(driver);
		logger.info("passing login credentials.....");
		lgnPage.setEmail(prop.getProperty("email"));
		lgnPage.setPassword(prop.getProperty("password"));
		lgnPage.clickLogin();
		logger.info("clicked login button.....");
	}

}
