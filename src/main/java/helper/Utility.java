package helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

public class Utility {

	public static Alert waitForAlert(WebDriver driver)
	{
		Alert alt =null;
		
		for(int i=0;i<=15;i++)
		{
			try
			{
				alt=driver.switchTo().alert();
				break;
			}
			catch(NoAlertPresentException e)
			{
				System.out.println("No Alert Found - Waiting for Alert");
				waitForSeconds(1);
			}
		}
		return alt;
	}

	public static Alert waitForAlert(WebDriver driver,int time)
	{
		Alert alt =null;
		
		for(int i=0;i<=time;i++)
		{
			try
			{
				alt=driver.switchTo().alert();
				break;
			}
			catch(NoAlertPresentException e)
			{
				System.out.println("No Alert Found - Waiting for Alert");
				waitForSeconds(1);
			}
		}
		return alt;
	}
	
	public static String captureScreenshotInBase64(WebDriver driver)
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		String base64 = ts.getScreenshotAs(OutputType.BASE64);
		return base64;
	}
	
	public static void captureScreenShot(WebDriver driver)
	{
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try 
		{
			FileHandler.copy(src, new File("./screenshots/ScreenShot"+getCurrentTime()+".png"));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public static void captureElementScreenShot(WebDriver driver,String locatorType,String locatorValue)
	{
		File ts = null;

		if(locatorType.equals("id"))
		{
			ts = driver.findElement(By.id(locatorValue)).getScreenshotAs(OutputType.FILE);
		}
		else if(locatorType.equals("name"))
		{
			ts = driver.findElement(By.name(locatorValue)).getScreenshotAs(OutputType.FILE);
		}
		else if(locatorType.equals("classname"))
		{
			ts = driver.findElement(By.className(locatorValue)).getScreenshotAs(OutputType.FILE);
		}
		else if(locatorType.equals("xpath"))
		{
			ts = driver.findElement(By.xpath(locatorValue)).getScreenshotAs(OutputType.FILE);
		}

		//		File ts = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try 
		{
			FileHandler.copy(ts, new File("./ElementScreenShots/element"+getCurrentTime()+".png"));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public static void captureElementScreenShot(WebElement element)
	{		
		File src = element.getScreenshotAs(OutputType.FILE);
		try 
		{
			FileHandler.copy(src, new File("./ElementScreenShots/element"+getCurrentTime()+".png"));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public static String getCurrentTime()
	{
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy");
		String date = sdf.format(d);
		//		String date = new SimpleDateFormat("HH-mm-ss_dd_MM_yyyy").format(new Date());
		return date;
	}

	// Method to wait for an element to be enabled, Alternative to Webdriver Wait
	public static boolean WaitForElement(WebDriver driver, By locator, int timeoutInSeconds) 
	{
		int elapsedTime = 0;
		boolean isEnabled = false;

		while (elapsedTime < timeoutInSeconds) 
		{
			try 
			{
				WebElement element = driver.findElement(locator);

				// Check if the element is enabled
				if (element.isEnabled()) 
				{
					System.out.println("Element is enabled.");
					isEnabled = true;
					break;
				}
			}
			catch (Exception e) 
			{
				// Handle exceptions like NoSuchElementException, StaleElementReferenceException, etc.
				System.out.println("Exception encountered: " + e.getMessage());
			}

			// Sleep for 1 second before retrying
			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				// Handle interrupted exception
				Thread.currentThread().interrupt();
			}

			elapsedTime++;
		}


		if(!isEnabled)
		{
			System.out.println("Timeout reached. Element is not enabled.");
		}

		return isEnabled;
	}

	public static void waitForSeconds(int seconds)
	{
		try 
		{
			Thread.sleep(seconds*1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void wait(int waitinmilliseconds)
	{
		try 
		{
			Thread.sleep(waitinmilliseconds);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

	public static void clickElement(WebDriver driver,By locator)
	{
		try {
			driver.findElement(locator).click();
		} 
		catch (Exception e) 
		{
			try {
				System.out.println("Trying with actions class click");
				Actions act = new Actions(driver);
				act.moveToElement(driver.findElement(locator)).click().build().perform();
			} 
			catch (Exception e1) 
			{
				System.out.println("Trying with  JS click");
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("argument[0].click", driver.findElement(locator));
			}
		}
	}

}
