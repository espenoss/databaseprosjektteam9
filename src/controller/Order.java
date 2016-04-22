package controller;

import java.sql.Date;
import java.util.ArrayList;
import databasePackage.*;


public class Order {
	private int orderID;
	private String orderDate; // java.util.Date / java.sql.Date?
	private int customerID;
	private String info;
	private String userID;
	private ArrayList<MealOrdered> meals = new ArrayList<MealOrdered>();

	
	public Order(int orderID, String orderDate, int customerID, String info, String userID){
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.customerID = customerID;
		this.info = info;
		this.userID = userID;
	}

	public int getOrderID(){
		return orderID;
	}
	
	public String getOrderDate(){
		return orderDate;
	}

	public int getCustomerID(){
		return customerID;
	}
	
	public String getInfo(){
		return info;
	}
	
	public String getUserID(){
		return userID;
	}
	
	public Meal getMeal(int index){
		if(meals.size() > 0) return meals.get(index);
		else return null;
	}
	
	public ArrayList<MealOrdered> getMeals(){
		return meals;
	}
	
	public void setOrderDate(String orderDate){
		this.orderDate = orderDate;
	}
	
	public void setInfo(String info){
		this.info = info;
	}
	/*
	//Skal vi ha denne??
	//Marks meal as ready for delivery to customer, given index of meal in mealArray
	public boolean markMealAsReadyByIndex(int index, Database database) throws Exception{
		return meals.get(index).markMealAsReady(database);
	}
	*/
	
	// FINISHED MÅ TESTES
	public boolean removeMealFromOrder(int index, Database database) throws Exception{
		QMOrder.removeMealFromOrder(orderID, meals.get(index).getMealID(), meals.get(index).getDeliverydate().toString(), database);
		fetchMealsInOrder(database);
		return false;
	}
	
	/*
	//Skal vi ha denne??
	//Marks all meals in order with given deliverydate as ready for delivery, returns count for 
	public int markAllMealsAsReadyByDate(java.sql.Date date, Database database) throws Exception{
		ArrayList<MealOrdered> meals = viewMealsInOrderByDate(date, database);
		int count = 0;
		boolean ok;
		
		for (int i=0; i<meals.size();i++){
			ok = meals.get(i).markMealAsReady(database);
			if (ok){count++;}
		}
		return count;
	}
	*/
	
	
	//FINISHED må testes
	//Register information to databasee
	public boolean uploadOrder(Database database) throws Exception{
		return QMOrder.updateOrder(orderID, orderDate, customerID, info, userID, database);
	}
	
	//FINISHED må testes
	//Makes an arrayList of all meals in an order that has a spesific delivery date. 
	public ArrayList<MealOrdered> viewMealsInOrderByDate(java.sql.Date date, Database database) throws Exception{
		fetchMealsInOrder(database);
		ArrayList<MealOrdered> tempMeals = new ArrayList<MealOrdered>();
		
		for (int i=0; i<meals.size();i++){
			if (meals.get(i).getDeliverydate().equals(date)){
				tempMeals.add(meals.get(i));
			}
		}
		return tempMeals;
	}
	
	//FINISHED testes?
	//Fetches meals from database
	public void fetchMealsInOrder(Database database) throws Exception{
		meals = new ArrayList<MealOrdered>(); //creates new empty meal arrayList
		String[][] mealT = QMOrder.viewMealsInOrder(orderID, database);
		
		TextEditor t = new TextEditor();
		
		for(int i=0;i<mealT.length;i++){			
			boolean readyDelivery = t.stringToInt(mealT[i][8])==1;
			boolean delivered = t.stringToInt(mealT[i][9])==1;
			
			meals.add(new MealOrdered(t.stringToInt(mealT[i][0]), mealT[i][1], mealT[i][2], t.stringToInt(mealT[i][4]),
					mealT[i][6], t.stringToInt(mealT[i][7]),t.stringToInt(mealT[i][5]),readyDelivery,delivered));
		}
	}
	
	public String toString(){
		String res = "";
		res += "OrderID: " + orderID + ". Orderdate: " + orderDate+ ". Info: " + info + "\n";
		res += "Meals: \n";
		
		for(Meal m:meals){
			res += "   " + m.toString() + "\n";
		}
		
		return res;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customerID;
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + orderID;
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		return result;
	}

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
	
	/*
	//TEST TEST TEST 
	static public void main(String[] arg) throws Exception{
		String username = "marith1";
		String password = "tgp8sBZA";
		String databaseName = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		String databaseDriver = "com.mysql.jdbc.Driver";
		Database database = new Database(databaseDriver, databaseName);
		
	    java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		Order test = new Order(10000,"2016-03-01",10000,"Info", "hanneh");
		//System.out.println(test);
		
		java.sql.Date dato = java.sql.Date.valueOf("2016-03-01");
		
		test.fetchMealsInOrder(database);
		System.out.println(test.viewMealsInOrderByDate(dato, database));
		//System.out.println(test);
		
	}
	*/
}
