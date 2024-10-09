
/** 
 * Title: Assignment 1
 * Author: Kim Andrew Dela Cruz 
 * Date: 06/10/2024
 * File Names: Client.java, Customer.java, Paying.java, Associate.java, Magazine.java, Supplement.java 
 * Purpose: Design, implement in Java, test and document a set of classes for use in a program to manage an 
online weekly personalized magazine service.
 * Assumptions: 
	- A customer cannot be inserted into previous magazine issues and can only receive issues and supplement on the week of their subscription.can only subscribe to the magazine service, therefore any previous magazine issues and
	  supplements cannot be obtained by a new customer and customers cannot choose magazine issues and supplements from those previous issues. 
 
**/
import java.util.*;

public class Client {

	// Main Program
	public static void main(String[] args) {
		// Declaration and intialiazation of variables and objects.
		Scanner userInput = new Scanner(System.in); // Scanner
		ArrayList<Magazine> magazines = new ArrayList<Magazine>(); // Magazine Object
		ArrayList<Supplement> tempSupplement = new ArrayList<Supplement>(); // Supplement to store temporary
											// supplement values.
		ArrayList<Paying> payingCustomer = new ArrayList<Paying>(); // Paying Customer Object
		ArrayList<Associate> associateCustomer = new ArrayList<Associate>(); // Associate Customer Object
		int currentWeek = 3; // Store the current week. Need to manually change.
		// Calling loadData function to load dummy data
		loadData(magazines, payingCustomer, associateCustomer);
		boolean loop = true; // boolean variable to break the do-while Loop
		displayStudentDetails(userInput);
		clear();
		// Loop to continue displaying Menu.
		do {

			// Print to display menu
			System.out.print("Main Menu" +
					"\n==================" +
					"\n1. Create a new Supplement " +
					"\n2. Create a new Customer " +
					"\n3. Add new supplement to this week's magazine" +
					"\n4. Add new customer to this week's subscription  " +
					"\n5. Print all existing customer's email for all weekly magazines" +
					"\n6. Print end of month invoice for paying customers  " +
					"\n7. Remove existing customer" +
					"\n8. Exit " +
					"\n9. Unit Testing" +
					"\n\n\tEnter an option: ");
			// Retrieve User input
			String choice = userInput.nextLine();

			// Switch Case to select a function/method
			switch (choice) {
				case "1":
					createSupplement(tempSupplement, userInput);
					break;
				case "2":
					createCustomer(payingCustomer, associateCustomer, currentWeek, userInput);

					break;
				case "3":
					addSupplement(tempSupplement, magazines, currentWeek, userInput);

					break;
				case "4":
					addCustomer(payingCustomer, associateCustomer, magazines, currentWeek,
							userInput);

					break;
				case "5":
					printAllEmails(payingCustomer, associateCustomer);

					break;
				case "6":
					printInvoice(payingCustomer, associateCustomer, magazines);

					break;
				case "7":
					removeCustomer(payingCustomer, associateCustomer, userInput);
					break;
				case "8":
					System.out.println("Program Exited");
					loop = false;
					break;

				// Case 9 is for debugging
				case "9":
					unitTesting(payingCustomer, associateCustomer, magazines, tempSupplement);
					break;

			}
			System.out.print("\n\nPress anything to continue....");
			userInput.nextLine();
			clear();

		} while (loop);
		userInput.close();
	}

	// Unit Testing Method - check the content inside the Data Structure.
	public static void unitTesting(ArrayList<Paying> payingCustomer, ArrayList<Associate> associateCustomer,
			ArrayList<Magazine> magazines,
			ArrayList<Supplement> tempSupplement) {
		clear();
		System.out.println("PayingCustomer");
		System.out.println("================");
		for (int i = 0; i < payingCustomer.size(); i++) {
			payingCustomer.get(i).unitTest(magazines);
			System.out.println();
		}

		System.out.println("AssociateCustomer");
		System.out.println("================");
		for (int i = 0; i < associateCustomer.size(); i++) {
			associateCustomer.get(i).unitTest(magazines);
			System.out.println();
		}

		System.out.println("TempSupplement");
		System.out.println("================");
		for (int i = 0; i < tempSupplement.size(); i++) {
			tempSupplement.get(i).unitTest();
			System.out.println();
		}

	}

	// Option 1. Create Supplement - ask user for the supplement name and cost and
	// ArrayList<Supplement> supplements,

