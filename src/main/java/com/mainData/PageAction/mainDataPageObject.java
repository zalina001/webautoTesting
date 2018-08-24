package com.mainData.PageAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mainData.PageObject.mainDataPagePath;

public class mainDataPageObject extends mainDataPagePath{

	 public void click_login(WebDriver driver) {
		 driver.findElement(By.linkText(login_submit)).click();
	 }
	
	
	
}
