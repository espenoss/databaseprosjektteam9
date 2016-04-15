package controller;

import java.sql.Date;
import java.util.ArrayList;
import databasePackage.*;

// import java.sql.Date;

public class Order {
	private int orderID;
	private String orderDate; // java.util.Date / java.sql.Date?
	private int customerID;
	private String info;
	private String userID;
	private ArrayList<Meal> meals;

	
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
	
	public Meal[] getMeals(){
		Meal[] temp = new Meal[meals.size()];
		return temp;
	}
	
	
	// LAG SET-METODER
	
	public void addMeal(Meal meal){
		meals.add(meal);
	}
	
	//Marks meal as ready for delivery to customer, given index of meal in mealArray
	public boolean markMealAsReady(int index, String deliveryDate, Database database) throws Exception{
		return QueryMethods.markMealOrderAsReadyForDelivery(orderID, meals.get(index).getMealID(), deliveryDate, database);
	}
	
	//Marks meal as delivered to customer, given index of meal in mealArray
	public boolean markMealAsDelivered(int index, String deliveryDate, Database database) throws Exception{
		return QueryMethods.markMealOrderAsDelivered(orderID, meals.get(index).getMealID(), deliveryDate, database);
	}	
	
	//Register information to databasee
	public boolean uploadOrder(Database database) throws Exception{
		return QueryMethods.updateOrder(orderID, orderDate, customerID, info, userID, database);
	}
	
	
	
	//Fetches meals from database
	public void fetchMealsByDeliveryDate(Date deliveryDate, Database database) throws Exception{

		String[][] mealT = QueryMethods.viewMealsInOrderByDeliveryDate(orderID, deliveryDate, database);
		TextEditor t = new TextEditor();
		boolean check= false;
		
		for(int i=0;i<mealT.length;i++){			
			for(int j=0;j<meals.size();j++){		//Checks if meal is in arrayList already, replaces the meal
				if (meals.get(j).getMealID()==t.stringToInt(mealT[i][0])){
					meals.add(j,new Meal(t.stringToInt(mealT[i][0]),mealT[i][1],mealT[i][2],true, t.stringToInt(mealT[i][4])));
					check= true;
				}
			}
			if (!check){							//Adds meal to arrayList if check is false
				meals.add(new Meal(t.stringToInt(mealT[i][0]),mealT[i][1],mealT[i][2],true, t.stringToInt(mealT[i][4])));
			}
			check=false;
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
	
	
	
	//TEST TEST TEST 
	static public void main(String[] arg){
		
	    java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		System.out.println(sqlDate);
		
	}
	
}
