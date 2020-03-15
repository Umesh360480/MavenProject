package resources;
import java.io.FileReader;
import java.util.Properties;

public class ReadProperty {

	static FileReader reader;
	static Properties prop;

	public static void init() throws Throwable {
		reader = new FileReader(getProjectDirectory().concat("\\GlobalData.properties"));
		prop = new Properties();
		prop.load(reader);
	}

	public static String getBaseURL() {			
		return prop.getProperty("baseURL").toString();
	}

	public static String getBrowserName() {
		return prop.getProperty("browserName").toString();
	}

	public static String getFirefoxExecutablePath() {
		return getProjectDirectory().concat(prop.getProperty("firefoxExecutablePath").toString());
	}

	public static String getTestDataExcelPath() {
		return getProjectDirectory().concat(prop.getProperty("testDataExcelPath").toString());
	}

	public static String getSheetName() {
		return prop.getProperty("sheetName").toString();
	}

	public static String getProjectDirectory() {
		return System.getProperty("user.dir").toString();
	}
	public static String getScreenshotDirectory() {
		return getProjectDirectory().concat(prop.getProperty("latestScreenshotPath"));
	}
	public static String getArchiveScreenshotDirectory() {
		return getProjectDirectory().concat(prop.getProperty("archiveScreenshotPath"));
	}
	public static String getLatestReportPath() {
		return getProjectDirectory().concat(prop.getProperty("latestReportPath"));
	}
	public static String getArchiveReportsPath() {
		return getProjectDirectory().concat(prop.getProperty("archiveReportsPath"));
	}
}
