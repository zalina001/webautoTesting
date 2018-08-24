package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {
	static String celldata[] = null;
	static String celldata1 = null;
	static List contentList = new ArrayList();
	static List dataList = new ArrayList();
	private static Object[][] obj;

	static Map<String, String> mapFactory = new HashMap<String, String>();

	/*
	 * 判断excel的格式
	 */

	public List switchReadExcel(String filepath, String sheetName) {
		File file = new File(filepath);
		if (filepath == null) {
			return null;
		}

		if (file.getName().endsWith("xlsx")) {

			// contentList=readExcel_XLSX(filepath, sheetName);

		} else {
			// readExcel_XLS(file, colum);
		}
		return contentList;

	}

	// 读取2007xlsx

	public static Object[][] readExcel_XLSX(String filepath, String sheetName) {
		Object[][] resultsObjects = null;
		File file = new File(filepath);
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int lastNum = sheet.getLastRowNum();
			int firstNum = sheet.getFirstRowNum();// 获取除了title以外的内容
			for (int i = 1; i < lastNum + 1; i++) {
				XSSFRow row = sheet.getRow(i);// 获取行对象
				int colunm = row.getLastCellNum();
				celldata = new String[row.getLastCellNum()];
				for (int j =0; j < colunm; j++) {
					celldata[j] = row.getCell(j).getStringCellValue();
				}
				contentList.add(celldata);
			}
			resultsObjects = new Object[contentList.size()][];
			for (int i = 0; i < contentList.size(); i++) {
				resultsObjects[i] = (Object[]) contentList.get(i);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultsObjects;
	}

	
			
			
			
	// 读取2003版本xls
	public static List<String> readExcel_XLS(File file, int colum) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);

			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = workbook.getSheetAt(0);
			// HSSFRow row = sheet.getRow(colum);
			int rows = sheet.getLastRowNum();
			// System.out.println(rows);
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				HSSFCell cell = sheet.getRow(i).getCell(colum);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return contentList;

	}

	// 去读Excel的方法readExcel，该方法的入口参数为一个File对象
	public List readExcel(File file) {
		try {
			// 创建输入流，读取Excel
			// InputStream is = new FileInputStream(file);
			// jxl提供的Workbook类
			Workbook wb = Workbook.getWorkbook(file);
			// Excel的页签数量
			Sheet sheet1 = wb.getSheet(0);
			System.out.println(sheet1.getRows());
			int sheet_size = wb.getNumberOfSheets();
			System.out.println(sheet_size + "===========");
			for (int index = 0; index < sheet_size; index++) {
				List<List> outerList = new ArrayList<List>();
				// 每个页签创建一个Sheet对象
				Sheet sheet = wb.getSheet(index);
				// sheet.getRows()返回该页的总行数
				for (int i = 0; i < sheet.getRows(); i++) {
					List innerList = new ArrayList();
					// sheet.getColumns()返回该页的总列数
					for (int j = 0; j < sheet.getColumns(); j++) {
						String cellinfo = sheet.getCell(j, i).getContents();
						if (cellinfo.isEmpty()) {
							continue;
						}
						innerList.add(cellinfo);
						System.out.print(cellinfo);
					}
					outerList.add(i, innerList);
					System.out.println();
				}
				return outerList;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}