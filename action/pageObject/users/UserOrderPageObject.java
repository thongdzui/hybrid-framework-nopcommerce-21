package pageObject.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserOrderPageObject extends BasePage {
	private WebDriver driver;

	public UserOrderPageObject(WebDriver _driver) {
		driver = _driver;
		System.out.println("Driver id của OrderPageObject" + driver.toString());

	}
	// đang đứng Order Page
	// muốn chuyển qua 7 trang còn lại
	// phải viết 7 hàm
	// phải viết 7 cái locator

}
