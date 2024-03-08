package com.genericUtil;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils 
{
	/**
	 * 
	 * @param driver
	 * @param sec
	 */
	public void waitForPageLoad(WebDriver driver,int sec)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
	}
	
	/**
	 * 
	 * @param driver
	 * @param sec
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * 
	 * @param driver
	 * @param sec
	 */
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	/**
	 * 
	 * @param driver
	 * @param sec
	 * @return
	 */
	public WebDriverWait webDriverWaitobj(WebDriver driver, int sec)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		return wait;
	}
	
	/**
	 * 
	 * @param driver
	 * @param sec
	 * @param ele
	 */
	public void waitUntilEleToBeVisible(WebDriver driver, int sec, WebElement ele)
	{
	
		webDriverWaitobj(driver, sec).until(ExpectedConditions.visibilityOf(ele));
	}
	
	/**
	 * 
	 * @param driver
	 * @param sec
	 * @param ele
	 */
	public void waitUntilEleToBeClickable(WebDriver driver, int sec, WebElement ele)
	{
		webDriverWaitobj(driver, sec).until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	/**
	 * 
	 * @param driver
	 * @param sec
	 */
	public void waitUntilAlertIspresent(WebDriver driver, int sec)
	{
		webDriverWaitobj(driver, sec).until(ExpectedConditions.alertIsPresent());
	}
	
	/**
	 * 
	 * @param driver
	 * @param sec
	 * @param url
	 */
	public void waitUntilUrlContains(WebDriver driver, int sec, String url)
	{
		webDriverWaitobj(driver, sec).until(ExpectedConditions.urlContains(url));
	}
	
	
	/**
	 * 
	 * @param driver
	 * @param sec
	 * @param ele
	 */
	public void waitUntilFrameToBeAvailable(WebDriver driver, int sec, WebElement ele)
	{
		webDriverWaitobj(driver, sec).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));
	}
	
	/**
	 * 
	 * @param ele
	 * @return 
	 */
	public Select dropdownObj(WebElement ele)
	{
		Select sel = new Select(ele);
		return sel;
		
	}
	
	/**
	 * 
	 * @param ele
	 * @param index
	 */
	public void handleDropdown(WebElement ele, int index)
	{
		dropdownObj(ele).selectByIndex(index);
	}
	
	/**
	 * 
	 * @param ele
	 * @param value
	 */
	public void handleDropdown(WebElement ele, String value)
	{
		dropdownObj(ele).selectByValue(value);
	}
	
	/**
	 * 
	 * @param text
	 * @param ele
	 */
	public void handleDropdown(String text, WebElement ele)
	{
		dropdownObj(ele).selectByVisibleText(text);
	}
	
	/**
	 * 
	 * @param driver
	 * @return
	 */
	public Actions actionObj(WebDriver driver)
	{
		Actions act = new Actions(driver);
		return act;
	}
	
	/**
	 * 
	 * @param driver
	 * @param src
	 * @param dest
	 */
	public void dragAndDrop(WebDriver driver, WebElement src, WebElement dest)
	{
		actionObj(driver).dragAndDrop(src, dest);
	}
	
	/**
	 * 
	 * @param driver
	 * @param ele
	 */
	public void mouseHoverEle(WebDriver driver, WebElement ele)
	{
		actionObj(driver).click(ele).perform();
		//actionObj(driver).moveToElement(ele).click().perform();
	}
	
	/**
	 * 
	 * @param driver
	 * @param ele
	 */
	public void doubleClickAction(WebDriver driver, WebElement ele)
	{
		actionObj(driver).doubleClick(ele).perform();
	}
	
	/**
	 * 
	 * @param driver
	 */
	public void doubleClickAction(WebDriver driver)
	{
		actionObj(driver).doubleClick().perform();
	}
	
	/**
	 * 
	 * @param driver
	 * @param ele
	 */
	public void rightClick(WebDriver driver, WebElement ele)
	{
		actionObj(driver).contextClick(ele).perform();
	}
	
	/**
	 * 
	 * @param driver
	 */
	public void rightClick(WebDriver driver)
	{
		actionObj(driver).contextClick().perform();
	}
	
	/**
	 * 
	 * @param driver
	 * @param y
	 */
	public void scrollByAmount(WebDriver driver, int y)
	{
		actionObj(driver).scrollByAmount(0, y);
	}
	
	/**
	 * This method will press enter key
	 * @param driver
	 */
	public void enterKeyPress(WebDriver driver)
	{
		actionObj(driver).sendKeys(Keys.ENTER).perform();
	}
	
	public Robot robotObj() throws AWTException
	{
		Robot r=new Robot();
		return r;
	}
	
	/**
	 * This method will press enter key
	 * @throws Throwable
	 */
	public void enterKey() throws Throwable
	{
		robotObj().keyPress(KeyEvent.VK_ENTER);
	}
	
	/**
	 * This method is to release key
	 * @throws Throwable
	 */
	public void enterRelease() throws Throwable
	{
		robotObj().keyRelease(KeyEvent.VK_ENTER);
	}
	
	/**
	 * This method will switch the frame based on index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	
	/**
	 * This method will switch the frame based on name or ID
	 * @param driver
	 * @param nameOrId
	 */
	public void switchToFrame(WebDriver driver, String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	
	/**
	 * This method will switch the frame based on address of element
	 * @param driver
	 * @param ele
	 */
	public void switchToFrame(WebDriver driver, WebElement ele)
	{
		driver.switchTo().frame(ele);
	}
	
	/**
	 * This method will accept alert popup
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method will cancel alert popup
	 * @param driver
	 */
	public void cancelAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * 
	 * @param driver
	 * @param partialWinTitle
	 */
	public void switchToWindow(WebDriver driver, String partialWinTitle)
	{
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it=windows.iterator();
		while (it.hasNext()) 
		{
			String winId = it.next();
			String currentWinTitle = driver.switchTo().window(winId).getTitle();
			
			if(currentWinTitle.contains(partialWinTitle))
			{
				break;
			}
			
		}
	}
	
	public static String getScreenshot(WebDriver driver, String screenshotname) throws IOException
	{
		JavaUtils jLib = new JavaUtils();
		String FSName = screenshotname+jLib.getSystemDateInFormat();
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/"+FSName+".png");
		String scrpath = dest.getAbsolutePath();
		FileUtils.copyFile(src, dest);
		return scrpath;
		
	}
	
	
	/*public void scrollAction(WebDriver driver, WebElement ele)
	{
		JavascriptExecutor jss=(JavascriptExecutor)driver;
		int y=ele.getLocation().getY();
	}*/
	
	

}
