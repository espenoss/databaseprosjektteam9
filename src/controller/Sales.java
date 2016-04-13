package controller;

import databasePackage.*;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Sales extends User {
	
	public Sales(String userID,int userType, String name,String pword, Database database) {
		super(userID, userType, name, pword, database);
	}

	public boolean registerNewOrder(int customerID, String date, String deliveryDate, String info, String userID, ArrayList<Meal> meals, Database database)throws Exception{
		boolean success = false;
		
		int orderID = QueryMethods.registerOrder(date, customerID, info, userID, database);
		
		for (Meal aMeal : meals){
	//		TextEditor t = new TextEditor();
			String quantityRead = JOptionPane.showInputDialog("How many of "+aMeal.getMealName()+" should be registerd?");
			int quantity = t.StringToInt(quantityRead);
			//int quantity = Integer.parseInt(quantityRead);
			
			if(!QueryMethods.addMealToOrder(orderID, aMeal.getMealID(), deliveryDate, quantity, false, false, database)){
				
			}
		}
		

		return success;
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
	
	//Registrerer ny kunde
	public int RegisterCustomer(String surName, String firstName, String phoneNumber, String email, String adress, 
			int zip_code, int zone_nr, String preferences, boolean active, Database database) throws Exception{
		return QueryMethods.registerCustomer(surName, firstName, phoneNumber, email, adress, zip_code, zone_nr, preferences, active, database);
	}
	
	
	public boolean changeCustomerInfo(String customerinfo){
		boolean success = false;
		
		// generer meny, hent nye verdier,
		// lagre i database
		
		return success;				
	}
	
	public boolean registerPackageDeal(){
		boolean success = false;
		
		// retrieve package from database
		// select package
		// display package
		// receive customer info
		
		return success;						
	}
		
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Sales";
		
		return s;
	}
	
	
	
}
