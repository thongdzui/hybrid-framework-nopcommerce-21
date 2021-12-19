package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObject extends BasePage {
	private WebDriver driver;
	// private HomePageUI homePageUI = new HomePageUI();

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
		System.out.println("Driver id của HomePageObject" + driver.toString());
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='ico-register']")
	private WebElement registerLink;

	public void clickToRegisterLink() {

		waitForElementClickable(driver, registerLink);
		clickToElement(registerLink);

	}

}
