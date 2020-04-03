package com.crm.qa.pages;

import org.openqa.selenium.By;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
public class LoginPage extends TestBase{
	
	HomePage homePage;

	public By txtEmail = By.name("email");
	public By txtPassword = By.name("password");
	public By btnSubmit = By.xpath("/html/body/div/div/div/form/div/div[3]");
	public By lnkForgotPassword = By.linkText("Forgot your password?");
	
	public String verifyTitle() {
		return driver.getTitle();			
	}
	
	public HomePage applicationLogin() {
		driver.findElement(txtEmail).sendKeys(prop.getProperty("username"));
		driver.findElement(txtPassword).sendKeys(prop.getProperty("password"));
		driver.findElement(btnSubmit).click();
		return new HomePage();
		
	}

	
}
