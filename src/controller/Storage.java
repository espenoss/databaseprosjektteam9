package controller;

import databasePackage.Database;

public class Storage extends User {

	public Storage(String userID,int userType, String name,String pword, Database database) {
		super(userID, userType, name, pword, database);
	}
	
	// Skal vi ha Storage, eller skal alt ligge i Ingredient?
	public boolean registerIngredientDelivery(String orderinfo){
		boolean success = false;
		
		// oppdater database med ny info
		
		return success;
	}
	
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Storage";
		
		return s;
	}

}