package controller;

import databasePackage.*;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Sales extends User {
	
	public Sales(String userID, int userType, String name, String pword, Database database) {
		super(userID, userType, name, pword, database);
	}

	
	//FINISHED --- NOT TESTED
	//registers new order, returns order object. 
	public Order registerNewOrder(int customerID, String deliveryDate, String info, String userID, Database database)throws Exception{
		
	    java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    String dateToday = sqlDate.toString();
	    
		int orderID = QMOrder.registerOrder(dateToday, customerID, info, userID, database);
		
		return new Order(orderID, dateToday, customerID, info, userID);
	}
	
	
	//FINISHED
	public boolean registerSubscriptionOrder(Order order, int quantitySub, String fromDate, String toDate, int subID) throws Exception{
		boolean success = QMOrder.addSubscriptionToOrder(order.getOrderID(), quantitySub, fromDate, toDate, subID, database);
		
		return success;
	}
	
	
	//FINISHED --- NOT TESTED
	//Register new customer
	public Customer registerCustomer(String surName, String firstName, String phoneNumber, String email, String adress, 
			int zip_code, int zone_nr, String preferences, boolean active, Database database) throws Exception{
		int customerID = QMCustomer.registerCustomer(surName, firstName, phoneNumber, email, adress, zip_code, zone_nr, preferences, active, database);
		
		if (customerID<0){
			return null;
		}
		return new Customer(customerID,firstName,surName,phoneNumber,email,adress,zip_code,zone_nr,preferences,true);
	}
	
	//FINISHED --- NOT TESTED
	//Register company
	public boolean registerCompanyToCustomer(Customer customer, String companyName) throws Exception{
		boolean ok = QMCustomer.registerCompanyToCustomer(customer.getCustomerID(), companyName, database);
		if(ok) {
			customer.setCompanyName(companyName);
			customer.setIsCompany(true);
		}
		return ok;
	}
		
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Sales";
		
		return s;
	}
	
	
	
}
