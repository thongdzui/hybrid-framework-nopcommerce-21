package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.HomePageObject;
import pageObjects.jQuery.PageGeneratorManager;

//class A kế thừa class B, có thể dùng các thuộc tính B, B là cha của A
public class Level_10_DataTable_DataGrid extends BaseTest {
	private WebDriver driver;
	HomePageObject homePage;

	// @Parameters({ "browser", "url" })
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {

		// 1) Quyền User mở url thì nó mở trang User homepage bussiness
		driver = getBrowserDriver(browserName, urlValue);
		homePage = PageGeneratorManager.getHomePage(driver);

	}

	@Test
	public void Table01_Paging() {
		homePage.openPagingByPageNumber("7");

	}

	@Test
	public void Table02() {

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}