package joseph.webservice.pojos;

/**
 * @param item		ItemInfo
 * @param quantity	int
 */
public class Character {
	
	String characterName;
	
	public Character() {
		characterName = "Lol";
	}
	
	public Character(String characterName) {
		this.characterName = characterName;
	}
	
	@Override
	public String toString() {
		return "Character [characterName=" + characterName + "]";
	}

	public String getName() {
		return characterName;
	}
	
	public void getName(String characterName) {
		this.characterName = characterName;
	}
}
