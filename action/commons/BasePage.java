package commons;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	/* Web Browser */
	public void openUrl(WebDriver driver, String url) {
		driver.get(url);

	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refeshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void fowardPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void acceptAlert(WebDriver driver) {
		// driver.switchTo().alert().accept();
		waitForAlertPresence(driver).accept();

	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void getAlertText(WebDriver driver) {
		driver.switchTo().alert().getText();
	}

	public void sendkeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void switchToWIndowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String id : allWindows) {

			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
			}
		}

	}

	public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {

		Set<String> allWindows = driver.getWindowHandles();

		for (String id : allWindows) {
			driver.switchTo().window(id);
			String windowTitle = driver.getTitle();

			if (windowTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	public void closeWindowWithoutParent(WebDriver driver, String parentID) {

		Set<String> allWindows = driver.getWindowHandles();

		for (String id : allWindows) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}

		driver.switchTo().window(parentID);

	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Web Element */
	public By getByXpath(String xpathExpression) {
		return By.xpath(xpathExpression);
	}

	public WebElement getWebElement(WebDriver driver, String xpathExpression) {
		return driver.findElement(getByXpath(xpathExpression));

	}

	public void clickToElement(WebDriver driver, String xpathExpression) {
		getWebElement(driver, xpathExpression).click();
	}

	public void sendkeyToElement(WebDriver driver, String xpathExpression, String value) {
		driver.findElement(By.xpath(xpathExpression)).clear();
		driver.findElement(By.xpath(xpathExpression)).sendKeys(value);
	}

	public String getElementText(WebDriver driver, String xpathExpression) {
		return driver.findElement(By.xpath(xpathExpression)).getText();

	}
}
