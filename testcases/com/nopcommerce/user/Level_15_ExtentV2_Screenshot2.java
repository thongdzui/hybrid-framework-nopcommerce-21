package com.nopcommerce.user;

import java.lang.reflect.Method;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.users.UserHomePageObject;
import pageObject.users.UserLoginPageObject;
import pageObject.users.UserRegisterPageObject;
import reportConfig.ExtentTestManager;

//class A kế thừa class B, có thể dùng các thuộc tính B, B là cha của A
public class Level_15_ExtentV2_Screenshot2 extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	// import class HomePageObject
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;

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
	public void User_01_Register(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_01_Register");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 01: Navigate to Register page");

		// 2) Từ Home page qua Register page khởi tạo Register page lên
		registerPage = homePage.clickToRegisterLink();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 02: Enter to Firstname textbox with value is" + firstName);
		registerPage.sendkeyToFirstNameTextbox(firstName);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 03: Enter to lastName textbox with value is" + lastName);
		registerPage.sendkeyToLastNameTextbox(lastName);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 04: Enter to emailAddress textbox with value is" + emailAddress);
		registerPage.sendkeyToEmailTextbox(emailAddress);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 05: Enter to password textbox with value is" + password);
		registerPage.sendkeyToPasswordTextbox(password);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 06: Enter to password textbox with value is" + password);
		registerPage.sendkeyToConfirmPasswordTextbox(password);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 07: Click to Register button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 08: Verify Register Success message display");
		Assert.assertEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed..");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 09: Click to logout link ");
		// 3)Từ Register chuyển về Home page khởi tạo lại trang Home Page
		homePage = registerPage.clickToLogoutLink();
		ExtentTestManager.endTest();

	}

	@Test
	public void User_02_Login(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_02_Login");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 01: Navigate to Login page");
		// 4)Từ trang homepage chuyển qua Login page
		loginPage = homePage.clickToLoginLink();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 02: Enter to emailAddress textbox with value is " + emailAddress);
		loginPage.sendkeyToEmailTextBox(emailAddress);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 03: Enter to password textbox with value is" + password);
		loginPage.sendkeyToPasswordTextBox(password);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 04: Click to Login button");
		// 5) login thành công chuyển qua homepage
		homePage = loginPage.clicktoLoginButton();

		ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 05: Verify My Account Link is display");
		Assert.assertFalse(homePage.isMyAccountLinkDisplay());
		ExtentTestManager.endTest();
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