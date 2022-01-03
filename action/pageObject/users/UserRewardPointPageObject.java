package pageObject.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserRewardPointPageObject extends BasePage {
	private WebDriver driver;

	public UserRewardPointPageObject(WebDriver _driver) {
		driver = _driver;
		System.out.println("Driver id của RewardPointPageObject" + driver.toString());

	}

}
