package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class User_01_Register_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		// driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");

		driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01() {
	}

	@Test
	public void TC_02() {
	}

	@Test
	public void TC_03() {
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
