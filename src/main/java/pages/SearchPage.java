package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
	
	WebDriver driver;
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	private WebElement searchTab;
	@FindBy(xpath="//input[@id='nav-search-submit-button']")
	private WebElement searchBtn;
	@FindBy(id="s-result-sort-select")
	private WebElement sortDropdown;
	@FindBy(xpath="//a[contains(@class,'s-link-style a-text-normal')]")
	private WebElement firstProduct;
	@FindBy(xpath="//a[contains(@class,'s-link-style a-text-normal')]")
	private List<WebElement> allProducts;
	
	public void search(String product) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(searchTab)).click();
		searchTab.clear();
		searchTab.sendKeys(product);
		searchBtn.click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(@class,'s-link-style a-text-normal')]")));
	}
	
	/*public void searchFilter(String filter) {
		try {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)).click();
		driver.findElement(By.xpath("//a[contains(text(),'"+filter+"')]")).click();
	}
		catch(Exception e) {
			System.out.println("No filter selected: "+e.getMessage());
		}
	}*/
	
	public boolean isResultDisplayed() {
		try {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
			List<WebElement> results=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(@class,'s-link-style a-text-normal')]")));
			return results.size()>0;
			
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public boolean isFilterApplied(String filter) {
		try {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(sortDropdown));
			Select select=new Select(sortDropdown);
			String option=select.getFirstSelectedOption().getText();
			return option.equalsIgnoreCase(filter);
		}
		catch(Exception e) {
			return false;
		}
	}
	public void applyFilter(String filter) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		WebElement dropDown=wait.until(ExpectedConditions.elementToBeClickable(By.id("s-result-sort-select")));
		Select select=new Select(dropDown);
		select.selectByVisibleText(filter);
	}
	
	public String getFirstProductTitle() {
		try {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(firstProduct));
			return firstProduct.getText();
		}
		catch(Exception e) {
			return "";
		}
	}
	public void clickFirstProduct() {
		try {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
	}
	catch(Exception e) {
		System.out.println("First product click failed: "+e.getMessage());
	}
	}
	public void clickFirstAvailableProduct(WebDriver driver) {
		try {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		List<WebElement> products=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(@class,'s-link-style a-text-normal')]")));
		for(WebElement product:products) {
			try{
				String productUrl=product.getAttribute("href");
				if(productUrl==null) continue;
				driver.get(productUrl);
				
			WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(5));
			wait2.until(ExpectedConditions.presenceOfElementLocated(By.id("add-to-cart-button")));
			System.out.println("Available product found: "+driver.getTitle());
			return;
		}catch(Exception e) {
			System.out.println("Not available");
			driver.navigate().back();

		}
		}
		}
		catch(Exception e) {
			System.out.println("No available product"+e.getMessage());
		}
		
		}
		
	}


