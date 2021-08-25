package javaBasic;

import org.testng.annotations.Test;

public class Topic_01_Data_Type {
	//toàn cục
	
	static int studenNumber;
	static boolean statusValue;
	String studentName = "Automation FC";
	String studentNameStatic = "Fighting";
	static final String BROWSER_NAME = "Chrome";//static final là 1 hằng số, constant phân tách nhau bằng _
	
	//hàm khởi tạo sẽ không có kiểu trả về
	//bắt buộc trùng tên với class
	public Topic_01_Data_Type() {
		String studentName;
	}
	
	public static void main(String[] args) {
		//trong 1 hàm static muốn dùng 1 biến bên ngoài thì biến đó cũng là static
		System.out.println(studenNumber);
		System.out.println(statusValue);
		Topic_01_Data_Type topic_1 = new Topic_01_Data_Type();
		Topic_01_Data_Type topic_2 = new Topic_01_Data_Type();
		Topic_01_Data_Type topic_3 = new Topic_01_Data_Type();
		//truy cập từ tên đối tượng đến tên biến
		System.out.println(topic_1.studentNameStatic);
		System.out.println(topic_2.studentNameStatic);
		System.out.println(topic_3.studentNameStatic);
		
		//truy cập từ tên class đến tên biến
		System.out.println(Topic_01_Data_Type.BROWSER_NAME);
		System.out.println(Topic_01_Data_Type.statusValue);
		System.out.println(Topic_01_Data_Type.studenNumber);
		
	}
	
	//getter: getCurrentURL/getTile/getText/getAttribute/getSize/getLocation/getPosition
	public String getStudentNumber() {
		return this.studentName;
		
	}
	@Test
	public void TC_01() {
		//cục bộ: sử dụng trong phạm vi của testcase/hàm
		String homePageUrl1 = "";
		System.out.println(homePageUrl1);
		
		//block code
		if(3<5) {
			//cục bộ trong 1 khối lệnh
			String homePageTitle2 = "";
			System.out.println(homePageTitle2);
		}
		
	}
	//setter: clic/sendkey/clear/select/forward/refesh/get những action
	public void setStudentName(String stdName) {
		this.studentName = stdName;
	}

}
