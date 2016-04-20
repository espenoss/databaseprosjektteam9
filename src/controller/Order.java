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
	
	public Meal[] getMeals(){
		Meal[] temp = new Meal[meals.size()];
		return temp;
	}
	
	public void setOrderDate(String orderDate){
		this.orderDate = orderDate;
	}
	
	public void setInfo(String info){
		this.info = info;
	}
	
	//Marks meal as ready for delivery to customer, given index of meal in mealArray
	public boolean markMealAsReady(int index, String deliveryDate, Database database) throws Exception{
		return QMOrder.markMealOrderAsReadyForDelivery(orderID, meals.get(index).getMealID(), deliveryDate, database);
	}
	
	//Marks meal as delivered to customer, given index of meal in mealArray
	public boolean markMealAsDelivered(int index, String deliveryDate, Database database) throws Exception{
		return QMOrder.markMealOrderAsDelivered(orderID, meals.get(index).getMealID(), deliveryDate, database);
	}	
	
	//Register information to databasee
	public boolean uploadOrder(Database database) throws Exception{
		return QMOrder.updateOrder(orderID, orderDate, customerID, info, userID, database);
	}
	
	public ArrayList<MealOrdered> viewMealsInOrderByDate(java.sql.Date date, Database database) throws Exception{
		fetchMealsInOrder(database);
		ArrayList<MealOrdered> tempMeals = new ArrayList<MealOrdered>();
		
		for (int i=0; i<meals.size();i++){
			System.out.println("Input dato: "+date);
			System.out.println("Obj dato: "+meals.get(i).getDeliverydate());
			
			if (meals.get(i).getDeliverydate().equals(date)){
				tempMeals.add(meals.get(i));
			}
		}
		return tempMeals;
	}
	
	
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
	//(int mealID, String mealName, String instructions, int price,
	//		String deliveryDate, int quantity, int orderID, boolean readyDelivery, boolean delivered)
	
	// 0 : meal_id - int
	// 1 : meal_name - String
	// 2 : instructions - String
	// 3 : available - boolean
	// 4 : price - int
	// 5 : order_id - int 
	// 6 : delivery_date - String
	// 7 : quantity - int 
	// 8 : ready_delivery
	// 9 : delivered
	
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
	
}
