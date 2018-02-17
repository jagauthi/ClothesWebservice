package joseph.webservice.pojos;

public class LoginPacket {
	
	String value1, value2;
	
	public LoginPacket() {
		this.value1 = "test";
		this.value2 = "test";
	}
	
	public LoginPacket(String value1, String value2) {
		this.value1 = value1;
		this.value2 = value2;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}
	
	public LoginPacket swap() {
		String temp = value1;
		value1 = value2;
		value2 = temp;
		return this;
	}

	@Override
	public String toString() {
		return "DataPacket [value1: " + value1 + ", value2:" + value2 + "]";
	}

}
