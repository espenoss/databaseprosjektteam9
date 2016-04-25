package controller;

import database.*;

/**
 * The Class MealOrdered.
 * Represents a specific meal in an order
 */
public class MealOrdered extends Meal {
	
	/** The delivery date. */
	private java.sql.Date deliveryDate;
	
	/** The quantity. */
	private int quantity;
	
	/** The order id. */
	private int orderID;
	
	/** Whether the meal is ready for delivery. */
	private boolean readyDelivery;
	
	/** Whether meal is delivered. */
	private boolean delivered;
	
	/**
	 * Gets the delivery date.
	 *
	 * @return the delivery date
	 */
	public java.sql.Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * Gets the order id.
	 *
	 * @return the order id
	 */
	public int getOrderID() {
		return orderID;
	}

	/**
	 * Checks if meal is ready delivery.
	 *
	 * @return true, if ready delivery
	 */
	public boolean isReadyDelivery() {
		return readyDelivery;
	}

	/**
	 * Checks if meal is delivered.
	 *
	 * @return true, if is delivered
	 */
	public boolean isDelivered() {
		return delivered;
	}
	
	/**
	 * Instantiate a meal order.
	 *
	 * @param mealID The meal id
	 * @param mealName The meal name
	 * @param instructions The instructions
	 * @param price The price
	 * @param deliveryDate The delivery date
	 * @param quantity The quantity
	 * @param orderID The order id
	 * @param readyDelivery Whether meal is ready for delivery
	 * @param delivered Whether meal is delivered
	 */
	public MealOrdered(int mealID, String mealName, String instructions, int price,
			String deliveryDate, int quantity, int orderID, boolean readyDelivery, boolean delivered) {
		super(mealID, mealName, instructions, true, price);
		this.deliveryDate = java.sql.Date.valueOf(deliveryDate);
		this.quantity = quantity;
		this.orderID = orderID;
		this.readyDelivery = readyDelivery;
		this.delivered = delivered;
	}
	
	/**
	 * Instantiate a meal order.
	 * @param meal Meal to attach order information to
	 * @param deliveryDate The delivery date
	 * @param quantity The quantity
	 * @param orderID The order id
	 * @param readyDelivery Whether meal is ready for delivery
	 * @param delivered Whether meal is delivered
	*/
	public MealOrdered(Meal meal,
			String deliveryDate, int quantity, int orderID, boolean readyDelivery, boolean delivered) {
		super(meal.getMealID(), meal.getMealName(), meal.getInstructions(), true, meal.getPrice());
		this.deliveryDate = java.sql.Date.valueOf(deliveryDate);
		this.quantity = quantity;
		this.orderID = orderID;
		this.readyDelivery = readyDelivery;
		this.delivered = delivered;
	}
	
	/**
	 * Gets the deliverydate.
	 *
	 * @return the deliverydate
	 */
	public java.sql.Date getDeliverydate(){
		return deliveryDate;
	}
	
	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity(){
		return quantity;
	}
	 
	
	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	/**
	 * Sets the deliverydate.
	 *
	 * @param deliveryDate the new deliverydate
	 */
	public void setDeliverydate(String deliveryDate){
		this.deliveryDate = java.sql.Date.valueOf(deliveryDate);
	}
	
	/**
	 * Marks meal as ready for delivery to customer, given index of meal in mealArray
	 *
	 * @param database database connection
	 * @return true, if successful
	 */
	public boolean markMealAsReady(Database database) {
		return QMOrder.markMealOrderAsReadyForDelivery(orderID, getMealID(), deliveryDate.toString(), database);
	}
	
	/**
	 * Mark meal as delivered.
	 *
	 * @param database database connection
	 * @return true, if successful
	 */
	//Marks meal as delivered to customer, given index of meal in mealArray
	public boolean markMealAsDelivered(Database database) {
		return QMOrder.markMealOrderAsDelivered(orderID, getMealID(), deliveryDate.toString(), database);
	} 
	
	/**
	 * Upload meal ordered.
	 *
	 * @param database database connection
	 * @return true, if successful
	 */
	public boolean uploadMealOrdered(Database database) {
		String delDate = deliveryDate.toString();
		System.out.println(delDate);
		boolean res = QMOrder.updateMealInOrder(orderID, getMealID(), delDate, quantity, readyDelivery, delivered, database);
		return res;
	}
}
