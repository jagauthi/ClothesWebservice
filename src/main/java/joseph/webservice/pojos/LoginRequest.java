package joseph.webservice.pojos;

public class LoginRequest {
	
	int userId;
	String username, password, email;

	@Override
	public String toString() {
		return "DataPacket [userId: " + userId + ", username: " + username + ", password: " + password + ", email: " + email + "]";
	}
	
	public LoginRequest(int userId, String username, String password, String email) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public int setUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
