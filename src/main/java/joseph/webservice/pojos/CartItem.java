package joseph.webservice.pojos;

/**
 * @param item		ItemInfo
 * @param quantity	int
 */
public class CartItem {
	
	ItemInfo item;
	int quantity;		
	
	public CartItem() {
		super();
	}
	
	public CartItem(ItemInfo item, int quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "CartItem [item=" + item + ", quantity=" + quantity + "]";
	}

	public ItemInfo getItem() {
		return item;
	}
	public void setItem(ItemInfo item) {
		this.item = item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
