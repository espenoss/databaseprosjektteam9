package controller;

import databasePackage.Database;

public class Driver extends User {
	
	public Driver(String userID, String name, Database database) {
		super(userID, 2, name, database);
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
