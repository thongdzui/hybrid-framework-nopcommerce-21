package pageUIs.nopCommerce.user;

public class BasePageNopCommerceUI {
	// 7 locator đại diện cho 7 page
	public static final String ORDER_PAGE_LINK = "xpath=//div[@class='side-2']//a[text()='Orders']";

	public static final String REWARD_POINT_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Reward points']";
	public static final String DYNAMIC_PAGE_MY_ACCOUNT_AREA = "xpath=//div[contains(@class,'account-navigation')]//a[text()='%s']";
	public static final String MY_PRODUCT_REVIEW_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='My product reviews']";

	public static final String ADDRESSES_PAGE_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Addresses']";

	public static final String CUTOMER_INFO_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Customer info']";

	// Logout Link
	public static final String ADMIN_LOGOUT_LINK = "xpath=//div[@id='navbarText']//a[text()='Logout']";
	public static final String USER_LOGOUT_LINK = "xpath=//a[@class='ico-logout']";

	// Pattern Object
	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
	public static final String DYNAMIC_DROP_DOWN_BY_NAME = "xpath=//select[@name='%s']";
	// public static final String DYNAMIC_RADIO_BUTTON_BY_RADIO = "xpath=//label[text()='%s']/preceding-sibling::input";
	// public static final String DYNAMIC_RADIO_BUTTON_BY_CHECK_BOX = "xpath=//label[contains(text(),'%s')]/following-sibling::input";
}
