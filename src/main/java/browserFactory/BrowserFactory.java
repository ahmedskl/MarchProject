package browserFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {

	static WebDriver driver;
	
	public static WebDriver getBrowserinstance()
	{
		return driver;
	}
	public static WebDriver startBrowser(String browserName,String applicationUrl) 
	{
/*	if we set 3.141.59 jars then java.lang.IllegalStateException: 
 * 	The path to the driver executable must be set by the webdriver.chrome.driver system property.
 */
		if(browserName.contains("Chrome") || browserName.contains("GC") || browserName.contains("Google Chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browserName.contains("Edge"))
		{
			driver = new EdgeDriver();
		}
		else if(browserName.contains("Firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browserName.contains("Safari"))
		{
			driver = new SafariDriver();
		}
		else 
		{
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));//4.27.0,4.13.0
//		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));//4.27.0,4.13.0
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		driver.get(applicationUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //4.27.0,4.13.0jar

		return driver;
	}


}
