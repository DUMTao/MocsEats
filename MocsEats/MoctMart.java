import java.util.*;

public class MoctMart {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//Program starts with user input max product and sales
		int maxProducts = scanner.nextInt();
		int maxSales = scanner.nextInt();
		String[] userCmds;
		
		
		MocMartProduct[] products = new MocMartProduct[maxProducts];
		MocMartSale[] sales = new MocMartSale[maxSales];
		
		//Create a loop to read the input, check the command
		//Break con is the word 'quit'
		while (true) {
			userCmds = scanner.nextLine().split(" ");
			
			//Check if the first word equals any of the methods
			if (userCmds[0].equals("ADDITEM")) {
				addItem(userCmds, products);
			}
			if (userCmds[0].equals("FINDITEM")) {
				findItem(userCmds, products);
			}
			if (userCmds[0].equals("RESTOCK")) {
				restock(userCmds, products);
			}
			if (userCmds[0].equals("CUSTOMER")) {
				customer(userCmds, products, sales);
			}
			if (userCmds[0].equals("INVENTORY")) {
				inventory(userCmds, products);
			}
			if (userCmds[0].equals("PRINTSUMMARY")) {
				printSummary(userCmds, products);
			}
			else {
				if (userCmds[0].equals("QUIT")) {
					break;
				}
			}
		
		
		
		}
	}
	
	/* Required Methods (Implementation Later)
	 * 1. AddItem
	 * 2. FindItem
	 * 3. Restock
	 * 4. Customer
	 * 5. Inventory
	 * 6. PrintSummary
	 */

	private static void addItem(String[] userCmd, MocMartProduct[] products) {
		MocMartProduct newPrd = new MocMartProduct(userCmd);
		
		//Insert where this new product at the correct spot
		
	
	}
	
	private static void findItem(String[] userCmd, MocMartProduct[] products) {
		//
	}
	
	private static void restock(String[] userCmd, MocMartProduct[] products) {
		//
	}
	
	private static void customer(String[] userCmd, MocMartProduct[] products, MocMartSale[] sales) {
		//Using the fl names from the sale file,
		//Conditional of <= 20 string length for each
		//Algorithm of 2n as the number of products they want to purchase
		//ID of the product they want
		//Next n ints for the quantity of products
		//If sold, create a new object for sales
	}
	
	private static void inventory(String[] userCmd, MocMartProduct[] products) {
		//Print out the remaining products from the products array
		//Don't forget the format in the PDF
	}
	
	private static void printSummary(String[] userCmd, MocMartProduct[] products) {
		//Print out all the sales from the sales array
		//Don't forget the format in the PDF aswell
	}
}
