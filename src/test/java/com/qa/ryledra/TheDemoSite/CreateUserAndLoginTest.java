package com.qa.ryledra.TheDemoSite;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CreateUserAndLoginTest {
	
	public static ExtentReports report;
	public ExtentTest test;
	
	WebDriver driver;
	String url = "http://thedemosite.co.uk/";
	final String cDFilePath = "C:\\Testing\\chromedriver.exe";
	final String driverType = "webdriver.chrome.driver";
	final String name = "Bobby";
	final String pass = "hello";
	
	@BeforeClass
	public static void initial()	{
		report = new ExtentReports("C:\\Users\\Admin\\Desktop\\Automated Testing\\Reports\\DemoSite.html", true);
	}
	
	@Before
	public void setup()	{
		System.setProperty(driverType, cDFilePath);
		driver = new ChromeDriver();
		// driver.manage().window().fullscreen();
		driver.get(url);
	}
	
	@Test
	public void test()	{
		
		test = report.startTest("Demo Site1", "Create user and validate successful login");
		
		Index indexPage = PageFactory.initElements(driver, Index.class);
		indexPage.moveToCreateUser();
		
		test.log(LogStatus.INFO, "moved to 'create user' page");
		
		CreateUser createUserPage = PageFactory.initElements(driver, CreateUser.class);
		createUserPage.fillUserPassCreate();
		createUserPage.submitCreate();
		
		test.log(LogStatus.INFO, "submitted new user info");
		
		createUserPage.moveToLogin();
		
		test.log(LogStatus.INFO, "moved to 'login' page");
		
		Login loginPage = PageFactory.initElements(driver, Login.class);
		loginPage.fillUserPassLogin();
		loginPage.submitLogin();
		
		test.log(LogStatus.INFO, "submitted user details");
		
		if(loginPage.getValidation().equals("**Successful Login**")) test.log(LogStatus.PASS, "logged in successfully");
		else test.log(LogStatus.FAIL, "log in failed");
		
		assertEquals("Login not successful", "**Successful Login**", loginPage.getValidation());
		
		report.endTest(test);
		
		//fail();
	}
	
	@After
	public void tearDown() throws InterruptedException	{
		//Thread.sleep(3000);
		driver.quit();
	}
	
	@AfterClass
	public static void end()	{
		report.flush();
	}
}
