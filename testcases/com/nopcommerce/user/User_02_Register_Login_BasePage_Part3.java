package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

//class A kế thừa class B, có thể dùng các thuộc tính B, B là cha của A
public class User_02_Register_Login_BasePage_Part3 extends BasePage {
	WebDriver driver;
	String emailAddress;
	String projectPath = System.getProperty("user.dir");
	Select select;
	WebDriverWait explicitWait;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();

		explicitWait = new WebDriverWait(driver, 30);
		action = new Actions(driver);

		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		// driver.get("https://demo.nopcommerce.com/");
		openUrl(driver, "https://demo.nopcommerce.com/");
		clickToElement(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//button[@id='register-button']");

		// driver.findElement(By.cssSelector("a.ico-register")).click();
		// driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");

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
		clickToElement(driver, "//a[@class='ico-register']");
		// driver.findElement(By.cssSelector("a.ico-register")).click();

		sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendkeyToElement(driver, "//input[@id='LastName']", "FC");
		sendkeyToElement(driver, "//input[@id='Email']", "123@456#%*");
		sendkeyToElement(driver, "//input[@id='Password']", "123456");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

		/*
		 * driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation"); driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		 * driver.findElement(By.cssSelector("input#Email")).sendKeys("123@456#%*"); driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		 * driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		 */

		driver.findElement(By.cssSelector("button#register-button")).click();
		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
		// Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Wrong email");

	}

	@Test
	public void TC_03_Register_Success() {
		clickToElement(driver, "//a[@class='ico-register']");
		// driver.findElement(By.cssSelector("a.ico-register")).click();

		sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendkeyToElement(driver, "//input[@id='LastName']", "FC");
		sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		sendkeyToElement(driver, "//input[@id='Password']", "123456");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

		/*
		 * driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation"); driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		 * driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress); driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		 * driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		 */

		// driver.findElement(By.cssSelector("button#register-button")).click();
		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
		// Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

		clickToElement(driver, "//a[@class='ico-logout']");
		// driver.findElement(By.cssSelector("a.ico-logout")).click();
	}

	@Test
	public void TC_04_Register_Existing_Email() {
		clickToElement(driver, "//a[@class='ico-register']");
		// driver.findElement(By.cssSelector("a.ico-register")).click();

		sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendkeyToElement(driver, "//input[@id='LastName']", "FC");
		sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		sendkeyToElement(driver, "//input[@id='Password']", "123456");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

		/*
		 * driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation"); driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		 * driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress); driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		 * driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		 */

		clickToElement(driver, "//button[@id='register-button']");
		// driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
		// Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(), "The specified email already exists");
	}

	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		clickToElement(driver, "//a[@class='ico-register']");
		// driver.findElement(By.cssSelector("a.ico-register")).click();

		sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendkeyToElement(driver, "//input[@id='LastName']", "FC");
		sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		sendkeyToElement(driver, "//input[@id='Password']", "123");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123");

		/*
		 * driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation"); driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		 * driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress); driver.findElement(By.cssSelector("input#Password")).sendKeys("123");
		 * driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");
		 */

		clickToElement(driver, "//button[@id='register-button']");
		// driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
		// Assert.assertEquals(driver.findElement(By.cssSelector("#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6
		// characters");
	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		// driver.findElement(By.cssSelector("a.ico-register")).click();
		clickToElement(driver, "//a[@class='ico-register']");

		sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendkeyToElement(driver, "//input[@id='LastName']", "FC");
		sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		sendkeyToElement(driver, "//input[@id='Password']", "123456");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "654321");

		/*
		 * driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation"); driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		 * driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress); driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		 * driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("654321");
		 */
		clickToElement(driver, "//button[@id='register-button']");
		// driver.findElement(By.cssSelector("button#register-button")).click();
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
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