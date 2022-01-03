package pageUIs;

public class BasePageUI {
	// 7 locator đại diện cho 7 page
	public static final String ORDER_PAGE_LINK = "//div[@class='side-2']//a[text()='Orders']";

	public static final String REWARD_POINT_LINK = "//div[contains(@class,'account-navigation')]//a[text()='Reward points']";

	public static final String ADDRESSES_PAGE_LINK = "//div[contains(@class,'account-navigation')]//a[text()='Addresses']";

	public static final String CUTOMER_INFO_LINK = "//div[contains(@class,'account-navigation')]//a[text()='Customer info']";

	// Logout Link
	public static final String ADMIN_LOGOUT_LINK = "//div[@id='navbarText']//a[text()='Logout']";
	public static final String USER_LOGOUT_LINK = "//a[@class='ico-logout']";
}
