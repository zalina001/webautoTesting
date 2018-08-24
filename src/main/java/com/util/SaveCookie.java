package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.mainData.CommonObject.DriverUtil;
import com.mainData.PageAction.LoginPageAction;

public class SaveCookie {

	public static Set<Cookie> getCookies(String username, String Password, String url) throws Exception, IOException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\edz\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.name("loginfmt")).clear();
		driver.findElement(By.name("loginfmt")).sendKeys(username);
		driver.findElement(By.id("idSIButton9")).click();
		driver.findElement(By.name("passwd")).clear();
		driver.findElement(By.name("passwd")).sendKeys(Password);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// 强制等待10s
		driver.findElement(By.xpath("//input[@value='登录']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// 强制等待10s
		driver.findElement(By.id("idSIButton9")).click();

		// Assert.assertTrue(driver.getCurrentUrl().contains(url));

		//
		Set<Cookie> cookies = driver.manage().getCookies();
		// System.out.println(cookies.size()+"size");
		// Iterator<org.openqa.selenium.Cookie> itr = cookies.iterator();
		// CookieStore cookieStore = new BasicCookieStore();
		// while (itr.hasNext()) {
		// Cookie cookie = itr.next();
		// BasicClientCookie bcco = new BasicClientCookie(cookie.getName(),
		// cookie.getValue());
		// bcco.setDomain(cookie.getDomain());
		// bcco.setPath(cookie.getPath());
		// cookieStore.addCookie(bcco);
		// }
		// // 保存到文件 转编码
		// OutputStreamWriter oos = new OutputStreamWriter(new FileOutputStream(new
		// File("E:\\testData\\a.txt")), "gbk");
		// oos.write(cookieStore.toString());
		// oos.close();
		driver.close();
		// driver.manage().deleteAllCookies();
		return cookies;
	}

	
	
	
	public void getcookie(WebDriver driver) {

		try {
			Set<Cookie> cookies = getCookies("LSK1702009@ykkap.partner.onmschina.cn", "Password001",
					"https://ykksoadev-webdev.chinacloudsites.cn");
			for (Cookie cookie : cookies) {
				driver.manage().addCookie(cookie);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\edz\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://ykksoadev-webdev.chinacloudsites.cn/");
		SaveCookie saveCookie = new SaveCookie();
		saveCookie.getcookie(driver);
		driver.get("https://ykksoadev-webdev.chinacloudsites.cn/defaultPage");

		// System.out.println(getCookies("LSK1702009@ykkap.partner.onmschina.cn",
		// "Password001",
		// "https://ykksoadev-webdev.chinacloudsites.cn"));
	}

}
