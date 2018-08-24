package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcle {
	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";

	private static String excelXpath = "E:\\firstExcel.xlsx";

	public static void main(String[] args) {

		Map<String, String> writeMap = new HashMap<>();
		List<Map> list01 = new ArrayList<Map>();
		writeMap.put("编号", "0");
		writeMap.put("用户名", "zl");
		writeMap.put("密码", "123456");
		
		Map<String,String> writeMap2=new HashMap<>();
		writeMap2.put("编号", "1");
		writeMap2.put("用户名", "22");
		writeMap2.put("密码", "123456");
	
		list01.add(writeMap);
		list01.add(writeMap2);
		writeWorkBook(list01, 3, excelXpath);

	}

	public static Workbook getWorkBook(String excelPath) throws IOException {
		// 新建一个工作薄，需要写入文件
		Workbook wbook = null;
		File file = new File(excelPath);
		FileInputStream fis;

		fis = new FileInputStream(file);
		if (file.getName().endsWith(EXCEL_XLS)) {
			wbook = new HSSFWorkbook(fis);
		} else if (file.getName().endsWith(EXCEL_XLSX)) {

			wbook = new XSSFWorkbook(fis);

		}

		return wbook;
	}

	public static void writeWorkBook(List<Map> dataList, int columnCount, String finalPath) {
		OutputStream out = null;

		try {
			int columnNumCount = columnCount;

//			File fileXlsxFile = new File(finalPath);
			Workbook workbook = getWorkBook(finalPath);

			 System.out.println(workbook.getNumberOfNames());
			Sheet sheet = workbook.getSheetAt(0);//查找excel中的第一个sheet

//			int rowSize = sheet.getLastRowNum();
//
//			System.out.println("原始数据总行数，除属性列：" + rowSize);
//			for (int i = 1; i <= rowSize; i++) {
//				Row row = sheet.getRow(i);
//				sheet.removeRow(row);
//			}
			// 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out = new FileOutputStream(finalPath);
			workbook.write(out);
			/**
			 * 往Excel中写新数据
			 */
			for (int j = 0; j < dataList.size(); j++) {
				// 创建一行：从第二行开始，跳过属性列
				Row row = sheet.createRow(j + 1);
				// 得到要插入的每一条记录
				Map dataMap = dataList.get(j);
				String name = dataMap.get("编号").toString();
				String address = dataMap.get("用户名").toString();
				String phone = dataMap.get("密码").toString();
				for (int k = 0; k <= columnNumCount; k++) {
					// 在一行内循环
					Cell first = row.createCell(0);
					first.setCellValue(name);

					Cell second = row.createCell(1);
					second.setCellValue(address);

					Cell third = row.createCell(2);
					third.setCellValue(phone);
				}
			}
			// 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out = new FileOutputStream(finalPath);
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("数据导出成功");

	}

}
