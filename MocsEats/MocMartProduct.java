
public class MocMartProduct {
	private int itemNum;
	private String itemName;
	private double itemPrice;
	private int quantity;
	private int restockQuantity;
	
	private static int numProducts;
	
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setRestockQuantity(int restockQuantity) {
		this.restockQuantity = restockQuantity;
	}
	
	public static void setNumProducts(int numProducts) {
		MocMartProduct.numProducts = numProducts;
	}
	
	
	//Return statements first
	public int getItemNum() {
		return itemNum;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public double getItemPrice() {
		return itemPrice;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getRestockQuantity() {
		return restockQuantity;
	}
	
	public static int getNumProducts() {
		return numProducts;
	}


}
