package eclipseTips;

public class Topic_03_Getter_Setter {
	// biến private bên ngoài ko thể truy cập dc, thông qua hàm, đó là hàm getter/setter
	// tính đóng gói ko cho phép truy cập qua tên biến mà qua method
	private String carName;
	private String carType;
	private String carColor;
	private String carMachine;
	// testtttttt

	/**
	 * @return the carType
	 */
	String getCarType() {
		return carType;
	}

	/**
	 * @param carType the carType to set
	 */
	void setCarType(String carType) {
		this.carType = carType;

	}

	/**
	 * @return the carColor
	 */
	String getCarColor() {
		return carColor;
	}

	/**
	 * @param carColor the carColor to set
	 */
	void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	/**
	 * @return the carMachine
	 */
	String getCarMachine() {
		return carMachine;
	}

	/**
	 * @param carMachine the carMachine to set
	 */
	void setCarMachine(String carMachine) {
		this.carMachine = carMachine;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

}
