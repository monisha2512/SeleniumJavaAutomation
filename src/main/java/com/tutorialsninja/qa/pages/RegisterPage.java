package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	//Objects
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(xpath="//label[normalize-space()='Yes']")
	private WebElement newsLetterYesRadioButton;
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement privacyPolicyCheckbox;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
		
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement registerWarningMessage;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPolicyWarningMessage;
	
	@FindBy(xpath="//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement firstNameWarningMessage;
	
	@FindBy(xpath="//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
	private WebElement lastNameWarningMessage;	
	
	@FindBy(xpath="//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
	private WebElement emailWarningMessage;	
	
	@FindBy(xpath="//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement telephoneWarningMessage;
	
	@FindBy(xpath="//div[contains(text(),'Password must be between 4 and 20 characters!')]")
	private WebElement passwordWarningMessage;
	
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this); 
	}
		//Actions
		public void enterFirstName(String firstNameText) {
			firstNameField.sendKeys(firstNameText);
		}
		public void enterLastName(String lastNameText) {
			lastNameField.sendKeys(lastNameText);
		}
		public void enterEmailAddress(String emailText) {
			emailAddressField.sendKeys(emailText);
		}
		public void enterTelephone(String telephoneText) {
			telephoneField.sendKeys(telephoneText);
		}
		public void enterPassword(String passwordText) {
			passwordField.sendKeys(passwordText);
		}

		public void confirmPassword(String confirmPasswordText) {
			confirmPasswordField.sendKeys(confirmPasswordText);
		}
		public void checkPrivacyPolicy() {
			privacyPolicyCheckbox.click();
		}
		public void newsLetter() {
			newsLetterYesRadioButton.click();
		}
		public AccountSuccessPage clickOnContinueButton() {
			continueButton.click();
			return new AccountSuccessPage(driver);
		}
		public AccountSuccessPage registerWithMandatoryFields(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText, String confirmPasswordText) {
			firstNameField.sendKeys(firstNameText);
			lastNameField.sendKeys(lastNameText);
			emailAddressField.sendKeys(emailText);
			telephoneField.sendKeys(telephoneText);
			passwordField.sendKeys(passwordText);
			confirmPasswordField.sendKeys(confirmPasswordText);
			privacyPolicyCheckbox.click();
			continueButton.click();
			return new AccountSuccessPage(driver);
		}
		public AccountSuccessPage registerWithAllFields(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText, String confirmPasswordText) {
			firstNameField.sendKeys(firstNameText);
			lastNameField.sendKeys(lastNameText);
			emailAddressField.sendKeys(emailText);
			telephoneField.sendKeys(telephoneText);
			passwordField.sendKeys(passwordText);
			confirmPasswordField.sendKeys(confirmPasswordText);
			newsLetterYesRadioButton.click();
			privacyPolicyCheckbox.click();
			continueButton.click();
			return new AccountSuccessPage(driver);
		}
		
		public String retrieveEmailAlreadyRegisteredWarningText() {
			String warningText=registerWarningMessage.getText();
			return warningText;
		}
		public String privacyPolicyWarningText() {
			String privacyPolicyWarningText=privacyPolicyWarningMessage.getText();
			return privacyPolicyWarningText;
		}
		public String firstNameWarningText() {
			String firstNameWarningText=firstNameWarningMessage.getText();
			return firstNameWarningText;
		}
		public String lastNameWarningText() {
			String lastNameWarningText=lastNameWarningMessage.getText();
			return lastNameWarningText;
		}
		public String emailWarningText() {
			String emailWarningText=emailWarningMessage.getText();
			return emailWarningText;
		}
		public String telephoneWarningText() {
			String telephoneWarningText=telephoneWarningMessage.getText();
			return telephoneWarningText;
		}
		public String passwordWarningText() {
			String passwordWarningText=passwordWarningMessage.getText();
			return passwordWarningText;
		}
		public boolean warningPage(String expectedPrivacyPolicyWarning,String expectedFirstNameWarning, String expectedLastNameWarning,String expectedEmailWarning,String expectedTelephoneWarning,String expectedPasswordWarning) {
			boolean privacyPolicyWarningStatus=privacyPolicyWarningMessage.getText().contains(expectedPrivacyPolicyWarning);
			
			boolean firstNameWarningStatus=firstNameWarningMessage.getText().contains(expectedFirstNameWarning);

			boolean lastNameWarningStatus=lastNameWarningMessage.getText().contains(expectedLastNameWarning);

			boolean emailWarningStatus=emailWarningMessage.getText().contains(expectedEmailWarning);

			boolean telephoneWarningStatus=telephoneWarningMessage.getText().contains(expectedTelephoneWarning);

			boolean passwordWarningStatus=passwordWarningMessage.getText().contains(expectedPasswordWarning);
			return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && 
					emailWarningStatus && telephoneWarningStatus && passwordWarningStatus;
		}
		
}
