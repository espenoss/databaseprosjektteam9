package controller;
import database.*;

public class Cook extends User {

	public Cook(String userID, String name, Database database) {
		super(userID, 1, name,  database);
	}
	
	
	//FINISHED, is tested
	//Lets the cook register a new meal
	public Meal createMeal(String mealName, String instructions, int price) { 
		int mealID = QMFood.registerMeal(mealName, instructions, true, price, database);
		if(mealID < 0){
			return null;
		}
		return new Meal(mealID, mealName, instructions, true, price);
	}
	//FINISHED, is tested
	public boolean addIngredientToMeal(int mealID, int ingredientID, float ingredientQuantity) {
		boolean res = QMFood.addIngredientToMeal(mealID, ingredientID, ingredientQuantity, database);
		return res;
	}
	
	//FINISHED, is tested
	//Registers new empty subscription plan
	public SubPlan registerSubPlan(String name,Database database) {
		int subPlanID = QMFood.registerSubscriptionPlan(name, database);
		
		return new SubPlan(subPlanID, name);
	}
	
	//FINISHED, is tested
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
	
	//FINISHED, is tested
	public boolean removeMealFromPlan(int subID, int mealID, int weekdayNr) {
		return QMFood.removeMealFromPlan(subID, mealID, weekdayNr, database);
		
	}
	
	// Adds/registers new ingredient to database --- NOT TESTED
	public Ingredient addNewIngredient(String name, float quantity, String unit, Database database) {
		int ingredientID = QMFood.registerIngredient(name, quantity, unit, database);
		if(ingredientID<0){
			return null;
		} 
		return new Ingredient(ingredientID, name, quantity, unit);
	}

	
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Cook";
		
		return s;
	}
}
