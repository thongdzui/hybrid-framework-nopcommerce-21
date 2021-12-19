package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	public WebDriver getBrowserDriver(String browserName) {
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

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// 1. Mở URL ra thì nó sẽ mở ra trang Home Page (Business page)
		driver.get("https://demo.nopcommerce.com/");

		return driver;
	}

}
