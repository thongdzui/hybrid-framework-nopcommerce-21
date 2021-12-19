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
import pageObject.CustomerInfoPageObject;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.RegisterPageObject;

//class A kế thừa class B, có thể dùng các thuộc tính B, B là cha của A
public class Level_06_Page_Generator_Manager_Part1 extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	// import class HomePageObject
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfo;

	WebDriverWait explicitWait;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		// Đưa việc khởi tạo page vào chính class test
		// 1) mở url thì nó mở trang homepage bussiness
		driver = getBrowserDriver(browserName);
		homePage = new HomePageObject(driver);

		emailAddress = "afc" + generateFakeNumber() + "@mail.net";
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		System.out.println("Driver id của class là " + driver.toString());
		explicitWait = new WebDriverWait(driver, 30);

	}

	@Test
	public void User_01_Register_To_System() {
		homePage.clickToRegisterLink();

		// 2) Từ Home page qua Register page khởi tạo Register page lên
		registerPage = new RegisterPageObject(driver);

		registerPage.sendkeyToFirstNameTextbox(firstName);
		registerPage.sendkeyToLastNameTextbox(lastName);
		registerPage.sendkeyToEmailTextbox(emailAddress);
		registerPage.sendkeyToPasswordTextbox(password);
		registerPage.sendkeyToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed");

		registerPage.clickToLogoutLink();
		// 3)Từ Register chuyển về Home page khởi tạo lại trang Home Page
		homePage = new HomePageObject(driver);

	}

	@Test
	public void User_02_Login_To_System() {

		homePage.clickToLoginLink();
		// 4)Từ trang homepage chuyển qua Login page
		loginPage = new LoginPageObject(driver);

		loginPage.sendkeyToEmailTextBox(emailAddress);
		loginPage.sendkeyToPasswordTextBox(password);

		loginPage.clicktoLoginButton();

		// 5) login thành công chuyển qua homepage
		homePage = new HomePageObject(driver);

	}

	@Test
	public void User_03_My_Account_Info() {

		homePage.clickToMyAccountLink();
		// 6Từ trang Login chuyển qua Customer info page
		customerInfo = new CustomerInfoPageObject(driver);

		Assert.assertEquals(customerInfo.getFirstNameTextboxValue(), firstName);
		Assert.assertEquals(customerInfo.getLastNameTextboxValue(), lastName);
		Assert.assertEquals(customerInfo.getEmailTextboxValue(), emailAddress);

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