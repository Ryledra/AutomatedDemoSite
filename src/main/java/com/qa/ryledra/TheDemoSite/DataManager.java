package com.qa.ryledra.TheDemoSite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataManager {

	private FileInputStream file;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public DataManager() {

		file = null;
		try {
			file = new FileInputStream(Constants.dataPath + Constants.fileName);
		} catch (FileNotFoundException e) {
		}

		workbook = null;
		try {
			workbook = new XSSFWorkbook(file);
		} catch (IOException e) {
		}

		sheet = workbook.getSheetAt(0);
	}

	public int getPhysicalNumberOfRows() {
		return sheet.getPhysicalNumberOfRows();
	}

	public String getNameAtI(int i) {
		return sheet.getRow(i).getCell(0).getStringCellValue();
	}

	public String getPasswordAtI(int i) {
		return sheet.getRow(i).getCell(1).getStringCellValue();
	}

	public void writeResult(boolean passed, int i)	{

		try {
			if (passed)
				sheet.getRow(i).getCell(2).setCellValue("Pass");
			else
				sheet.getRow(i).getCell(2).setCellValue("Fail");
			
			FileOutputStream fileOut = new FileOutputStream(Constants.dataPath + Constants.fileName);

			workbook.write(fileOut);

			fileOut.flush();
			fileOut.close();
		} catch (NullPointerException npx) {
			XSSFRow Row = sheet.getRow(i);
			XSSFCell Cell = Row.createCell(2);
			writeResult(passed, i);
		} catch (IOException e) {
			System.out.println("you left the .xlsx file open");
		}
	}
}
