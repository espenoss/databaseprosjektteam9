package controller;

import databasePackage.*;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Sales extends User {
	
	public Sales(String userID,int userType, String name,String pword, Database database) {
		super(userID, userType, name, pword, database);
	}

	
	//FINISHED
	//registers new order, returns order object. 
	public Order registerNewOrder(int customerID, String deliveryDate, String info, String userID, Database database)throws Exception{
		
	    java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
	    String dateToday = sqlDate.toString();
		int orderID = QueryMethods.registerOrder(dateToday, customerID, info, userID, database);
		
		return new Order(orderID, dateToday, deliveryDate, customerID, info, userID);

	}
	
	public boolean registerSubscription(String orderinfo){
		boolean success = false;
		
		// opprett ny ordre i database
		
		return success;
	}
	

	public boolean changeFoodOrder(String orderinfo){
		boolean success = false;
		
		// generer meny, hent nye verdier,
		// lagre i database
		
		return success;
	}
	
	
	//FINISHED
	//Register new customer
	public Customer RegisterCustomer(String surName, String firstName, String phoneNumber, String email, String adress, 
			int zip_code, int zone_nr, String preferences, boolean active, Database database) throws Exception{
		int customerID = QueryMethods.registerCustomer(surName, firstName, phoneNumber, email, adress, zip_code, zone_nr, preferences, active, database);
		
		if (customerID<0){
			return null;
		}
		return new Customer(customerID,firstName,surName,phoneNumber,email,adress,zip_code,zone_nr,preferences,true);
	}
	
		
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Sales";
		
		return s;
	}
	
	
	
}