	// insert it into a temporary Supplementary ArrayList object.
	public static void createSupplement(ArrayList<Supplement> tempSupplements, Scanner userInput) {
		String name;
		double weeklyCost;
		clear();
		System.out.print("\nEnter Supplement Name: ");
		name = userInput.nextLine();
		System.out.print("\nEnter Weekly Cost: ");
		weeklyCost = userInput.nextDouble();
		tempSupplements.add(new Supplement(name, weeklyCost));
		clear();
		System.out.println("Supplement Created");
		userInput.nextLine();

	}

	// Option 2. Create Customer - ask user for a customer name, email, and customer
	// type. The customer type are separated by using two different ArrayList object
	// classes with the same parent.
	public static void createCustomer(ArrayList<Paying> payingCustomer, ArrayList<Associate> associateCustomer,
			int currentWeek, Scanner userInput) {
		String name;
		String email;
		String choice;
		boolean userExist = false;
		clear();
		System.out.print("\nEnter Customer Name: ");
		name = userInput.nextLine();

		System.out.print("\nEnter Email: ");
		email = userInput.nextLine();

		System.out.print("\nType 1 for paying, 2 for associate (1|2): ");
		choice = userInput.nextLine();
		switch (choice) {
			case "1":
				System.out.print(
						"\nEnter payment type, type 1 for Credit Card, 2 for Debit Card (1|2):");
				choice = userInput.nextLine();
				if (choice.equalsIgnoreCase("1")) {
					clear();
					System.out.println("Paying Customer added");
					payingCustomer.add(new Paying(name, email, currentWeek, "Credit Card"));

				} else if (choice.equalsIgnoreCase("2")) {
					clear();
					System.out.println("Paying Customer added");
					payingCustomer.add(new Paying(name, email, currentWeek, "Debit Card"));
				} else {
					clear();
					System.out.println("Invalid Response. Try Again.");
				}
				break;
			case "2":
				System.out.print("Enter paying customer: ");
				String payingCustomerName = userInput.nextLine();
				for (int i = 0; i < payingCustomer.size(); i++) {
					if (payingCustomerName.equalsIgnoreCase(payingCustomer.get(i).getName())) {
						associateCustomer.add(new Associate(name, email, currentWeek,
								payingCustomerName, payingCustomer));
						userExist = true;
						clear();
						System.out.println("Associate Customer Added.");
						break;
					}

				}
				if (!userExist) {
					clear();
					System.out.println("Paying User does not exist. Try Again.");
				}
				break;
		}

	}

	// Option 3. Add supplement - user can choose from a list of user-created
	// supplements and add them to the magazines object.
	public static void addSupplement(ArrayList<Supplement> tempSupplements, ArrayList<Magazine> magazines,
			int currentWeek, Scanner userInput) {
		int choice;
		String tempName = "N/A";
		double tempCost = 0;
		clear();
		if (tempSupplements.size() == 0) {
			System.out.print("There is no available supplements to add");
		} else {
			System.out.println("Available Supplements to add to magazine:");
			System.out.println("==============================================");
			for (int i = 0; i < tempSupplements.size(); i++) {
				System.out.println((i + 1) + ". " + tempSupplements.get(i).getName() + " - "
						+ tempSupplements.get(i).getWeeklyCost());
			}
			System.out.println("Select which supplement to add (Type the number)");
			choice = userInput.nextInt();
			choice--;
			if (choice < 0 || choice >= tempSupplements.size()) {
				System.out.println("Choice out of bounds. Try Again");
			} else {

				tempName = tempSupplements.get(choice).getName();
				tempCost = tempSupplements.get(choice).getWeeklyCost();
				tempSupplements.remove(choice);
				magazines.get(currentWeek).setSupplement(new Supplement(tempName, tempCost));
				clear();
				userInput.nextLine();
				System.out.println("Successfully Added");
			}

		}
	}

