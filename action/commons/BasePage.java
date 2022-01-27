package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.admin.AdminLoginPageObject;
import pageObject.users.UserAddressesPageObject;
import pageObject.users.UserCustomerInfoPageObject;
import pageObject.users.UserHomePageObject;
import pageObject.users.UserOrderPageObject;
import pageObject.users.UserRewardPointPageObject;
import pageUIs.jQuery.uploadFile.BasePageJQueryUI;
import pageUIs.nopCommerce.user.BasePageNopCommerceUI;

public class BasePage {
	public static BasePage getBasePage() {
		return new BasePage();
	}

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
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
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

	public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));

	}

	// locatorType id =,css=, xpath=,name=, class=
	private By getByLocator(String locatorType) {
		By by = null;
		System.out.println("Locator type : " + locatorType);
		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator type is not supported");
		}
		return by;

	}

	private String getDynamicXpath(String locatorType, String... dynamicValues) {
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		}
		return locatorType;

	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sleepInMiliSecond(long timeoutInMillisecond) {
		try {
			Thread.sleep(timeoutInMillisecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Web Element */

	public WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	public List<WebElement> getWebElements(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}

	public void sendkeyToElement(WebDriver driver, String locatorType, String value) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(value);
		// driver.findElement(By.xpath(locatorType)).clear();
		// driver.findElement(By.xpath(locatorType)).sendKeys(value);
	}

	public void sendkeyToElement(WebDriver driver, String locatorType, String value, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(value);

	}

	public String getElementText(WebDriver driver, String locatorType) {
		return driver.findElement(By.xpath(locatorType)).getText();

	}

	public String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		return driver.findElement(By.xpath(getDynamicXpath(locatorType, dynamicValues))).getText();

	}

	public void selectItemInDefaultDropDown(WebDriver driver, String locatorType, String itemText) {
		new Select(getWebElement(driver, locatorType)).selectByVisibleText(itemText);
		// Select select = new Select(getWebElement(driver, locatorType));
		// select.selectByVisibleText(itemText);

	}

	public void selectItemInDefaultDropDown(WebDriver driver, String locatorType, String itemText, String... dynamicValues) {
		new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))).selectByVisibleText(itemText);
		// Select select = new Select(getWebElement(driver, locatorType));
		// select.selectByVisibleText(itemText);

	}

	// verify dropdown chọn thành công
	public String getSelectTextInDefaulDropDown(WebDriver driver, String locatorType, String itemText) {
		return new Select(getWebElement(driver, locatorType)).getFirstSelectedOption().getText();

	}

	public boolean isDefaultDropdownMulti(WebDriver driver, String locatorType) {
		return new Select(getWebElement(driver, locatorType)).isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	// bài xử lí textbox textarea
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	public void getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		getWebElement(driver, locatorType).getCssValue(propertyName);
	}

	public String getHexaColorByRgbaColor(String rgbaColor) {
		return Color.fromString(rgbaColor).asHex();
	}

	public int getElementNumber(WebDriver driver, String locatorType) {
		return getWebElements(driver, locatorType).size();

	}

	public void checkToRadioOrCheckbox(WebDriver driver, String locatorType) {
		if (!getWebElement(driver, locatorType).isSelected()) {
			getWebElement(driver, locatorType).click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locatorType) {
		if (getWebElement(driver, locatorType).isSelected()) {
			getWebElement(driver, locatorType).click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	public void switchToFrame(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	public void switchToDefaultContentPage(WebDriver driver) {

	}

	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		new Actions(driver).moveToElement(getWebElement(driver, locatorType)).perform();
		// Actions action = new Actions(driver);
		// action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	public void pressKeyBoardToElement(WebDriver driver, String locatorType, Keys key) {
		new Actions(driver).sendKeys(getWebElement(driver, locatorType), key).perform();
		// Actions action = new Actions(driver);
		// action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		return ((JavascriptExecutor) driver).executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String locatorType) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForElementClickable(WebDriver driver, String locatorType) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	public void waitForAllElementsVisible(WebDriver driver, String locatorType) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	public void waitForAllElementsVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForAllElementClickable(WebDriver driver, String locatorType) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfAllElements(getWebElements(driver, locatorType)));
	}

	public void waitForAllElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfAllElements(getWebElements(driver, getDynamicXpath(locatorType, dynamicValues))));
	}

	// ham Upload file
	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FILE_FOLDER;
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";

		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, BasePageJQueryUI.UPLOAD_FILE).sendKeys(fullFileName);

	}

	// Từ 56 hàm ruốt ngắn còn 8 hàm mở 8 page
	// demo 4 page có 4 hàm
	// User site
	public UserOrderPageObject clickToOrderLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.ORDER_PAGE_LINK);
		clickToElement(driver, BasePageNopCommerceUI.ORDER_PAGE_LINK);
		// return new LoginPageObject(driver);
		return PageGeneratorManager.getUserOrderPage(driver);
	}

	public UserAddressesPageObject clickToAddressesPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.ADDRESSES_PAGE_LINK);
		clickToElement(driver, BasePageNopCommerceUI.ADDRESSES_PAGE_LINK);
		// return new LoginPageObject(driver);
		return PageGeneratorManager.getUserAddressesPage(driver);
	}

	public UserRewardPointPageObject clickToRewardPointLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.REWARD_POINT_LINK);
		clickToElement(driver, BasePageNopCommerceUI.REWARD_POINT_LINK);
		// return new LoginPageObject(driver);
		return PageGeneratorManager.getUserRewardPointPage(driver);
	}

	public UserCustomerInfoPageObject clickToCustomerInfoLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.CUTOMER_INFO_LINK);
		clickToElement(driver, BasePageNopCommerceUI.CUTOMER_INFO_LINK);
		// return new LoginPageObject(driver);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public UserHomePageObject clickToUserLogoutLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.USER_LOGOUT_LINK);
		clickToElement(driver, BasePageNopCommerceUI.USER_LOGOUT_LINK);
		return PageGeneratorManager.getUserHomePage(driver);

	}

	public BasePage openPagesAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_PAGE_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_PAGE_MY_ACCOUNT_AREA, pageName);
		switch (pageName) {
		case "Customer info":
			PageGeneratorManager.getUserCustomerInfoPage(driver);
		case "Addresses":
			PageGeneratorManager.getUserAddressesPage(driver);
		case "My product reviews":
			PageGeneratorManager.getUserCustomerInfoPage(driver);
		case "Reward points":
			PageGeneratorManager.getUserRewardPointPage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account Area");
		}
	}

	// admin site
	public AdminLoginPageObject clickToAdminLogoutLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.ADMIN_LOGOUT_LINK);
		clickToElement(driver, BasePageNopCommerceUI.ADMIN_LOGOUT_LINK);
		return PageGeneratorManager.getAdminLoginPage(driver);

	}

	private long longTimeout = GlobalConstants.LONG_TIME_OUT;

}
