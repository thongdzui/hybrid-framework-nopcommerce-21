package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
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

	public HomePageObject(WebDriver _driver) {
		driver = _driver;
		System.out.println("Driver id của HomePageObject" + driver.toString());

	}

	public RegisterPageObject clickToRegisterLink() {
		// gán biến đó bằng static = truy cập trực tiếp từ class
		// waitForElementClickable(driver, homePageUI.REGISTER_LINK);
		// clickToElement(driver, homePageUI.REGISTER_LINK);

		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		// return new RegisterPageObject(driver);
		return PageGeneratorManager.getRegisterPage(driver);

		// System.out.println(HomePageUI.REGISTER_LINK);
		// System.out.println(homePageUI.loginLink);

	}

	public LoginPageObject clickToLoginLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		// return new LoginPageObject(driver);
		return PageGeneratorManager.getLoginPage(driver);

	}

	public boolean isMyAccountLinkDisplay() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	public CustomerInfoPageObject clickToMyAccountLink() {
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		// return new CustomerInfoPageObject(driver);
		return PageGeneratorManager.getCustomerInfoPage(driver);

	}

}
