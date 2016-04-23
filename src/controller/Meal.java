package controller;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import databasePackage.Database;
import databasePackage.QMFood;

public class Meal {
	private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>(); 	//Lists ingredients (objects) that is used in the meal
	private ArrayList<Float> ingQuantity = new ArrayList<Float>();		//List ingredient quantity in same order as ingredients
	private int mealID;
	private String mealName;
	private String instructions;
	private boolean available;
	private int price;

	
	public Meal(int mealID, String mealName, String instructions, boolean available, int price){
		this.mealID = mealID;
		this.mealName = mealName;
		this.instructions = instructions;
		this.available = available;
		this.price = price;
	}
	
	public int getMealID(){
		return mealID;
	}
	
	public String getMealName(){
		return mealName;
	}
	
	public String getInstructions(){
		return instructions;
	}
	
	public ArrayList<Ingredient> getIngredients(){
		return ingredients;
	}
	public ArrayList<Float> getIngQuantity(){
		return ingQuantity;
	}
	
	public boolean getAvailable(){
		return available;
	}
	
	public int getPrice(){
		return price;
	}
	
	public void setMealName(String name){
		name = mealName;
	}
	
	public void setInstructions(String instr){
		instr = instructions;
	}
	
	
	//FINISHED (Must be tested)
	//Fetches ingredient information from database belonging to this meal. 
	public boolean fetchIngredients(Database database) {
		ingredients = new ArrayList<Ingredient>();
		TextEditor t = new TextEditor();
		String[][] ingT = QMFood.viewIngredientsInMeal(mealID, database);
		
		if (ingT.length==0){
			return false;
		}
		
		for (int i=0; i<ingT.length; i++){
			ingredients.add(new Ingredient(t.stringToInt(ingT[i][0]), ingT[i][1], t.stringToFloat(ingT[i][3]),ingT[i][4]));
			ingQuantity.add(t.stringToFloat(ingT[i][2]));
		}
		 
		return true;
	}
	
	public boolean updateMealToDatabase(Database database) {
		boolean res = QMFood.updateMeal(mealID, mealName, instructions, available, price, database);
		return res;
	}
	
	public String toString(){
		String res = "Meal name: "+mealName;
		
		if(ingredients.size()!=0){
			res += mealName + "\nIngredients: ";
			for(int i=0; i<ingredients.size(); i++){
				res += ingredients.get(i).getIngName() + ". Quantity: " + ingQuantity.get(i) + " " + ingredients.get(i).getUnit();
			}
		}
		return res;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meal other = (Meal) obj;
		if (mealID != other.mealID)
			return false;
		if (mealName == null) {
			if (other.mealName != null)
				return false;
		} else if (!mealName.equals(other.mealName))
			return false;
		return true;
	}


}
