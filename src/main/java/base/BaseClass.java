package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import browserFactory.BrowserFactory;
import dataProvider.ConfigReader;

public class BaseClass {

	public WebDriver driver;

	@Parameters({"",""})
	@BeforeMethod
	public void setupBrowser()
	{
		System.out.println("LOG:INFO - Setting up browser");
		//driver = BrowserFactory.startBrowser("Chrome", "https://ineuron-courses.vercel.app/login");

//1st Approch when u take from Config - this doesn't suite for cross browser, coz in cross browser u keep passing the browsers.
// 2nd Approch instead of taking from config we take from XML and runtime parameter.
		driver = BrowserFactory.startBrowser(ConfigReader.getProperty("browser"), ConfigReader.getProperty("URL"));

		System.out.println("LOG:INFO - Application up and running");
	}

	@AfterMethod
	public void closeBrowser()
	{
		driver.quit();
		System.out.println("LOG:INFO - Closing the browser and application");
	}


}
