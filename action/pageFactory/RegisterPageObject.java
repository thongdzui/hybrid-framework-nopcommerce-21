package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		System.out.println("Driver ID của RegisterPageObject" + driver.toString());
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "register-button")
	private WebElement registerButton;

	@FindBy(css = "input[id='FirstName']")
	private WebElement firstNameTextBox;

	@FindBy(css = "input[id='LastName']")
	private WebElement lastNameTextBox;

	@FindBy(css = "input[id='Email']")
	private WebElement emailTextBox;

	@FindBy(css = "input[id='Password']")
	private WebElement passwordTextBox;

	@FindBy(css = "input[id='ConfirmPassword']")
	private WebElement confirmPasswordTextBox;

	@FindBy(xpath = "//a[@class='ico-logout']")
	private WebElement logoutLink;

	@FindBy(css = "#FirstName-error")
	private WebElement firstNameErrorMsg;

	@FindBy(css = "#LastName-error")
	private WebElement lastNameErrorMsg;

	@FindBy(css = "#Email-error")
	private WebElement emailErrorMsg;

	@FindBy(css = "#Password-error")
	private WebElement passwordErrorMsg;

	@FindBy(css = "#ConfirmPassword-error")
	private WebElement confirmPassErrorMsg;

	@FindBy(css = "div[class='result']")
	private WebElement registerSuccessMsg;

	@FindBy(xpath = "//div[contains(@class,'message-error')]//li")
	private WebElement existedEmailErrorMsg;

	public void clickToRegisterButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, registerButton);

		clickToElement(registerButton);

	}

	public String getFirstNameErrorMessage() {
		waitForElementVisible(driver, firstNameErrorMsg);
		return getElementText(firstNameErrorMsg);
	}

	public String getLastNameErrorMessage() {
		waitForElementVisible(driver, lastNameErrorMsg);
		return getElementText(lastNameErrorMsg);
	}

	public String getEmailErrorMessage() {
		waitForElementVisible(driver, emailErrorMsg);
		return getElementText(emailErrorMsg);
	}

	public String getPasswordErrorMessage() {
		waitForElementVisible(driver, passwordErrorMsg);
		return getElementText(passwordErrorMsg);
	}

	public String getConfirmPasswordErrorMessage() {
		waitForElementVisible(driver, confirmPassErrorMsg);
		return getElementText(confirmPassErrorMsg);
	}

	public void sendkeyToFirstNameTextbox(String firstname) {
		waitForElementVisible(driver, firstNameTextBox);
		sendkeyToElement(firstNameTextBox, firstname);

	}

	public void sendkeyToLastNameTextbox(String lastname) {
		waitForElementVisible(driver, lastNameTextBox);
		sendkeyToElement(lastNameTextBox, lastname);

	}

	public void sendkeyToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, emailTextBox);
		sendkeyToElement(emailTextBox, emailAddress);

	}

	public void sendkeyToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextBox);
		sendkeyToElement(passwordTextBox, password);

	}

	public void sendkeyToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, confirmPasswordTextBox);
		sendkeyToElement(confirmPasswordTextBox, confirmPassword);

	}

	public String getRegisteredSuccessMessage() {
		waitForElementVisible(driver, registerSuccessMsg);
		return getElementText(registerSuccessMsg);

	}

	public void clickToLogoutLink() {
		waitForElementClickable(driver, logoutLink);

		clickToElement(logoutLink);

	}

	public String getExsitedEmailErrorMessage() {
		waitForElementVisible(driver, existedEmailErrorMsg);
		return getElementText(existedEmailErrorMsg);
	}

}
