package controller;

import databasePackage.*;

public class MealOrdered extends Meal {
	private String deliveryDate;
	private int quantity;
	private int orderID;
	private boolean readyDelivery;
	private boolean delivered;
	
	public MealOrdered(int mealID, String mealName, String instructions, int price,
			String deliveryDate, int quantity, int orderID, boolean readyDelivery, boolean delivered) {
		super(mealID, mealName, instructions, true, price);
		this.deliveryDate = deliveryDate;
		this.quantity = quantity;
		this.orderID = orderID;
		this.readyDelivery = readyDelivery;
		this.delivered = delivered;
	}
	
	public MealOrdered(Meal meal,
			String deliveryDate, int quantity, int orderID, boolean readyDelivery, boolean delivered) {
		super(meal.getMealID(), meal.getMealName(), meal.getInstructions(), true, meal.getPrice());
		this.deliveryDate = deliveryDate;
		this.quantity = quantity;
		this.orderID = orderID;
		this.readyDelivery = readyDelivery;
		this.delivered = delivered;
	}
	
	public String getDeliverydate(){
		return deliveryDate;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	public void setDeliverydate(String deliveryDate){
		this.deliveryDate = deliveryDate;
	}
	
	public boolean uploadMealOrdered(Database database) throws Exception{
		boolean res = QMOrder.updateMealInOrder(orderID, getMealID(), deliveryDate, quantity, readyDelivery, delivered, database);
		return res;
	}
}
