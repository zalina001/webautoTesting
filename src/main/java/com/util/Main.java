package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
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

public class Main {
	   /**
     * 模拟csdn登录，获取登录后的cookies保存到文件
     * 
     * @param username
     *            用户名
     * @param password
     *            密码
     * @param geckodriverpath
     *            gecko的路径（见https://github.com/mozilla/geckodriver）
     * @param savecookiepath
     *            cookies保存的路径
     * @throws Exception
     */
    public static void firfoxDriverGetCookies(String username, String password,
            String savecookiepath) throws Exception {
        // 初始化参数据
    	System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\edz\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String baseUrl = "https://passport.csdn.net/account/login?ref=toolbar";
        // 加载url
        driver.get(baseUrl);
        // 等待加载完成
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        // 获取页面元素
        WebElement zhanghao = driver.findElement(By.linkText("账号登录"));

        WebElement elemUsername = driver.findElement(By.name("username"));
        WebElement elemPassword = driver.findElement(By.name("password"));
        WebElement btn = driver.findElement(By.className("logging"));
        WebElement rememberMe = driver.findElement(By.id("rememberMe"));
        // 操作页面元素
        zhanghao.click();
        elemUsername.sendKeys(username);
        elemPassword.sendKeys(password);
        rememberMe.click();
        // 提交表单
        btn.submit();
        Thread.sleep(5000);
        driver.get("http://msg.csdn.net/");
        Thread.sleep(5000);
        // 获取cookies
        driver.manage().getCookies();
        Set<org.openqa.selenium.Cookie> cookies = driver.manage().getCookies();
        System.out.println("Size: " + cookies.size());
        Iterator<org.openqa.selenium.Cookie> itr = cookies.iterator();

        CookieStore cookieStore = new BasicCookieStore();

        while (itr.hasNext()) {
            Cookie cookie = itr.next();
            BasicClientCookie bcco = new BasicClientCookie(cookie.getName(), cookie.getValue());
            bcco.setDomain(cookie.getDomain());
            bcco.setPath(cookie.getPath());
            cookieStore.addCookie(bcco);
        }
        // 保存到文件
        OutputStreamWriter oos = new OutputStreamWriter(new FileOutputStream(new File(savecookiepath)),"gbk");
        oos.write(cookieStore.toString());
        oos.close();

    }

    public static void main(String[] args) throws Exception {

        firfoxDriverGetCookies("18721110242","wo04031992z","E:\\testData\\a.txt");
    }
}