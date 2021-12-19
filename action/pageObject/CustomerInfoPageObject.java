package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.CustomerInfoPageUI;

public class CustomerInfoPageObject extends BasePage {
	private WebDriver driver;

	public CustomerInfoPageObject(WebDriver _driver) {
		driver = _driver;
		System.out.println("Driver id của HomePageObject" + driver.toString());
	}

	public String getFirstNameTextboxValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.FIRST_NAME_TEXT_BOX);
		return getElementAttribute(driver, CustomerInfoPageUI.FIRST_NAME_TEXT_BOX, "value");
	}

	public String getLastNameTextboxValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.LAST_NAME_TEXT_BOX);
		return getElementAttribute(driver, CustomerInfoPageUI.LAST_NAME_TEXT_BOX, "value");
	}

	public String getEmailTextboxValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.EMAIL_TEXT_BOX);
		return getElementAttribute(driver, CustomerInfoPageUI.EMAIL_TEXT_BOX, "value");
	}

}
