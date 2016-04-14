package controller;
import java.util.ArrayList;

import databasePackage.Database;
import databasePackage.QueryMethods;

public class Meal {
	private ArrayList<Ingredient> ingredients; 	//Lists ingredients (objects) that is used in the meal
	private ArrayList<Float> ingQuantity;		//List ingredient quantity in same order as ingredients
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
	
	public boolean getAvailable(){
		return available;
	}
	
	public int getPrice(){
		return price;
	}
	
	
	public String setMealName(String name){
		name = mealName;
		return name;
	}
	
	public String setInstructions(String instr){
		instr = instructions;
		return instr;
	}
	

	public void addIngredients(Ingredient obj, float quantity){
		ingredients.add(obj);	
		ingQuantity.add(quantity);
	}
	
	//Fetches ingredient information from database belonging to this meal. 
	public void fetchIngredients(Database database) throws Exception{
		String[][] ingT = QueryMethods.viewIngredientsInMeal(mealID, database);
		TextEditor t = new TextEditor();
		
		for (int i=0;i<ingT.length;i++){
			ingredients.add(new Ingredient(t.StringToInt(ingT[i][0]), ingT[i][1], t.StringToFloat(ingT[i][3])));
			ingQuantity.add(t.StringToFloat(ingT[i][0]));
		}
	}
	
	
	public String toString(){
		String res = "Meal name: ";
		res += mealName + "\nIngredients: ";
		for(int i=0; i<ingredients.size(); i++){
			res += ingredients.get(i).getIngName() + ". Quantity: " + ingQuantity.get(i) + " " + ingredients.get(i).getUnit();
		}
		return res;
	}
}
