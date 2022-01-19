package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.users.UserAddressesPageObject;
import pageObject.users.UserCustomerInfoPageObject;
import pageObject.users.UserHomePageObject;
import pageObject.users.UserLoginPageObject;
import pageObject.users.UserOrderPageObject;
import pageObject.users.UserRegisterPageObject;
import pageObject.users.UserRewardPointPageObject;

//class A kế thừa class B, có thể dùng các thuộc tính B, B là cha của A
public class Level_07_Switch_Page extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	// import class HomePageObject
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfo;
	private UserOrderPageObject orderPage;
	private UserRewardPointPageObject rewardPointPage;
	private UserAddressesPageObject addressesPage;

	WebDriverWait explicitWait;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		// Đưa việc khởi tạo vào trong 1 hàm trc đó
		// 1) mở url thì nó mở trang homepage bussiness
		driver = getBrowserDriver(browserName, url);
		// chưa xử lý dc khúc này
		homePage = PageGeneratorManager.getUserHomePage(driver);

		emailAddress = "afc" + generateFakeNumber() + "@mail.net";
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		System.out.println("Driver id của class là " + driver.toString());
		explicitWait = new WebDriverWait(driver, 30);

	}

	@Test
	public void User_01_Register_To_System() {
		// 2) Từ Home page qua Register page khởi tạo Register page lên
		registerPage = homePage.clickToRegisterLink();

		registerPage.sendkeyToFirstNameTextbox(firstName);
		registerPage.sendkeyToLastNameTextbox(lastName);
		registerPage.sendkeyToEmailTextbox(emailAddress);
		registerPage.sendkeyToPasswordTextbox(password);
		registerPage.sendkeyToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		// Assert.assertEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed");

		// 3)Từ Register chuyển về Home page khởi tạo lại trang Home Page
		homePage = registerPage.clickToLogoutLink();

	}

	@Test
	public void User_02_Login_To_System() {
		// 4)Từ trang homepage chuyển qua Login page
		loginPage = homePage.clickToLoginLink();

		loginPage.sendkeyToEmailTextBox(emailAddress);
		loginPage.sendkeyToPasswordTextBox(password);

		// 5) login thành công chuyển qua homepage
		homePage = loginPage.clicktoLoginButton();

	}

	@Test
	public void User_03_My_Account_Info() {
		// 6Từ trang Login chuyển qua Customer info page
		customerInfo = homePage.clickToMyAccountLink();

		Assert.assertEquals(customerInfo.getFirstNameTextboxValue(), firstName);
		Assert.assertEquals(customerInfo.getLastNameTextboxValue(), lastName);
		Assert.assertEquals(customerInfo.getEmailTextboxValue(), emailAddress);

	}

	@Test
	public void User_04_Navigator_Page() {
		System.out.println("// 7 Từ CustomerInfo page chuyển Order Page");

		// orderPage = customerInfo.clickToOrderLink();

		orderPage = customerInfo.clickToOrderLink(driver);

		System.out.println("// 8 Từ Order page chuyển qua Reward Point");

		rewardPointPage = orderPage.clickToRewardPointLink(driver);

		System.out.println("// 9 Từ Reward Point chuyển qua Order page");

		orderPage = rewardPointPage.clickToOrderLink(driver);

		System.out.println("// 10 Từ Order Page chuyển qua CustomerInfo Page");

		customerInfo = orderPage.clickToCustomerInfoLink(driver);

		System.out.println("// 11 Từ Customer Page chuyển qua Addresses Page");

		addressesPage = customerInfo.clickToAddressesPage(driver);

		System.out.println("// 12 Từ Address Page chuyển qua Reward Point");

		rewardPointPage = addressesPage.clickToRewardPointLink(driver);

		customerInfo = homePage.clickToMyAccountLink();

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}