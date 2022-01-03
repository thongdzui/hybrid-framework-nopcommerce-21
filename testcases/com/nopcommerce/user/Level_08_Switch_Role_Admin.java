package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.admin.AdminLoginPageObject;
import pageObject.users.UserHomePageObject;

//class A kế thừa class B, có thể dùng các thuộc tính B, B là cha của A
public class Level_08_Switch_Role_Admin extends BaseTest {
	private WebDriver driver;

	// import class HomePageObject
	UserHomePageObject homePage;
	AdminLoginPageObject adminLoginPage;

	WebDriverWait explicitWait;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {

		// 2) Quyền Admin mở url thì nó mở trang Admin Login Page
		driver = getBrowserDriver(browserName, url);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

	}

	@Test
	public void Role_01_Switch_User_To_Admin() {

	}

	@Test
	public void Role_02_Switch_Admin_To_User() {

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