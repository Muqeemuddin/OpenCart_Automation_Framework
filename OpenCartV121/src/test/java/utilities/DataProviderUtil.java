package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {
	
	@DataProvider(name="dpLoginData")
	public Object[][] dataProvider() throws IOException{
		String filePath = ".//testData//OpenCart_Login_data.xlsx";
		ExcelUtilities excelUtil = new ExcelUtilities(filePath);
		int rowCount = excelUtil.getRowCount("loginDataSheet");
		int cellCount = excelUtil.getCellCount("loginDataSheet");
		Object[][] loginData = new Object[rowCount][cellCount] ;
		for(int row = 1; row<=rowCount; row++) {
			for(int col=0; col<cellCount; col++) {
				loginData[row-1][col] = excelUtil.getCellData("loginDataSheet", row, col);
			}
		}
		
		return loginData;
		
	}

}
