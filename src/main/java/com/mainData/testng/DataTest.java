package com.mainData.testng;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mainData.CommonObject.DriverUtil;
import com.mainData.PageAction.mainDataPageObject;

public class DataTest {
	mainDataPageObject pageObject = new mainDataPageObject();
	DriverUtil driverutil = new DriverUtil();
	WebDriver driver;

	@BeforeSuite
	public void beforeSuite() {
		driver = driverutil.setChromeDriver("https://ykksoadev-webdev.chinacloudsites.cn");
		System.out.println("Before Suite success....");
	}

	@AfterSuite
	public void afterSuite() {
		driverutil.teardownBrower(driver);
		System.out.println("After Suite success....");
	}

	@BeforeClass
	public void setUp() throws Exception {
		System.out.println("在测试方法之前");

	}

	@AfterClass
	public void afterclass() throws Exception {
		System.out.println("在测试方法之后");

	}

	@BeforeTest
	public void beforeTest() throws Exception {
		System.out.println("在所有用例之前之前");

	}

	@AfterTest
	public void afterTest() throws Exception {
		System.out.println("在所有用例之后");

	}

	@Test(priority=0)
	public void test() {
		
//		pageObject.click_login(driver);
		System.out.println("第一个方法");
	}

	@Test(priority=1)
	public void test1() {
		System.out.println("第二个方法");
	}
}
