package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {

	WebDriver driver;
	//Objects

	@FindBy(css="div[id='content'] h1")
	private WebElement yourAccountHasBeenCreated;
	
	
	public AccountSuccessPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this); 
	}

	public String retrieveYourAccountHasBeenCreatedText() {
		String warningText=yourAccountHasBeenCreated.getText();
		return warningText;
	}
}
