package controller;
import databasePackage.*;

public class Cook extends User {

	public Cook(String userID,int userType, String name,String pword, Database database) {
		super(userID, userType, name, pword, database);
	}
	
	public boolean markAsReady(){
		boolean success = false;
		
		// oppdater ordre i database
		
		return success;
	}
	
	public void createMeal(Meal meal) throws Exception{
		// register meal and put it in database
		// opprett tabell 
		int mealID = QueryMethods.registerMeal(meal.getMealName(), meal.getInstructions(), meal.getAvailable(), meal.getPrice(), meal.getDiscount(), meal.getDiscountLimit(), database);
		
	}
	
	public void addIngredient(){
		
	}
	
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Cook";
		
		return s;
	}
	
}
