package controller;

import java.util.ArrayList;
import databasePackage.*;

// import java.sql.Date;

public class Order {
	private int orderID;
	private String orderDate; // java.util.Date / java.sql.Date?
	private String deliveryDate;
	private int customerID;
	private String info;
	private String userID;
	private ArrayList<Meal> meals;
	
	public Order(int orderID, String orderDate, String deliveryDate, int customerID, String info, String userID){
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.customerID = customerID;
		this.info = info;
		this.userID = userID;
	}

	public boolean markMealAsReady(int index, Database database) throws Exception{
		return QueryMethods.markMealOrderAsReadyForDelivery(orderID, meals.get(index).getMealID(), deliveryDate, database);
	}
	
	public boolean markMealAsDelivered(int index, Database database) throws Exception{
		return QueryMethods.markMealOrderAsDelivered(orderID, meals.get(index).getMealID(), deliveryDate, database);
	}	
	
	public int getOrderID(){
		return orderID;
	}
	
	public String getOrderDate(){
		return orderDate;
	}
	
	public String getDeliveryDate(){
		return deliveryDate;
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
	
	public boolean updateOrder(int orderID, String orderDate, int newCustomerID, String newInfo, String newUserID, Database database) throws Exception{
		if(QueryMethods.updateOrder(orderID, orderDate, newCustomerID, newInfo, newUserID, database)){
			return true;
		}else {
		return false;
		}
	}
	
	public void addMeal(Meal meal){
		meals.add(meal);
	}
	
	public Meal getMeal(int index){
		if(meals.size() > 0) return meals.get(index);
		else return null;
	}
	
	public Meal[] getMeals(){
		Meal[] temp = new Meal[meals.size()];
		return temp;
	}
	
	public boolean updateOrder(Database database) throws Exception{
		return QueryMethods.updateOrder(orderID, orderDate, customerID, info, userID, database);
	}
	
	public String toString(){
		String res = "";
		res += "OrderID: " + orderID + ". Orderdate: " + orderDate + ". Delivery date: " + deliveryDate
				+ ". Info: " + info + "\n";
		res += "Meals: \n";
		for(Meal m:meals){
			res += "   " + m.toString() + "\n";
		}
		return res;
		
	}
	
}
