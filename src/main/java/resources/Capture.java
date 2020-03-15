package resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Capture {

	public static void screenshot(WebDriver driver, String methodName) throws IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_hh-mm-ss");
		String	fileName = methodName.concat("_").concat(sdf.format(new Date())).concat(".png");
		TakesScreenshot takeScreenshot = ((TakesScreenshot) driver);
		File srcFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		if(new File(ReadProperty.getScreenshotDirectory()).exists())
			copyScreenshotToArchiveFolder();			
		File destFile = new File(ReadProperty.getScreenshotDirectory().concat(fileName));
		FileUtils.copyFile(srcFile, destFile);
	}
	public static void copyScreenshotToArchiveFolder() throws IOException {
		Path srcDir = Paths.get(ReadProperty.getScreenshotDirectory());
		Path destDir = Paths.get(ReadProperty.getArchiveScreenshotDirectory()
				.concat(new SimpleDateFormat("dd-MMM-yyyy_hh-mm-ss").format(new Date())));
		Files.move(srcDir, destDir, StandardCopyOption.REPLACE_EXISTING);

	}


}
