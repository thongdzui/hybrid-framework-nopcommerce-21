package pageUIs.nopCommerce.user;

public class UserHomePageUI {
	// locator của search button
	// locator của user name text box
	// static final là hằng số, viết hoa
	// biến REGISTER_LINK thuộc class
	// biến loginLink thuộc đối tượng, phải khởi tạo đối tưỡng mới truy cập dc
	public static final String REGISTER_LINK = "css=a[class='ico-register']";
	public static final String LOGIN_LINK = "xpath=//a[@class='ico-login']";
	// public String loginLink = "//a[@class='ico-login']";

	public static final String MY_ACCOUNT_LINK = "xpath=//a[@class='ico-account']";
}