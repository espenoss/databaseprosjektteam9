package userPackage;

public class Cook extends User {

	public Cook(String name, String pword) {
		super(name, pword);
	}

	public String viewFoodOrders(){
		// Skal spørre database
		return "Food orders:\n";
	}
	
	public boolean markAsReady(){
		boolean success = false;
		
		// oppdater ordre i database
		
		return success;
	}
	
	public String viewOrderIngredients(){
		String ingredients = null;
		
		// hent ingredienser fra database
		
		return ingredients;
	}
	
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Cook";
		
		return s;
	}
	
}
