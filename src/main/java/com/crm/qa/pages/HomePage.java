package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{

	@FindBy(xpath = "//span[@class='user-display']") WebElement lblUsername;
	@FindBy(xpath = "//span[contains(text(),'Contacts')]") WebElement lnkContacts;
	@FindBy(xpath = "//span[contains(text(),'Companies')]") WebElement lnkCompanies;
	@FindBy(xpath = "//span[contains(text(),'Deals')]") WebElement lnkDeals;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getHomeUsername() {
		return lblUsername.getText();
	}
	
	public ContactsPage clickLink(String link) {
		driver.findElement(By.xpath("//span[contains(text(),'"+link+"')]")).click();
		return new ContactsPage();
	}
	
	public ContactsPage clickContactsLink() {
		lnkContacts.click();
		return new ContactsPage();
	}
	
	public DealsPage clickDealsLink() {
		lnkDeals.click();
		return new DealsPage();
	}
	
}
