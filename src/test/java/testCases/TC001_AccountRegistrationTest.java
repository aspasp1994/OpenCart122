package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistration;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups= {"Regression","Master"})
	public void verifyAccountRegistration() {
		
		logger.info("********** Starting TC001_AccountRegistrationTest ********");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on MyAccount link");

		hp.clickRegister();
		logger.info("clicked on Register link");

		AccountRegistration regiPage = new AccountRegistration(driver);
		logger.info("Providing customer details");


		regiPage.setFirstName(randomString().toUpperCase());
		regiPage.setLastName(randomString().toUpperCase());
		regiPage.setEmail(randomString() + "@gmail.com");
		regiPage.setMobileNo(randomNumber());

		String pass = randomAlphaNumber();
		regiPage.setPass(pass);
		regiPage.setConfPass(pass);

		regiPage.checkPolicy();
		regiPage.clickContinue();

		String confMsg = regiPage.getConfirmationMsg();
		logger.info("validating expected message");

		Assert.assertEquals(confMsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error("Test Failed..");
			logger.debug("Debug logs..");
			Assert.fail();
			
		}
		
		logger.info("*** Finished TC001_AccountRegistrationTest *******");

	}

}
