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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel2 {
	static String celldata = null;
	static String celldata1 = null;
	static List<String> contentList = new ArrayList<String>();
	static Map<String, String> mapFactory = new HashMap<String, String>();

	/*
	 * 判断excel的格式
	 */

	public Map<String,String> switchReadExcel(File file, int colum,int colum1) {

		Map<String,String> map=new HashMap<String, String>();
		if (file == null) {
			return null;
		}
		
		if (file.getName().endsWith("xlsx")) {
			 System.out.println(file+"2007________"+readExcel_XLSX(file, colum, colum1).size());

			 return readExcel_XLSX(file, colum,colum1);

		} else {
			System.out.println(file+"-------------"+readExcel_XLS(file, colum, colum1).size());
			return  readExcel_XLS(file, colum,colum1);
		}

	}

	// 读取2007xlsx

	public static Map<String,String> readExcel_XLSX(File file, int colum,int column1) {

		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);

			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			// HSSFRow row = sheet.getRow(colum);
			int rows = sheet.getLastRowNum();
			// System.out.println(rows);
			for (int i = 1; i < rows; i++) {
				// System.out.println(i);

				XSSFCell cell = sheet.getRow(i).getCell(colum);
				
				celldata = cell.toString();
				celldata1 = sheet.getRow(i).getCell(column1).toString();
				mapFactory.keySet();
				mapFactory.put(celldata, celldata1);
//				celldata = cell.toString();
//				contentList.add(celldata);
				if (cell == null)
					break;

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
		return mapFactory;

	}

	// 读取2003版本xls
	public static Map<String, String> readExcel_XLS(File file, int colum, int column1) {
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
				celldata = cell.toString();
				celldata1 = sheet.getRow(i).getCell(column1).toString();
				mapFactory.keySet();
				mapFactory.put(celldata, celldata1);
				// contentList.add(celldata);
				if (cell == null)
					break;

				// System.out.println(cell);
				// if (cell != null) {
				// celldata = sheet.getRow(i).getCell(colum).toString();
				// System.out.println(celldata);
				// if (!StringUtils.isNotBlank(celldata)) {
				//
				// contentList.add(celldata);
				//
				// } else {
				// break;
				// }
				// } else {
				// break;
				// }
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
		return mapFactory;

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