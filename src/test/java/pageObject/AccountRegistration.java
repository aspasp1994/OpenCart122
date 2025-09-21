package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistration extends BasePage {

	public AccountRegistration(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement firstIn;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement lastIn;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement emailIn;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement mobileNoIn;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement passIn;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement confPassIn;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement policyCheck;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement conBtn;

	@FindBy(xpath = "//div[@id='content']/h1")
	WebElement msgConf;

	public void setFirstName(String fName) {
		firstIn.sendKeys(fName);
	}

	public void setLastName(String lName) {
		lastIn.sendKeys(lName);
	}

	public void setEmail(String email) {
		emailIn.sendKeys(email);
	}

	public void setMobileNo(String moNo) {
		mobileNoIn.sendKeys(moNo);
	}

	public void setPass(String pass) {
		passIn.sendKeys(pass);
	}

	public void setConfPass(String confPass) {
		confPassIn.sendKeys(confPass);
	}

	public void checkPolicy() {
		policyCheck.click();
	}

	public void clickContinue() {
		conBtn.click();
	}

	public String getConfirmationMsg() {
		try {
			return (msgConf.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

}
