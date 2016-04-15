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
	private TextEditor t = new TextEditor();
	
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
	
	
	//IKKE FERDIG --- MÅ TESTES
	//Legger for øyebl ikke inn info om måltid i objektene
	//Returns an arraylist with orderObjects containing belonging meal objects. 
	public ArrayList<Order> viewFoodOrders(java.sql.Date date) throws Exception{
		
		String[][] orderT = QMOrder.viewOrdersByDeliveryDate(date, database);
		Order tempOrder;
		ArrayList<Order> orderList = new ArrayList<Order>();
		TextEditor t = new TextEditor();
		
		System.out.println("lengde tabell: "+orderT[0].length);
		
		
		for(int i=0; i<orderT.length;i++){
			int orderID = t.stringToInt(orderT[i][0]);
			int customerID = t.stringToInt(orderT[i][2]);
			
			tempOrder = new Order(t.stringToInt(orderT[i][0]), 	orderT[i][1], t.stringToInt(orderT[i][2]),  orderT[i][3], orderT[i][4]);
			
			//tempOrder.fetchMealsByDeliveryDate(date, database);
			orderList.add(tempOrder);
		}
		
		return orderList;
	}
	
	//FINISHED NOT TESTED
	//returns an arraylist with all available meals
	public ArrayList<Meal> viewAvailableMeals() throws Exception{
		String[][] mealT = QMFood.viewMeals(database);
		Meal tempMeal; 
		
		ArrayList<Meal> mealList = new ArrayList<Meal>();
		
		
		for (int i=0; i<mealT.length; i++){
			if (t.stringToInt(mealT[i][3])>0){
				tempMeal = new Meal(t.stringToInt(mealT[i][0]), mealT[i][1], mealT[i][2], true, t.stringToInt(mealT[i][4]));
				mealList.add(tempMeal);
			}
		}
		return mealList;
		
	}
	

	//FINISHED --- MÃ… TESTES
	//returns an arraylist with customer objects with all active customers
	public ArrayList<Customer> viewCustomerList() throws Exception{
		
		String[][] list = QMCustomer.viewAllCustomers(database);
		Customer tempCustomer;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		
		for(int i=0; i<list.length; i++){
			if (t.stringToInt(list[i][9])==1){
				int customerId = t.stringToInt(list[i][0]); //0
				int zipCode = t.stringToInt(list[i][6]); //6
				int zoneNr = t.stringToInt(list[i][7]); //7
				
				tempCustomer = new Customer(customerId, list[i][1],list[i][2],list[i][3],list[i][4],list[i][5],zipCode,zoneNr,list[i][8],true);
				customerList.add(tempCustomer);
			}
		}
		return customerList;
	}
	
	
	//FINISHED --- MÃ… TESTES
	//takes an customerId returns a single customer object, or null if not found
	public Customer viewSingleCustomer(int customerId) throws Exception{
		String[][] list = QMCustomer.viewAllCustomers(database);
		
		for(int i=0; i<list.length; i++){
			if (t.stringToInt(list[i][9])==1){
				int dbCustomerId = t.stringToInt(list[i][0]);
				if(customerId==dbCustomerId){
					int zipCode = t.stringToInt(list[i][6]);
					int zoneNr = t.stringToInt(list[i][7]);
					return new Customer(customerId, list[i][2],list[i][1],list[i][3],list[i][4],list[i][5],zipCode,zoneNr,list[i][8],true);
				}
			}
		}
		return null;
	}
	
	//Lists orderIngredients as String
	public String viewOrderIngredients() throws Exception{
		String res = "";
			String[][] temp = QMFood.viewIngredients(database);
			for (int i = 0; i < temp.length; i++){
				res += temp[i];
			}
			return res;
	}
	
	//LAG VIEW SUBSCRIPTION PLAN
	
	public SubPlan viewSubPlan(){
		return null;
	}
	
	
	public Ingredient addNewIngredient(){
		return null;
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
	
	
	
	
	
	//TEST
	public static void main(String[] args) throws Exception{
		String username = "marith1";
		String password = "tgp8sBZA";
		String databaseName = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		String databaseDriver = "com.mysql.jdbc.Driver";
		Database database = new Database(databaseDriver, databaseName);
		
		
		User user = new User("Hanne", 1, "Hanne","1234", database);
		
		/*
		System.out.println("Enkelt bruker: "+user.viewSingleCustomer(10000));
		System.out.println(user.viewCustomerList());
		*/
		
		java.sql.Date dato = java.sql.Date.valueOf( "2016-03-01");
		System.out.println(dato);
		
		System.out.println(user.viewFoodOrders(dato));
	}
}
