package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	@FindBy(xpath="//span[text()='Hello, sign in']")
	private WebElement signIn;
	@FindBy(xpath="//input[@id='ap_email_login']")
	private WebElement username;
	@FindBy(xpath="//input[@class='a-button-input']")
	private WebElement continueBtn;
	@FindBy(xpath="//input[@id='ap_password']")
	private WebElement password;
	@FindBy(xpath="//input[@id='signInSubmit']")
	private WebElement logIn;
	@FindBy(xpath="//div[@id='invalid-email-alert']")
	private WebElement errorMsg;
	
	public void login(String user,String pass) {
		signIn.click();
		username.sendKeys(user);
		continueBtn.click();
		try {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOf(password));
		password.sendKeys(pass);
		logIn.click();
		
	}
		catch(Exception e) {
			System.out.println("Password field not found"+e.getMessage());
		}
	}
	public boolean isErrorMessageDisplayed() {
		try {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(errorMsg));
			return errorMsg.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	public boolean isLoginPageDisplayed() {
		return username.isDisplayed();
	}

}
