package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	WebDriver driver;
	
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath="//h2[text()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath="//a[text()='Logout' and @class=\"list-group-item\"]")
	WebElement logoutLink;
	
	public boolean getMyAccountPageHeader()
	{
		
		try {
			
			return (msgHeading.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public void logoutFromAccount()
	{
		logoutLink.click();
	}
	
	
	
	
	
}
