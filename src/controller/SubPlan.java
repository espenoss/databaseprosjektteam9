package controller;

import databasePackage.Database;
import databasePackage.QueryMethods;

class SubPlan {
	private int subPlanID;
	private String name;
	private Meal[] meals = new Meal[7]; // [i] = null if no delivery that day
	
	public SubPlan(int subPlanID, String name, Meal[] meals){
		this.subPlanID = subPlanID;
		this.name = name;
		this.meals = meals;
	}
	
	public int getSubPlanID(){
		return subPlanID;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String newName){
		name = newName;
	}
	
	public String getMeals(){
		String res = "";
		for(int i = 0; i < meals.length; i++){
			res += meals.toString();
		}
		return res;
	}
	
	public boolean updateSubPlan(Database database) throws Exception{
		return QueryMethods.updateSubscriptionPlan(subPlanID, name, database);
	}
	
	public void addMealToSubPlan(Meal meal, int index){
		for(int i = 0; i < meals.length; i++){
			if(i==index){
				meal = meals[i];
			}
		}
	}
}
