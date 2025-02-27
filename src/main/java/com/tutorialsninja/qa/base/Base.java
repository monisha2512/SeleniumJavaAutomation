package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {
	
	WebDriver driver;
	public Properties prop; //making it public so that we can make it accessed inside the child class
	public Properties dataProp;
	public Base() {
        prop = new Properties();
        dataProp = new Properties();

        // Paths to property files
        String configPath = System.getProperty("user.dir") + File.separator +
                            "src" + File.separator + "main" + File.separator +
                            "java" + File.separator + "com" + File.separator +
                            "tutorialsninja" + File.separator + "qa" + File.separator +
                            "config" + File.separator + "config.properties";

        String dataPath = System.getProperty("user.dir") + File.separator +
                          "src" + File.separator + "main" + File.separator +
                          "java" + File.separator + "com" + File.separator +
                          "tutorialsninja" + File.separator + "qa" + File.separator +
                          "testdata" + File.separator + "testdata.properties";

        try {
            // Load config.properties
            FileInputStream configFile = new FileInputStream(configPath);
            prop.load(configFile);
            configFile.close(); // Close file after loading
            
            // Load testdata.properties
            FileInputStream dataFile = new FileInputStream(dataPath);
            dataProp.load(dataFile);
            dataFile.close(); // Close file after loading

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties files. Check file paths.");
        }
    }
public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {
	
	if(browserName.equalsIgnoreCase("chrome")){
		driver=new ChromeDriver();
	}
	else if(browserName.equalsIgnoreCase("firefox")){
		driver=new FirefoxDriver();
	}
	else if(browserName.equalsIgnoreCase("edge")){
		driver=new EdgeDriver();
	}
	else if(browserName.equalsIgnoreCase("safari")){
		driver=new SafariDriver();
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
	driver.get(prop.getProperty("url"));
	
	return driver;
}
}