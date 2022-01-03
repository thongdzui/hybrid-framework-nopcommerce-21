package pageObject.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserAddressesPageObject extends BasePage {
	private WebDriver driver;

	public UserAddressesPageObject(WebDriver _driver) {
		driver = _driver;
		System.out.println("Driver id của AddressesPageObject" + driver.toString());

	}

}
