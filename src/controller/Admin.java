package controller;
import databasePackage.*;

import databasePackage.Database;

public class Admin extends User {
	

	public Admin(String userID,int userType, String name,String pword, Database database) {
		super(userID, userType, name, pword, database);
	}

	public boolean registerUser(){
		boolean success = true;
		
		// register ny bruker i databasen
		
		return success;
	}
	
	public String getStatistics(){
		String statistics = null;
		
		// hent statistikk fra databasen
		
		return statistics;
	}
	
	
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Admin";
		
		return s;
	}
	
}
