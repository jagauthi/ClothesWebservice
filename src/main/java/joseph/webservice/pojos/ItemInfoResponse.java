package joseph.webservice.pojos;

public class ItemInfoResponse {
	
	int itemNumber;
	float cost, price;
	String description, category;

	public ItemInfoResponse(int itemNumber, float cost, float price, String description, String category) {
		super();
		this.itemNumber = itemNumber;
		this.cost = cost;
		this.price = price;
		this.description = description;
		this.category = category;
	}

	@Override
	public String toString() {
		return "ItemInfoResponse [itemNumber=" + itemNumber + ", cost=" + cost + ", price=" + price + ", description="
				+ description + ", category=" + category + "]";
	}
	
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
