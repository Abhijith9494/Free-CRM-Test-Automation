package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
public class HomePageTest extends TestBase{
	
	

	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	String sheetname = "Contacts";
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	@BeforeTest
	public void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
	}
	
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	}

	
	@BeforeMethod
	public void init() {
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.applicationLogin();
	}
	
	@Test(priority=3)
	public void verifyUsernameTest() {
		extentTest = extent.startTest("verifyUsernameTest");
		System.out.println("Login Username obtained was"+homePage.getHomeUsername());
		Assert.assertEquals("Abhijith KS", homePage.getHomeUsername(),"Wrong Username displayed in home page");
		
	}
	
	
	@Test(priority=2)
	public void navigateToContactsPage() {
		extentTest = extent.startTest("navigateToContactsPage");
		contactsPage=homePage.clickLink("Contacts");
		
		System.out.println("Clicked on Contacts link");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@DataProvider
	public String[][] getCRMTestData() {
		String[][] data = TestUtil.getTestData(sheetname);
		return data;
	}
	
	@Test(priority=1, dataProvider="getCRMTestData" )
	public void addContactTest(String fName, String lName, String comp) throws InterruptedException {
		extentTest = extent.startTest("addContactTest");
		contactsPage=homePage.clickLink("Contacts");
		homePage.clickContactsLink();
		driver.navigate().refresh();
		//wait.until(ExpectedConditions.visibilityOfElementLocated((By) contactsPage.btnNew));
		contactsPage.createContact(fName,lName,comp);
	}
	
//	@AfterMethod
//	public void teardown(){
//		driver.quit();
//	}
	
	
	@AfterMethod
public void tearDown(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS -"+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS -"+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = TestUtil.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS -" + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS -" + result.getName());

		}
		
		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		driver.quit();
	}	
	
	
}
