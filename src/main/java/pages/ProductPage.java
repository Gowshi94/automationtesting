package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
	WebDriver driver;
	public ProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	@FindBy(id="add-to-cart-button")
	private WebElement addToCart;
	@FindBy(id="nav-cart-count")
	private WebElement cartCount;
	@FindBy(id="quantity")
	private WebElement quantityDropdown;
	
	public void clickAddtoCart() {
		try {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(driver1->((JavascriptExecutor)driver1).executeScript("return document.readyState").equals("complete"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add-to-cart-button")));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",addToCart);
		js.executeScript("arguments[0].click();",addToCart);
		System.out.println("Add to cart clicked");
		
		
	}
		catch(Exception e) {
			System.out.println("Add to cart failed: "+e.getMessage());
		}
	}
	public void selectQuantity(String quantity) {
		try {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("quantity")));
			JavascriptExecutor js=(JavascriptExecutor)driver;
			WebElement qty=driver.findElement(By.id("quantity"));
			js.executeScript("arguments[0].value=arguments[1];",qty,quantity);
			System.out.println("Quantity selected: "+quantity);
		}
		catch(Exception e) {
			System.out.println("Quantity selection failed: "+quantity);
		}
	}
	public int getCartcount() {
		try {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-cart-count")));
			String count=driver.findElement(By.id("nav-cart-count")).getText().trim();
			System.out.println("Cart count: "+count);
			return Integer.parseInt(count);
		}
		catch(Exception e) {
			return 0;
		}
	}
	

}
