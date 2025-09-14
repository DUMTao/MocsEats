import java.util.*;

public class MocMart {
	private static MocMartProduct[] products;
	private static MocMartSale[] sales;

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
				System.out.print(userCmds[0] + ":\n");
				addItem(userCmds, products);
			}
			
			if (userCmds[0].equals("FINDITEM")) {
				System.out.print(userCmds[0] + ":\n");
				findItem(userCmds, products);
			}
			
			if (userCmds[0].equals("RESTOCK")) {
				System.out.print(userCmds[0] + ":\n");
				restock(userCmds, products);
			}
			
			if (userCmds[0].equals("CUSTOMER")) {
				System.out.print(userCmds[0] + ":\n");
				customer(userCmds, products, sales);
			}
			
			if (userCmds[0].equals("INVENTORY")) {
				System.out.print(userCmds[0] + ":\n");
				inventory(userCmds, products);
			}
			
			if (userCmds[0].equals("PRINTSALESSUMMARY")) {
				System.out.print(userCmds[0] + ":\n");
				printSummary();
			}
			
			else {
				if (userCmds[0].equals("QUIT")) {
					System.out.print("Goodbye.");
					break;
				}
			}
		
		
		
		}
	}

	//Works
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
		
		System.out.printf("\tItem %d, %s, with a cost of $%.2f and initial stock of %d, has been added to the product database.\n",
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
	
	//Works
	private static void restock(String[] userCmd, MocMartProduct[] products) {
		boolean isEmpty = true;
		
		//Loop through the array and find which stock is 0, then reset if needed using restockQuantity
		for (int i = 0; i < products.length; i++) {
			if (products[i] != null && products[i].getQuantity() == 0) {
				isEmpty = false;
				products[i].setQuantity(products[i].getRestockQuantity());
			
				System.out.printf("\tItem %d, %s, restocked to a quantity of %d\n",
						products[i].getItemNum(), 
						products[i].getItemName(), 
						products[i].getRestockQuantity());
			}
		}
		
		if (isEmpty) {
			System.out.println("\tThere are no items to restock.");
		}
	}
	
	//Works (maybe)
	private static void customer(String[] userCmd, MocMartProduct[] products, MocMartSale[] sales) {
		String firstName = userCmd[1];
		String lastName = userCmd[2];
		int prdNum = Integer.parseInt(userCmd[3]) / 2;
		int[] itemsPurchased = new int[prdNum * 2];
		boolean purchase = false;
		
		//Check if customer name is more than 20
		if (firstName.length() > 20 || lastName.length() > 20) {
			System.out.print("Customer First/Last name is more than 20 characters.");
			return;
		}
		
		//Iterate through the command line for both the product id and the quantity
		for (int i = 0; i < prdNum; i++) {
			int index = 4 + i * 2;
			int qty = index + 1;
			
			int itemNum = Integer.parseInt(userCmd[index]);
			int userQty = Integer.parseInt(userCmd[qty]);
			itemsPurchased[i * 2] = itemNum;
			itemsPurchased[i * 2 + 1] = 0;
			
			//Check if the product has stock and if it exists
			for (MocMartProduct product : products) {
				if (product != null && product.getItemNum() == itemNum) {
					//Handle the quantity from the product
					if (product.getQuantity() >= userQty) {
						itemsPurchased[i * 2 + 1] = userQty;
						product.setQuantity(product.getQuantity() - userQty);
						purchase = true;
					}
					break; //This is to stop searching after product is found
				}
			}
		}
		
		if (purchase) {
			System.out.printf("\tCustomer %s %s came and made some purchases.\n", 
					firstName, lastName);
			
			//Create the object and save it as a sale in the array
			MocMartSale sale = new MocMartSale(firstName, lastName, itemsPurchased);
			for (int k = 0; k < sales.length; k++) {
				if (sales[k] == null) {
					sales[k] = sale;
					break;
				}
			}
		}
		else {
			System.out.printf("\tCustomer %s %s came and made no purchases.\n", 
					firstName, lastName);
		}
		
	}
	
	//Works
	private static void inventory(String[] userCmd, MocMartProduct[] products) {
		//Print out the remaining products from the products array
		//Check if the list is empty or not
		boolean isEmpty = true;
		
		
		for (int i = 0; i < products.length; i++) {
			if (products[i] != null) {
				isEmpty = false;
				System.out.println("\tContains the following item(s):");
				System.out.printf("\t| Item #%7d | %-20s | $%6.2f | %4d unit(s) |\n", 
						products[i].getItemNum(), 
						products[i].getItemName(), 
						products[i].getItemPrice(), 
						products[i].getQuantity());
				
			}
		}
		
		if (isEmpty) {
			System.out.println("\tContains no items.");
		}
		
		//Don't forget the format in the PDF
		
		
	}
	
	//WORKS
	private static void printSummary() {
		boolean isEmpty = true;
		double grandTotal = 0.0;
		
		//Exit before running the loop so I don't get a null exception error ;-;
		if (sales == null) {
			System.out.println("\tThere are no sales to print.");
			return;
		}
		
		System.out.println("Moc Mart Sales Report:");
		for (int i = 0; i < sales.length; i++) {
			//Run if the sale isn't null
			if (sales[i] != null) {
				MocMartSale sale = sales[i];
				String firstName = sale.getFirstName();
				String lastName = sale.getLastName();
			
				int[] userItems = sale.getItemsPurchased();
				int totalItems = 0;
				double saleTotal = 0.0;
				
				for (int n = 1; n < userItems.length; n += 2) {
					totalItems += userItems[n];
				}
				
				System.out.printf("\tSale #%d, %s %s purchased %d items(s):\n", i + 1,
						firstName.toUpperCase(), 
						lastName.toUpperCase(), 
						totalItems);
				
				//Loop to print the lines now
				for (int k = 0; k < userItems.length; k += 2) {
					int itemNum = userItems[k];
					int qty = userItems[k + 1];
					
					//Run if the qty is greater than 0
					if (qty > 0) {
						//Search the product by ID
						for (int l = 0; l < products.length; l++) {
							if (products[l] != null && products[l].getItemNum() == itemNum) {
								MocMartProduct product = products[l];
								double price = product.getItemPrice();
								String name = product.getItemName();
								saleTotal += price * qty;
								
								System.out.printf("\t\t| Item #%6d | %-20s | $%6.2f | (x%4d) |\n", 
										itemNum, 
										name, 
										price, 
										qty);
								break; //End if there aren't any more sales recorded
							}
						}
					}	
				}
				
				System.out.printf("\t\tTotal: $%.2f\n", 
						saleTotal);
				grandTotal += saleTotal;
				isEmpty = false;
			
			}
		}
		
		if (!isEmpty) {
			System.out.printf("\tGrand Total: $%.2f\n", 
					grandTotal);
		}
	}
}
