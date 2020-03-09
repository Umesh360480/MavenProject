package resources;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadProperty {

	static FileReader reader;
	static Properties prop;

	ReadProperty() throws Throwable {
		 reader = new FileReader("C:\\Automation\\Practise\\MavenProject\\GlobalData.properties");
		 prop = new Properties(); 
		 prop.load(reader);
		 
	}

	public static String getBaseURL() {
		System.out.println(prop.getProperty("baseURL").toString());
		return prop.getProperty("baseURL").toString();
	}

	public static String getBrowserName() {
		return prop.getProperty("browserName").toString();
	}

	public static String getFirefoxExecutablePath() {
		return prop.getProperty("firefoxExecutablePath").toString();
	}

	public static String getTestDataExcelPath() {
		try {
			reader = new FileReader("C:\\Automation\\Practise\\MavenProject\\GlobalData.properties");
			prop = new Properties();
			prop.load(reader);
		} catch (FileNotFoundException fe) {
			// TODO Auto-generated catch block
			fe.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty("testDataExcelPath").toString();
	}

	public static String getSheetName() {
		return prop.getProperty("sheetName").toString();
	}
}
