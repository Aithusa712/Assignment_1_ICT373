
public class Supplement {
	// Private Variables
	private String name;
	private double weeklyCost;

	// Default Constructor
	public Supplement() {
		name = "N/A";
		weeklyCost = 0;
	}

	// Constructor
	public Supplement(String name, double weeklyCost) {
		this.name = name;
		this.weeklyCost = weeklyCost;
	}

	// Setter Methods
	public void setName(String name) { // Set name for this supplement
		this.name = name;
	}

	public void setWeeklyCost(double weeklyCost) { // Set Weekly Cost for this supplement
		this.weeklyCost = weeklyCost;
	}

	// Getter Methods

	public String getName() { // Return Supplement's Name
		return name;
	}

	public double getWeeklyCost() { // Return Supplement's Weekly Cost
		return weeklyCost;
	}

	public void unitTest() { // Unit Testing
		System.out.println("Name: " + name);
		System.out.println("weekly Cost: " + weeklyCost);
	}

}
