package controller;

import java.util.ArrayList;
import database.*;

/**
 * The Class Order.
 * Represents entry in 'food_order' table in database
 */
public class Order {
	
	/** The order id. */
	private int orderID;
	
	/** The order date. */
	private String orderDate;
	
	/** The customer id. */
	private int customerID;
	
	/** Order info. */
	private String info;
	
	/** The user id. */
	private String userID;
	
	/** Meals in the order. */
	private ArrayList<MealOrdered> meals = new ArrayList<MealOrdered>();

	
	/**
	 * Instantiates order.
	 *
	 * @param orderID The order id
	 * @param orderDate The order date
	 * @param customerID The customer id
	 * @param info Order info
	 * @param userID The user id
	 */
	public Order(int orderID, String orderDate, int customerID, String info, String userID){
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.customerID = customerID;
		this.info = info;
		this.userID = userID;
	}

	/**
	 * Gets the order id.
	 *
	 * @return The order id
	 */
	public int getOrderID(){
		return orderID;
	}
	
	/**
	 * Gets the order date.
	 *
	 * @return The order date
	 */
	public String getOrderDate(){
		return orderDate;
	}

	/**
	 * Gets the customer id.
	 *
	 * @return The customer id
	 */
	public int getCustomerID(){
		return customerID;
	}
	
	/**
	 * Gets order info.
	 *
	 * @return Info String
	 */
	public String getInfo(){
		return info;
	}
	
	/**
	 * Gets the user id.
	 *
	 * @return The user id
	 */
	public String getUserID(){
		return userID;
	}
	
	/**
	 * Gets the meal.
	 *
	 * @param index Index of meal in ordered meal list
	 * @return The meal
	 */
	public Meal getMeal(int index){
		if(meals.size() > 0) return meals.get(index);
		else return null;
	}
	
	/**
	 * Gets the meals with order info.
	 *
	 * @return ArrayList of OrderedMeal objects
	 */
	public ArrayList<MealOrdered> getMeals(){
		return meals;
	}
	
	/**
	 * Sets the order date.
	 *
	 * @param orderDate The new order date
	 */
	public void setOrderDate(String orderDate){
		this.orderDate = orderDate;
	}
	
	/**
	 * Sets the info.
	 *
	 * @param info The new info
	 */
	public void setInfo(String info){
		this.info = info;
	}
	
	/**
	 * Removes the meal from order.
	 *
	 * @param index The index
	 * @param database Database connection
	 * @return true, if successful
	 */
	public boolean removeMealFromOrder(int index, Database database) {
		boolean ok = QMOrder.removeMealFromOrder(orderID, meals.get(index).getMealID(), meals.get(index).getDeliverydate().toString(), database);
		fetchMealsInOrder(database);
		return ok;
	}
	
	/**
	 * Register information to databasee.
	 *
	 * @param database Database connection
	 * @return true, if successful
	 */
	public boolean uploadOrder(Database database) {
		return QMOrder.updateOrder(orderID, orderDate, customerID, info, userID, database);
	}
	
	/**
	 * View meals in order by date.<br>
	 * Makes an arrayList of all meals in an order that has a specific delivery date.
	 *
	 * @param date The date
	 * @param database Database connection
	 * @return ArrayList of MealOrdered objects
	 */ 
	public ArrayList<MealOrdered> viewMealsInOrderByDate(java.sql.Date date, Database database) {
		fetchMealsInOrder(database);
		ArrayList<MealOrdered> tempMeals = new ArrayList<MealOrdered>();
		 
		for (int i=0; i<meals.size();i++){
			if (meals.get(i).getDeliverydate().equals(date)){
				tempMeals.add(meals.get(i));
			}
		}
		return tempMeals;
	}
	
	/**
	 * Fetches meals from database.
	 *
	 * @param database Database connection
	 * @return true, if successful
	 */
	public boolean fetchMealsInOrder(Database database) {

		// Fetch meals from database
		meals = new ArrayList<MealOrdered>(); 
		String[][] mealT = QMOrder.viewMealsInOrder(orderID, database);
		if(mealT.length == 0){
			return false;
		}

		TextEditor t = new TextEditor();

		// Convert to MealOrdered objects and add to 'meals'
		for(int i=0;i<mealT.length;i++){			
			boolean readyDelivery = t.stringToInt(mealT[i][8])==1; 
			boolean delivered = t.stringToInt(mealT[i][9])==1;
			
			meals.add(new MealOrdered(t.stringToInt(mealT[i][0]), mealT[i][1], mealT[i][2], t.stringToInt(mealT[i][4]),
					mealT[i][6], t.stringToInt(mealT[i][7]),t.stringToInt(mealT[i][5]),readyDelivery,delivered));
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String res = "";
		res += "OrderID: " + orderID + ". Orderdate: " + orderDate+ ". Info: " + info + "\n ";
		res += "Meals: \n";
		
		for(Meal m:meals){
			res += "   " + m.toString() + "\n";
		}
		
		return res;
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
		Order other = (Order) obj;
		if (customerID != other.customerID)
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderID != other.orderID)
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}	
}
