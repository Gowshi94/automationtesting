package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.ProductPage;
import pages.SearchPage;
import utils.DataProviderClass;

public class CartTest extends BaseClass {
	@Test(dataProvider = "cartData",dataProviderClass = DataProviderClass.class)
	public void verifyCart(String product,String filter,String quantity,String expected) {
		SearchPage sp=new SearchPage(getDriver());
		ProductPage pp=new ProductPage(getDriver());
		
		sp.search(product);
		Assert.assertTrue(sp.isResultDisplayed(),"No results found: "+product);
		
		sp.applyFilter(filter);
		sp.clickFirstAvailableProduct(getDriver());
		pp.selectQuantity(quantity);
		pp.clickAddtoCart();
		
		int count=pp.getCartcount();
		System.out.println("Cart count after adding product "+product+" quantity: "+quantity);
		
	}

}
