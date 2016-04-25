package controller;
import database.*;
/**
 * The Class Cook.
 * Contains all methods exclusive to the cook usertype
 */
public class Cook extends User {

	/**
	 * Instantiates a new cook.
	 *
	 * @param userID User id
	 * @param name Name of user
	 * @param database Database connection
	 */
	public Cook(String userID, String name, Database database) {
		super(userID, 1, name,  database);
	}
	
	/**
	 * Registers new meal in database.
	 *
	 * @param mealName Meal name
	 * @param instructions Preparation instructions
	 * @param price Price
	 * @return Meal object
	 */
	public Meal createMeal(String mealName, String instructions, int price) { 
		int mealID = QMFood.registerMeal(mealName, instructions, true, price, database);
		if(mealID < 0){
			return null;
		}
		return new Meal(mealID, mealName, instructions, true, price);
	}
	
	/**
	 * Adds ingredient to meal.
	 *
	 * @param mealID Meal id
	 * @param ingredientID Ingredient id
	 * @param ingredientQuantity Ingredient quantity
	 * @return true, if successful
	 */
	public boolean addIngredientToMeal(int mealID, int ingredientID, float ingredientQuantity) {
		boolean res = QMFood.addIngredientToMeal(mealID, ingredientID, ingredientQuantity, database);
		return res;
	}
	
	/**
	 * Registers new empty subscription plan.
	 *
	 * @param name Name of subscription plan	 
	 * @return SubPlan object
	 */
	public SubPlan registerSubPlan(String name) {
		int subPlanID = QMFood.registerSubscriptionPlan(name, database);
		
		return new SubPlan(subPlanID, name);
	}
	
	/**
	 * Adds a meal to a subscription plan.
	 *
	 * @param subID the sub id
	 * @param mealID the meal id
	 * @param weekdayNr the weekday nr
	 * @return true, if successful
	 */
	public boolean addMealToSubPlan(int subID, int mealID, int weekdayNr) {
		String weekday="";
		if (weekdayNr<1 || weekdayNr>7){
			return false;
		}
		if (weekdayNr==1){weekday="Monday";}
		else if (weekdayNr==2){weekday="Tuesday";}
		else if (weekdayNr==3){weekday="Wednesday";}
		else if (weekdayNr==4){weekday="Thursday";}
		else if (weekdayNr==5){weekday="Friday";}
		else if (weekdayNr==6){weekday="Saturday";}
		else if (weekdayNr==7){weekday="Sunday";}
		
		boolean res = QMFood.addMealToPlan(subID, mealID, weekdayNr, weekday, database);
		return res;
	}
	
	/**
	 * Removes a meal from subscription plan.
	 *
	 * @param subID Sub id
	 * @param mealID Meal id
	 * @param weekdayNr Weekday number (1=Monday, 2=Tuesday ...)
	 * @return true, if successful
	 */
	public boolean removeMealFromPlan(int subID, int mealID, int weekdayNr) {
		return QMFood.removeMealFromPlan(subID, mealID, weekdayNr, database);
		
	}
	
	/**
	 * Adds new ingredient to  database.
	 *
	 * @param name Ingredient name
	 * @param quantity Quantity
	 * @param unit Unit(kg, l, ...)
	 * @return the ingredient
	 */
	// Adds/registers new ingredient to database --- NOT TESTED
	public Ingredient addNewIngredient(String name, float quantity, String unit) {
		int ingredientID = QMFood.registerIngredient(name, quantity, unit, database);
		if(ingredientID<0){
			return null;
		} 
		return new Ingredient(ingredientID, name, quantity, unit);
	}

	
	/* (non-Javadoc)
	 * @see controller.User#toString()
	 */
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Cook";
		
		return s;
	}
}
