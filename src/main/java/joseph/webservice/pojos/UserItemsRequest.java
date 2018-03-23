package joseph.webservice.pojos;

import java.util.List;

public class UserItemsRequest {
	
	String user;
	List<ItemInfo> items;

	public UserItemsRequest() {
		
	}

	public UserItemsRequest(String user, List<ItemInfo> items) {
		super();
		this.user = user;
		this.items = items;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<ItemInfo> getItems() {
		return items;
	}

	public void setItems(List<ItemInfo> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "AddToCartRequest [user=" + user + ", items=" + items + "]";
	}
	
	
}
