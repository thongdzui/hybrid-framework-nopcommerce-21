package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_New_Account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.users.UserHomePageObject;
import pageObject.users.UserLoginPageObject;

//class A kế thừa class B, có thể dùng các thuộc tính B, B là cha của A
public class Level_16_Share_Data extends BaseTest {
	private WebDriver driver;

	// import class HomePageObject
	private UserHomePageObject homePage;
	private String firstName, lastName, emailAddress, password;
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

		emailAddress = Common_01_Register_New_Account.emailAddress;
		password = Common_01_Register_New_Account.password;

		log.info("Pre-condition - Step 01: Navigate to Login page");
		// 4)Từ trang homepage chuyển qua Login page
		loginPage = homePage.clickToLoginLink();

		log.info("Pre-condition - Step 02: Enter to emailAddress textbox with value is " + emailAddress);
		loginPage.sendkeyToEmailTextBox(emailAddress);
		log.info("Pre-condition - Step 03: Enter to password textbox with value is" + password);
		loginPage.sendkeyToPasswordTextBox(password);

		log.info("Pre-condition - Step 04: Click to Login button");
		// 5) login thành công chuyển qua homepage
		homePage = loginPage.clicktoLoginButton();

	}

	@Test
	public void Search_01_Empty_Data() {

	}

	@Test
	public void Seatch_02_Relative_Product_Name() {

	}

	@Test
	public void Seatch_03_Absolute_Product_Name() {

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