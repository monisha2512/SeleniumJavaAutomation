package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener{//ITestListener interface to take screenshots of error
	ExtentReports extentReports;
	ExtentTest extentTest ;
	
	@Override
	public void onStart(ITestContext context) {
		//to generate the report
		extentReports = ExtentReporter.generateExtentReport();		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
		//to print the sentence in the extent report not in the console
		extentTest = extentReports.createTest(result.getName());
		//writing a log
		extentTest.log(Status.INFO, result.getName()+"started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS, result.getName()+"got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
	
	// to retrieve the driver using result
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		//calling method to take screenshot
		String destinationScreenshotPath=Utilities.captureScreenshot(driver,result.getName());
		
		// to attach screenshot to the extentReport
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName()+" got failed");	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+" got skipped");
	}
	@Override
	public void onFinish(ITestContext context) {
			extentReports.flush();
			
			String pathOfExtentReport=System.getProperty("user.dir")+ File.separator + 
                "test-output" + File.separator + "ExtentReports" + File.separator + "extentReport.html";
		File extentReport= new File(pathOfExtentReport);
			try {
				Desktop.getDesktop().browse(extentReport.toURI());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	} 	
}
