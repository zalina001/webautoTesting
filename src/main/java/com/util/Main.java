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
     * ģ��csdn��¼����ȡ��¼���cookies���浽�ļ�
     * 
     * @param username
     *            �û���
     * @param password
     *            ����
     * @param geckodriverpath
     *            gecko��·������https://github.com/mozilla/geckodriver��
     * @param savecookiepath
     *            cookies�����·��
     * @throws Exception
     */
    public static void firfoxDriverGetCookies(String username, String password,
            String savecookiepath) throws Exception {
        // ��ʼ��������
    	System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\edz\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String baseUrl = "https://passport.csdn.net/account/login?ref=toolbar";
        // ����url
        driver.get(baseUrl);
        // �ȴ��������
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        // ��ȡҳ��Ԫ��
        WebElement zhanghao = driver.findElement(By.linkText("�˺ŵ�¼"));

        WebElement elemUsername = driver.findElement(By.name("username"));
        WebElement elemPassword = driver.findElement(By.name("password"));
        WebElement btn = driver.findElement(By.className("logging"));
        WebElement rememberMe = driver.findElement(By.id("rememberMe"));
        // ����ҳ��Ԫ��
        zhanghao.click();
        elemUsername.sendKeys(username);
        elemPassword.sendKeys(password);
        rememberMe.click();
        // �ύ��
        btn.submit();
        Thread.sleep(5000);
        driver.get("http://msg.csdn.net/");
        Thread.sleep(5000);
        // ��ȡcookies
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
        // ���浽�ļ�
        OutputStreamWriter oos = new OutputStreamWriter(new FileOutputStream(new File(savecookiepath)),"gbk");
        oos.write(cookieStore.toString());
        oos.close();

    }

    public static void main(String[] args) throws Exception {

        firfoxDriverGetCookies("18721110242","wo04031992z","E:\\testData\\a.txt");
    }
}