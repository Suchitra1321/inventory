package com.genericUtil;

import java.sql.SQLException;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;



public class BaseClass 
{
	public DatabaseUtils dlib=new DatabaseUtils();
	public FileUtils flib=new FileUtils();
	public ExcelUtils elib= new ExcelUtils();
	public JavaUtils jlib=new JavaUtils();
	public WebDriverUtils wlib=new WebDriverUtils();
	
	public static WebDriver driver;
	
	@BeforeSuite(alwaysRun = true)
	public void connectToDB() throws SQLException 
	{
		dlib.connectToDatabase();
		System.out.println("---connect to DB---");
	}
	
	
	//@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void launchBrowser() throws Throwable
	{
		String browser = flib.readDataFromPropertyFile("browser");
		String url = flib.readDataFromPropertyFile("url");
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			System.out.println("invalid browser");
		}
		
		
		//maximize browser
		wlib.maximizeWindow(driver);
		
		//enter url
		driver.get(url);
		
		//wait for page load
		wlib.waitForPageLoad(driver, 10);
		
		System.out.println("---launch the browser---");
	}
	
//	@BeforeMethod
//	public void loginToApplication() throws Throwable
//	{
//		String admin_un = flib.readDataFromPropertyFile("admin_un");
//		String admin_pass = flib.readDataFromPropertyFile("admin_pass");
//		
//		LoginPage lp = new LoginPage(driver);
//		lp.loginToApp(admin_un, admin_pass, driver);
//		
//		System.out.println("----login to app----");
//	}
	
//	@AfterMethod
//	public void logoutFromApp()
//	{
//		HomePage hp = new HomePage(driver);
//		hp.adminLogout();
//		
//		System.out.println("----logout from app----");
//	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser()
	{
		driver.quit();
		System.out.println("----close the browser---");
	}
	
	@AfterSuite(alwaysRun = true)
	public void closeDB() throws SQLException
	{
		dlib.disconnectDB();
		System.out.println("----close DB----");
	}
	
	
}
