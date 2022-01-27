package pageObject.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
		System.out.println("Driver id của LoginPageObject" + driver.toString());
	}

	public UserHomePageObject clicktoLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);

		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		// return new HomePageObject(driver);
		return PageGeneratorManager.getUserHomePage(driver);

	}

	public String getErrorMessageAtEmailTextBox() {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	public void sendkeyToEmailTextBox(String email) {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, email);
	}

	public String getErrorMessageUnsuccessful() {
		waitForElementVisible(driver, UserLoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
		return getElementText(driver, UserLoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
	}

	public void sendkeyToPasswordTextBox(String password) {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);

	}

	// hàm gọi qua hàm
	public UserHomePageObject loginAsUser(String email, String password) {
		sendkeyToEmailTextBox(email);
		sendkeyToPasswordTextBox(password);
		return clicktoLoginButton();

	}

}
