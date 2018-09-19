package com.qa.ryledra.TheDemoSite;

import com.relevantcodes.extentreports.ExtentReports;

public class Constants {
	public static String url = "http://thedemosite.co.uk/";
	
	public static final String cDFilePath = "C:\\Testing\\chromedriver.exe";
	
	public static final String driverType = "webdriver.chrome.driver";
	
	public static final String dataPath = "C:\\Users\\Admin\\Desktop\\EclipseWorkspace\\TheDemoSite\\";
	
	public static final String fileName = "data.xlsx";
	
	public static ExtentReports report = new ExtentReports(
			"C:\\Users\\Admin\\Desktop\\Automated Testing\\Reports\\DemoSite.html", true);
}
