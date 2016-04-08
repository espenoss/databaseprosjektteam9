package controller;
import java.util.ArrayList;

public class Meal {
	private ArrayList<Ingredient> ingredients;
	private int mealID;
	private String mealName;
	private String instructions;
	private int available;
	private int price;
	private ArrayList<Float> ingQuantity;
	private int discount;
	private int discountLimit; // fjerne?
	
	public Meal(int mealID, String mealName, String instructions, boolean available, int price, int discount, int discountLimit){
		this.mealID = mealID;
		this.mealName = mealName;
		this.instructions = instructions;
		this.available = available;
		this.price = price;
		this.discount = discount;
		this.discountLimit = discountLimit;
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
	
	public int getAvailable(){
		return available;
	}
	
	public int getPrice(){
		return price;
	}
	
	public int getDiscount(){
		return discount;
	}
	
	public int getDiscountLimit(){
		return discountLimit;
	}
	
	public String setMealName(String name){
		name = mealName;
		return name;
	}
	
	public String setInstructions(String instr){
		instr = instructions;
		return instr;
	}
	
	public int setDiscount(int newDiscount){
		newDiscount = discount;
		return newDiscount;
	}
	
	public int setDiscountLimit(int newDiscountLimit){
		newDiscountLimit = discountLimit;
		return newDiscountLimit;
	}
	
	public void addIngredients(Ingredient obj, float quantity){
		ingredients.add(obj);	
		ingQuantity.add(quantity);
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
