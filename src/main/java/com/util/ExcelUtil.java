package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static ArrayList<ArrayList<Object>> readExcel(File file) {
		if (file == null) {

		}
		if (file.getName().endsWith("xlsx")) {
			return readExcel(file);

		}

		else {

			return readExcel(file);
		}

	}

	public static ArrayList<ArrayList<Object>> readExcel2003(String url) {
		ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> colList;

		XSSFWorkbook xssfWorkbook = null;
		XSSFSheet sheet = null;
		XSSFRow xssfRow=null;
		XSSFCell cell;
		
		File file = new File(url);
		try {
			InputStream iStream = new FileInputStream(file);

			xssfWorkbook = new XSSFWorkbook(iStream);

			if (xssfWorkbook == null) {
				System.out.println("未读取到内容");
				return null;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>();

		int sheetSize = xssfWorkbook.getNumberOfSheets();
		for (int numSheet = 0; numSheet < sheetSize; numSheet++) {

			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}

			int rowNumSize = sheet.getLastRowNum();

			for (int rowNum = 0; rowNum < rowNumSize; rowNum++) {

				xssfRow=xssfSheet.getRow(rowNum);
				if(xssfRow==null)
					continue;
				
				ArrayList<String> curArrayList=new ArrayList<String>();
//				for(int columnNum=0;columnNum<args) {}
			}
			
			
		}
		

		return rowList;

	}

	public static void main(String[] args) {
		String urlString = "E:\\worspace\\WebdriverTest\\source\\百度测试用例.xlsx";

		readExcel2003(urlString);
	}

}
