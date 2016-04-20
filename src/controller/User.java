package controller;

import java.util.ArrayList;
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
	
	//IKKE FERDIG --- Mï¿½ TESTES
	//Legger for ï¿½yebl ikke inn info om mï¿½ltid i objektene
	//Returns an arraylist with orderObjects containing belonging meal objects. 
	public ArrayList<Order> viewFoodOrders(java.sql.Date date) throws Exception{
		
		String[][] orderT = QMOrder.viewOrdersByDeliveryDate(date, database);
		Order tempOrder;
		ArrayList<Order> orderList = new ArrayList<Order>();
		TextEditor t = new TextEditor();
		
		for(int i=0; i<orderT.length;i++){
			tempOrder = new Order(t.stringToInt(orderT[i][0]), 	orderT[i][1], t.stringToInt(orderT[i][2]),  orderT[i][3], orderT[i][4]);
			
			tempOrder.fetchMealsInOrder(date, database);
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
	
	public ArrayList<Customer> viewCompanyList() throws Exception{
		
		String[][] list = QMCustomer.viewAllCompanies(database);
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
	
	//FINISHED --- Må testes
	public ArrayList<Ingredient> viewIngredients() throws Exception{
		String[][] ingT = QMFood.viewIngredients(database);
		Ingredient tempIng; 
		
		ArrayList<Ingredient> ingList = new ArrayList<Ingredient>();
		
		for (int i=0; i<ingT.length; i++){
			if (t.stringToInt(ingT[i][3])>0){
				tempIng = new Ingredient(t.stringToInt(ingT[i][0]), ingT[i][1], t.stringToFloat(ingT[i][2]), ingT[i][3]);
				ingList.add(tempIng);
			}
		}
		return ingList;
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
	
	// FINISHED må testes
	public ArrayList<SubPlan> viewAllSubPlans() throws Exception{
		String[][] subT = QMFood.viewSubscriptionPlans(database);
		SubPlan tempSub; 
		
		ArrayList<SubPlan> subList = new ArrayList<SubPlan>();
		
		System.out.println("tabell lengde: "+subT.length);
		System.out.println("tabell lengde2: "+subT[0].length);
		
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
		
		
		java.sql.Date dato = java.sql.Date.valueOf( "2016-03-01");
		//System.out.println(dato);
		
		System.out.println(user.viewFoodOrders(dato));
		//System.out.println(user.viewCompanyList());
		//System.out.println(user.viewAllSubPlans());
		
	}
}
