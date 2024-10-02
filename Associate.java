import java.util.ArrayList;

public class Associate extends Customer {
	// Declare private variables
	private String associateName; // Store the associated paying Customer's name

	// Constructor
	public Associate(String name, String email, int weekStarted, String associateName,
			ArrayList<Paying> payingCustomer) {
		super(name, email, weekStarted);
		super.CustomerType = "Associate";
		this.associateName = associateName;
		addAssociate(payingCustomer);

	}

	// Method to add their name into their associated paying Customer's
	// AssociateList
	public void addAssociate(ArrayList<Paying> payingCustomer) {
		// For loop to go through all paying customer until the associate's paying
		// customer name matches.
		for (int i = 0; i < payingCustomer.size(); i++) {
			if (payingCustomer.get(i).getName().equalsIgnoreCase(associateName)) {
				payingCustomer.get(i).setAssociate(this.getName()); // insert associate's name into the
											// paying Customer's Associate
											// List
			}
		}
	}

	// Method to return associated customer's name
	public String getAssociate() {
		return associateName;
	}

}
