package utils;

import static tests.BaseTest.extent;
import static tests.BaseTest.htmlReport;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

import resources.ReadProperty;

public class ExtentManager {
	ExtentHtmlReporter htmlReport;
	ExtentReports extent;
	ExtentTest test;
	
	public void setConfiguration() {
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
	
	}
	public ExtentReports getExtentReporter() {
		extent = new ExtentReports();
		extent.attachReporter(htmlReport);
		extent.setSystemInfo("BrowserName", ReadProperty.getBrowserName());
		extent.setSystemInfo("OS", System.getenv("OS"));
		return extent;
	}

}
