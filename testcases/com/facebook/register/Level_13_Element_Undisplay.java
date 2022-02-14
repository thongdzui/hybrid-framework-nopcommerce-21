package com.facebook.register;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;

//class A kế thừa class B, có thể dùng các thuộc tính B, B là cha của A
public class Level_13_Element_Undisplay extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		// Đưa việc khởi tạo vào trong 1 hàm trc đó
		// 1) mở url thì nó mở trang homepage bussiness
		driver = getBrowserDriver(browserName, url);
		loginPage = PageGeneratorManager.getLoginPage(driver);

	}

	@Test
	public void TC01_Verify_Element_Display() {
		loginPage.clickToCreateNewAccountButton();
		verifyTrue(loginPage.isEmailAddressTextboxDisplayed());

		loginPage.enterToEmailAddressTextbox("automationfc@gmail.com");
		loginPage.sleepInSecond(3);

		// verify True - mong doi Comfirm Email Display - true
		verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());

	}

	@Test
	public void TC02_Verify_Element_UnDisplay_IN_DOM() {
		// verify False - mong doi comfirm email undisplayed

		loginPage.enterToEmailAddressTextbox("");
		loginPage.sleepInSecond(3);
		// verify False - cho ham tra ve la displayed
		verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());

	}

	// isDisplayed: ban chat khong kiem tra 1 element khong co trong DOM dc vi no khong pass dc ham findElement lam sao no isDisplayed
	@Test
	public void TC03_Verify_Element_UnDisplay_Not_DOM() {
		loginPage.clickCloseIconAtRegisterForm();
		// khi close form Register thi Confirm Email khong con trong DOM nua
		// nen ham find element se bi failed
		loginPage.sleepInSecond(3);

		verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());

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