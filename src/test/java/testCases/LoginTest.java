package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import dataProvider.CustomDataProvider;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseClass{


	@Test(dataProvider = "loginDetails", dataProviderClass = CustomDataProvider.class)
	public void loginToApplication(String uname, String pass)
	{
		//		WebDriver driver = new ChromeDriver();
		//		driver.get("https://ineuron-courses.vercel.app/login");
		//		WebDriver driver = BrowserFactory.startBrowser("Chrome", "https://ineuron-courses.vercel.app/login");
		//		Transferred to base class	
		//		
		//		driver.findElement(By.id("email1")).sendKeys("ineuron@ineuron.ai");
		//		driver.findElement(By.id("password1")).sendKeys("ineuron");
		//		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		LoginPage login = new LoginPage(driver);//from loginpage object
		login.loginToApplication(uname, pass);//Data shouldn't be hard coded, take DataProvider
		
		HomePage home= new HomePage(driver);
		
		Assert.assertTrue(home.getWelcomeMsg().contains("iNeuron Courses"));
		home.clickOnSignOut();
		Assert.assertTrue(login.isSignInPresent());
	}

}
