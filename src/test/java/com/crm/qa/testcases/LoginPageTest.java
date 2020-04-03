package com.crm.qa.testcases;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.*;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void init() {
		initialization();
		loginPage = new LoginPage();
	}
	
	@Test
	 public void loginTest() {
		loginPage.applicationLogin();
	}
	
	@Test
	public void verifyTitleTest() {
		String title = loginPage.verifyTitle();
		System.out.println(title);
		Assert.assertEquals("Cogmento CR",title,"Wrong application title displayed");
		
	}
	
	
	@AfterMethod
	public void teardown(){
		driver.quit();
	}

}
