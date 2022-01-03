package pageObject.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.UserHomePageUI;

public class UserHomePageObject extends BasePage {
	// chứa những action của page
	// click vào button
	// send keys vào text box
	// search
	// hàm khởi tạo
	// 1.trùng tên vs tên class
	// 2.ko có kiểu trả về
	// 3.có tham số hoặc ko
	// 4.1 hàm có thể có 1 hoặc nhiều hàm khởi tạo
	// 6.luôn luôn dc chạy đầu tiên
	private WebDriver driver;
	// private HomePageUI homePageUI = new HomePageUI();

	public UserHomePageObject(WebDriver _driver) {
		driver = _driver;
		System.out.println("Driver id của HomePageObject" + driver.toString());

	}

	public UserRegisterPageObject clickToRegisterLink() {
		// gán biến đó bằng static = truy cập trực tiếp từ class
		// waitForElementClickable(driver, homePageUI.REGISTER_LINK);
		// clickToElement(driver, homePageUI.REGISTER_LINK);

		waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
		clickToElement(driver, UserHomePageUI.REGISTER_LINK);
		// return new RegisterPageObject(driver);
		return PageGeneratorManager.getUserRegisterPage(driver);

		// System.out.println(HomePageUI.REGISTER_LINK);
		// System.out.println(homePageUI.loginLink);

	}

	public UserLoginPageObject clickToLoginLink() {
		waitForElementClickable(driver, UserHomePageUI.LOGIN_LINK);
		clickToElement(driver, UserHomePageUI.LOGIN_LINK);
		// return new LoginPageObject(driver);
		return PageGeneratorManager.getUserLoginPage(driver);

	}

	public boolean isMyAccountLinkDisplay() {
		waitForElementVisible(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, UserHomePageUI.MY_ACCOUNT_LINK);
	}

	public UserCustomerInfoPageObject clickToMyAccountLink() {
		waitForElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		// return new CustomerInfoPageObject(driver);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);

	}

}
