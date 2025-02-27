package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base{
	public LoginTest() {
		super();//super class constructor
	}
	
	public WebDriver driver;
	LoginPage loginPage;
	@BeforeMethod
	public void setup() {	
	
		driver= initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		
		//creating object for the homepage
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
    	
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}
	@Test(priority=1, dataProvider="supplyTestData")
    public void verifyLoginWithValidCredentials(String email, String password) {
    
		AccountPage accountPage =loginPage.Login(email, password);    
        Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),
                "Edit your Account option is not displayed");
    } 

    @DataProvider(name="supplyTestData")
    public Object[][] supplyTestData() {
        Object[][] data = Utilities.getTestDataFromExcel("Login");
        return data;
    }
    @Test(priority=2)
    public void verifyLoginWithInvalidCredentials() {

    	loginPage.Login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword")); 
       	Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningText().contains(dataProp.getProperty("invalidLoginWarningMessage")), "Expected message is not displayed");
    	
    }
    @Test(priority=3)
    public void verifyLoginWithInvalidEmailAndValidPassword() {

    	loginPage.Login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword")); 

    	//loginPage.enterEmail(Utilities.generateEmailWithTimeStamp());
    	//loginPage.enterPassword(prop.getProperty("validPassword"));
    	//loginPage.clickOnLoginButton();
    	Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningText().contains(dataProp.getProperty("invalidLoginWarningMessage")), "Expected message is not displayed");
    	
    }
    
    @Test(priority=4)
    public void verifyLoginWithValidEmailAndInvalidPassword() {

    	loginPage.Login(prop.getProperty("validEmail"),dataProp.getProperty("invalidPassword")); 

    	//loginPage.enterEmail(prop.getProperty("validEmail"));
    	//loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
    	//loginPage.clickOnLoginButton();    	    	
    	
    	Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningText().contains(dataProp.getProperty("invalidLoginWarningMessage")), "Expected message is not displayed");  
    }
    
    @Test(priority=5)
    public void verifyLoginWithoutProvidingCredentials() {   	
    	loginPage.clickOnLoginButton();	
    	Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningText().contains(dataProp.getProperty("invalidLoginWarningMessage")), "Expected message is not displayed");
    
    }	
}
