package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProvidersfortest;

public class TC003_LoginTestDataDriven extends BaseClass{
	
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProvidersfortest.class, groups="DataDrivenTest")
	public void verify_loginDDT(String userName, String password, String exp)
	{
		logger.info("****** Starting TC003_LoginTestDataDriven ****** ");
		
		try {
			
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		Thread.sleep(3000);
		
		LoginPage lp = new LoginPage(driver);
		lp.enterUserName(userName);
		lp.enterPassword(password);
		lp.loginClick();
		
		MyAccountPage MyAcP = new MyAccountPage(driver);
		Thread.sleep(3000);
		boolean Headerstatus = MyAcP.getMyAccountPageHeader();
		System.out.print(Headerstatus);
		System.out.print(exp);

		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(Headerstatus==true)
			{
				MyAcP.logoutFromAccount();
				Assert.assertTrue(true);
			}
			
			else {
				Assert.assertTrue(false);
			}
			
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(Headerstatus==false)
			{
				Assert.assertTrue(true, exp);
			}
			else {
				
				MyAcP.logoutFromAccount();
				Assert.assertTrue(false);
			}	
			
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("****** Finishing TC003_LoginTestDataDriven ****** ");

	}

}
