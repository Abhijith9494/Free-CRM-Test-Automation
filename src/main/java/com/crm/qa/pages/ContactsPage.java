package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
public class ContactsPage extends TestBase{

	@FindBy(xpath = "//button[@class='ui linkedin button']//i[@class='edit icon']") WebElement btnNew;
	@FindBy(xpath = "//*[@name='first_name']") WebElement txtFirstname;
	@FindBy(xpath = "//*[@name='last_name']") WebElement txtLastname;
	@FindBy(xpath="//div[@name='company']//input[@class='search']") WebElement txtCompany;
	@FindBy(xpath="//div[@class='selected item addition' or @class='active selected item' or @class='selected item']") WebElement lookupCompany;
	@FindBy(xpath="//button[@class='ui linkedin button']//i[@class='save icon']") WebElement btnSave;
	
	HomePage homePage = new HomePage();
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void createContact(String fName, String lName, String comp) throws InterruptedException {
		System.out.println("Entered method createContact");
		btnNew.click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated((By) txtFirstname));
		txtFirstname.sendKeys(fName);
		txtLastname.sendKeys(lName);
		txtCompany.sendKeys(comp);
		Thread.sleep(2000);
		lookupCompany.click();
		btnSave.click();
		System.out.println("Exit method createContact");
		Thread.sleep(1000);
		homePage.clickLink("Contacts");
	}
	
	
}
