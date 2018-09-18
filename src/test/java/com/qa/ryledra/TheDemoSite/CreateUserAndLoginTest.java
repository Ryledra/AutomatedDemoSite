package com.qa.ryledra.TheDemoSite;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class CreateUserAndLoginTest {
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
		// driver.manage().window().fullscreen();
		driver.get(url);
	}
	
	@Test
	public void test()	{
		
		Index indexPage = PageFactory.initElements(driver, Index.class);
		indexPage.moveToCreateUser();
		
		CreateUser createUserPage = PageFactory.initElements(driver, CreateUser.class);
		createUserPage.fillUserPassCreate();
		createUserPage.submitCreate();
		createUserPage.moveToLogin();
		
		Login loginPage = PageFactory.initElements(driver, Login.class);
		loginPage.fillUserPassLogin();
		loginPage.submitLogin();
		
		assertEquals("Login not successful", "**Successful Login**", loginPage.getValidation());
		//fail();
	}
	
	@After
	public void tearDown() throws InterruptedException	{
		Thread.sleep(3000);
		driver.quit();
	}
}
