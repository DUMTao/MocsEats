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
		
		
		products = new MocMartProduct[maxProducts];
		sales = new MocMartSale[maxSales];
		
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
		
		System.out.printf("    Item %d, %s, with a cost of $%.2f and initial stock of %d, has been added to the product database.\n",
				products[insertionIndex].getItemNum(), products[insertionIndex].getItemName(), products[insertionIndex].getItemPrice(), products[insertionIndex].getQuantity());
		
	}
	
	
	private static void findItem(String[] userCmd, MocMartProduct[] products) {
		if (products == null) {
	        System.out.println("    Cannot perform command; there are no items in the product database.");
	        return;
	    }
		
		//This command will be followed by an ID
		int itemID = Integer.parseInt(userCmd[1]); //Convert the ID string into int
		int units = 0;
		double total = 0.0;
		
		int low = 0;
		int mid;
		int high = MocMartProduct.getNumProducts() - 1;
		String binSer = "    Performing Binary Search...(Indices viewed: ";
		
		
		
		//Start the Binary Search
		while (low <= high) {
			mid = low + (high - low) / 2;
			binSer += mid + " ";
			
			if (products[mid] != null && products[mid].getItemNum() == itemID) {
				System.out.println(binSer.trim() + " )");
				System.out.printf("    Item #%d (%s)\n", products[mid].getItemNum(), products[mid].getItemName());
				System.out.printf("    Price            :  $%.2f%n", products[mid].getItemPrice());
				System.out.printf("    Current Quantity :  %d%n", products[mid].getQuantity());
				
				if (sales != null) {
					for (MocMartSale sale : sales) {
						if (sale != null) {	
							int[] items = sale.getItemsPurchased();
							for (int i = 0; i < items.length - 1; i += 2) {
								int itemNum = items[i];
								int qty = items[i + 1];
								
								if (itemNum == itemID) {
									units += qty;
									total += qty * products[mid].getItemPrice();
								}
							}
						}
					}
				}
			
				System.out.printf("    Units Sold       :  %d%n", units);
				System.out.printf("    Total Amount     :  $%.2f%n", total);
				return;
				
			}
			else if (products[mid].getItemNum() < itemID) {
				low = mid + 1;
			}
			else {
				high = mid - 1;
			}
			
		
		}
		
		//If this gets printed, the item wasn't found
		System.out.printf("    Item #%d was not found in the product database.%n", itemID);
	
	}
	
	//Works
	private static void restock(String[] userCmd, MocMartProduct[] products) {
		boolean isEmpty = true;
		
		//Loop through the array and find which stock is 0, then reset if needed using restockQuantity
		for (int i = 0; i < products.length; i++) {
			if (products[i] != null && products[i].getQuantity() == 0) {
				isEmpty = false;
				products[i].setQuantity(products[i].getRestockQuantity());
			
				System.out.printf("    Item %d, %s, restocked to a quantity of %d.\n",
						products[i].getItemNum(), 
						products[i].getItemName(), 
						products[i].getRestockQuantity());
			}
		}
		
		if (isEmpty) {
			System.out.println("    There are no items to restock.");
		}
	}
	
	//Works (maybe)
	private static void customer(String[] userCmd, MocMartProduct[] products, MocMartSale[] sales) {
		String firstName = userCmd[1];
		String lastName = userCmd[2];
		
		int prdNum = (userCmd.length - 4) / 2;
		int[] itemsPurchased = new int[prdNum * 2];
		boolean purchase = false;
		
		if (userCmd.length != 4 + prdNum * 2) {
			return;
		}
		
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
					int allowedQty = Math.min(product.getQuantity(), userQty);
					
					//Handle the quantity from the product
					if (allowedQty > 0) {
						itemsPurchased[i * 2 + 1] = allowedQty;
						product.setQuantity(product.getQuantity() - allowedQty);
						purchase = true;
						
					}
					break;
				}
			}
			
		}
		
		if (purchase) {
			System.out.printf("    Customer %s %s came and made some purchases.\n", 
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
			System.out.printf("    Customer %s %s came and made no purchases.\n", 
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
				if (isEmpty) {
					System.out.println("    Contains the following items:");
					isEmpty = false;
				}
				
				System.out.printf("    | Item%7d | %-20s | $ %6.2f |    %4d unit(s) |\n", 
						products[i].getItemNum(), 
						products[i].getItemName(), 
						products[i].getItemPrice(), 
						products[i].getQuantity());
				
			}
		}
		
		if (isEmpty) {
			System.out.println("    Contains no items.");
		}
		
		
	}
	
	//WORKS
	private static void printSummary() {
		double grandTotal = 0.0;
		boolean validSale = false;
		
		System.out.println("Moc Mart Sales Report:");
		
		for (int i = 0; i < sales.length; i++) {
			MocMartSale sale = sales[i];
			if (sale == null) {
				continue;
			}
			
			validSale = true;
			//Run if the sale isn't null
			String firstName = sale.getFirstName();
			String lastName = sale.getLastName();
			int[] userItems = sale.getItemsPurchased();
				
			int totalItems = 0;
			double saleTotal = 0.0;
			
			
			for (int n = 1; n < userItems.length; n += 2) {
				totalItems += userItems[n];
			}
				
			System.out.printf("    Sale #%d, %s %s purchased %d item(s):\n", 
					i + 1,
					firstName.toUpperCase(), 
					lastName.toUpperCase(), 
					totalItems);
				
				//Loop to print the lines now
			for (int k = 0; k < userItems.length - 1; k += 2) {
				int itemNum = userItems[k];
				int qty = userItems[k + 1];
					
				//Run if the qty is greater than 0
				if (qty > 0) {
					//Search the product by ID
					for (MocMartProduct product : products) {
						if (product != null && product.getItemNum() == itemNum) {
							double price = product.getItemPrice();
							String name = product.getItemName();
							saleTotal += price * qty;
								
							System.out.printf("        | Item  %6d | %-20s | $ %6.2f |  (x%4d) |\n", 
									itemNum, 
									name, 
									price, 
									qty);
							break; //End if there aren't any more sales recorded
						}
					}
				}	
			}
				
			System.out.printf("        Total: $%.2f\n", saleTotal);
			grandTotal += saleTotal;
			
		}
		
		if (validSale) {
			System.out.printf("    Grand Total: $%.2f\n", grandTotal);
		}
		else {
			System.out.println("    There are no sales to print.");
		}
	}
}
