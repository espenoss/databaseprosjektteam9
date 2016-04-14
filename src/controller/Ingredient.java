package controller;
import databasePackage.*;

public class Ingredient {
	public final int ingID; 
	public String ingName;
	public float storageQuantity;
	public String unit;
	
	public Ingredient(int ingID, String ingName, float quantity, String unit){
		this.ingID = ingID;
		this.ingName = ingName;
		this.storageQuantity = quantity;
		this.unit = unit;
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
	
	public boolean updateStorage(Database database) throws Exception{
		return(QueryMethods.updateIngredient(ingID, ingName, storageQuantity, unit, database));
	}
	
	public String toString(){
		return "Ingredient: "+ingName+". Quantity: "+storageQuantity +" "+unit;
	}
}
