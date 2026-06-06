package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utils.DriverFactory;

public class LoginSteps {
	LoginPage lp;
	@Given("User should be on amazon login page")
	public void user_should_be_on_amazon_login_page() {
		lp=new LoginPage(DriverFactory.getDriver());
		
	    
	}
	@When("User enters {string} and {string}")
	public void user_enters_and(String user, String pass) {
	   lp.login(user, pass);
	}
	@Then("login result should be as {string}")
	public void login_result_should_be_as(String expected) {
		if(expected.equalsIgnoreCase("valid")) {
			Assert.assertTrue(DriverFactory.getDriver().getTitle().contains("Amazon.in"),"Valid login failed");
		}
		else {
			Assert.assertTrue(lp.isErrorMessageDisplayed(),"Error message not displayed");
		}
	    
	}

}
