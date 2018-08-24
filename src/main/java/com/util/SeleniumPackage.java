package com.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SeleniumPackage {


	    //封装Selenium常用方法
	    public static WebDriver driver;
	    public static String osName = System.getProperties().getProperty("os.name");
	    //操作系统

	    public static void firefox(){
	        //封装打开Firefox浏览器
	        if (osName.equals("Linux")){
	            System.setProperty("webdriver.firefox.marionette", "src/main/resources/geckodriver");
	        }else {
	            System.setProperty("webdriver.firefox.marionette", "src/main/resources/geckodriver.exe");
	        }
	        driver =new FirefoxDriver();
	    }

	    public static void chrome(){
	        //封装打开Chrome浏览器
	        if (osName.equals("Linux")){
	            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
	        }else {
	            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
	        }
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("disable-infobars");
	        driver =new ChromeDriver(options);
	    }

	    public static void url(String value){
	        //封装打开地址
	        driver.get(value);
	    }

	    public static void max(){
	        //封装窗口最大化
	        driver.manage().window().maximize();
	    }

	    public static String pageSource(){
	        //封装获取页面源码
	        return driver.getPageSource();
	    }

	    public static void quit(){
	        //封装退出driver
	        driver.quit();
	    }

	    public static void xpath(String by){
	        //封装Xpath
	        driver.findElement(By.xpath(by));
	    }

	    public static void click(String by){
	        //封装点击
	        driver.findElement(By.xpath(by)).click();
	    }

	    public static void drag(String by){
	        //封装拖动滚动条到指定位置
	        WebElement target = driver.findElement(By.xpath(by));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", target);
	    }

	    public static void bottom() {
	        //封装拖动滚动条到最底部
	        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    }

	    public static void input(String by, String value){
	        driver.findElement(By.xpath(by)).sendKeys(value);
	        //封装输入
	    }

	    public static void inputClear(String by, String value){
	        //封装先清除、再输入
	        WebElement element = driver.findElement(By.xpath(by));
	        element.clear();
	        element.sendKeys(value);
	    }

	    public static void inputBox(String by, String value){
	        //封装先点击、再全选、再退格、再输入
	        WebElement element = driver.findElement(By.xpath(by));
	        element.click();
	        element.sendKeys(Keys.CONTROL, "a");
	        element.sendKeys(Keys.BACK_SPACE);
	        element.sendKeys(value);
	    }

	    public static void select(String by, String value){
	        //封装选择下拉框（ByValue）
	        Select select = new Select(driver.findElement(By.xpath(by)));
	        select.selectByValue(value);
	    }

	    public static void refresh(){
	        //封装刷新浏览器
	        driver.navigate().refresh();
	    }

	    public static void close(){
	        //封装关闭浏览器
	        driver.close();
	    }

	    public static void kill(){
	        //封装杀掉Firefox进程
	        WindowsUtils.killByName("firefox.exe");
	    }

	    public static void hide(){
	        //封装隐式等待
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    }

	    public static String text(String by){
	        //封装获取文本值
	        return  driver.findElement(By.xpath(by)).getText();
	    }

	    public static String attribute(String by){
	        //封装获取输入框提示语
	        return driver.findElement(By.xpath(by)).getAttribute("placeholder");
	    }

	    public static String value(String by){
	        //封装获取输入框的值
	        return driver.findElement(By.xpath(by)).getAttribute("value");
	    }

	    public static String style(String by){
	        //封装获取输入框的状态
	        return driver.findElement(By.xpath(by)).getAttribute("style");
	    }

	    public static void load(String by){
	        //封装等待页面元素加载完成
	        WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(by)));
	    }

	    public static void loadForClick(String by){
	        //封装在页面上可用和可被单击
	        WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(by)));
	    }

	    public static void loadForCustom(String by, String text){
	        //封装自定义的显示等待
	        WebElement webElement = (new WebDriverWait(driver, 30))
	                .until(new ExpectedCondition<WebElement>() {
	                    @Override
	                    public WebElement apply(WebDriver d) {
	                        return d.findElement(By.xpath(by));
	                    }
	                });
	        Assert.assertEquals(webElement.getText(), text);
	    }

	    public static void windows(){
	        //封装切换窗口句柄
	        Set<String> winHandels = driver.getWindowHandles(); //得到当前窗口的set集合
	        List<String> it = new ArrayList<String>(winHandels); //将set集合存入list对象
	        driver.switchTo().window(it.get(1)); //切换到弹出的新窗口
	    }

	    public static void iframe(String value){
	        //封装进入id=value的frame中
	        driver.switchTo().frame(value);
	    }

	    public static void iframeNo(String by){
	        //封装进入没有id的frame中
	        WebElement frame=driver.findElement(By.xpath(by));
	        driver.switchTo().frame(frame);
	    }

	    public static void iframeOut(){
	        //封装跳出iframe，进入default content
	        driver.switchTo().defaultContent();
	    }

	    public static void upFile(String by, String value){
	        //封装普通上传图片
	        driver.findElement(By.xpath(by)).sendKeys(value);
	    }

	    public static void suspend(String by){
	        //封装鼠标悬浮
	        Actions action = new Actions(driver);
	        action.clickAndHold(driver.findElement(By.xpath(by)));
	    }

	    public static void actionClick(String by) {
	        //封装鼠标点击
	        WebElement webElement = driver.findElement(By.xpath(by));
	        Actions builder = new Actions(driver);
	        Action mouserOverlogin = builder.moveToElement(webElement).click().build();
	        mouserOverlogin.perform();
	    }

	    public static void doubleClick(String by){
	        //封装鼠标双击
	        Actions action = new Actions(driver);
	        action.doubleClick(driver.findElement(By.xpath(by)));
	    }

	    public static void clickJS(String by){
	        //封装使用JS点击
	        WebElement element = driver.findElement(By.xpath(by));
	        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	    }

	    public static String randomString(int length) {
	        //封装随机生成数字
	        String str = "0123456789";
	        //含有数字的字符串
	        Random random = new Random();//随机类初始化
	        StringBuilder sb = new StringBuilder();//StringBuffer类生成，为了拼接字符串

	        for (int i = 0; i < length; ++i) {
	            int number = random.nextInt(10);// [0,10)
	            sb.append(str.charAt(number));
	        }
	        return sb.toString();
	    }

	    public static String randomEnglish(int length) {
	        //封装随机生成大写字母
	        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        Random random = new Random();
	        StringBuilder sb = new StringBuilder();

	        for (int i = 0; i < length; ++i) {
	            int number = random.nextInt(26);
	            sb.append(str.charAt(number));
	        }
	        return sb.toString();
	    }

	    public static void pull(String by_1, String by_2){
	        //封装拖拽
	        WebElement element = driver.findElement(By.xpath(by_1));
	        WebElement target = driver.findElement(By.xpath(by_2));
	        (new Actions(driver)).dragAndDrop(element, target).perform();
	    }
	}
