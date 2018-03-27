package joseph.webservice.pojos;

import java.util.List;

/**
 * @param username	String
 * @param cart		List of CartItems
 */
public class UserCartInfo {
	
	String username;
	List<CartItem> cart;	
	
	public UserCartInfo() {
		super();
	}
	
	public UserCartInfo(String username, List<CartItem> cart) {
		super();
		this.username = username;
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "UserCartInfo [username=" + username + ", cart=" + cart + "]";
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<CartItem> getCart() {
		return cart;
	}
	public void setCart(List<CartItem> cart) {
		this.cart = cart;
	}

}
