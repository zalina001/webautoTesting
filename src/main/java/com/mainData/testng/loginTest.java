package com.mainData.testng;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mainData.PageAction.LoginPageAction;
import com.util.ReadExcel;

public class loginTest {
	LoginPageAction loginPageAction = new LoginPageAction();

	@Test(dataProvider = "loginProvider")
	public void testlogin(String userNamevalue, String passwordvalue, String url) {
		loginPageAction.click_login(userNamevalue, passwordvalue, url);
	} 

	@Test(dataProvider = "loginProvider")
	public void testlogin_out(String userNamevalue, String passwordvalue, String url) {
		loginPageAction.click_login_out(userNamevalue, passwordvalue, url);
	}

	@DataProvider(name = "loginProvider")
	public Object[][] dataprovides() {
		return ReadExcel.readExcel_XLSX("E:\\testData\\YKKData.xlsx", "YKKData1");
	}

	
	@AfterTest
	public void shutDown() {
//		loginPageAction.tearDown();

	}

}
