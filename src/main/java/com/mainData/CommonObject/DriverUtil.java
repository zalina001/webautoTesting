package com.mainData.CommonObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverUtil {
	public WebDriver driver;

	public WebDriver setIEDriver(String url) {

		System.setProperty("webdriver.ie.driver", "E:\\worspace\\ie\\IEDriverServer.64.exe");

		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		driver = new InternetExplorerDriver(ieCapabilities);

		driver.get(url);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.manage().window().maximize();// 窗口最大化

		return driver;
	}

	public WebDriver setChromeDriver(String url) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\edz\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();// 窗口最大化
		return driver;
	}

	public WebDriver setFirefoxDriver(String url) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\edz\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();// 窗口最大化
		return driver;
	}

	public void teardownBrower(WebDriver driver) {
		driver.close();

	}

}
