package controller;
import databasePackage.*;

public class Ingredient {
	public final int ingID; 
	public String ingName;
	public float storageQuantity;
	public String unit;
	
	public Ingredient(int ingID, String ingName, float quantity){
		this.ingID = ingID;
		this.ingName = ingName;
		this.storageQuantity = quantity;
	}
	
	public int getIngID(){
		return ingID;
	}
	
	public String getIngName(){
		return ingName;
	}
	
	public float getStorageQuantity(){
		return storageQuantity;
	}
	
	public String getUnit(){
		return unit;
	}
	
	public String setIngName(String ingName){
		this.ingName=ingName;
		return ingName;
	}
	
	public float setStorageQuantity(float quantity){ 
		this.storageQuantity = quantity;
		return quantity;
	}
	
	public boolean updateStorage(int ingredientID, String ingName, float quantity, String unit, Database database) throws Exception{
		if(QueryMethods.updateIngredient(ingredientID, setIngName(ingName), setStorageQuantity(quantity), unit, database)){
			return true;
		}else {
			System.out.println("Could not update ingredient");
			return false;
		}
	}
	
	public String toString(){
		return "Ingredient: "+ingName+". Quantity: "+storageQuantity +" "+unit;
	}
}
