package com.qa.ryledra.TheDemoSite;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login {
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input")
	private WebElement usernameLogin;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input")
	private WebElement passwordLogin;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input")
	private WebElement buttonLogin;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")
	private WebElement loginVal;
	
	public void fillUserPassLogin(String name, String pass)	{
		usernameLogin.sendKeys(name);
		passwordLogin.sendKeys(pass);
	}
	
	public void submitLogin()	{
		buttonLogin.click();
	}
	
	public String getValidation()	{
		return loginVal.getText();
	}
}
