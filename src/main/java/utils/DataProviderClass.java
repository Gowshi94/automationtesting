package utils;

import java.util.Properties;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	@DataProvider(name="loginData")
	public static Object[][] getData() throws Exception{
		return ExcelUtils.getTestData("src/test/resources/shop_testdata.xlsx","Sheet1");
	}
	@DataProvider(name="searchData")
	public static Object[][] getsearchData() throws Exception{
		return ExcelUtils.getTestData("src/test/resources/shop_testdata.xlsx","Sheet2");
	}
	@DataProvider(name="cartData",parallel=true)
	public static Object[][] getcartData() throws Exception{
		return ExcelUtils.getTestData("src/test/resources/shop_testdata.xlsx","Sheet3");
	}
}