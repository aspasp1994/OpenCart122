package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement userNameBox;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement passwordBox;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginBtn;

	public void enterUserName(String userName) {
		userNameBox.sendKeys(userName);
	}

	public void enterPassword(String password) {
		passwordBox.sendKeys(password);
	}

	public void loginClick()
	{
		loginBtn.click();
	}
}
