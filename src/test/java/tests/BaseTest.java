package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import pages.HomePage;
import pages.LoginPage;
import resources.ExcelReader;
import resources.ReadProperty;
import resources.browserName;

public class BaseTest {

	static LoginPage objLoginPage;
	public static WebDriver driver;
	static HomePage objHomePage;
	static ExcelReader readExcel;
	static ExtentHtmlReporter htmlReport;
	static ExtentReports extent;
	static ExtentTest test;

	@BeforeSuite
	public static void initializeResource() throws Throwable {
		ReadProperty.init();
		readExcel = new ExcelReader(ReadProperty.getTestDataExcelPath(), ReadProperty.getSheetName());
		if (ReadProperty.getBrowserName().equalsIgnoreCase(browserName.Firefox.toString())) {
			System.setProperty("webdriver.gecko.driver", ReadProperty.getFirefoxExecutablePath());
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		htmlReport = new ExtentHtmlReporter(ReadProperty.getLatestReportPath().concat(getReportName()));

		htmlReport.config().setAutoCreateRelativePathMedia(true);
		htmlReport.config().setCSS("css-string");
		htmlReport.config().setDocumentTitle("Captue Automation");
		htmlReport.config().setEncoding("utf-8");
		htmlReport.config().setJS("js-string");
		htmlReport.config().setProtocol(Protocol.HTTPS);
		htmlReport.config().setReportName("build name");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

		extent = new ExtentReports();
		extent.attachReporter(htmlReport);
		extent.setSystemInfo("BrowserName", ReadProperty.getBrowserName());
		extent.setSystemInfo("OS", System.getenv("OS"));
	}

	@Test
	public static void launchURL() {
		driver.get(ReadProperty.getBaseURL());
		driver.manage().window().maximize();
	}

	@Test(dependsOnMethods = "launchURL")
	public static void login() {
		test = extent.createTest("Login Test");
		test.log(Status.INFO, "Login Test started");
		objLoginPage = new LoginPage(driver);
		test.log(Status.INFO, "User Credentials reading from excel");
		objHomePage = objLoginPage.loginUser(readExcel.getData("Username"), readExcel.getData("Password"));
		Assert.assertTrue(objHomePage.getUserName().contains(readExcel.getData("Username")), "User not logged");
		test.log(Status.INFO, "Login Test ended");
		// test.log(Status.PASS, "User logged in successfully");
	}

	@Test(dataProvider = "LoginDetails", enabled = false)
	public static void loginUsers(String user, String pass, String type) {
		objLoginPage = new LoginPage(driver);
		objHomePage = objLoginPage.loginUser(user, pass);
		if (type.equalsIgnoreCase("valid")) {
			Assert.assertTrue(objHomePage.getUserName().contains(readExcel.getData("Username")), "User not logged");
			objHomePage.clickBackward();
		} else {
			objHomePage.dismiss();
			objHomePage.clickBackward();
		}

	}

	@AfterMethod
	public static void getStatus(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test = extent.createTest(result.getName());
			test.log(Status.FAIL, result.getName() + " test is Failed");
			test.log(Status.FAIL, result.getThrowable());
			// test.log(Status.,result.getName() + " is failed");

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test = extent.createTest(result.getName());
			test.log(Status.PASS, result.getName() + " is passed");
		}
		if (result.getStatus() == ITestResult.SKIP) {
			// test.skip(MarkupHelper.createLabel(result.getName() + " test is Skipped",
			// ExtentColor.AMBER));
			test = extent.createTest(result.getName());
			test.log(Status.SKIP, result.getName() + " is skipped");
			test.log(Status.SKIP, result.getThrowable());
		}
	}

	@DataProvider(name = "LoginDetails")
	public static Object[][] getLoginDetails() {
		return new Object[][] { { "mngr248511", "mUvAjeg", "Invalid" }, { "mngr248510", "mUvAjeg1", "Invalid" },
				{ "mngr248512", "mUvAjeg1", "Invalid" }, { "mngr248510", "mUvAjeg", "Valid" } };
	}

	@Test(dependsOnMethods = "login")
	public static void validateHomePageTitle() throws IOException {
		test = extent.createTest("Validating Home Page Title Test");
		test.log(Status.INFO, "Validating Home Page Title");
		Assert.assertEquals(objHomePage.getPageTitle(), "Guru99 Bank Manager HomePage", "HomePage Title doesn't match");
		// test.log(Status.INFO, "Validate HomePage Title not matched");
		// test.log(Status.PASS, "Home Page Title matched");
	}

	@AfterSuite
	public static void closeConnections() throws Throwable {
		driver.close();
		moveReportsToArchive();
		extent.flush();
	}

	public static String getReportName() {
		String reportName = "Capture_Automation_Report";
		reportName = reportName.concat(".html");
		return reportName;
	}

	public static void moveReportsToArchive() throws Throwable {
		File reportFolder = new File(ReadProperty.getLatestReportPath());
		String dateName = new SimpleDateFormat("dd-MMM-yyyy_hhmmss").format(new Date()).concat("\\");
		if (reportFolder.isDirectory() && reportFolder.listFiles().length > 0) {
			File[] lstFiles = reportFolder.listFiles();
			for (File srcFile : lstFiles) {
				File targetDirectory = new File(ReadProperty.getArchiveReportsPath().concat(dateName));
				if (!targetDirectory.exists())
					targetDirectory.mkdir();
				File destFile = new File(targetDirectory.toString().concat("\\").concat(srcFile.getName()));
				Files.move(srcFile, destFile);
			}
		}
	}
}
