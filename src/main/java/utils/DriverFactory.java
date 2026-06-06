package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
	private static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<>();
	public static WebDriver initDriver() {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
		
	}
	public static void setDriver(WebDriver driver) {
		tlDriver.set(driver);
		
	}
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	public static void removeDriver() {
		tlDriver.remove();
	}

}
