
public class MocMartProduct {
	private int itemNum;
	private String itemName;
	private double itemPrice;
	private int quantity;
	private int restockQuantity;
	
	private static int numProducts;
	
	public MocMartProduct(String[] userCmd) {
		// "ADDITEM", "4011", "USA_FLAG", "10.99", "1", "7"
		this.itemNum = Integer.parseInt(userCmd[1]);
		this.itemName = userCmd[2];
		this.itemPrice = Double.parseDouble(userCmd[3]);
		this.quantity = Integer.parseInt(userCmd[4]);
		this.restockQuantity = Integer.parseInt(userCmd[5]);
	}
	
	public MocMartProduct(int itemNum, String itemName, double itemPrice, int quantity, int restockQuantity) {
		this.itemNum = itemNum;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.quantity = quantity;
		this.restockQuantity = restockQuantity;
	}

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
