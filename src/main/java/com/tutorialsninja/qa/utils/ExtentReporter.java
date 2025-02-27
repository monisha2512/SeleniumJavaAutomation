package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport()  {
		
		ExtentReports extentReports = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir") + File.separator + 
                "test-output" + File.separator + "ExtentReports" + File.separator + "extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialsNinja Test Automation Result");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReports.attachReporter(sparkReporter);
		Properties configProp= new Properties();
		File configPropFile=new File(System.getProperty("user.dir") + File.separator +
                            "src" + File.separator + "main" + File.separator +
                            "java" + File.separator + "com" + File.separator +
                            "tutorialsninja" + File.separator + "qa" + File.separator +
                            "config" + File.separator + "config.properties");
		 try {
	            // Load config.properties
	       
		FileInputStream fis=new FileInputStream(configPropFile);
		configProp.load(fis);
		 } catch (IOException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Failed to load properties files. Check file paths.");
	     }
		extentReports.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReports.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
		extentReports.setSystemInfo("Email",configProp.getProperty("validEmail"));
		extentReports.setSystemInfo("Password",configProp.getProperty("validPassword"));
		extentReports.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentReports.setSystemInfo("User Name",System.getProperty("user.name"));
		extentReports.setSystemInfo("Java Version",System.getProperty("java.version"));
		
		return extentReports;
	}
}
