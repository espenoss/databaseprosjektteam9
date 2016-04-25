package controller;
import java.util.ArrayList;

import database.Database;
import database.QMFood;

/**
 * The Class Meal.
 * Represents entry in 'Meal' database table
 */
public class Meal {
	
	/** The ingredients. 
	 * Lists ingredients (objects) that is used in the meal*/
	private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>(); 
	
	/** The ingredient quantities. 
	 * List ingredient quantity in same order as ingredients*/
	private ArrayList<Float> ingQuantity = new ArrayList<Float>();
	
	/** The meal id. */
	private int mealID;
	
	/** The meal name. */
	private String mealName;
	
	/** The instructions. */
	private String instructions;
	
	/** Whether meal is available. */
	private boolean available;
	
	/** The price. */
	private int price;

	
	/**
	 * Instantiates meal.
	 *
	 * @param mealID The meal id
	 * @param mealName The meal name
	 * @param instructions The instructions
	 * @param available Whether meal is available
	 * @param price The price
	 */
	public Meal(int mealID, String mealName, String instructions, boolean available, int price){
		this.mealID = mealID;
		this.mealName = mealName;
		this.instructions = instructions;
		this.available = available;
		this.price = price;
	}
	
	/**
	 * Gets the meal id.
	 *
	 * @return the meal id
	 */
	public int getMealID(){
		return mealID;
	}
	
	/**
	 * Gets the meal name.
	 *
	 * @return the meal name
	 */
	public String getMealName(){
		return mealName;
	}
	
	/**
	 * Gets the instructions.
	 *
	 * @return the instructions
	 */
	public String getInstructions(){
		return instructions;
	}
	
	/**
	 * Gets the ingredients.
	 *
	 * @return the ingredients
	 */
	public ArrayList<Ingredient> getIngredients(){
		return ingredients;
	}
	
	/**
	 * Gets the ingredient quantities.
	 *
	 * @return the ingredient quantities
	 */
	public ArrayList<Float> getIngQuantity(){
		return ingQuantity;
	}
	
	/**
	 * Checks whether meal is available.
	 *
	 * @return True if available, false if not
	 */
	public boolean getAvailable(){
		return available;
	}
	
	/**
	 * Gets the price.
	 *
	 * @return The price
	 */
	public int getPrice(){
		return price;
	}
	
	/**
	 * Sets the meal name.
	 *
	 * @param name The new meal name
	 */
	public void setMealName(String name){
		name = mealName;
	}
	
	/**
	 * Sets the instructions.
	 *
	 * @param instr The new instructions
	 */
	public void setInstructions(String instr){
		instr = instructions;
	}
	
	
	/**
	 * Fetches ingredient information belonging to this meal from database.
	 *
	 * @param database Database connection
	 * @return true, if successful
	 */ 
	public boolean fetchIngredients(Database database) {
		ingredients = new ArrayList<Ingredient>();
		TextEditor t = new TextEditor();
		// Get ingredeint information from database
		String[][] ingT = QMFood.viewIngredientsInMeal(mealID, database);

		// If there are no ingredients connected to meal
		if (ingT.length==0){
			return false;
		}

		// Convert to ingredient objects
		for (int i=0; i<ingT.length; i++){
			ingredients.add(new Ingredient(t.stringToInt(ingT[i][0]), ingT[i][1], t.stringToFloat(ingT[i][3]),ingT[i][4]));
			ingQuantity.add(t.stringToFloat(ingT[i][2]));
		}
		 
		return true;
	}
	
	/**
	 * Update meal information to database.
	 *
	 * @param database Database connection
	 * @return true, if successful
	 */
	public boolean updateMealToDatabase(Database database) {
		boolean res = QMFood.updateMeal(mealID, mealName, instructions, available, price, database);
		return res;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
