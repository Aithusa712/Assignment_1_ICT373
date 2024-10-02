import java.util.ArrayList;

public class Customer {
	// Declare variables
	private String name;
	private String email;
	private int weekStarted;
	private ArrayList<String> subscription = new ArrayList<String>();
	public String CustomerType;

	// Constructor
	public Customer(String name, String email, int weekStarted) {
		this.name = name;
		this.CustomerType = "N/A";
		this.email = email;
		this.weekStarted = weekStarted;
	}

	// Setter Methods

	public void setName(String name) { // Set the Customer's Name
		this.name = name;
	}

	public void setEmail(String email) { // Set the Customer's Email
		this.email = email;
	}

	public void setSubscription(String supplement) { // Set the customer's supplement
		subscription.add(supplement);
	}

	// Getter Methods
	//
	public String getName() { // Return the Customer's Name
		return name;
	}

	public String getEmail() { // Return the Customer's Email
		return email;
	}

	public double getMagazineCosts(ArrayList<Magazine> magazines) { // Return the subscription cost of the customer.
									// Note. Magazine cost is tied to how many weeks
									// the user has subscribed to the service.
		double magazineCost = 0;
		for (int i = weekStarted; i < magazines.size(); i++) {
			magazineCost += magazines.get(i).getMagazineCost();
		}

		return magazineCost;
	}

	public double getSupplementCosts(ArrayList<Magazine> magazines) { // Return the subscription cost of all the
										// supplements the customer has
										// subscribed to.
		double supplementCost = 0;
		for (int i = weekStarted; i < magazines.size(); i++) {
			for (int j = 0; j < subscription.size(); j++) {
				if (magazines.get(i).checkSupplement(subscription.get(j))) {
					supplementCost += magazines.get(i).getSupplementCost(subscription.get(j));
				}
			}
		}

		return supplementCost;
	}

	public double getTotalCosts(ArrayList<Magazine> magazines) { // Return the total subscription cost for both
									// magazine service and it's supplements.
		double totalCost = 0;
		for (int i = weekStarted; i < magazines.size(); i++) {
			totalCost += magazines.get(i).getMagazineCost();
		}

		for (int i = weekStarted; i < magazines.size(); i++) {
			for (int j = 0; j < subscription.size(); j++) {
				if (magazines.get(i).checkSupplement(subscription.get(j))) {
					totalCost += magazines.get(i).getSupplementCost(subscription.get(j));
				}
			}
		}

		return totalCost;
	}

	public int getWeekStarted() { // Return the week the Customer started subscibing to the service.
		return weekStarted;
	}

	// Unit Testing

	public void unitTest(ArrayList<Magazine> magazines) {
		System.out.println("Name: " + name);
		System.out.println("Email: " + email);
		System.out.println("Week Started: " + weekStarted);
		System.out.println("Subscribed Supplements: ");
		for (int i = 0; i < subscription.size(); i++) {
			for (int j = 0; j < magazines.size(); j++) {
				if (subscription.get(i).equalsIgnoreCase(
						magazines.get(j).getSupplement(subscription.get(i)))) {
					System.out.println(magazines.get(j).getSupplement(subscription.get(i)));
				}
			}

		}

	}
}
