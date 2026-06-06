package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ProductPage;
import pages.SearchPage;
import utils.DriverFactory;

public class CartSteps {
	SearchPage sp;
	ProductPage pp;
	@Given("User should be on home page")
	public void user_should_be_on_home_page() {
	    sp=new SearchPage(DriverFactory.getDriver());
	}
	@When("User searches for {string}")
	public void user_searches_for(String product) {
	    sp.search(product);
	}
	@When("applies filter {string}")
	public void applies(String filter) {
		sp.applyFilter(filter);
	}
	
	   
	@When("selects first available product")
	public void selects_first_available_product() {
	    sp.clickFirstAvailableProduct(DriverFactory.getDriver());
	}
	@When("selects {string}")
	public void selects(String quantity) {
	    pp=new ProductPage(DriverFactory.getDriver());
	    pp.selectQuantity(quantity);
	}
	@When("clicks add to cart button")
	public void clicks_add_to_cart_button() {
		pp.clickAddtoCart();
	    
	}
	@Then("cart count should be updated")
	public void cart_count_should_be_updated() {
		int count=pp.getCartcount();
		System.out.println("Cart count: "+count);
		assert count>0 : "Cart count not updated";
	    
	}

}
