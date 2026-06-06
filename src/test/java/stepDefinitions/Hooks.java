package stepDefinitions;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;
import utils.DriverFactory;

public class Hooks {
	@Before
	public void setUp() {
		WebDriver driver=DriverFactory.initDriver();
		DriverFactory.setDriver(driver);
		Properties prop=ConfigReader.initProperties();
		DriverFactory.getDriver().get(prop.getProperty("url"));
		
	}
	@After
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed()) {
			byte[] screenshot=((TakesScreenshot)DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot,"image/png","Screenshot");
			
		}
		if(DriverFactory.getDriver()!=null) {
			DriverFactory.getDriver().quit();
			DriverFactory.removeDriver();
		}
		
		
	}

}
