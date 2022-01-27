package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.uploadFile.HomePageObject;
import pageObjects.jQuery.uploadFile.PageGeneratorManager;

//class A kế thừa class B, có thể dùng các thuộc tính B, B là cha của A
public class Level_11_Upload_File extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	String pythonFileName = "python.png";
	String rubyFileName = "ruby.png";
	String[] multiFile = { pythonFileName, rubyFileName };

	// @Parameters({ "browser", "url" })
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {

		// 1) Quyền User mở url thì nó mở trang User homepage bussiness
		driver = getBrowserDriver(browserName, urlValue);
		homePage = PageGeneratorManager.getHomePage(driver);

	}

	@Test
	public void Upload01_One_File_Per_Time() {
		homePage.uploadMultipleFiles(driver, pythonFileName);
		Assert.assertTrue(homePage.isFileLoadedByName(pythonFileName));
		// homePage.clickToStartButton();

	}

	@Test
	public void Upload02_Multi_File_Per_Time() {
		homePage.refeshPage(driver);
		homePage.uploadMultipleFiles(driver, multiFile);

		Assert.assertTrue(homePage.isFileLoadedByName(pythonFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(rubyFileName));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}