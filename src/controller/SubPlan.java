package controller;

import databasePackage.Database;
import databasePackage.QMFood;

/* Subscription plan
 * A set plan made by the cook 
 * Can contain different meals Mon - Sun
 * 
 * for example lunch delivery mon - fri
 * or dinner delivery mon/wed/fri/sun 
 * etc
 * 
 * Days set as "null" has no delivery 
 * Customers can subscribe to a plan, but not change it
 */

public class SubPlan {
	private int subPlanID;
	private String name;
	private Meal[] meals = new Meal[7]; // [i] = null if no delivery that day
	
	public SubPlan(int subPlanID, String name){
		this.subPlanID = subPlanID;
		this.name = name;
	}
	
	public int getSubPlanID(){
		return subPlanID;
	}
	
	public String getName(){
		return name;
	}
	
	public Meal[] getMeals(){
		return meals;
	}
	
	public void setName(String newName){
		name = newName;
	}
	
	//Lists subsriction plan 
	public String getMealsAsText(){
		String res = "";
		for(int i = 0; i < meals.length; i++){
			res += meals.toString();
		}
		return res;
	}

	//Adds new meal to subscription plan, given a weekday where mon = 1 and sun = 7
	public boolean addMealToSubPlan(Meal meal, int weekdayNumber){
		if (weekdayNumber<=meals.length){
			meals[weekdayNumber-1]=meal;
			return true;
		}
		return false;
	}
	
	//Removes meal from plan
	public boolean removeMealFromPlan(int weekdayNumber){
		if (weekdayNumber<=meals.length){
			meals[weekdayNumber-1]=null;
			return true;
		}
		return false;
	}
	
	//Registers information to database
	public boolean updateSubPlan(Database database) throws Exception{
		return QMFood.updateSubscriptionPlan(subPlanID, name, database);
	}
}
