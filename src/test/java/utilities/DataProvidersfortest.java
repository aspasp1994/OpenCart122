package utilities;

import java.io.IOException;
import java.util.Arrays;

import org.testng.annotations.DataProvider;

public class DataProvidersfortest {

	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException {
		
		String path = ".\\testData\\testData.xlsx";
		ExcelUtility excelUtility = new ExcelUtility(path);

		int totalRows = excelUtility.getRowCount("data1");
		int totalCell = excelUtility.getCellCount("data1", 1);

		String loginData[][] = new String[totalRows][totalCell];

		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCell; j++) {
				loginData[i-1][j] = excelUtility.getCellData("data1", i, j);
			}
		}
		
		return loginData;

	}

}
