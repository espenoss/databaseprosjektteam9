package controller;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import databasePackage.*;

public class Cook extends User {

	public Cook(String userID, String name, Database database) {
		super(userID, 1, name,  database);
	}
	
	
	//FINISHED is tested
	//Lets the cook register a new meal
	public Meal createMeal(String mealName, String instructions, int price) throws Exception{ 
		int mealID = QMFood.registerMeal(mealName, instructions, true, price, database);
		if(mealID < 0){
			return null;
		}
		return new Meal(mealID, mealName, instructions, true, price);
	}
	
	public boolean addIngredientToMeal(int mealID, int ingredientID, float ingredientQuantity) throws Exception{
		boolean res = QMFood.addIngredientToMeal(mealID, ingredientID, ingredientQuantity, database);
		return res;
	}
	
	// Deletes meal from database. ---  is tested //
	public boolean deleteMeal(int mealID) throws Exception{
		boolean ok = QMFood.removeMeal(mealID, database);
		if(ok){
			return true;
		} else{
		return false;
		}
	}
	
	// Finished - lacking method in User before testing
	//Registers new empty subscription plan
	public SubPlan registerSubPlan(String name,Database database) throws Exception{
		int subPlanID = QMFood.registerSubscriptionPlan(name, database);
		
		return new SubPlan(subPlanID, name);
	}
	
	// FINISHED NOT TESTED
	public boolean addMealToSubPlan(int subID, int mealID, int weekdayNr, String weekday) throws Exception{
		boolean res = QMFood.addMealToPlan(subID, mealID, weekdayNr, weekday, database);
		
		return res;
	}
	
	// FINISHED NOT TESTED
	public boolean removeMealFromPlan(int subID, int mealID, int weekdayNr) throws Exception{
		return QMFood.removeMealFromPlan(subID, mealID, weekdayNr, database);
		
	}
	
	//MÅ LAGES!!
	public boolean registerIngredientDelivery(String orderinfo){
		boolean success = false;
		
		// oppdater database med ny info
		
		return success;
	}
	
	// Adds/registers new ingredient to database --- NOT TESTED
	public Ingredient addNewIngredient(String name, float quantity, String unit, Database database) throws Exception{
		int ingredientID = QMFood.registerIngredient(name, quantity, unit, database);
		if(ingredientID<0){
			return null;
		} 
		return new Ingredient(ingredientID, name, quantity, unit);
	}
	
	// Deletes ingredient from database --- NOT TESTED
	public boolean deleteIngredient(int ingredientID) throws Exception{
		return QMFood.removeIngredient(ingredientID, database);
	}
	
	
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Cook";
		
		return s;
	}
}
