package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
	private WebDriver driver;
	By lblUserName = By.cssSelector(".heading3 > td");

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	// private WebElement lblUserName = driver.findElement(By.cssSelector(".heading3
	// > td"));

	public String getUserName() {
		return driver.findElement(lblUserName).getText();
	}
}
