package commons;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	protected final Log log;

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	public WebDriver getDriverInstance() {
		return this.driver;
	}

	public WebDriver getBrowserDriver(String browserName, String urlValue) {
		// dùng Web Driver Manager
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

		if (browser == BrowserList.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser == BrowserList.CHROME) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser == BrowserList.EDGE) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("This bowser is not support");
		}

		// dùng enum

		// BrowserLists browser = BrowserLists.valueOf(browserName.toUpperCase());
		// if (browser == BrowserLists.FIREFOX) {
		// System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		// driver = new FirefoxDriver();
		// } else if (browser == BrowserLists.CHROME) {
		// System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		// driver = new ChromeDriver();
		// } else if (browser == BrowserLists.EDGE) {
		// System.setProperty("webdriver.edge.driver", projectPath + "/browserDriver/msedgedriver");
		// driver = new EdgeDriver();
		// } else {
		// // xử lí trường hợp nhập sai
		// throw new RuntimeException("This bowser is not support");
		// }

		/*
		 * if (browserName.equalsIgnoreCase("firefox")) { System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver"); driver = new
		 * FirefoxDriver(); } else if (browserName.equalsIgnoreCase("chrome")) { System.setProperty("webdriver.chrome.driver", projectPath +
		 * "/browserDriver/chromedriver"); driver = new ChromeDriver(); } else if (browserName.equalsIgnoreCase("edge")) { System.setProperty("webdriver.edge.driver",
		 * projectPath + "/browserDriver/msedgedriver"); driver = new EdgeDriver(); }
		 */

		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIME_OUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// 1. Mở URL ra thì nó sẽ mở ra trang Home Page (Business page)
		driver.get(urlValue);

		return driver;
	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

}
