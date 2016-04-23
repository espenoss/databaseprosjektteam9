package controller;

import databasePackage.*;

public class MealOrdered extends Meal {
	private java.sql.Date deliveryDate;
	private int quantity;
	private int orderID;
	private boolean readyDelivery;
	public java.sql.Date getDeliveryDate() {
		return deliveryDate;
	}

	public int getOrderID() {
		return orderID;
	}

	public boolean isReadyDelivery() {
		return readyDelivery;
	}

	public boolean isDelivered() {
		return delivered;
	}

	private boolean delivered;
	
	public MealOrdered(int mealID, String mealName, String instructions, int price,
			String deliveryDate, int quantity, int orderID, boolean readyDelivery, boolean delivered) {
		super(mealID, mealName, instructions, true, price);
		this.deliveryDate = java.sql.Date.valueOf(deliveryDate);
		this.quantity = quantity;
		this.orderID = orderID;
		this.readyDelivery = readyDelivery;
		this.delivered = delivered;
	}
	
	public MealOrdered(Meal meal,
			String deliveryDate, int quantity, int orderID, boolean readyDelivery, boolean delivered) {
		super(meal.getMealID(), meal.getMealName(), meal.getInstructions(), true, meal.getPrice());
		this.deliveryDate = java.sql.Date.valueOf(deliveryDate);
		this.quantity = quantity;
		this.orderID = orderID;
		this.readyDelivery = readyDelivery;
		this.delivered = delivered;
	}
	
	public java.sql.Date getDeliverydate(){
		return deliveryDate;
	}
	
	public int getQuantity(){
		return quantity;
	}
	 
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	public void setDeliverydate(String deliveryDate){
		this.deliveryDate = java.sql.Date.valueOf(deliveryDate);
	}
	
	//Marks meal as ready for delivery to customer, given index of meal in mealArray
	public boolean markMealAsReady(Database database) {
		return QMOrder.markMealOrderAsReadyForDelivery(orderID, getMealID(), deliveryDate.toString(), database);
	}
	
	//Marks meal as delivered to customer, given index of meal in mealArray
	public boolean markMealAsDelivered(Database database) {
		return QMOrder.markMealOrderAsDelivered(orderID, getMealID(), deliveryDate.toString(), database);
	} 
	
	public boolean uploadMealOrdered(Database database) {
		String delDate = deliveryDate.toString();
		System.out.println(delDate);
		boolean res = QMOrder.updateMealInOrder(orderID, getMealID(), delDate, quantity, readyDelivery, delivered, database);
		return res;
	}
}
