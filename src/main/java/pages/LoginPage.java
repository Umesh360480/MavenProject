package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
	
	private WebDriver driver;
	By txtUserName = By.cssSelector("input[name='uid']");
	By txtPassword = By.cssSelector("input[name='password']");
	By btnLogin = By.cssSelector("input[name='btnLogin']");
		public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;		
	}
	
	public HomePage loginUser(String strUserName, String strPassword) {
		driver.findElement(txtUserName).sendKeys(strUserName);
		driver.findElement(txtPassword).sendKeys(strPassword);
		driver.findElement(btnLogin).click();
		return new HomePage(driver);
	}
	
	
}
