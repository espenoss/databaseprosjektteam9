package controller;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import databasePackage.*;

public class Cook extends User {

	public Cook(String userID,int userType, String name,String pword, Database database) {
		super(userID, userType, name, pword, database);
	}
	
	public boolean createMeal(Meal meal) throws Exception{
		TextEditor t = new TextEditor(); 
		int mealID = QueryMethods.registerMeal(meal.getMealName(), meal.getInstructions(), meal.getAvailable(), meal.getPrice(), meal.getDiscount(), meal.getDiscountLimit(), database);
		if(mealID < 0){
			JOptionPane.showMessageDialog(null,"Something went wrong, please try again");
			return false;
		} 
		
		ArrayList<Ingredient> ingredient = meal.getIngredients();
		for(int i = 0; i < ingredient.size(); i++){
			if(!QueryMethods.addIngredientToMeal(mealID, ingredient.get(i).getIngID(), t.StringToFloat(JOptionPane.showInputDialog("Ingredient quantity: ")), database)){
			// lagt til metode for håndtering av tekst til float
				JOptionPane.showMessageDialog(null, "Could not register ingredient "+ ingredient.get(i).getIngName());
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Cook";
		
		return s;
	}
	
}
