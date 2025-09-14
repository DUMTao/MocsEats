
public class MocMartSale {
	private String firstName;
	private String lastName;
	private int numItemsOnList;
	private int[] itemsPurchased;
	
	private static int numSales = 0;
	
	public MocMartSale(String[] userCmd) {
		this.firstName = userCmd[1];
		this.lastName = userCmd[2];
		
		numSales++;
	}
	
	public MocMartSale(String firstName, String lastName, int[] itemsPurchased) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.itemsPurchased = itemsPurchased;
		
		numSales++;
	}
	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setNumItemsOnList(int numItemsOnList) {
		this.numItemsOnList = numItemsOnList;
	}
	
	public void setItemsPurchased(int[] itemsPurchased) {
		this.itemsPurchased = itemsPurchased;
	}
	
	public static void setNumSales(int numSales) {
		MocMartSale.numSales = numSales;
	}
	
	//Return them all here
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public int getNumItemsOnList() {
		return numItemsOnList;
	}
	
	public int[] getItemsPurchased() {
		return itemsPurchased;
	}
	
	public static int getNumSales() {
		return numSales;
	}
	
	
}
