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
	DataManager data;

	@BeforeClass
	public static void initial() {
		report = Constants.report;
	}

	@Before
	public void setup() {
		System.setProperty(Constants.driverType, Constants.cDFilePath);
		driver = new ChromeDriver();
		// driver.manage().window().fullscreen();
		data = new DataManager();
	}

	@Test
	public void test()	{
		
		String name = "";
		String pass = "";
		String failures = "";

		for (int i = 1; i < data.getPhysicalNumberOfRows(); i++) {
			
			name = data.getNameAtI(i);
			pass = data.getPasswordAtI(i);

			test = report.startTest("Demo Site - "+name, "Create user and validate successful login");
			
			driver.get(Constants.url);
			
			test.log(LogStatus.INFO, "Directed to the main site");

			Index indexPage = PageFactory.initElements(driver, Index.class);
			indexPage.moveToCreateUser();

			test.log(LogStatus.INFO, "moved to 'create user' page");

			CreateUser createUserPage = PageFactory.initElements(driver, CreateUser.class);
			createUserPage.fillUserPassCreate(name, pass);
			createUserPage.submitCreate();

			test.log(LogStatus.INFO, "submitted new user info");

			createUserPage.moveToLogin();

			test.log(LogStatus.INFO, "moved to 'login' page");

			Login loginPage = PageFactory.initElements(driver, Login.class);
			loginPage.fillUserPassLogin(name, pass);
			loginPage.submitLogin();

			test.log(LogStatus.INFO, "submitted user details");

			if (loginPage.getValidation().equals("**Successful Login**"))	{
				test.log(LogStatus.PASS, "logged in successfully");
				data.writeResult(true, i);
			}
			else	{
				test.log(LogStatus.FAIL, "log in failed");
				data.writeResult(false, i);
				failures += "\n" + name + " failed to log in";
			}

			report.endTest(test);

		}
		
		assertEquals(failures, "", failures);
		// fail();
	}

	@After
	public void tearDown() throws InterruptedException {
		// Thread.sleep(3000);
		driver.quit();
	}

	@AfterClass
	public static void end() {
		report.flush();
	}
}
