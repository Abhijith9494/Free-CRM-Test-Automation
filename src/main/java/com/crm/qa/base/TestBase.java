package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase() {
		
		try {
			
			prop = new Properties();
			//FileInputStream ip = new FileInputStream(System.getProperty("C:\\Users\\ABHI\\eclipse-workspace\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties"));
			FileInputStream ip = new FileInputStream("C:\\Users\\ABHI\\eclipse-workspace\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void initialization() {
		 String browser = prop.getProperty("browser");
		 if (browser.equalsIgnoreCase("chrome")) {
			 System.setProperty("webdriver.chrome.driver","C:\\Users\\ABHI\\Downloads\\Selenium\\Browser Drivers\\chromedriver.exe");
			 driver = new ChromeDriver();
		 }
		 else if(browser.equalsIgnoreCase("ie")) {
			 System.setProperty("webdriver.ie.driver","C:\\Users\\ABHI\\Downloads\\Selenium\\Browser Drivers\\IEDriverServer.exe");
			 driver = new InternetExplorerDriver(); 
		 }
		 
		 e_driver = new EventFiringWebDriver(driver);
		 eventListener  = new WebEventListener();
		 e_driver.register(eventListener);
		 driver=e_driver;
		 
		 driver.manage().window().maximize();
		 driver.manage().deleteAllCookies();
		 driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		 driver.get(prop.getProperty("url"));
		 wait =new WebDriverWait(driver,10);
	}
	
	 
}
