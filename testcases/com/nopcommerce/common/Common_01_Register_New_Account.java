package com.nopcommerce.common;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.users.UserHomePageObject;
import pageObject.users.UserLoginPageObject;
import pageObject.users.UserRegisterPageObject;

//class A kế thừa class B, có thể dùng các thuộc tính B, B là cha của A
public class Common_01_Register_New_Account extends BaseTest {
	private WebDriver driver;
	public static String emailAddress, password;
	private String firstName, lastName;
	// import class HomePageObject
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;

	@Parameters({ "browser", "url" })
	@BeforeTest(description = "Create new user for all classes test")
	public void Register(String browserName, String url) {

		driver = getBrowserDriver(browserName, url);
		// chưa xử lý dc khúc này
		homePage = PageGeneratorManager.getUserHomePage(driver);

		emailAddress = "afc" + generateFakeNumber() + "@mail.net";
		firstName = "Automation";
		lastName = "FC";
		password = "123456";

		log.info("Register - Step 01: Navigate to Register page");
		// 2) Từ Home page qua Register page khởi tạo Register page lên
		registerPage = homePage.clickToRegisterLink();

		log.info("Register - Step 02: Enter to Firstname textbox with value is" + firstName);
		registerPage.sendkeyToFirstNameTextbox(firstName);
		log.info("Register - Step 03: Enter to lastName textbox with value is" + lastName);
		registerPage.sendkeyToLastNameTextbox(lastName);
		log.info("Register - Step 04: Enter to emailAddress textbox with value is" + emailAddress);
		registerPage.sendkeyToEmailTextbox(emailAddress);
		log.info("Register - Step 05: Enter to password textbox with value is" + password);
		registerPage.sendkeyToPasswordTextbox(password);
		log.info("Register - Step 06: Enter to password textbox with value is" + password);
		registerPage.sendkeyToConfirmPasswordTextbox(password);

		log.info("Register - Step 07: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Register - Step 08: Verify Register Success message display");
		// verifyEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed..");
		log.info("Register - Step 09: Click to logout link ");
		// 3)Từ Register chuyển về Home page khởi tạo lại trang Home Page
		homePage = registerPage.clickToLogoutLink();

	}

	@AfterTest
	public void afterClass() {
		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}