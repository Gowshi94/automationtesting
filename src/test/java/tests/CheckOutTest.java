package tests;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import pages.SearchPage;
import utils.DataProviderClass;

public class CheckOutTest extends BaseClass{
	
	/*public void switchToLatestTab() {
		ArrayList<String> tabs=new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabs.size()-1));
	}*/
	@Test
	@Parameters({"username","password","product1","filter1","qty1","product2","filter2","qty2"})
	public void verifyCheckout(String user,String pass,String product1,String filter1,String qty1,String product2,String filter2,String qty2) throws Exception {
		SearchPage sp=new SearchPage(getDriver());
		ProductPage pp=new ProductPage(getDriver());
		CartPage cp=new CartPage(getDriver());
		LoginPage lp=new LoginPage(getDriver());
		lp.login(user,pass);
		sp.search(product1);
		Assert.assertTrue(sp.isResultDisplayed(),"No results found "+product1);
		sp.applyFilter(filter1);
		sp.clickFirstAvailableProduct(getDriver());
		//switchToLatestTab();
		pp.selectQuantity(qty1);
		pp.clickAddtoCart();
		getDriver().get("https://www.amazon.in");
		Thread.sleep(3000);
		
		
		sp.search(product2);
		Assert.assertTrue(sp.isResultDisplayed(),"No results found "+product2);
		sp.applyFilter(filter2);
		sp.clickFirstAvailableProduct(getDriver());
		//switchToLatestTab();
		pp.selectQuantity(qty2);
		pp.clickAddtoCart();
		
		ArrayList<String> tabs=new ArrayList<>(getDriver().getWindowHandles());
		for(int i=tabs.size()-1;i>0;i--) {
			getDriver().switchTo().window(tabs.get(i));
			getDriver().close();
		}
		getDriver().switchTo().window(tabs.get(0));
		Thread.sleep(3000);
		cp.goToCart();
		System.out.println("cart page title: "+getDriver().getTitle());
		System.out.println("cart page url: "+getDriver().getCurrentUrl());
		CartPage cpp=new CartPage(getDriver());
		cpp.proceedToBuy();
		//Assert.assertTrue(lp.isLoginPageDisplayed(),"Sign in page not displayed");
	}

}
