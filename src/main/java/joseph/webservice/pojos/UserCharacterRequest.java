package joseph.webservice.pojos;

import java.util.List;

/**
 * @param user		String
 * @param items	List of ItemInfo
 */
public class UserCharacterRequest {
	
	String user;
	Character character;

	public UserCharacterRequest() {
		
	}

	public UserCharacterRequest(String user, Character character) {
		this.user = user;
		this.character = character;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	@Override
	public String toString() {
		return "UserCharacterRequest [user=" + user + ", character=" + character + "]";
	}
	
	
}
