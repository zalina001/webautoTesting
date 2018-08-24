package com.mainData.PageAction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.mainData.CommonObject.DriverUtil;
import com.mainData.PageObject.LoginPageObject;

public class LoginPageAction extends LoginPageObject {
	DriverUtil driverUtil = new DriverUtil();

	 WebDriver driver = null;

	public WebDriver getUrl(String url) {
		driver = driverUtil.setChromeDriver(url);
		return driver;

	}

	public WebDriver click_login(String userNamevalue, String passwordvalue, String url) {
		getUrl(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();// 窗口最大化
		driver.findElement(By.name(userName)).clear();
		driver.findElement(By.name(userName)).sendKeys(userNamevalue);
		driver.findElement(By.id(userNameNext)).click();
		driver.findElement(By.name(Password)).clear();
		driver.findElement(By.name(Password)).sendKeys(passwordvalue);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// 强制等待10s
		driver.findElement(By.xpath(login_submit)).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// 强制等待10s
		driver.findElement(By.id(userNameNext)).click();

		return driver;
	}

	public  Set<Cookie> saveCookie(String username, String password, String url) {
		driver = click_login(username, password, url);
		Set<Cookie> cookies = driver.manage().getCookies();
		// Set<Cookie> cookies = driver.manage().getCookies();
		// System.out.println(cookies.size() + "size");
		// Iterator<org.openqa.selenium.Cookie> itr = cookies.iterator();
		// CookieStore cookieStore = new BasicCookieStore();
		// Cookie cookie1=new Cookie("username","zhaolin","/");
		// Cookie cookie2=new Cookie("password","zhaolin","/");
		//
		//
		// driver.manage().addCookie(cookie1);
		// driver.manage().addCookie(cookie2);
		// while (itr.hasNext()) {
		// Cookie cookie = itr.next();
		// BasicClientCookie bcco = new BasicClientCookie(cookie.getName(),
		// cookie.getValue());
		// bcco.setDomain(cookie.getDomain());
		// bcco.setPath(cookie.getPath());
		// cookieStore.addCookie(bcco);
		// }
		// // 保存到文件 转编码
		// OutputStreamWriter oos;
		// try {
		// oos = new OutputStreamWriter(new FileOutputStream(new
		// File("E:\\testData\\a.txt")), "gbk");
		// oos.write(cookieStore.toString());
		// oos.close();
		//
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		driver.close();
		return cookies;
	}

	
	
	public void getCookie(WebDriver driver,String username,String password,String url) {
		Set<Cookie> cookies=saveCookie(username, password, url);
		for(Cookie cookie:cookies) {
			driver=
			
			
			
		}
		
	}
	public WebDriver click_login_out(String userNamevalue, String passwordvalue, String url) {
		click_login(userNamevalue, passwordvalue, url);
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);// 强制等待10s
		WebElement element = driver.findElement(By.xpath(link_user));
		System.out.println("登录名称为：" + element.getText());

		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
		// actions.doubleClick(element);

		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].aria-expanded='true'");
		// element.get(0).click();;

		// element.click();

		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);// 强制等待10s

		driver.findElement(By.xpath(login_out)).click();

		return driver;
	}

	public void tearDown() {
		driverUtil.teardownBrower(driver);
	}

}
