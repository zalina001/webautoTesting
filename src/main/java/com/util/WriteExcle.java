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
		writeMap.put("���", "0");
		writeMap.put("�û���", "zl");
		writeMap.put("����", "123456");
		
		Map<String,String> writeMap2=new HashMap<>();
		writeMap2.put("���", "1");
		writeMap2.put("�û���", "22");
		writeMap2.put("����", "123456");
	
		list01.add(writeMap);
		list01.add(writeMap2);
		writeWorkBook(list01, 3, excelXpath);

	}

	public static Workbook getWorkBook(String excelPath) throws IOException {
		// �½�һ������������Ҫд���ļ�
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
			Sheet sheet = workbook.getSheetAt(0);//����excel�еĵ�һ��sheet

//			int rowSize = sheet.getLastRowNum();
//
//			System.out.println("ԭʼ�������������������У�" + rowSize);
//			for (int i = 1; i <= rowSize; i++) {
//				Row row = sheet.getRow(i);
//				sheet.removeRow(row);
//			}
			// �����ļ��������������ӱ����������У���������sheet�������κβ�����������Ч
			out = new FileOutputStream(finalPath);
			workbook.write(out);
			/**
			 * ��Excel��д������
			 */
			for (int j = 0; j < dataList.size(); j++) {
				// ����һ�У��ӵڶ��п�ʼ������������
				Row row = sheet.createRow(j + 1);
				// �õ�Ҫ�����ÿһ����¼
				Map dataMap = dataList.get(j);
				String name = dataMap.get("���").toString();
				String address = dataMap.get("�û���").toString();
				String phone = dataMap.get("����").toString();
				for (int k = 0; k <= columnNumCount; k++) {
					// ��һ����ѭ��
					Cell first = row.createCell(0);
					first.setCellValue(name);

					Cell second = row.createCell(1);
					second.setCellValue(address);

					Cell third = row.createCell(2);
					third.setCellValue(phone);
				}
			}
			// �����ļ��������׼��������ӱ����������У���������sheet�������κβ�����������Ч
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
		System.out.println("���ݵ����ɹ�");

	}

}
