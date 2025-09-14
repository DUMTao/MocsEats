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
				System.out.print(userCmds[0] + ":");
				addItem(userCmds, products);
			}
			
			if (userCmds[0].equals("FINDITEM")) {
				System.out.print(userCmds[0] + ":");
				findItem(userCmds, products);
			}
			
			if (userCmds[0].equals("RESTOCK")) {
				System.out.print(userCmds[0] + ":");
				restock(userCmds, products);
			}
			
			if (userCmds[0].equals("CUSTOMER")) {
				System.out.print(userCmds[0] + ":");
				customer(userCmds, products, sales);
			}
			
			if (userCmds[0].equals("INVENTORY")) {
				System.out.print(userCmds[0] + ":");
				inventory(userCmds, products);
			}
			
			if (userCmds[0].equals("PRINTSUMMARY")) {
				System.out.print(userCmds[0] + ":");
				printSummary(userCmds, products);
			}
			
			else {
				if (userCmds[0].equals("QUIT")) {
					System.out.print(userCmds[0]);
					break;
				}
			}
		
		
		
		}
	}

	private static void addItem(String[] userCmd, MocMartProduct[] products) {
		int itemNum = Integer.parseInt(userCmd[1]);
		String itemName = userCmd[2];
		double itemPrice = Double.parseDouble(userCmd[3]);
		int itemStk = Integer.parseInt(userCmd[4]);
		int resQty = Integer.parseInt(userCmd[5]);
		
		int size = 0;
		int insertionIndex = 0;
		
		//Insert where this new product at the correct spot
		MocMartProduct newPrd = new MocMartProduct(itemNum, itemName, itemPrice, itemStk, resQty);
		
		//Loop to find out where it goes
		for (int i = 0; i < products.length; i++) {
			if (products[i] == null || products[i].getItemNum() > newPrd.getItemNum()) {
				insertionIndex = i;
				break;
			}
		}
		
		//Shifting loop
		for (int j = size; j >= insertionIndex; j--) {
			products[j + 1] = products[j];
		}
		
		size++;
		products[insertionIndex] = newPrd;
		
		System.out.printf("\tItem %d, %s, with a cost of $%.2f and initial stock of %d, has been added to the product database.",
				products[insertionIndex].getItemNum(), products[insertionIndex].getItemName(), products[insertionIndex].getItemPrice(), products[insertionIndex].getQuantity());
		
	}
	
	private static void findItem(String[] userCmd, MocMartProduct[] products) {
		//This command will be followed by an ID
		int itemID = Integer.parseInt(userCmd[1]); //Convert the ID string into int
		String itemName = userCmd[3];
		
		int low = 0;
		int mid;
		int high = products.length - 1;
		
		if (products == null || products.length == 0) {
			System.out.println("\tCannot perform command; there are no items in the product database.");
			return;
		}
		
		
		//Start the Binary Search
		while (low <= high) {
			mid = low + (high - low) / 2;
			
			String binSer = "\tPerforming Binary Search...(Indices searched: " + mid + " )";
			System.out.println(binSer);
			
			if (products[mid].getItemNum() == itemID) {
				System.out.printf("\tItem #%d (%s)\n", products[mid].getItemNum(), products[mid].getItemName());
				System.out.printf("\tPrice            :  $%.2f%n", products[mid].getItemPrice());
				System.out.printf("\tCurrent Quantity :  %d%n", products[mid].getQuantity());
				//System.out.printf("\tUnits Sold       :  %d%n", sales.getUnitsSold());
				//System.out.printf("\tTotal Amount     :  $%.2f%n", products[mid].getItemPrice());
			
				return; //Get out of the method after the item was found
			}
			else if (products[mid].getItemNum() < itemID) {
				low = mid + 1;
			}
			else {
				high = mid - 1;
			}
			
		
		}
		
		//If this gets printed, the item wasn't found
		System.out.println("This item was not found as a product");
	
	}
	
	private static void restock(String[] userCmd, MocMartProduct[] products) {
		//Loop through the array and find which stock is 0, then reset if needed using restockQuantity
		for (int i = 0; i < products.length; i++) {
			if (products[i].getQuantity() == 0) {
				products[i].setQuantity(products[i].getRestockQuantity());
			
				System.out.printf("\tItem %d, %s, restocked to a quantity of %d",
						products[i].getItemNum(), products[i].getItemName(), products[i].getRestockQuantity());
			}
		}
	}
	
	private static void customer(String[] userCmd, MocMartProduct[] products, MocMartSale[] sales) {
		String firstName = userCmd[1];
		String lastName = userCmd[2];
		int prdNum = Integer.parseInt(userCmd[3]) / 2;
		
		//Iterate through the command line for both the product id and the quantity
		for (int i = 0; i < prdNum; i++) {
			int index = 4 + i * 2;
			int qty = index + 1;
			
			int itemNum = Integer.parseInt(userCmd[index]);
			int userQty = Integer.parseInt(userCmd[qty]);
			
			//Check if the product has stock and if it exists
			for (int j = 0; j < products.length; j++) {
				if (products[j] != null && products[j].getItemNum() == itemNum) {
					//Handle the quantity from the product
					if (products[j].getQuantity() >= userQty) {
						//Save it somewhere
					}
				}
			}
		}
		
		
	}
	
	private static void inventory(String[] userCmd, MocMartProduct[] products) {
		//Print out the remaining products from the products array
		//Check if the list is empty or not
		boolean isEmpty = true;
		
		for (int i = 0; i < products.length; i++) {
			if (products[i] != null) {
				isEmpty = false;
				System.out.printf("| Item #%7s | %-20s | $%6.2f | %4d unit(s) |\n", 
						products[i].getItemNum(), products[i].getItemName(), products[i].getItemPrice(), products[i].getQuantity());
				
			}
		}
		
		if (isEmpty) {
			System.out.println("Contains no items.");
		}
		
		//Don't forget the format in the PDF
		
		
	}
	
	private static void printSummary(String[] userCmd, MocMartProduct[] products, MocMartSale[] sales) {
		boolean isEmpty = true;
		
		for (int i = 0; i < sales.length; i++) {
			
		}
		
		//Don't forget the format in the PDF as well
		
		
	}
}
