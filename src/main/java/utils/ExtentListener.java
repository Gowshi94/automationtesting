package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.BaseClass;

public class ExtentListener implements ITestListener{
	private static ExtentReports extent=ExtentManager.getInstance();
	private static ThreadLocal<ExtentTest> test=new ThreadLocal<>();
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest=extent.createTest(result.getTestClass().getName()+"-"+result.getMethod().getMethodName());
		test.set(extentTest);
		test.get().info("Test Started: "+result.getMethod().getMethodName());
		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS,"Test Passed: "+result.getMethod().getMethodName());
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		test.get().log(Status.FAIL,"Test Failed: "+result.getThrowable().getMessage());
		try {
			WebDriver driver=BaseClass.getDriver();
			if(driver!=null) {
				String base64=((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
				test.get().fail("Screenshot on failure: ",MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
				
			}
		}
		catch(Exception e) {
			test.get().info("Screenshot failed: "+e.getMessage());
		}
		
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().log(Status.SKIP,"Test Skipped: "+result.getMethod().getMethodName());
		
	}
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
