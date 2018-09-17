package com.qa.ryledra.TheDemoSite;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestRunner {

	WebDriver driver;
	String url = "http://thedemosite.co.uk/";
	final String cDFilePath = "C:\\Testing\\chromedriver.exe";
	final String driverType = "webdriver.chrome.driver";
	final String name = "Bobby";
	final String pass = "hello";
	
	@Before
	public void setup()	{
		System.setProperty(driverType, cDFilePath);
		driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.get(url);
	}
	
	public void moveToCreateUser()	{
		String link = "/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]";
		WebElement createUserHL = driver.findElement(By.xpath(link));
		createUserHL.click();
	}
	
	public void fillUserPassCreate()	{
		String userXPath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input";
		String passXPath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input";
		
		WebElement username = driver.findElement(By.xpath(userXPath));
		WebElement password = driver.findElement(By.xpath(passXPath));
		
		username.sendKeys(name);
		password.sendKeys(pass);
	}
	
	public void submitCreate()	{
		String buttonXPath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input";
		WebElement button = driver.findElement(By.xpath(buttonXPath));
		button.click();
	}
	
	public void moveToLogin()	{
		String buttonXPath = "/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]";
		WebElement createUserHL = driver.findElement(By.xpath(buttonXPath));
		createUserHL.click();
	}
	
	public void fillUserPassLogin()	{
		String userXPath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input";
		String passXPath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input";
		
		WebElement username = driver.findElement(By.xpath(userXPath));
		WebElement password = driver.findElement(By.xpath(passXPath));
		
		username.sendKeys(name);
		password.sendKeys(pass);
	}
	
	public void submitLogin()	{
		String buttonXPath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input";
		WebElement createUserHL = driver.findElement(By.xpath(buttonXPath));
		createUserHL.click();
	}
	
	public String getValidation()	{
		String vaildationXPath = "/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b";
		WebElement validation = driver.findElement(By.xpath(vaildationXPath));
		// System.out.println(validation.getText());
		return validation.getText();
	}
	
	@Test
	public void test()	{
		moveToCreateUser();
		fillUserPassCreate();
		submitCreate();
		moveToLogin();
		fillUserPassLogin();
		submitLogin();
		
		assertEquals("**Successful Login**", getValidation());
		//fail();
	}
	
	@After
	public void tearDown()	{
		driver.quit();
	}
}
