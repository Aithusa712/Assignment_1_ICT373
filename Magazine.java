import java.util.ArrayList;

public class Magazine {
	// Declare Variables
	private double magazineWeeklyCost;
	private int week;
	private String name;
	private ArrayList<Supplement> supplements = new ArrayList<Supplement>();

	// Constructor
	public Magazine(double magazineWeeklyCost, int week, String name) {
		this.magazineWeeklyCost = magazineWeeklyCost;
		this.week = week;
		this.name = name;
	}

	// Setter Methods

	public void setSupplement(Supplement newSupplement) { // Set/Add supplements into the class's Supplement Object
								// ArrayList
		supplements.add(newSupplement);
	}

	// Getter Methods
	public double getSupplementCost(String supplementName) { // Get Cost for a supplement that is stored in the
									// Supplement Object ArrayList
		double weeklyCosts = 0;
		for (int i = 0; i < supplements.size(); i++) {
			if (supplements.get(i).getName().equalsIgnoreCase(supplementName)) {
				weeklyCosts += supplements.get(i).getWeeklyCost();
			}
		}

		return weeklyCosts;
	}

	public String getSupplementIndex(int index) { // Accepts a integer parameter and returns a supplement name
							// stored in the ArrayList with the parameter being the index.
		return supplements.get(index).getName();
	}

	public int getSupplementCount() { // Return total count of all Supplements in the supplement ArrayList
		return supplements.size();
	}

	public String getMagazineName() { // Return Magazine Name.
		return name;
	}

	public double getMagazineCost() { // Return the Magazine's weekly cost.
		return magazineWeeklyCost;
	}

	public boolean checkSupplement(String supplementName) { // Check if Supplement Exists inside this class's
								// Supplement ArrayList
		for (int i = 0; i < supplements.size(); i++) {
			if (supplements.get(i).getName().equalsIgnoreCase(supplementName)) {
				return true;
			}
		}

		return false;
	}

	public String getSupplement(String SupplementName) { // Return the name of the supplement in the parameter if it
								// exists in the Supplement ArrayList inside this class.
								// Used for Unit Testing in the Customer class.
		String name = "N/A";
		for (int i = 0; i < supplements.size(); i++) {
			if (supplements.get(i).getName().equalsIgnoreCase(SupplementName)) {
				name = supplements.get(i).getName();
			}
		}

		return name;

	}

	public void getWeeklySupplement() { // Get/Printout/Display the available supplements stored in the ArrayList.
						// This is for Unit Testing.
		for (int i = 0; i < supplements.size(); i++) {
			System.out.println("Available Supplements:");
			System.out.println("=========================");
			System.out.println((i + 1) + ". " + supplements.get(i).getName() + " - "
					+ supplements.get(i).getWeeklyCost() + "$");
		}

	}

}
