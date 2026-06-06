package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static Object[][] getTestData(String filepath,String sheetname) throws Exception{
		FileInputStream fis=new FileInputStream(filepath);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheet(sheetname);
		DataFormatter formatter=new DataFormatter();
		int rows=sheet.getPhysicalNumberOfRows();
		int cols=sheet.getRow(0).getLastCellNum();
		
		Object[][] data=new Object[rows-1][cols];
		for(int i=1;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				data[i-1][j]=formatter.formatCellValue(sheet.getRow(i).getCell(j));
			}
		}
		wb.close();
		return data;
	}

}
