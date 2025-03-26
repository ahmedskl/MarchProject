package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import browserFactory.BrowserFactory;
import helper.Utility;

public class ExtentTestNGTestListener implements ITestListener{

	/*
	 * In java ThreadLocal is a class in the java.lang package that provides a way to store value in a specific thread.
	 * 
	 * It creates a separate instance of the value for each thread, allowing each thread to have its own copy of the value
	 * without interfering with other threads.
	 * 
	 * It is useful for storing values that are specific to a single thread, such as transaction or request-specific data.
	 * 
	 * ThreadLocal provides a simple and efficient way to store thread-local variables withour having to pass them as method
	 * arguments or store them as instance variables.
	 */
	ExtentReports extent = ExtentManager.getInstance();

	ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) 
	{
		ExtentTest  extentTest =extent.createTest(result.getMethod().getMethodName());
		parentTest.set(extentTest);
	}

	public void onTestSuccess(ITestResult result) 
	{
		parentTest.get().pass("Test Passed ",MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.captureScreenshotInBase64(BrowserFactory.getBrowserinstance())).build());
	}

	public  void onTestFailure(ITestResult result)
	{
		WebDriver driver = BrowserFactory.getBrowserinstance();
		String base64 = Utility.captureScreenshotInBase64(driver);
		parentTest.get().fail("Test Failed : "+result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
	}

	public void onTestSkipped(ITestResult result)
	{
		parentTest.get().skip("Test Skipped "+result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context)
	{
		extent.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
