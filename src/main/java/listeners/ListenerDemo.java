package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerDemo implements ITestListener{


	public void onTestSuccess(ITestResult result)
	{
		System.out.println("Test Passed : "+result.getMethod().getMethodName());
	}


	public void onTestFailure(ITestResult result)
	{
		System.out.println("Test Failed : "+result.getMethod().getMethodName());
		System.out.println("Exception thrown by this method : "+result.getThrowable().getMessage());
	}


	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}


}
