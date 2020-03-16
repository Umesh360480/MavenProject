package listeners;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import resources.ReadProperty;

public class ExtentManager{
	static ExtentHtmlReporter htmlReport;
	static ExtentReports extent;
	
	public static void setConfiguration() {
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
	public static ExtentReports getExtentReporter() {
		extent = new ExtentReports();
		extent.attachReporter(htmlReport);
		extent.setSystemInfo("BrowserName", ReadProperty.getBrowserName());
		extent.setSystemInfo("OS", System.getenv("OS"));
		return extent;
	}
	public static String getReportName() {
		String reportName = "Capture_Automation_Report";
		reportName = reportName.concat(".html");
		return reportName;
	}

}
