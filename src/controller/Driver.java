package controller;

public class Driver extends User {
	
	public Driver(String userID,int userType, String name,String pword) {
		super(userID, userType, name, pword);
	}
	
	public boolean markDelivered(int orderIndex){
		
		boolean success = false;
		
		// registrer levering i database
		
		return success;
	}
	
	public String generateDeliveryPlan(){
		String plan = null;
		
		// Hent ordre og generer plan
		
		return plan;
	}
	
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Driver";
		
		return s;
	}	
	
}
