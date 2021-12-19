package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;

	public RegisterPageObject(WebDriver _driver) {
		driver = _driver;
		System.out.println("Driver ID của RegisterPageObject" + driver.toString());
	}

	public void clickToRegisterButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);

		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);

	}

	public String getFirstNameErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.FRIST_NAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.FRIST_NAME_ERROR_MESSAGE);
	}

	public String getLastNameErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
	}

	public String getEmailErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getPasswordErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	public String getConfirmPasswordErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	public void sendkeyToFirstNameTextbox(String firstname) {
		waitForElementVisible(driver, RegisterPageUI.FRIST_NAME_TEXT_BOX);
		sendkeyToElement(driver, RegisterPageUI.FRIST_NAME_TEXT_BOX, firstname);

	}

	public void sendkeyToLastNameTextbox(String lastname) {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXT_BOX);
		sendkeyToElement(driver, RegisterPageUI.LAST_NAME_TEXT_BOX, lastname);

	}

	public void sendkeyToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXT_BOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXT_BOX, emailAddress);

	}

	public void sendkeyToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXT_BOX);
		sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXT_BOX, password);

	}

	public void sendkeyToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRIM_PASSWORD_TEXT_BOX);
		sendkeyToElement(driver, RegisterPageUI.CONFIRIM_PASSWORD_TEXT_BOX, confirmPassword);

	}

	public String getRegisteredSuccessMessage() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);

	}

	public HomePageObject clickToLogoutLink() {
		waitForElementClickable(driver, RegisterPageUI.LOGOUT_LINK);

		clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
		// return new HomePageObject(driver);
		return PageGeneratorManager.getHomePage(driver);

	}

	public String getExsitedEmailErrorMessage() {
		waitForElementVisible(driver, RegisterPageUI.EXISTED_EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EXISTED_EMAIL_ERROR_MESSAGE);
	}

}
