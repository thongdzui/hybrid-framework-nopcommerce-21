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
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageObject.users.UserHomePageObject;
import pageObject.users.UserLoginPageObject;
import pageObject.users.UserRegisterPageObject;

//class A kế thừa class B, có thể dùng các thuộc tính B, B là cha của A
public class Level_15_Allure extends BaseTest {
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

	@Description("Register to system")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void User_01_Register() {

		// 2) Từ Home page qua Register page khởi tạo Register page lên
		registerPage = homePage.clickToRegisterLink();

		registerPage.sendkeyToFirstNameTextbox(firstName);

		registerPage.sendkeyToLastNameTextbox(lastName);

		registerPage.sendkeyToEmailTextbox(emailAddress);

		registerPage.sendkeyToPasswordTextbox(password);

		registerPage.sendkeyToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed..");
		// 3)Từ Register chuyển về Home page khởi tạo lại trang Home Page
		homePage = registerPage.clickToLogoutLink();

	}

	@Description("Login to system")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void User_02_Login() {

		// 4)Từ trang homepage chuyển qua Login page
		loginPage = homePage.clickToLoginLink();

		loginPage.sendkeyToEmailTextBox(emailAddress);

		loginPage.sendkeyToPasswordTextBox(password);

		// 5) login thành công chuyển qua homepage
		homePage = loginPage.clicktoLoginButton();

		Assert.assertFalse(homePage.isMyAccountLinkDisplay());

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