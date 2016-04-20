package controller;

import databasePackage.*;

public class MealOrdered extends Meal {
	private java.sql.Date deliveryDate;
	private int quantity;
	private int orderID;
	private boolean readyDelivery;
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
	
	public boolean uploadMealOrdered(Database database) throws Exception{
		String delDate = deliveryDate.toString();
		boolean res = QMOrder.updateMealInOrder(orderID, getMealID(), delDate, quantity, readyDelivery, delivered, database);
		return res;
	}
}
