package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchPage;
import utils.DriverFactory;

public class SearchSteps {
	SearchPage sp;
	@Given("User should be on amazon home page")
	public void user_should_be_on_amazon_home_page() {
	 sp=new SearchPage(DriverFactory.getDriver());
	    
	}
	@When("user searches for product {string}")
	public void usersearchesfor(String product) {
		sp.search(product);
	    
	}
	@When("applies {string}")
	public void appliesfilter(String filter) {
	    sp.applyFilter(filter);
	}
	@Then("search results should be displayed")
	public void search_results_should_be_displayed() {
		Assert.assertTrue(sp.isResultDisplayed(), "No results displayed");
	   	}

}
