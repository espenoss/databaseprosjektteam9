package controller;
import java.util.ArrayList;

import databasePackage.Database;
import databasePackage.QMFood;

public class Meal {
	private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>(); 	//Lists ingredients (objects) that is used in the meal
	private ArrayList<Float> ingQuantity = new ArrayList<Float>();		//List ingredient quantity in same order as ingredients
	private int mealID;
	private int orderID; //?? trenger vi dette?
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
	
	
	public void setMealName(String name){
		name = mealName;
	}
	
	public void setInstructions(String instr){
		instr = instructions;
	}
	
	// trenger vi disse??? 
	public void setOrderID(int orderID){
		this.orderID = orderID;
	}
	
	public int getOrderID(){
		return orderID;
	}
	

	public void addIngredients(Ingredient obj, float quantity){
		ingredients.add(obj);	
		ingQuantity.add(quantity);
	}
	
	
	//FINISHED (Must be tested)
	//Fetches ingredient information from database belonging to this meal. 
	public void fetchIngredients(Database database) throws Exception{
		String[][] ingT = QMFood.viewIngredientsInMeal(mealID, database);
		TextEditor t = new TextEditor();
		
		for (int i=0;i<ingT.length;i++){
			ingredients.add(new Ingredient(t.stringToInt(ingT[i][0]), ingT[i][1], t.stringToFloat(ingT[i][3]),ingT[i][4]));
			ingQuantity.add(t.stringToFloat(ingT[i][2]));
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