	// Option 4. Add Customer - User will input an existing customer name and will
	// be prompted the available supplements for the current week.
	public static void addCustomer(ArrayList<Paying> payingCustomer, ArrayList<Associate> associateCustomer,
			ArrayList<Magazine> magazines, int currentWeek, Scanner userInput) {
		boolean userExist = false;
		boolean paying = false;
		int index = 0;
		clear();
		System.out.print("Enter Customer Name:");
		String name = userInput.nextLine();

		for (int i = 0; i < payingCustomer.size(); i++) {
			if (payingCustomer.get(i).getName().equalsIgnoreCase(name)) {
				userExist = true;
				paying = true;
				index = i;
				break;
			}
		}
		for (int i = 0; i < associateCustomer.size(); i++) {
			if (associateCustomer.get(i).getName().equalsIgnoreCase(name)) {
				userExist = true;
				index = i;
				break;
			}
		}

		if (userExist) {
			magazines.get(currentWeek).getWeeklySupplement();
			System.out.print("Select which supplement to subscribe to(Type the index): ");
			int choice = userInput.nextInt();
			choice--;
			if (choice < 0 || choice >= magazines.get(currentWeek).getSupplementCount()) {
				clear();
				System.out.print("Choice out of bounds. Try Again.");
			} else {
				String supplementName = magazines.get(currentWeek).getSupplementIndex(choice);
				if (paying) {
					payingCustomer.get(index).setSubscription(supplementName);

				} else {
					associateCustomer.get(index).setSubscription(supplementName);
				}
				clear();
				System.out.println("Successfully added customer");
			}
		} else {
			clear();
			System.out.println("User does not Exist. Try Again.");
		}
		userInput.nextLine();

	}

	// Option 5. Print All Emails - program will printout emails for existing paying
	// and associate customers
	public static void printAllEmails(ArrayList<Paying> payingCustomer, ArrayList<Associate> associateCustomer) {
		clear();
		System.out.print("paying customer: ");
		System.out.println("\n======================");
		for (int i = 0; i < payingCustomer.size(); i++) {
			System.out.println(payingCustomer.get(i).getEmail() + "\n");
		}

		System.out.println("associate customer: ");
		System.out.print("\n========================");
		for (int i = 0; i < associateCustomer.size(); i++) {
			System.out.println(associateCustomer.get(i).getEmail() + "\n");
		}

	}

	// Option 6. Print Invoice - program will printout the end of month invoice for
	// all paying customer and their associate
	public static void printInvoice(ArrayList<Paying> payingCustomer, ArrayList<Associate> associateCustomer,
			ArrayList<Magazine> magazines) {
		clear();
		System.out.print("\n\nend of month invoice: ");
		System.out.print("\n======================");
		for (int i = 0; i < payingCustomer.size(); i++) {
			double totalCost = 0;
			ArrayList<String> associateList = payingCustomer.get(i).getAssociateList();
			System.out.println("\nName: " + payingCustomer.get(i).getName());
			System.out.println("Email: " + payingCustomer.get(i).getEmail());
			System.out.println("Subscribed since Week: "
					+ (payingCustomer.get(i).getWeekStarted()));
			System.out.println("Payment Type: " + payingCustomer.get(i).getPaymentType());
			System.out.println("\nPayment Breakdown: ");
			System.out.println("\tBase Magazine subscription - "
					+ payingCustomer.get(i).getMagazineCosts(magazines)
					+ "$");
			totalCost += payingCustomer.get(i).getMagazineCosts(magazines);
			System.out.println("\tSupplement subscription - "
					+ payingCustomer.get(i).getSupplementCosts(magazines)
					+ "$");
			totalCost += payingCustomer.get(i).getSupplementCosts(magazines);
			System.out.println("\tAssociate Cost Breakdown: ");
			for (int j = 0; j < associateList.size(); j++) {
				for (int k = 0; k < associateCustomer.size(); k++) {
					if (associateCustomer.get(k).getName().equalsIgnoreCase(associateList.get(j))) {
						System.out.println("\t\tAssociate's Email Address: "
								+ associateCustomer.get(k).getEmail());
						System.out.println("\t\tAssociate's Total cost : "
								+ associateCustomer.get(k).getTotalCosts(magazines)
								+ "$");
						totalCost += associateCustomer.get(k).getTotalCosts(magazines);

					}

				}
			}

			System.out.println("Total: " + totalCost + "$");
			System.out.print("======================\n");

		}
	}

