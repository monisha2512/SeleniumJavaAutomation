package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base{
	public RegisterTest() {
		super();//super class constructor
	}
	public WebDriver driver;
	RegisterPage registerPage ;
	AccountSuccessPage accountSuccessPage ;
	@BeforeMethod
	public void setup() {	
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		//creating object for the homepage
				HomePage homePage = new HomePage(driver);
				registerPage=homePage.navigateToRegisterPage();
				
   
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}
	
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {	    
		accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), 
												Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephone"), 
												prop.getProperty("validPassword"), prop.getProperty("validPassword"));
	    Assert.assertEquals(accountSuccessPage.retrieveYourAccountHasBeenCreatedText(), "Your Account Has Been Created!", "Account Success Page is not displayed");	
	}

	
	@Test(priority=2)
	public void verifyRegisteringAccountByProvidingAllFields() {	    

		accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
											Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephone"),
											prop.getProperty("validPassword"), prop.getProperty("validPassword"));
	    Assert.assertEquals(accountSuccessPage.retrieveYourAccountHasBeenCreatedText(), "Your Account Has Been Created!", "Account Success Page is not displayed");
	
	}
	
	@Test(priority=3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {		
		registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				prop.getProperty("validEmail"), dataProp.getProperty("telephone"),
				prop.getProperty("validPassword"), prop.getProperty("validPassword"));
	    Assert.assertTrue(registerPage.retrieveEmailAlreadyRegisteredWarningText().contains(dataProp.getProperty("dupicateEmailWarningMessage")), "email address is not Displayed");

	}
	
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {

		registerPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.warningPage(dataProp.getProperty("privacyPolicyWarningMessage"), dataProp.getProperty("firstNameErrorMessage"), 
				 dataProp.getProperty("lastNameErrorMessage"), dataProp.getProperty("emailErrorMessage"),
				 dataProp.getProperty("telephoneErrorMessage"), dataProp.getProperty("passwordErrorMessage")));		
	}	
}
