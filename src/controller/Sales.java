package controller;

import database.*;

/**
 * The Class Sales.
 * Contains all methods exclusive to the sales usertype
 */
public class Sales extends User {
	
	/**
	 * Instantiates a new sales.
	 *
	 * @param userID The user id
	 * @param name Name of user
	 * @param database Database connection
	 */
	public Sales(String userID, String name, Database database) {
		super(userID, 3, name, database);
	} 

	/**
	 * Register new order.
	 *
	 * @param customerID Customer id
	 * @param info Order info
	 * @param userID The user id
	 * @return The registered order as Order object
	 */
	public Order registerNewOrder(int customerID, String info, String userID){
		
	    java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    String dateToday = sqlDate.toString();
	    
		int orderID = QMOrder.registerOrder(dateToday, customerID, info, userID, database);
		return new Order(orderID, dateToday, customerID, info, userID);
	}
	
	/**
	 * Adds meal to order.
	 *
	 * @param orderID The order id
	 * @param mealID The meal id
	 * @param deliveryDate The delivery date
	 * @param quantity The quantity
	 * @return true, if successful
	 */
	public boolean addMealToOrder(int orderID, int mealID, String deliveryDate, int quantity) {
		boolean res = QMOrder.addMealToOrder(orderID, mealID, deliveryDate, quantity, false, false, database);
		return res;
	}
	
	/**
	 * Register subscription order.
	 *
	 * @param order The order
	 * @param quantitySub The quantity
	 * @param fromDate From date
	 * @param toDate To date
	 * @param subID The subscription id
	 * @return true, if successful
	 */
	public boolean registerSubscriptionOrder(Order order, int quantitySub, String fromDate, String toDate, int subID) {
		boolean success = QMOrder.addSubscriptionToOrder(order.getOrderID(), quantitySub, fromDate, toDate, subID, database);
		
		return success;
	}
	
	/**
	 * Register new customer.
	 *
	 * @param surName Customer surname
	 * @param firstName Customer first name
	 * @param phoneNumber Customer phone number
	 * @param email Customer email
	 * @param adress Customer street adress
	 * @param zip_code Customer zip code
	 * @param zone_nr Customer zone number
	 * @param preferences Customer preferences
	 * @param active Whether customer is active
	 * @return Customer object representing registered customer
	 */
	public Customer registerCustomer(String surName, String firstName, String phoneNumber, String email, String adress, 
			int zip_code, int zone_nr, String preferences, boolean active) {
		int customerID = QMCustomer.registerCustomer(surName, firstName, phoneNumber, email, adress, zip_code, zone_nr, preferences, active, database);
		
		if (customerID<0){
			return null;
		}
		return new Customer(customerID,firstName,surName,phoneNumber,email,adress,zip_code,zone_nr,preferences,true);
	}
	
	/**
	 * Register company to customer.
	 *
	 * @param customer The customer to register the company to
	 * @param companyName The company name
	 * @return true, if successful
	 */
	public boolean registerCompanyToCustomer(Customer customer, String companyName) {
		boolean ok = QMCustomer.registerCompanyToCustomer(customer.getCustomerID(), companyName, database);
		if(ok) {
			customer.setCompanyName(companyName);
			customer.setIsCompany(true);
		}
		return ok;
	}
	
	
	
		
	/* (non-Javadoc)
	 * @see controller.User#toString()
	 */
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Sales";
		
		return s;
	}
	
	
	
}
