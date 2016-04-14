package controller;
import databasePackage.*;

public class Ingredient {
	public final int ingID; 
	public String ingName;
	public int storageQuantity;
	public String unit;
	
	public Ingredient(int ingID, String ingName, int quantity){
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
	
	public int getStorageQuantity(){
		return storageQuantity;
	}
	
	public String getUnit(){
		return unit;
	}
	
	public void setIngName(String ingName){
		this.ingName=ingName;
	}
	
	public void setStorageQuantity(int quantity){ 
		this.storageQuantity = quantity;
	}
	
	public boolean updateStorage(){
		// hvis ingrediensen eksisterer, oppdater antall i databasen
		// dersom den ikke eksisterer, registrer ny ingrediens
		//boolean updateIngredient(int ingredientID, String name, float quantity, String unit, Database database)
		// int registerIngredient(String name, float quantity, String unit, Database database)	
	}
	
	public String toString(){
		return "Ingredient: "+ingName+". Quantity: "+storageQuantity +" "+unit;
	}
}
