package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JOptionPane;
import databasePackage.*;

public class User implements java.io.Serializable{
	private String userID;
	private int userType;
	private String name;
	Database database;
	private TextEditor t = new TextEditor();
	
	public User(String userID, int userType, String name, Database database){
		this.userID=userID;
		this.userType=userType;
		this.name=name;
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
	
	//FINISHED, is tested
	//View all orders that has meals with deliverydate on the given date
	//Returns arraylist with orders that contains all meals in order
	public ArrayList<Order> viewFoodOrders(java.sql.Date date) throws Exception{
		
		String[][] orderT = QMOrder.viewOrdersByDeliveryDate(date, database);
		if (orderT.length==0){
			JOptionPane.showMessageDialog(null,"Could not find anything for the date "+date);
			return null;
		}

		Order tempOrder;
		ArrayList<Order> orderList = new ArrayList<Order>();
		
		for(int i=0; i<orderT.length;i++){
			tempOrder = new Order(t.stringToInt(orderT[i][0]), 	orderT[i][1], t.stringToInt(orderT[i][2]),  orderT[i][3], orderT[i][4]);
			
			tempOrder.fetchMealsInOrder(database);
			orderList.add(tempOrder);
		}
		
		return orderList; 
	}
	
	
	//FINISHED, NOT tested
	//View all orders with a customerID
	//Returns arraylist with orders that contains all meals in order
	public ArrayList<Order> viewFoodOrdersByCustomer(int customerID) throws Exception{
		
		String[][] orderT = QMOrder.viewAllOrdersFromCustomer(customerID, database);
		if (orderT.length==0){
			return null;
		}

		Order tempOrder;
		ArrayList<Order> orderList = new ArrayList<Order>();
		
		for(int i=0; i<orderT.length;i++){
			tempOrder = new Order(t.stringToInt(orderT[i][0]), 	orderT[i][1], t.stringToInt(orderT[i][2]),  orderT[i][3], orderT[i][4]);
			
			tempOrder.fetchMealsInOrder(database);
			orderList.add(tempOrder);
		}
		
		return orderList; 
	}
	
	
	//FINISHED NOT TESTED 
	//Returns an arraylist with all available meals
	public ArrayList<Meal> viewAvailableMeals() throws Exception{
		String[][] mealT = QMFood.viewMeals(database);
		
		if (mealT.length==0){
			JOptionPane.showMessageDialog(null,"Could not find any available orders.");
			return null;
		}
		
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
	

	//FINISHED --- MÅ TESTES
	//returns an arraylist with customer objects with all active customers
	public ArrayList<Customer> viewCustomerList() throws Exception{
		
		String[][] list = QMCustomer.viewAllCustomers(database);
		if (list.length==0){
			JOptionPane.showMessageDialog(null,"Could not find any customers.");
			return null;
		}
		
		Customer tempCustomer;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		
		for(int i=0; i<list.length; i++){
			if (t.stringToInt(list[i][9])==1){
				int customerId = t.stringToInt(list[i][0]); //0
				int zipCode = t.stringToInt(list[i][6]); //6
				int zoneNr = t.stringToInt(list[i][7]); //7
				
				tempCustomer = new Customer(customerId, list[i][2],list[i][1],list[i][3],list[i][4],list[i][5],zipCode,zoneNr,list[i][8],true);
				customerList.add(tempCustomer);
			}
		}
		return customerList;
	}
	
	public ArrayList<Customer> viewCompanyList() throws Exception{
		
		String[][] list = QMCustomer.viewAllCompanies(database);
		if (list.length==0){
			JOptionPane.showMessageDialog(null,"Could not find any companies.");
			return null;
		}
		
		Customer tempCompany;
		ArrayList<Customer> companyList = new ArrayList<Customer>();
		
		
		for(int i=0; i<list.length; i++){
			if (t.stringToInt(list[i][10])==1){
				int customerId = t.stringToInt(list[i][0]); //0
				int zipCode = t.stringToInt(list[i][7]); //6
				int zoneNr = t.stringToInt(list[i][8]); //7
				
				tempCompany = new Customer(customerId, list[i][2],list[i][3],list[i][4],list[i][5],list[i][6],zipCode,zoneNr,list[i][9],true,list[i][1]);
				
				companyList.add(tempCompany);
			}
		}
		return companyList;
	}
	
	
	//FINISHED --- MÅ TESTES
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
	
	//FINISHED --- M� testes
	public ArrayList<Ingredient> viewIngredients() throws Exception{
		String[][] ingT = QMFood.viewIngredients(database);
		if (ingT.length==0){
			JOptionPane.showMessageDialog(null,"Could not find any ingredients.");
			return null;
		}
		
		
		Ingredient tempIng; 
		ArrayList<Ingredient> ingList = new ArrayList<Ingredient>();
		
		for (int i=0; i<ingT.length; i++){
			tempIng = new Ingredient(t.stringToInt(ingT[i][0]), ingT[i][1], t.stringToFloat(ingT[i][2]), ingT[i][3]);
			ingList.add(tempIng);
		}
		return ingList;
	}
	
	// FINISHED
	public ArrayList<SubPlan> viewAllSubPlans() throws Exception{
		String[][] subT = QMFood.viewSubscriptionPlans(database);
		
		if (subT.length==0){
			JOptionPane.showMessageDialog(null,"Could not find any subscription plans.");
			return null;
		}
		
		SubPlan tempSub; 
		ArrayList<SubPlan> subList = new ArrayList<SubPlan>();
		
		for (int i=0; i<subT.length; i++){
			tempSub = new SubPlan(t.stringToInt(subT[i][0]), subT[i][1]);
			tempSub.fetchMealsInPlan(database);
			subList.add(tempSub);
		}
		return subList;
	}
	
	public boolean equals(Object obj){
		if(!(obj instanceof User)){
			return false;
		}
		if(this==obj){
			return true;
		}
		User p=(User)obj;
		return (userID==p.getUserID() && name==p.getName()&&userType==p.getUserType());
	}
	
	
	public String toString(){
		return "Username: "+userID+", usertype: "+userType+", name: "+name;
	}
	
	
	
/*	
	
	//TEST
	public static void main(String[] args) throws Exception{
		String username = "marith1";
		String password = "tgp8sBZA";
		String databaseName = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		String databaseDriver = "com.mysql.jdbc.Driver";
		Database database = new Database(databaseDriver, databaseName);
		
		
		User user = new User("Hanne", 1, "Hanne", database);
		
		
		//System.out.println("Enkelt bruker: "+user.viewSingleCustomer(10000));
		//System.out.println(user.viewCustomerList());
		
		
		java.sql.Date dato = java.sql.Date.valueOf("2016-03-01");
		//System.out.println(dato);
		
		//System.out.println(user.viewFoodOrders(dato));
		System.out.println(user.viewFoodOrdersByCustomer(10000));
		//System.out.println(user.viewCompanyList());
		//System.out.println(user.viewAllSubPlans());
		
	}
	*/
}
