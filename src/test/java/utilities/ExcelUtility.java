package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelUtility {

	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public String Excelpath;
	public FileOutputStream fos;

	public ExcelUtility(String filePath) {
		this.Excelpath = filePath;
	}

	public int getRowCount(String sheetName) throws IOException {
		fis = new FileInputStream(Excelpath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rowCount;
	}

	public int getCellCount(String sheetName, int rowNum) throws IOException {
		fis = new FileInputStream(Excelpath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellCount;
	}

	public String getCellData(String sheetName, int rowNum, int columnNum) throws IOException {

		fis = new FileInputStream(Excelpath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(columnNum);

		DataFormatter formatter = new DataFormatter();
		String data;

		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}

		workbook.close();
		fis.close();
		return data;
	}

	public void setCellData(String sheetName, int rowNum, int columnNum, String data) throws IOException {

		File xlFile = new File(Excelpath);
		if (!xlFile.exists()) {
			fos = new FileOutputStream(Excelpath);
			workbook = new XSSFWorkbook();
			workbook.write(fos);
		}
		fis = new FileInputStream(Excelpath);
		workbook = new XSSFWorkbook(fis);

		if (workbook.getSheetIndex(sheetName) == -1) {
			workbook.createSheet(sheetName);
		}

		sheet = workbook.getSheet(sheetName);
		if (sheet.getRow(rowNum) == null) {
			sheet.createRow(rowNum);
		}
		row = sheet.getRow(rowNum);

		cell = row.createCell(columnNum);
		cell.setCellValue(data);
		fos = new FileOutputStream(Excelpath);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}

}
