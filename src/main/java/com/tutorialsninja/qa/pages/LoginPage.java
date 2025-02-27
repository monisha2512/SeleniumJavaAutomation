package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	//Objects
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailAddressField;
	
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement loginWarningMessage;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this); 
	}
	
	//Actions
	public void enterEmail(String emailText) {
		emailAddressField.sendKeys(emailText);
	}
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public AccountPage Login(String emailText,String passwordText) {
		emailAddressField.sendKeys(emailText);
		passwordField.sendKeys(passwordText);
		loginButton.click();
		return new AccountPage(driver);
		
	}
		public String retrieveEmailPasswordNotMatchingWarningText() {
		String warningText=loginWarningMessage.getText();
		return warningText;
	}


}
