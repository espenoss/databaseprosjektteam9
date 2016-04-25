package controller;

import database.*;

/**
 * The Class Driver.
 * Contains all methods exclusive to the driver usertype
 */
public class Driver extends User {

	/**
	 * Instantiates driver user.
	 *
	 * @param userID User id
	 * @param name Name of user
	 * @param database Database connection
	 */
	public Driver(String userID, String name, Database database) {
		super(userID, 2, name, database);
	}

	/**
	 * Mark meal in order as delivered.
	 *
	 * @param orderID Order id
	 * @param mealID Meal id
	 * @param date Delivery date
	 * @return true, if successful
	 */
	public boolean markDelivered(int orderID, int mealID, java.sql.Date date) {

		boolean success = QMOrder.markMealOrderAsDelivered(orderID, mealID, date.toString(), database);

		return success;
	}

	/**
	 * Generate delivery plan.
	 * Fetches list of meal and delivery information that are ready to be delivered for todays date
	 *
	 * @return String[][] of delivery and meal information
	 */
	public String[][] generateDeliveryPlan() {
		// Get todays date
		String[][] plan = null;
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

		// Get orders to be delivered today
		plan = QMOrder.generateDeliveryList(sqlDate, database);

		return plan;
	}

	/* (non-Javadoc)
	 * @see controller.User#toString()
	 */
	@Override
	public String toString() {
		String s = super.toString() + "\n";

		s += "Driver";

		return s;
	}	

	/*
	public static void main(String[] args) {
 		String username = "espenme";
 		String passingword = "16Sossosem06";
 		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
 		Database database = new Database("com.mysql.jdbc.Driver", databasename);
		Driver driver = new Driver("","", database);
		String[][] plan = driver.generateDeliveryPlan();
	}
	 */
}
