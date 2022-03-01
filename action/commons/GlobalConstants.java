package commons;

import java.io.File;

public class GlobalConstants {
	// URL
	// Username
	// Password
	// Server / Enviroment
	// Timeout
	// Folder download/ upload
	// ......
	public static final String USER_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_URL = "https://admin-demo.nopcommerce.com/";
	public static final String PROJECT_PATH = System.getProperty("user.dir");

	// win/mac /linux
	public static final String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;

	// tại sao cần thư mục download files
	public static final String DOWNLOAD_FILE = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BRWOSER_LOGS = PROJECT_PATH + File.separator + "browserLogs";
	public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
	public static final String REPORT_SCREENSHOT = PROJECT_PATH + File.separator + "ReportNGImages" + File.separator;

	// Database account/ User/ Pass/ Port
	public static final String DB_DEV_URL = "32.18.252.185:9860";
	public static final String DB_DEV_USER = "automaticfc";
	public static final String DB_DEV_PASS = "P@ssworld";

	public static final String DB_TEST_URL = "32.18.195.23:9860";
	public static final String DB_TEST_USER = "automaticfc";
	public static final String DB_TEST_PASS = "P@ssworld";

	public static final long SHORT_TIME_OUT = 5;
	public static final long LONG_TIME_OUT = 30;
	public static final long RETRY_TEST_FAIL = 3;

}
