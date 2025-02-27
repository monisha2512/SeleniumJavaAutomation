package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
		WebDriver driver;
		//Objects
		
		
		@FindBy(linkText="HP LP3065") //notation
		private WebElement searchProductLink;
		
		@FindBy(xpath="//p[contains(text(),'There is no product that matches the search criter')]") //notation
		private WebElement noProductText;
		
		public SearchPage(WebDriver driver) {// name of class and constructor should be same 
			
			this.driver=driver;
			PageFactory.initElements(driver, this); 
		}
		//actions
		public boolean getDisplayStatusOfProductLink() {
			boolean displayStatus=searchProductLink.isDisplayed();
			return displayStatus;
		}
		public String noProductText() {
			String warningText=noProductText.getText();
			return warningText;
		}
}
