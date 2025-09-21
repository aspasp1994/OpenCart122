package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups= {"sanity","Master"})
	public void verifyLogin() {
		logger.info("***** starting TC002_LoginTest *****");

		try {

			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();

			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);

			lp.enterUserName(prop.getProperty("username"));
			lp.enterPassword(prop.getProperty("password"));

			lp.loginClick();

			MyAccountPage MyAcP = new MyAccountPage(driver);
			boolean Headerstatus = MyAcP.getMyAccountPageHeader();
			Assert.assertEquals(Headerstatus, true, "Login failed");

		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("****** Finished TC002_LoginTest *****");

	}

}
