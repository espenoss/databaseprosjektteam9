package controller;

import databasePackage.*;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Sales extends User {
	
	public Sales(String userID,int userType, String name,String pword) {
		super(userID, userType, name, pword);
	}

	public boolean registerNewOrder(int customerID, String date, String deliveryDate, String info, String userID, ArrayList<Meal> meals, Database database)throws Exception{
		boolean success = false;
		
		int orderID = QueryMethods.registerOrder(date, customerID, info, userID, database);
		
		for (Meal aMeal : meals){
			String quantityRead = JOptionPane.showInputDialog("How many of "+aMeal.getMealName()+" should be registerd?");
			int quantity = Integer.parseInt(quantityRead);
			
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
	
	public boolean registerNewCustomer(String customerinfo){
		boolean success = false;
		
		// opprett ny kunde i database
		
		return success;		
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
