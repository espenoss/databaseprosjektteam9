package controller;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import databasePackage.*;

public class User implements java.io.Serializable{
	private String userID;
	private int userType;
	private String name;
	private String pword;
	Database database;
	
	public User(String userID, int userType, String name,String pword, Database database){
		this.userID=userID;
		this.userType=userType;
		this.name=name;
		this.pword=pword;
		this.database = database;
	}	

	public String getUserID(){
		return userID;
	}
	
	public String getName(){
		return name;
	}
	public int getUserType(){
		return userType;
	}
	public String getPword(){
		return pword;
	}
	
	public Database getDatabase(){
		return database;
		
	}
	
	public void setUserID(String userID){
		this.userID=userID;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setUserType(int userType){
		this.userType = userType;
	}
	public void setPassword(String pword){
		this.pword=pword;
	}
	
	// 0 : order_id - int
	// 1 : order_date - String
	// 2 : customer_id - int
	// 3 : info - String
	// 4 : user_id - String
	
	
	public ArrayList<Order> viewFoodOrders(Date date) throws Exception{
		
		
		String[][] orderT = QueryMethods.viewOrdersByDeliveryDate(date, database);
		Order tempOrder;
		ArrayList<Order> OrderList = new ArrayList<Order>();
		TextEditor t = new TextEditor();
		
		for(int i=0; i<orderT.length;i++){
			int orderID = t.StringToInt(orderT[i][0]);
			int customerID = t.StringToInt(orderT[i][2]);
			
			tempOrder = new Order(orderID,orderT[i])
		}
		
		return null;
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
				
				tempCustomer = new Customer(customerId, list[i][1],list[i][2],list[i][3],list[i][4],list[i][5],zipCode,zoneNr,list[i][8]);
				customerList.add(tempCustomer);
			}
		}
		return customerList;
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
					return new Customer(customerId, list[i][1],list[i][2],list[i][3],list[i][4],list[i][5],zipCode,zoneNr,list[i][8]);
				}
			}
		}
		return null;
	}
	
	public String viewOrderIngredients(){
		String ingredients = null;
		
		// hent ingredienser fra database
		
		return ingredients;
	}
	
	public boolean equals(Object obj){
		if(!(obj instanceof User)){
			return false;
		}
		if(this==obj){
			return true;
		}
		User p=(User)obj;
		return (userID==p.getUserID() && name==p.getName()&&userType==p.getUserType()&&pword==p.getPword());
	}
	public String toString(){
		return userID+""+ name+" "+ userType +" "+pword;
	}
	
	
	public static void main(String[] args) throws Exception{
		String username = "marith1";
		String password = "tgp8sBZA";
		String databaseName = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		String databaseDriver = "com.mysql.jdbc.Driver";
		Database database = new Database(databaseDriver, databaseName);
		
		User user = new User("Hanne", 1, "Hanne","1234", database);
		
		System.out.println("Enkelt bruker: "+user.viewSingleCustomer(10000));
		System.out.println(user.viewCustomerList());
		
	}
}
