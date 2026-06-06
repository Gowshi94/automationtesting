package base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.ConfigReader;
import utils.DriverFactory;

public class BaseClass {
	@BeforeMethod
	public void setUp() {
		WebDriver d=DriverFactory.initDriver();
		DriverFactory.setDriver(d);
		Properties prop=ConfigReader.initProperties();
		DriverFactory.getDriver().get(prop.getProperty("url"));
	}
	@AfterMethod
	public void tearDown() {
		if(DriverFactory.getDriver()!=null) {
			DriverFactory.getDriver().quit();
			DriverFactory.removeDriver();
		}
		
	}
	public static WebDriver getDriver() {
		return DriverFactory.getDriver();
	}
	
	

}
