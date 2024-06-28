package org.Appium.TestUtills;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Android.utils.AndroidAction;
import io.appium.java_client.AppiumDriver;

public class Listener extends AndroidAction implements ITestListener{

	ExtentTest test;
	AppiumDriver driver;
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	  
	  public  void onTestStart(ITestResult result) {
		  
	 test = extent.createTest(result.getMethod().getMethodName());
	  }

	  public void onTestSuccess(ITestResult result) {
	    // not implemented
	  }

	
	  public void onTestFailure(ITestResult result) {
		  test.fail(result.getThrowable());
		  
		  try {
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		  try {
			test.addScreenCaptureFromPath(getScreenShot(result.getMethod().getMethodName(), driver),result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }


	  public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }

	
	  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }

	  
	  public void onTestFailedWithTimeout(ITestResult result) {
	   
	  }


	  public void onStart(ITestContext context) {
	    // not implemented
	  }

	  
	  public void onFinish(ITestContext context) {
	   extent.flush();
	  }
	}


