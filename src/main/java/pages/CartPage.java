package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	WebDriver driver;
	public CartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//input[@name='proceedToRetailCheckout']")
	private WebElement proceedBtn;
	@FindBy(id="nav-cart")
	private WebElement cartIcon;
	
	public void proceedToBuy() {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		System.out.println("cart page title: "+driver.getTitle());
		System.out.println("cart page url: "+driver.getCurrentUrl());
		wait.until(ExpectedConditions.elementToBeClickable(proceedBtn));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",proceedBtn);
		js.executeScript("arguments[0].click();",proceedBtn);
	
		
	}
	public void goToCart() {
		cartIcon.click();
	}

}
