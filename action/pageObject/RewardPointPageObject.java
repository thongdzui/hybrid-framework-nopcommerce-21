package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class RewardPointPageObject extends BasePage {
	private WebDriver driver;

	public RewardPointPageObject(WebDriver _driver) {
		driver = _driver;
		System.out.println("Driver id của RewardPointPageObject" + driver.toString());

	}

}
