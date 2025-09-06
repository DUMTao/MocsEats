import java.util.*;

public class MoctMart {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//Program starts with user input max product and sales
		int maxProducts = scanner.nextInt();
		int maxSales = scanner.nextInt();
		
		
		MocMartProduct[] products = new MocMartProduct[maxProducts];
		MocMartSale[] sales = new MocMartSale[maxSales];
		
		
	}
	
	/* Required Methods (Implementation Later)
	 * 1. AddItem
	 * 2. FindItem
	 * 3. Restock
	 * 4. Customer
	 * 5. Inventory
	 * 6. PrintSummary
	 */

	public void addItem() {
		//
	}
	
	public void findItem() {
		//
	}
	
	public void restock() {
		//
	}
	
	public void customer() {
		//Using the fl names from the sale file,
		//Conditional of <= 20 string length for each
		//Algorithm of 2n as the number of products they want to purchase
		//ID of the product they want
		//Next n ints for the quantity of products
		//If sold, create a new object for sales
	}
	
	public void inventory() {
		//Print out the remaining products from the products array
		//Don't forget the format in the PDF
	}
	
	public void printSummary() {
		//Print out all the sales from the sales array
		//Don't forget the format in the PDF aswell
	}
}
