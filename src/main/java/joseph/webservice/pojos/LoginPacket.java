package joseph.webservice.pojos;

public class LoginPacket {
	
	String user, pass;
	
	public LoginPacket() {
		this.user = "test";
		this.pass = "test";
	}
	
	public LoginPacket(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public LoginPacket swap() {
		String temp = user;
		user = pass;
		pass = temp;
		return this;
	}

	@Override
	public String toString() {
		return "DataPacket [user: " + user + ", pass:" + pass + "]";
	}

}
