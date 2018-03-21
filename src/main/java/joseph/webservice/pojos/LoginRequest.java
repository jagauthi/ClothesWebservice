package joseph.webservice.pojos;

public class LoginRequest {
	
	int id;
	String user, pass;

	@Override
	public String toString() {
		return "DataPacket [id: " + id + ", user: " + user + ", pass:" + pass + "]";
	}
	
	public LoginRequest(int id, String user, String pass) {
		this.id = id;
		this.user = user;
		this.pass = pass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public LoginRequest swap() {
		String temp = user;
		user = pass;
		pass = temp;
		return this;
	}
}
