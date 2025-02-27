package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	//objects
	@FindBy(xpath="//span[normalize-space()='My Account']") //notation
	private WebElement myAccountDropMenu; // making it privatte so that it cannot be accessed outside and updates
	
	@FindBy(linkText="Login") //notation
	private WebElement loginOption;
	
	@FindBy(linkText="Register") //notation
	private WebElement registerOption;
	
	@FindBy(name="search") //notation
	private WebElement searchProductField;
	
	@FindBy(xpath="//*[@id=\"search\"]/span/button") //notation
	private WebElement searchButton;
	
	public HomePage(WebDriver driver) {// name of class and constructor should be same 
		
		this.driver=driver;
		PageFactory.initElements(driver, this); 
	}
	//actions
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	public LoginPage navigateToLoginPage() {
		myAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);	
	}
	public RegisterPage navigateToRegisterPage() {
		myAccountDropMenu.click();
		registerOption.click();
		return new RegisterPage(driver);	
	}	
	
	public RegisterPage selectRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}
	public void searchProduct(String productName) {
		searchProductField.sendKeys(productName);
	}
	public void selectSearchButton() {
		searchButton.click();		
	} 
	public SearchPage searchForProduct(String productName) {
		searchProductField.sendKeys(productName);
		searchButton.click();
		return new SearchPage(driver);
	}
	
	
} 
