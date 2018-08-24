package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelXlsx {
	public static void main(String args[]) {
		File file = new File("E:\\firstExcel.xlsx");
		readExcel(file);
	}

	public static void readExcel(File file) {
		InputStream iStream;
		try {
			iStream = new FileInputStream(file);
			Workbook workbook = null;
			if (file.getName().endsWith("xls")) {
				workbook = new HSSFWorkbook(iStream);
			} else if (file.getName().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(iStream);

				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				int rowsize = sheet.getLastRowNum();
				for (int i = 0; i <= rowsize; i++) {

					XSSFRow row = sheet.getRow(i);

					int maxsize = row.getLastCellNum();
					int minsize = row.getFirstCellNum();
					for(int col=minsize;col<maxsize;col++) {
						
						
						
					}
					
					
					
					System.out.println(rowsize + "---"+maxsize+minsize );

				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
