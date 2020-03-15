package listeners;
import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import resources.Capture;
import tests.BaseTest;

public class MyListeners extends BaseTest implements ITestListener{
	
	@Override
	public void onTestFailure(ITestResult result) {
		try {
			Capture.screenshot(driver,result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
