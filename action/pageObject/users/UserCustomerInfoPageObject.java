package pageObject.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.UserCustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage {
	private WebDriver driver;

	public UserCustomerInfoPageObject(WebDriver _driver) {
		driver = _driver;
		System.out.println("Driver id của HomePageObject" + driver.toString());
	}

	public String getFirstNameTextboxValue() {
		waitForElementVisible(driver, UserCustomerInfoPageUI.FIRST_NAME_TEXT_BOX);
		return getElementAttribute(driver, UserCustomerInfoPageUI.FIRST_NAME_TEXT_BOX, "value");
	}

	public String getLastNameTextboxValue() {
		waitForElementVisible(driver, UserCustomerInfoPageUI.LAST_NAME_TEXT_BOX);
		return getElementAttribute(driver, UserCustomerInfoPageUI.LAST_NAME_TEXT_BOX, "value");
	}

	public String getEmailTextboxValue() {
		waitForElementVisible(driver, UserCustomerInfoPageUI.EMAIL_TEXT_BOX);
		return getElementAttribute(driver, UserCustomerInfoPageUI.EMAIL_TEXT_BOX, "value");
	}

}