	// Option 7. Remove Customer - User enter's a customer name and the program will
	// determine whether the customer is an associate or a paying customer and
	// delete them from their respective ArrayList data structure.
	public static void removeCustomer(ArrayList<Paying> payingCustomer, ArrayList<Associate> associateCustomer,
			Scanner userInput) {
		boolean userExist = false;
		boolean paying = false;
		int index = 0;
		clear();
		System.out.println("Enter Customer Name:");
		String name = userInput.nextLine();

		for (int i = 0; i < payingCustomer.size(); i++) {
			if (payingCustomer.get(i).getName().equalsIgnoreCase(name)) {
				userExist = true;
				paying = true;
				index = i;
				break;
			}
		}
		for (int i = 0; i < associateCustomer.size(); i++) {
			if (associateCustomer.get(i).getName().equalsIgnoreCase(name)) {
				userExist = true;
				index = i;
				break;
			}
		}
		if (userExist) {
			if (paying) {
				payingCustomer.remove(index);
				clear();
				System.out.println("Customer Successfully removed");
			} else {
				for (int i = 0; i < payingCustomer.size(); i++) {
					if (associateCustomer.get(index).getAssociate()
							.equalsIgnoreCase(payingCustomer.get(i).getName())) {
						payingCustomer.get(i).removeAssociate(
								associateCustomer.get(index).getAssociate());
					}
				}
				clear();
				System.out.println("Customer Successfully removed");
				associateCustomer.remove(index);

			}
		} else {
			clear();
			System.out.println("Customer not found. Try Again.");
		}

	}

	// Option 9 - load Data - Method to load dummy values for the purpose of
	// testing.
	public static void loadData(ArrayList<Magazine> magazines, ArrayList<Paying> payingCustomer,
			ArrayList<Associate> associateCustomer) {

		// Note Magazine = Weekly Cost | week | Name
		magazines.add(new Magazine(50, 0, "MagWeek01"));
		magazines.add(new Magazine(50, 1, "MagWeek02"));
		magazines.add(new Magazine(50, 2, "MagWeek03"));
		magazines.add(new Magazine(50, 3, "MagWeek04"));

		magazines.get(0).setSupplement(new Supplement("TechTrends Weekly", 4.99));
		magazines.get(1).setSupplement(new Supplement("Fitness Focus", 3.50));
		magazines.get(2).setSupplement(new Supplement("Travel Explorer", 5.25));
		magazines.get(3).setSupplement(new Supplement("Culinary Delights", 3.75));

		// Note Paying = Name | Email | Week Started | Payment Method
		payingCustomer.add(new Paying("Olivia Bennett", "olivia.bennett@example.com", 0, "Debit Card"));
		payingCustomer.add(new Paying("Liam Harris", "liam.harris@example.com", 0, "Credit Card"));
		payingCustomer.add(new Paying("Emma Walker", "emma.walker@example.com", 0, "Credit Card"));

		// Note Associate = Name | Email | Week Started | Associated Customer | Paying
		// ArrayList
		associateCustomer.add(new Associate("Noah Turner", "noah.turner@example.com", 0, "Olivia Bennet",
				payingCustomer));
		associateCustomer.add(new Associate("Sophia Mitchell", "sophia.mitchell@example.com", 0, "Liam Harris",
				payingCustomer));
		associateCustomer.add(new Associate("James Carter", "james.carter@example.com", 0, "Emma Walker",
				payingCustomer));

		payingCustomer.get(0).setSubscription("TechTrends Weekly");
		payingCustomer.get(0).setSubscription("Fitness Focus");
		payingCustomer.get(1).setSubscription("Culinary Delights");
		payingCustomer.get(1).setSubscription("TechTrends Weekly");
		payingCustomer.get(2).setSubscription("Travel Explorer");

		associateCustomer.get(0).setSubscription("TechTrends Weekly");
		associateCustomer.get(1).setSubscription("Fitness Focus");
		associateCustomer.get(1).setSubscription("TechTrends Weekly");
		associateCustomer.get(1).setSubscription("Travel Explorer");
		associateCustomer.get(2).setSubscription("TechTrends Weekly");
		associateCustomer.get(2).setSubscription("Culinary Delights");

	}

	public static void clear() {
		for (int i = 0; i < 10; i++) {
			System.out.print("\033[H\033[2J");
			System.out.flush();
		}
	}

	public static void displayStudentDetails(Scanner userInput) {
		clear();
		System.out.println("Name: Kim Andrew Dela Cruz" +
				"\nStudent no.: 35282436" +
				"\nMode of Enrollment: Full Time" +
				"\nTutor Name: Mr. Loo Poh Kok" +
				"\nTutorial Attendance Date and time: Wednesday | 1416-1745 GMT +08");

		System.out.print("\nPress anything to continue... ");
		userInput.nextLine();
	}

}
