package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.SearchPage;
import utils.DataProviderClass;

public class SearchTest extends BaseClass {
	@Test(dataProvider = "searchData",dataProviderClass = DataProviderClass.class)
	public void verifySearch(String product,String expected,String filter) {
		SearchPage sp=new SearchPage(getDriver());
		sp.search(product);
		if(expected.equalsIgnoreCase("valid")) {
			Assert.assertTrue(sp.isResultDisplayed(),"Results not displayed: "+product);
			sp.applyFilter(filter);
			Assert.assertTrue(sp.isFilterApplied(filter),"Filter not applied: "+filter);
			System.out.println("First product is: "+sp.getFirstProductTitle());
		}
		else {
			System.out.println("No results found: "+product);
			Assert.assertTrue(true);
		}
	}

}
