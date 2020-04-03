package com.crm.qa.testcases;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
public class Test {
	
	public String TESTDATA_SHEET_PATH = "C:\\Users\\ABHI\\eclipse-workspace\\FreeCRMTest\\src"
			+ "\\main\\java\\com\\crm\\qa\\testdata\\FreeCRM_Testdata.xlsx";
	
	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;
	
	public Object[][] getTestData(String sheetname) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetname);
		System.out.println(sheet);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		System.out.println(sheet.getLastRowNum() + "--------" +
		sheet.getRow(0).getLastCellNum());
	
		
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				System.out.println(data[i][k]);
			}
		}
		
		return data;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test test = new Test();
		String sheetname = "Contacts";
		
		test.getTestData(sheetname);
	}

}
