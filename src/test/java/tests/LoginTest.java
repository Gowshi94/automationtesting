package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;
import utils.DataProviderClass;

public class LoginTest extends BaseClass {
	@Test(dataProvider = "loginData",dataProviderClass = DataProviderClass.class)
	public void verifyLogin(String user,String pass,String expected) {
		LoginPage lp=new LoginPage(getDriver());
		lp.login(user, pass);
		
		if(expected.equalsIgnoreCase("valid")) {
			Assert.assertTrue(getDriver().getTitle().contains("Amazon.in"),"Valid login failed");
		}
		else {
			Assert.assertTrue(lp.isErrorMessageDisplayed(),"Error message not displayed for invalid login");
		}
	}
	

}
