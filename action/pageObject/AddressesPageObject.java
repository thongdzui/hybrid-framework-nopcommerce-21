package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class AddressesPageObject extends BasePage {
	private WebDriver driver;

	public AddressesPageObject(WebDriver _driver) {
		driver = _driver;
		System.out.println("Driver id của AddressesPageObject" + driver.toString());

	}

}
