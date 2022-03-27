package com.nopcommerce.common;

import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Cookie;
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
public class Common_01_Register_Cookies extends BaseTest {
	private WebDriver driver;
	public static String emailAddress, password;
	private String firstName, lastName;
	// import class HomePageObject
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	public static Set<Cookie> loggedCookies;

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

		log.info("Pre-condition - Step 01: Navigate to Register page");
		// 2) Từ Home page qua Register page khởi tạo Register page lên
		registerPage = homePage.clickToRegisterLink();

		log.info("Pre-condition - Step 02: Enter to Firstname textbox with value is" + firstName);
		registerPage.sendkeyToFirstNameTextbox(firstName);
		log.info("Pre-condition - Step 03: Enter to lastName textbox with value is" + lastName);
		registerPage.sendkeyToLastNameTextbox(lastName);
		log.info("Pre-condition - Step 04: Enter to emailAddress textbox with value is" + emailAddress);
		registerPage.sendkeyToEmailTextbox(emailAddress);
		log.info("Pre-condition - Step 05: Enter to password textbox with value is" + password);
		registerPage.sendkeyToPasswordTextbox(password);
		log.info("Pre-condition - Step 06: Enter to password textbox with value is" + password);
		registerPage.sendkeyToConfirmPasswordTextbox(password);

		log.info("Pre-condition - Step 07: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Pre-condition - Step 08: Verify Register Success message display");
		// verifyEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed..");
		log.info("Pre-condition - Step 09: Click to logout link ");
		// 3)Từ Register chuyển về Home page khởi tạo lại trang Home Page
		homePage = registerPage.clickToLogoutLink();

		log.info("Preconditon - Step 10: Navigate to Login page");
		// 4)Từ trang homepage chuyển qua Login page
		loginPage = homePage.clickToLoginLink();

		log.info("Preconditon - Step 11: Enter to emailAddress textbox with value is " + emailAddress);
		loginPage.sendkeyToEmailTextBox(emailAddress);
		log.info("Preconditon - Step 12: Enter to password textbox with value is" + password);
		loginPage.sendkeyToPasswordTextBox(password);

		log.info("Preconditon - Step 13: Click to Login button");
		// 5) login thành công chuyển qua homepage
		homePage = loginPage.clicktoLoginButton();

		// Sau khi loggin thành công
		// Get all cookies
		loggedCookies = driver.manage().getCookies();

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