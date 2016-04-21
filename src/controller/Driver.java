package controller;

import java.util.ArrayList;

import databasePackage.Database;
import databasePackage.QMOrder;

public class Driver extends User {
	
	public Driver(String userID, String name, Database database) {
		super(userID, 2, name, database);
	}
	
	public boolean markDelivered(int orderID, int mealID, java.sql.Date date) throws Exception{
		
		boolean success = QMOrder.markMealOrderAsDelivered(orderID, mealID, date.toString(), database);
				
		return success;
	}
	
	public String[][] generateDeliveryPlan() throws Exception{
		String[][] plan = null;
	    java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

	    // Get orders to be delivered today
		plan = QMOrder.generateDeliveryList(sqlDate, database);
	    	    
		return plan;
	}
	
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Driver";
		
		return s;
	}	
	
	
	public static void main(String[] args) throws Exception{
 		String username = "espenme";
 		String passingword = "16Sossosem06";
 		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
 		Database database = new Database("com.mysql.jdbc.Driver", databasename);
		Driver driver = new Driver("","", database);
		String[][] plan = driver.generateDeliveryPlan();
	}
}
