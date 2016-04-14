package controller;

import databasePackage.*;

public class SubOrder extends Order {
	private SubPlan subPlan = null;
	private String fromDate;
	private String toDate;
	private int subQuantity;			//Portion number
	
	public SubOrder(int orderID, String orderDate, String deliveryDate, int customerID, String info, String userID,
			String fromDate, String toDate, int subQuantity) {
		super(orderID, orderDate, deliveryDate, customerID, info, userID);
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.subQuantity = subQuantity;
	}
	
	//Constructor with only superclass ... 
	public SubOrder(int orderID, String orderDate, String deliveryDate, int customerID, String info, String userID) {
		super(orderID, orderDate, deliveryDate, customerID, info, userID);
	}
	
	public String getFromDate(){
		return fromDate;
	}
	
	public String getToDate(){
		return toDate;
	}
	
	public int getSubQuantity(){
		return subQuantity;
	}
	
	public void setFromDate(String fromDate){
		this.fromDate = fromDate;
	}
	public void setToDate(String toDate){
		this.toDate = toDate;
	}
	public void setSubQuantity(int subQuantity){
		this.subQuantity = subQuantity;
	}
	
	public void setSubPlan(int subPlanID, Database database){
		String[][] subPlanT = QueryMethods.viewSingleSubscriptionPlan(subPlanID, database);  // MÅ LAGES METODE I QUERYMETHODS.(hvis den ikke finnes...)
		
		
		// GJØR OM TIL subPlan object
	}

}
