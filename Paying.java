import java.util.ArrayList;

public class Paying extends Customer {
	// Declare Variables
	private ArrayList<String> AssociateList = new ArrayList<String>();
	private String paymentType;

	// Constructor
	public Paying(String name, String email, int weekStarted, String paymentType) {
		super(name, email, weekStarted);
		super.CustomerType = "Paying";
		this.paymentType = paymentType;
	}

	// Setter Methods
	public void setPaymentType(int choice) { // Set Payment type
		String[] paymentTypeArray = { "Credit Card", "Debit Card" };

		paymentType = paymentTypeArray[choice];
	}

	public void setAssociate(String name) { // Set/Add an associate to the Paying Customer's Associate ArrayList
		AssociateList.add(name);
	}

	public ArrayList<String> getAssociateList() { // Return the AssociateList ArrayList. Used in Option 6. Print
							// Invoice
		return AssociateList;
	}

	public String getPaymentType() { // Return Payment Type
		return paymentType;
	}

	public void removeAssociate(String associate) { // Remove Associate from Associate List
		for (int i = 0; i < AssociateList.size(); i++) {
			if (AssociateList.get(i).equalsIgnoreCase(associate)) {
				AssociateList.remove(i);
			}
		}
	}
}
