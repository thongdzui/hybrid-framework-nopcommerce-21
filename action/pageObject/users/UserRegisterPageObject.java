package pageObject.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
	private WebDriver driver;

	public UserRegisterPageObject(WebDriver _driver) {
		driver = _driver;
		System.out.println("Driver ID của RegisterPageObject" + driver.toString());
	}

	@Step("Click to Register Button")
	public void clickToRegisterButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);

		clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);

	}

	public String getFirstNameErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.FRIST_NAME_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.FRIST_NAME_ERROR_MESSAGE);
	}

	public String getLastNameErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
	}

	public String getEmailErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getPasswordErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	public String getConfirmPasswordErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	@Step("Enter to Firstname texbox with value is {0}")
	public void sendkeyToFirstNameTextbox(String firstname) {
		waitForElementVisible(driver, UserRegisterPageUI.FRIST_NAME_TEXT_BOX);
		sendkeyToElement(driver, UserRegisterPageUI.FRIST_NAME_TEXT_BOX, firstname);

	}

	@Step("Enter to Lastname texbox with value is {0}")
	public void sendkeyToLastNameTextbox(String lastname) {
		waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXT_BOX);
		sendkeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXT_BOX, lastname);

	}

	@Step("Enter to Email Address texbox with value is {0}")
	public void sendkeyToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXT_BOX);
		sendkeyToElement(driver, UserRegisterPageUI.EMAIL_TEXT_BOX, emailAddress);

	}

	@Step("Enter to Password texbox with value is {0}")
	public void sendkeyToPasswordTextbox(String password) {
		waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXT_BOX);
		sendkeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXT_BOX, password);

	}

	@Step("Enter to Confirm Password texbox with value is {0}")
	public void sendkeyToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, UserRegisterPageUI.CONFIRIM_PASSWORD_TEXT_BOX);
		sendkeyToElement(driver, UserRegisterPageUI.CONFIRIM_PASSWORD_TEXT_BOX, confirmPassword);

	}

	@Step("Verify register success message is display")
	public String getRegisteredSuccessMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);

	}

	public UserHomePageObject clickToLogoutLink() {
		waitForElementClickable(driver, UserRegisterPageUI.LOGOUT_LINK);

		clickToElement(driver, UserRegisterPageUI.LOGOUT_LINK);
		// return new HomePageObject(driver);
		return PageGeneratorManager.getUserHomePage(driver);

	}

	public String getExsitedEmailErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.EXISTED_EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.EXISTED_EMAIL_ERROR_MESSAGE);
	}

}
