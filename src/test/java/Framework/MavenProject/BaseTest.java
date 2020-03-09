package Framework.MavenProject;

import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import resources.ExcelReader;
import resources.ReadProperty;
import resources.browserName;

public class BaseTest {

	LoginPage objLoginPage;
	WebDriver driver;
	HomePage objHomePage;
	ExcelReader readExcel;

	@BeforeSuite
	public void initializeResource() throws Throwable {
		ReadProperty.init();
		readExcel = new ExcelReader(ReadProperty.getTestDataExcelPath(),ReadProperty.getSheetName());
		if(ReadProperty.getBrowserName().equalsIgnoreCase(browserName.Firefox.toString())) {
		System.setProperty("webdriver.gecko.driver",ReadProperty.getFirefoxExecutablePath());
		driver = new FirefoxDriver();			
		}
	}	
	@Test
	public void launchURL() {
		driver.get(ReadProperty.getBaseURL());
		driver.manage().window().maximize();	    	
	}
	@Test(dependsOnMethods="launchURL")
	public void login() {
		
		objLoginPage = new LoginPage(driver);
		objHomePage = objLoginPage.loginUser(readExcel.getData("Username"), readExcel.getData("Password"));
		Assert.assertTrue(objHomePage.getUserName().contains(readExcel.getData("Username")),"User not logged");							
	}
	@Test(dependsOnMethods="login")
	public void validateHomePageTitle() {
	 Assert.assertEquals(objHomePage.getPageTitle(),"Guru99 Bank Manager HomePage","HomePage Title doesn't match");
	}
	@AfterSuite
	public void closeConnections() {
			driver.close();		
	}	
}
