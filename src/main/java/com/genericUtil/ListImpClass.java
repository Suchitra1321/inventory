package com.genericUtil;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ListImpClass implements ITestListener
{
	ExtentReports report;
	ExtentTest test;
	//WebDriver driver;
	@Override
	public void onTestStart(ITestResult result)
	{
		//Execution starts
		String methodName = result.getMethod().getMethodName();
		test = report.createTest(methodName);
		Reporter.log(methodName+"------>Execution starts");
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		String methodName = result.getMethod().getMethodName();
		test.log(Status.PASS, methodName+"---->passed");
		Reporter.log(methodName+"----->TestScript exceuted successfully");
	}

	@Override
	public  void onTestFailure(ITestResult result)
	{
		
		try {
			String methodName = result.getMethod().getMethodName();
			String screenshot = WebDriverUtils.getScreenshot(BaseClass.driver, methodName);
			test.log(Status.FAIL, methodName+"------->failed");
			test.log(Status.FAIL, result.getThrowable());
			Reporter.log(methodName+"------>TestScript failed");
			test.addScreenCaptureFromPath(screenshot);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//String methodName=result.getMethod().getMethodName();
//		TakesScreenshot ts=(TakesScreenshot)BaseClass.driver;
//		File src=ts.getScreenshotAs(OutputType.FILE);
//		File dest=new File("./screenshot/"+methodName+".png");
//		String path=dest.getAbsolutePath();
//		try {
//			FileUtils.copyFile(src,dest);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
//		//return path;
		
		
	
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, methodName+"----skipped");
		test.log(Status.SKIP, result.getThrowable());
		Reporter.log(methodName+"------->Testscript skipped");
	}

	@Override
	public void onStart(ITestContext context) 
	{
		ExtentSparkReporter htmlReport = new ExtentSparkReporter("./ExtentReport/report.html");
		htmlReport.config().setDocumentTitle("SDET-54");
		htmlReport.config().setTheme(Theme.STANDARD);
		htmlReport.config().setReportName("SalesAndInventory");
		
		report=new ExtentReports();
		report.attachReporter(htmlReport);
		
		report.setSystemInfo("Base platform", "windows 10");
		report.setSystemInfo("Base browser", "chrome");
		report.setSystemInfo("Base url", "http://rmgtestingserver/domain/Sales_And_Inventory_System/pages/login.php");
		report.setSystemInfo("Reporter name", "Suchitra");
		
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		report.flush();
	}


	
}


