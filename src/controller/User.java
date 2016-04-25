package controller;

import java.util.ArrayList;

import database.*;

/**
 * The Class User.
 * Superclass for all usertypes, contains all general view methods
 */
public class User{
	
	/** The user id. */
	private String userID;
	
	/** The user type. */
	private int userType;
	
	/** User name. */
	private String name;
	
	/** Database connections. */
	Database database;
	
	/** Convenience string functions */
	private TextEditor t = new TextEditor();
	
	/**
	 * Instantiates user.
	 *
	 * @param userID User id
	 * @param userType User type
	 * @param name User name
	 * @param database Database connection
	 */
	public User(String userID, int userType, String name, Database database){
		this.userID=userID;
		this.userType=userType;
		this.name=name;
		this.database = database;
	}
 
	/**
	 * Gets the user id.
	 *
	 * @return User id
	 */
	public String getUserID(){
		return userID;
	}
	
	/**
	 * Gets user name.
	 *
	 * @return Name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Gets the user type.
	 *
	 * @return User type
	 */
	public int getUserType(){
		return userType;
	}
	
	/**
	 * Gets the database connection.
	 *
	 * @return Database connection
	 */
	public Database getDatabase(){
		return database;
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param userID the new user id
	 */
	public void setUserID(String userID){
		this.userID=userID;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name){
		this.name=name;
	}
	
	/**
	 * Sets the user type.
	 *
	 * @param userType the new user type
	 */
	public void setUserType(int userType){
		this.userType = userType;
	}
	
	/**
	 * View all orders that has meals with deliverydate on the given date.<br>
	 * Returns arraylist with orders that contains all meals in order.
	 * @param date Date to retrieve food orders for
	 * @return ArrayList of Order objects
	 */
	
	public ArrayList<Order> viewFoodOrders(java.sql.Date date) {
		
		// Retrieve orders from database
		String[][] orderT = QMOrder.viewOrdersByDeliveryDate(date, database);
		if (orderT.length==0){
			return null; 
		}

		Order tempOrder;
		ArrayList<Order> orderList = new ArrayList<Order>();

		// Convert string array to order object and add to arraylist
		for(int i=0; i<orderT.length;i++){
			tempOrder = new Order(t.stringToInt(orderT[i][0]), 	orderT[i][1], t.stringToInt(orderT[i][2]),  orderT[i][3], orderT[i][4]);
			
			tempOrder.fetchMealsInOrder(database);
			orderList.add(tempOrder);
		}
		return orderList; 
	}
	
	/**
	 * View all orders with a customerID.
	 * Returns arraylist with orders that contains all meals in order
	 *
	 * @param customerID the customer id
	 * @return the array list
	 */
	//
	public ArrayList<Order> viewFoodOrdersByCustomer(int customerID) {
		
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
	
	/**
	 * View single order.
	 *
	 * @param orderID Order id of order
	 * @return Order object
	 */
	public Order viewSingleOrder(int orderID) {
		// Retrieve from database
		String[] orderT = QMOrder.viewOrder(orderID, database);
		
		// Convert to Order object
		Order tempOrder = new Order(t.stringToInt(orderT[0]), orderT[1], t.stringToInt(orderT[2]), orderT[3], orderT[4]);
		tempOrder.fetchMealsInOrder(database);
		return tempOrder;
	}
	
	/**
	 * View available meals.<br>
	 * Returns an arraylist with all available meals.
	 *
	 * @return Arraylist of Meals
	 */
	public ArrayList<Meal> viewAvailableMeals() {
		// Retrieve from database
		String[][] mealT = QMFood.viewMeals(database);
		
		if (mealT.length==0){
			return null;
		}
		
		Meal tempMeal; 
		ArrayList<Meal> mealList = new ArrayList<Meal>();

		// Convert to Meal objects
		for (int i=0; i<mealT.length; i++){
			if (t.stringToInt(mealT[i][3])>0){
				tempMeal = new Meal(t.stringToInt(mealT[i][0]), mealT[i][1], mealT[i][2], true, t.stringToInt(mealT[i][4]));
				tempMeal.fetchIngredients(database);
				mealList.add(tempMeal);
			}
		}
		return mealList;
	}
	
	/**
	 * View customer list.<br>
	 * Returns an arraylist with customer objects with all active customers
	 * @return Array list of Customer objects
	 */
	public ArrayList<Customer> viewCustomerList() {

		// Retrieve from database
		String[][] list = QMCustomer.viewAllCustomers(database);
		if (list.length==0){
			return null;
		}
		
		Customer tempCustomer;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		
		// Convert to Customer objects
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
	
	/**
	 * View list of all company customers.
	 *
	 * @return ArrayList of Customer objects
	 */
	public ArrayList<Customer> viewCompanyList() {
		
		// Retrieve from database
		String[][] list = QMCustomer.viewAllCompanies(database);
		if (list.length==0){
			return null;
		}
		
		Customer tempCompany;
		ArrayList<Customer> companyList = new ArrayList<Customer>();
		
		// Convert to Customer objects
		for(int i=0; i<list.length; i++){
			if (t.stringToInt(list[i][10])==1){
				int customerId = t.stringToInt(list[i][0]);
				int zipCode = t.stringToInt(list[i][7]);
				int zoneNr = t.stringToInt(list[i][8]);
				
				tempCompany = new Customer(customerId, list[i][2],list[i][3],list[i][4],list[i][5],list[i][6],zipCode,zoneNr,list[i][9],true,list[i][1]);
				
				companyList.add(tempCompany);
			}
		}
		return companyList;
	}
	
	
	/**
	 * View single customer.<br>
	 * Takes an customerId returns a single customer object, or null if not found.
	 * @param customerId the customer id
	 * @return the customer
	 */
	public Customer viewSingleCustomer(int customerId) {
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
	
	/**
	 * View ingredients.
	 *
	 * @return the array list
	 */
	//FINISHED --- M� testes
	public ArrayList<Ingredient> viewIngredients() {
		String[][] ingT = QMFood.viewIngredients(database);
		if (ingT.length==0){
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
	
	/**
	 * View all sub plans.
	 *
	 * @return the array list
	 */
	// FINISHED
	public ArrayList<SubPlan> viewAllSubPlans() {
		String[][] subT = QMFood.viewSubscriptionPlans(database);
		
		if (subT.length==0){
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
	
	/**
	 * View zones.
	 *
	 * @return the string[][]
	 */
	public String[][] viewZones() {
		String[][] zones = null;
		
		zones = QMCustomer.viewZones(database);
		
		return zones;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		if (userType != other.userType)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "Username: "+userID+", usertype: "+userType+", name: "+name;
	}
}
