package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObject.admin.AdminDashboardPageObject;
import pageObject.admin.AdminLoginPageObject;
import pageObject.users.UserCustomerInfoPageObject;
import pageObject.users.UserHomePageObject;
import pageObject.users.UserLoginPageObject;

//class A kế thừa class B, có thể dùng các thuộc tính B, B là cha của A
public class Level_08_Switch_Role_User extends BaseTest {
	private WebDriver driver;

	// import class HomePageObject
	UserHomePageObject userHomePage;
	AdminLoginPageObject adminLoginPage;
	UserLoginPageObject userLoginPage;
	AdminDashboardPageObject adminDashboardPage;
	UserCustomerInfoPageObject userCustomerPage;
	String userEmailAddress, userPassword, adminEmailAddress, adminPassword;

	WebDriverWait explicitWait;

	// @Parameters({ "browser", "url" })
	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		userEmailAddress = "kaka123@mail.net";
		userPassword = "123456";
		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";
		// 1) Quyền User mở url thì nó mở trang User homepage bussiness
		driver = getBrowserDriver(browserName, GlobalConstants.USER_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

	}

	@Test
	public void Role_01_Switch_User_To_Admin() {
		// Home -> Login
		userLoginPage = userHomePage.clickToLoginLink();

		// Login =>...
		// userLoginPage.sendkeyToEmailTextBox(userEmailAddress);
		// userLoginPage.sendkeyToPasswordTextBox(userPassword);
		// userHomePage = userLoginPage.clicktoLoginButton();

		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);

		// Home =>My Account

		userCustomerPage = userHomePage.clickToMyAccountLink();
		// Customer Info =>Click Logout Link => Home
		userHomePage = userCustomerPage.clickToUserLogoutLink(driver);

		// từ 1 page bất kì của User mở ra trang Admin Login
		userHomePage.openUrl(driver, GlobalConstants.ADMIN_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		// Login -> Dashboard
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);

		// Anypage => Click log out -> Login Page admin
		adminLoginPage = adminDashboardPage.clickToAdminLogoutLink(driver);

	}

	@Test
	public void Role_02_Switch_Admin_To_User() {
		// Login/ Any Page (Admin) -> Mở ra User URl -> Home Page (User)
		adminLoginPage.openUrl(driver, GlobalConstants.USER_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		// Home -> Register/ Login/ Checkout/ Shopping/.....

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}