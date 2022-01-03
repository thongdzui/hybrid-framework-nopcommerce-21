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
import pageFactory.HomePageObject;
import pageFactory.RegisterPageObject;

//class A kế thừa class B, có thể dùng các thuộc tính B, B là cha của A
public class Level_05_Page_Factory extends BaseTest {
	private WebDriver driver;
	private String emailAddress;
	// import class HomePageObject
	private HomePageObject homePage;
	private RegisterPageObject registerPage;

	WebDriverWait explicitWait;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);

		/*
		 * switch (browserName) { case "firefox": System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver"); driver = new
		 * FirefoxDriver(); break; case "chrome": System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		 * 
		 * driver = new ChromeDriver(); break; case "edge": System.setProperty("webdriver.edge.driver", projectPath + "/browserDriver/msedgedriver"); driver = new
		 * EdgeDriver(); break;
		 * 
		 * default: throw new RuntimeException("This bowser is not support"); }
		 */
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
		System.out.println("Driver id của class là " + driver.toString());
		explicitWait = new WebDriverWait(driver, 30);

		homePage = new HomePageObject(driver);
		// Chuyển trang business thì khởi tạo page object class lên
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		// TDD
		// Viet code truoc, implement sau
		// 2. Từ Home Page click vào Register link chuyển trang Register Page
		homePage.clickToRegisterLink();
		// clickToElement(driver, "//a[@class='ico-register']");

		// khởi tạo Register page lên
		registerPage = new RegisterPageObject(driver);
		registerPage.clickToRegisterButton();
		// clickToElement(driver, "//button[@id='register-button']");

		// driver.findElement(By.cssSelector("a.ico-register")).click();
		// driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
		Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");

		/*
		 * Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required."); Assert.assertEquals(getElementText(driver,
		 * "//span[@id='LastName-error']"), "Last name is required."); Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		 * Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required."); Assert.assertEquals(getElementText(driver,
		 * "//span[@id='ConfirmPassword-error']"), "Password is required.");
		 */
		/*
		 * Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(), "First name is required.");
		 * Assert.assertEquals(driver.findElement(By.cssSelector("span#LastName-error")).getText(), "Last name is required.");
		 * Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Email is required.");
		 * Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password is required.");
		 * Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "Password is required.");
		 */
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		// Vẫn đang ở trang Register page
		homePage.clickToRegisterLink();
		// clickToElement(driver, "//a[@class='ico-register']");
		// driver.findElement(By.cssSelector("a.ico-register")).click();
		// khởi tạo lại Register page
		registerPage = new RegisterPageObject(driver);

		registerPage.sendkeyToFirstNameTextbox("Automation");
		registerPage.sendkeyToLastNameTextbox("FC");
		registerPage.sendkeyToEmailTextbox("123@456#%*");
		registerPage.sendkeyToPasswordTextbox("123456");
		registerPage.sendkeyToConfirmPasswordTextbox("123456");

		/*
		 * sendkeyToElement(driver, "//input[@id='FirstName']", "Automation"); sendkeyToElement(driver, "//input[@id='LastName']", "FC"); sendkeyToElement(driver,
		 * "//input[@id='Email']", "123@456#%*"); sendkeyToElement(driver, "//input[@id='Password']", "123456"); sendkeyToElement(driver,
		 * "//input[@id='ConfirmPassword']", "123456");
		 */

		/*
		 * driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation"); driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		 * driver.findElement(By.cssSelector("input#Email")).sendKeys("123@456#%*"); driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		 * driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		 */
		registerPage.clickToRegisterButton();
		// driver.findElement(By.cssSelector("button#register-button")).click();
		// clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Wrong email");

		// Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
		// Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Wrong email");

	}

	@Test
	public void TC_03_Register_Success() {
		homePage.clickToRegisterLink();
		// khởi tạo lại Register page
		registerPage = new RegisterPageObject(driver);
		// clickToElement(driver, "//a[@class='ico-register']");
		// driver.findElement(By.cssSelector("a.ico-register")).click();

		registerPage.sendkeyToFirstNameTextbox("Automation");
		registerPage.sendkeyToLastNameTextbox("FC");
		registerPage.sendkeyToEmailTextbox(emailAddress);
		registerPage.sendkeyToPasswordTextbox("123456");
		registerPage.sendkeyToConfirmPasswordTextbox("123456");

		/*
		 * sendkeyToElement(driver, "//input[@id='FirstName']", "Automation"); sendkeyToElement(driver, "//input[@id='LastName']", "FC"); sendkeyToElement(driver,
		 * "//input[@id='Email']", emailAddress); sendkeyToElement(driver, "//input[@id='Password']", "123456"); sendkeyToElement(driver,
		 * "//input[@id='ConfirmPassword']", "123456");
		 */

		/*
		 * driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation"); driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		 * driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress); driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		 * driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		 */

		// driver.findElement(By.cssSelector("button#register-button")).click();
		// clickToElement(driver, "//button[@id='register-button']");
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed");
		// Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

		// 3 - Từ trang Register chuyển qua HomePage
		registerPage.clickToLogoutLink();
		// clickToElement(driver, "//a[@class='ico-logout']");
		// driver.findElement(By.cssSelector("a.ico-logout")).click();
		// khởi tạo lại trang homePage
		homePage = new HomePageObject(driver);
	}

	@Test
	public void TC_04_Register_Existing_Email() {

		homePage.clickToRegisterLink();
		// clickToElement(driver, "//a[@class='ico-register']");
		// driver.findElement(By.cssSelector("a.ico-register")).click();

		registerPage.sendkeyToFirstNameTextbox("Automation");
		registerPage.sendkeyToLastNameTextbox("FC");
		registerPage.sendkeyToEmailTextbox(emailAddress);
		registerPage.sendkeyToPasswordTextbox("123456");
		registerPage.sendkeyToConfirmPasswordTextbox("123456");

		/*
		 * sendkeyToElement(driver, "//input[@id='FirstName']", "Automation"); sendkeyToElement(driver, "//input[@id='LastName']", "FC"); sendkeyToElement(driver,
		 * "//input[@id='Email']", emailAddress); sendkeyToElement(driver, "//input[@id='Password']", "123456"); sendkeyToElement(driver,
		 * "//input[@id='ConfirmPassword']", "123456");
		 */

		/*
		 * driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation"); driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		 * driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress); driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		 * driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		 */

		registerPage.clickToRegisterButton();
		// clickToElement(driver, "//button[@id='register-button']");
		// driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(registerPage.getExsitedEmailErrorMessage(), "The specified email already exists");
		// Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(), "The specified email already exists");
	}

	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		homePage.clickToRegisterLink();
		// clickToElement(driver, "//a[@class='ico-register']");

		registerPage.sendkeyToFirstNameTextbox("Automation");
		registerPage.sendkeyToLastNameTextbox("FC");
		registerPage.sendkeyToEmailTextbox(emailAddress);
		registerPage.sendkeyToPasswordTextbox("123");
		registerPage.sendkeyToConfirmPasswordTextbox("123");

		/*
		 * sendkeyToElement(driver, "//input[@id='FirstName']", "Automation"); sendkeyToElement(driver, "//input[@id='LastName']", "FC"); sendkeyToElement(driver,
		 * "//input[@id='Email']", emailAddress); sendkeyToElement(driver, "//input[@id='Password']", "123"); sendkeyToElement(driver,
		 * "//input[@id='ConfirmPassword']","123");
		 */

		/*
		 * driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation"); driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		 * driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress); driver.findElement(By.cssSelector("input#Password")).sendKeys("123");
		 * driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");
		 */

		registerPage.clickToRegisterButton();
		// clickToElement(driver, "//button[@id='register-button']");
		// driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password must meet the following rules:\nmust have at least 6 characters");
		// Assert.assertEquals(driver.findElement(By.cssSelector("#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6
		// characters");
	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		homePage.clickToRegisterLink();
		// driver.findElement(By.cssSelector("a.ico-register")).click();
		// clickToElement(driver, "//a[@class='ico-register']");

		registerPage.sendkeyToFirstNameTextbox("Automation");
		registerPage.sendkeyToLastNameTextbox("FC");
		registerPage.sendkeyToEmailTextbox(emailAddress);
		registerPage.sendkeyToPasswordTextbox("123456");
		registerPage.sendkeyToConfirmPasswordTextbox("654321");

		/*
		 * sendkeyToElement(driver, "//input[@id='FirstName']", "Automation"); sendkeyToElement(driver, "//input[@id='LastName']", "FC"); sendkeyToElement(driver,
		 * "//input[@id='Email']", emailAddress); sendkeyToElement(driver, "//input[@id='Password']", "123456"); sendkeyToElement(driver,
		 * "//input[@id='ConfirmPassword']", "654321");
		 */

		/*
		 * driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation"); driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		 * driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress); driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		 * driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("654321");
		 */

		registerPage.clickToRegisterButton();

		// driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "The password and confirmation password do not match.");
		// Assert.assertEquals(driver.findElement(By.cssSelector("#ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
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