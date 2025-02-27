package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base{
	public SearchTest() {
		super();//super class constructor
	}
		public WebDriver driver;
		SearchPage searchPage;
		HomePage homePage;
		@BeforeMethod
		public void setup() {	
			
			driver= initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
			homePage = new HomePage(driver);
		}
		@AfterMethod
		public void tearDown() {
			driver.quit();
			
		}
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		
		searchPage= homePage.searchForProduct(dataProp.getProperty("productName"));
		Assert.assertTrue(searchPage.getDisplayStatusOfProductLink(),"Product link is not displayed");
	}
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
					
		searchPage= homePage.searchForProduct(dataProp.getProperty("invalidProductName"));
		Assert.assertEquals(searchPage.noProductText(),"abcd","Search product is not displayed");
	}
	@Test(priority=3,dependsOnMethods= {"verifySearchWithInvalidProduct","verifySearchWithValidProduct"})
	public void verifySearchWithoutProduct() {

		searchPage=homePage.searchForProduct("");
		Assert.assertEquals(searchPage.noProductText(),dataProp.getProperty("NoProductFoundError"),"No product for search is not displayed");
	}	
}
