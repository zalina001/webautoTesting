package com.mainData.CommonObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.util.ReadExcel;

public class DataProviders {
	private static List contentList = new ArrayList();
	private static List dataList=new ArrayList();
	private static Object[][] obj;
	

	public static Object[][] dataProvider(Method method) {
		ReadExcel readExcel = new ReadExcel();

		contentList = readExcel.switchReadExcel("E:\\testData\\YKKData.xlsx", "YKKData1");

		return null;

	}

}
