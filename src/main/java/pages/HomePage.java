package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
//		PageFactory.initElements(driver, this);
	}
	
	By loginBtn = By.xpath("//button[text()='Log in ']");
	
	By signOut = By.xpath("//button[text()='Sign out']");
	By welcomeMsg = By.xpath("//h1[text()='iNeuron Courses']");
	
	public LoginPage clickonLoginButton()
	{
		driver.findElement(loginBtn).click();	
//		LoginPage login = new LoginPage(driver);
//		LoginPage login = PageFactory.initElements(driver,LoginPage.class);
		return PageFactory.initElements(driver,LoginPage.class);
	}
	public String getWelcomeMsg()
	{
		return driver.findElement(welcomeMsg).getText();
	}
	public void clickOnSignOut()
	{
		driver.findElement(signOut).click();
	}

}
