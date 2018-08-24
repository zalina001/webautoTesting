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


	    //��װSelenium���÷���
	    public static WebDriver driver;
	    public static String osName = System.getProperties().getProperty("os.name");
	    //����ϵͳ

	    public static void firefox(){
	        //��װ��Firefox�����
	        if (osName.equals("Linux")){
	            System.setProperty("webdriver.firefox.marionette", "src/main/resources/geckodriver");
	        }else {
	            System.setProperty("webdriver.firefox.marionette", "src/main/resources/geckodriver.exe");
	        }
	        driver =new FirefoxDriver();
	    }

	    public static void chrome(){
	        //��װ��Chrome�����
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
	        //��װ�򿪵�ַ
	        driver.get(value);
	    }

	    public static void max(){
	        //��װ�������
	        driver.manage().window().maximize();
	    }

	    public static String pageSource(){
	        //��װ��ȡҳ��Դ��
	        return driver.getPageSource();
	    }

	    public static void quit(){
	        //��װ�˳�driver
	        driver.quit();
	    }

	    public static void xpath(String by){
	        //��װXpath
	        driver.findElement(By.xpath(by));
	    }

	    public static void click(String by){
	        //��װ���
	        driver.findElement(By.xpath(by)).click();
	    }

	    public static void drag(String by){
	        //��װ�϶���������ָ��λ��
	        WebElement target = driver.findElement(By.xpath(by));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", target);
	    }

	    public static void bottom() {
	        //��װ�϶�����������ײ�
	        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    }

	    public static void input(String by, String value){
	        driver.findElement(By.xpath(by)).sendKeys(value);
	        //��װ����
	    }

	    public static void inputClear(String by, String value){
	        //��װ�������������
	        WebElement element = driver.findElement(By.xpath(by));
	        element.clear();
	        element.sendKeys(value);
	    }

	    public static void inputBox(String by, String value){
	        //��װ�ȵ������ȫѡ�����˸�������
	        WebElement element = driver.findElement(By.xpath(by));
	        element.click();
	        element.sendKeys(Keys.CONTROL, "a");
	        element.sendKeys(Keys.BACK_SPACE);
	        element.sendKeys(value);
	    }

	    public static void select(String by, String value){
	        //��װѡ��������ByValue��
	        Select select = new Select(driver.findElement(By.xpath(by)));
	        select.selectByValue(value);
	    }

	    public static void refresh(){
	        //��װˢ�������
	        driver.navigate().refresh();
	    }

	    public static void close(){
	        //��װ�ر������
	        driver.close();
	    }

	    public static void kill(){
	        //��װɱ��Firefox����
	        WindowsUtils.killByName("firefox.exe");
	    }

	    public static void hide(){
	        //��װ��ʽ�ȴ�
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    }

	    public static String text(String by){
	        //��װ��ȡ�ı�ֵ
	        return  driver.findElement(By.xpath(by)).getText();
	    }

	    public static String attribute(String by){
	        //��װ��ȡ�������ʾ��
	        return driver.findElement(By.xpath(by)).getAttribute("placeholder");
	    }

	    public static String value(String by){
	        //��װ��ȡ������ֵ
	        return driver.findElement(By.xpath(by)).getAttribute("value");
	    }

	    public static String style(String by){
	        //��װ��ȡ������״̬
	        return driver.findElement(By.xpath(by)).getAttribute("style");
	    }

	    public static void load(String by){
	        //��װ�ȴ�ҳ��Ԫ�ؼ������
	        WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(by)));
	    }

	    public static void loadForClick(String by){
	        //��װ��ҳ���Ͽ��úͿɱ�����
	        WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(by)));
	    }

	    public static void loadForCustom(String by, String text){
	        //��װ�Զ������ʾ�ȴ�
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
	        //��װ�л����ھ��
	        Set<String> winHandels = driver.getWindowHandles(); //�õ���ǰ���ڵ�set����
	        List<String> it = new ArrayList<String>(winHandels); //��set���ϴ���list����
	        driver.switchTo().window(it.get(1)); //�л����������´���
	    }

	    public static void iframe(String value){
	        //��װ����id=value��frame��
	        driver.switchTo().frame(value);
	    }

	    public static void iframeNo(String by){
	        //��װ����û��id��frame��
	        WebElement frame=driver.findElement(By.xpath(by));
	        driver.switchTo().frame(frame);
	    }

	    public static void iframeOut(){
	        //��װ����iframe������default content
	        driver.switchTo().defaultContent();
	    }

	    public static void upFile(String by, String value){
	        //��װ��ͨ�ϴ�ͼƬ
	        driver.findElement(By.xpath(by)).sendKeys(value);
	    }

	    public static void suspend(String by){
	        //��װ�������
	        Actions action = new Actions(driver);
	        action.clickAndHold(driver.findElement(By.xpath(by)));
	    }

	    public static void actionClick(String by) {
	        //��װ�����
	        WebElement webElement = driver.findElement(By.xpath(by));
	        Actions builder = new Actions(driver);
	        Action mouserOverlogin = builder.moveToElement(webElement).click().build();
	        mouserOverlogin.perform();
	    }

	    public static void doubleClick(String by){
	        //��װ���˫��
	        Actions action = new Actions(driver);
	        action.doubleClick(driver.findElement(By.xpath(by)));
	    }

	    public static void clickJS(String by){
	        //��װʹ��JS���
	        WebElement element = driver.findElement(By.xpath(by));
	        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	    }

	    public static String randomString(int length) {
	        //��װ�����������
	        String str = "0123456789";
	        //�������ֵ��ַ���
	        Random random = new Random();//������ʼ��
	        StringBuilder sb = new StringBuilder();//StringBuffer�����ɣ�Ϊ��ƴ���ַ���

	        for (int i = 0; i < length; ++i) {
	            int number = random.nextInt(10);// [0,10)
	            sb.append(str.charAt(number));
	        }
	        return sb.toString();
	    }

	    public static String randomEnglish(int length) {
	        //��װ������ɴ�д��ĸ
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
	        //��װ��ק
	        WebElement element = driver.findElement(By.xpath(by_1));
	        WebElement target = driver.findElement(By.xpath(by_2));
	        (new Actions(driver)).dragAndDrop(element, target).perform();
	    }
	}
