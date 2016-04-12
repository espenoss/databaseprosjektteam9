package controller;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import databasePackage.*;

public class QueryMethodsController implements java.io.Serializable{
	String username = "espenme";
	String passingword = "16Sossosem06";
	String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
	String databaseDriver = "com.mysql.jdbc.Driver";
	Database database = new Database(databaseDriver, databasename); 
	
	//Registrerer ny bruker
	public boolean RegisterUser(String userID, int userType, String name,String pword, Database database) throws Exception{
		return QueryMethods.registerUser(userID, userType, name, pword, database);
	}
	
	//Registrerer ny kunde
	public int RegisterCustomer(String surName, String firstName, String phoneNumber, String email, String adress, 
			int zip_code, int zone_nr, String preferences, boolean active, Database database) throws Exception{
		return QueryMethods.registerCustomer(surName, firstName, phoneNumber, email, adress, zip_code, zone_nr, preferences, active, database);
	}
	
	
	//returns an arraylist with customer objects with all active customers
		public ArrayList<User> viewUserList() throws Exception{
			
			String[][] list = QueryMethods.viewAllUsers(database);
			User tempUser;
			ArrayList<User> userList = new ArrayList<User>();
			
			for(int i=0; i<list.length; i++){
				if (Integer.parseInt(list[i][1])==1){
			//		int userID = Integer.parseInt(list[i][0]); //0
					int userType = Integer.parseInt(list[i][1]); 
					
					tempUser = new User(list[i][0],userType,list[i][2], null, database);
					userList.add(tempUser);
				}
			}
			return userList;
		}
		
		//returns an arraylist with customer objects with all active customers
		public ArrayList<Customer> viewCustomerList() throws Exception{
			
			String[][] list = QueryMethods.viewAllCustomers(database);
			Customer tempCustomer;
			ArrayList<Customer> customerList = new ArrayList<Customer>();
			
			for(int i=0; i<list.length; i++){
				if (Integer.parseInt(list[i][9])==1){
					int customerId = Integer.parseInt(list[i][0]); //0
					//1 first name
					//2 sur name
					//3 phonenumber
					//4 email
					//5 adress
					int zipCode = Integer.parseInt(list[i][6]); //6
					int zoneNr = Integer.parseInt(list[i][7]); //7
					//8 preferences
					
					tempCustomer = new Customer(list[i][1],list[i][2],list[i][3],list[i][4],list[i][5],zipCode,zoneNr,list[i][8]);
					customerList.add(tempCustomer);
				}
			}
			return customerList;
		}
		
		//Lister opp alle kunder 
		public String[][] ViewAllCustomersList(){
			String text = "Customer List:\n";

			  String[][] dbData = null;
			  
			  try {
				  dbData = QueryMethods.viewAllCustomers(database);
			  } catch (Exception e) {
				  e.printStackTrace();
			  }
			
			for(int x=0;x<dbData.length; x++){
				for(int y=0;y<dbData[x].length;y++){
					text += dbData[x][y] + " ";
				}
				text += "\n";
			}
			JOptionPane.showMessageDialog(null, text);
		    return  dbData;
		}
	
	//takes an customerId returns a single customer object, or null if not found
	public Customer viewSingleCustomer(int customerId) throws Exception{
		String[][] list = QueryMethods.viewAllCustomers(database);
		
		for(int i=0; i<list.length; i++){
			if (Integer.parseInt(list[i][9])==1){
				int dbCustomerId = Integer.parseInt(list[i][0]);
				if(customerId==dbCustomerId){
					int zipCode = Integer.parseInt(list[i][6]);
					int zoneNr = Integer.parseInt(list[i][7]);
					return new Customer(list[i][1],list[i][2],list[i][3],list[i][4],list[i][5],zipCode,zoneNr,list[i][8]);
				}
			}
		}
		return null;
	}
	
	//Se liste over alle bedriftskunder
	public String[][] ViewAllCompaniesList() throws Exception{
		return QueryMethods.viewAllCompanies(database);
	}
	
	
	public String viewFoodOrders(){
		// Skal sporre database
		return "Food orders:\n";
	}
	
	
	public static void main(String[] args) throws Exception{
		QueryMethodsController queryMethodsController = new QueryMethodsController();

		System.out.println("Enkelt bruker: ");
	}
	
}


