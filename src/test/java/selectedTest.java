
import java.util.concurrent.TimeUnit;

import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class selectedTest {
	
	private Navigation navigation;
@Test
	public void selecte() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\edz\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		WebDriver driver = new ChromeDriver ();
		driver.get("http://ykksoaportaluat.chinacloudsites.cn/projectmanagement");
		driver.manage().window().maximize();//�������
		driver.findElement(By.name("loginfmt")).sendKeys("asu0503217@ykkap.partner.onmschina.cn");
		driver.findElement(By.id("idSIButton9")).click();
		driver.findElement(By.name("passwd")).sendKeys("Password001");
		
		
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// ǿ�Ƶȴ�10s
		driver.findElement(By.xpath("//input[@value='��¼']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// ǿ�Ƶȴ�10s
		driver.findElement(By.id("idSIButton9")).click();
		driver.findElement(By.linkText("��ע����")).click();
		driver.findElement(By.linkText("��Ŀ����")).click();
		Select select=new Select(driver.findElement(By.xpath("//select[starts-with(@class,'form-control')]")));
		select.selectByValue("27");
//		for(WebElement e : select.getOptions()) {
//			System.out.println(select.getOptions().size());
//			System.out.println(e.getText()); 
//			System.out.println(select.getOptions());
//			e.click();
//		}

//		select.selectByIndex(1);
		 driver.findElement(By.xpath("//button[contains(text(),\"��ѯ\")]")).click();

		driver.manage().window().maximize();

		driver.quit();

	}


}
