package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.RegisterPageObject;

//class A kế thừa class B, có thể dùng các thuộc tính B, B là cha của A
public class Level_03_Login_Base_Object_Pattern_Part2 /* extends BasePage */ {
	private WebDriver driver;
	private String existingEmail, notFoundEmail, invalidEmail, firstName, lastName, validPassword;
	// import class HomePageObject
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	String projectPath = System.getProperty("user.dir");
	Select select;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		firstName = "Automation";
		lastName = "FC";
		invalidEmail = "123@456#%*";
		notFoundEmail = "afc" + generateFakeNumber() + "@mail.com";
		existingEmail = "afc" + generateFakeNumber() + "@mail.com";
		validPassword = "123456";

		System.out.println("Driver id của class là " + driver.toString());

		// 1. Mở URL ra thì nó sẽ mở ra trang Home Page (Business page)
		driver.get("https://demo.nopcommerce.com/");
		homePage = new HomePageObject(driver);
		// Chuyển trang business thì khởi tạo page object class lên

		System.out.println("Pre-condition - Step 01: Click to Register Link");
		homePage.clickToRegisterLink();

		registerPage = new RegisterPageObject(driver);
		System.out.println("Pre-condition - Step 02: Input to require filed");

		registerPage.sendkeyToFirstNameTextbox(firstName);
		registerPage.sendkeyToLastNameTextbox(lastName);
		registerPage.sendkeyToEmailTextbox(existingEmail);
		registerPage.sendkeyToPasswordTextbox(validPassword);
		registerPage.sendkeyToConfirmPasswordTextbox(validPassword);

		System.out.println("Pre-condition - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Pre-condition - Step 04: Verify sucess message display");
		Assert.assertEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed");

		registerPage.clickToLogoutLink();
		// Chuyển trang business thì khởi tạo page object class lên
		homePage = new HomePageObject(driver);

	}

	@Test
	public void TC_01_Login_Empty_Data() {

		homePage.clickToLoginLink();
		// từ trang home click Login qua trang login
		loginPage = new LoginPageObject(driver);
		loginPage.clicktoLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Please enter your email");

	}

	@Test
	public void TC_02_Login_Invalid_Email() {

		homePage.clickToLoginLink();
		// từ trang home click Login qua trang login
		loginPage.sendkeyToEmailTextBox(invalidEmail);
		loginPage.clicktoLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Wrong email");

	}

	@Test
	public void TC_03_Login_Email_Not_Found() {
		homePage.clickToLoginLink();
		loginPage.sendkeyToEmailTextBox(notFoundEmail);
		loginPage.clicktoLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

	}

	@Test
	public void TC_04_Login_Existing_Email_Empty_Password() {
		homePage.clickToLoginLink();

		loginPage.sendkeyToEmailTextBox(existingEmail);
		loginPage.clicktoLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void TC_05_Login_Existing_Email_Incorrect_Passwords() {
		homePage.clickToLoginLink();
		loginPage.sendkeyToEmailTextBox(existingEmail);
		loginPage.sendkeyToPasswordTextBox("654321");
		loginPage.clicktoLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {

		homePage.clickToLoginLink();
		loginPage.sendkeyToEmailTextBox(existingEmail);
		loginPage.sendkeyToPasswordTextBox(validPassword);
		loginPage.clicktoLoginButton();
		// login thành công qua trang Homepage
		homePage = new HomePageObject(driver);

		Assert.assertTrue(homePage.isMyAccountLinkDisplay());

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