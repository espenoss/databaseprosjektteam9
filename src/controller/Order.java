package controller;

import databasePackage.Database;

// import java.sql.Date;

public class Order {
	private int orderID;
	private String orderDate; // java.util.Date / java.sql.Date?
	private String deliveryDate;
	private int customerID;
	private String info;
	private String userID;
	
	public Order(int orderID, String orderDate, String deliveryDate, int customerID, String info, String userID){
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
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
	
	public String toString(){
		String res = "";
		res += "OrderID: " + orderID + ". Orderdate: " + orderDate + ". Delivery date: " + deliveryDate
				+ ". Info: " + info;
		return res;
		
	}
	
}
