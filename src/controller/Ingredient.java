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
	
	public int getQuantity(){
		return storageQuantity;
	}
	
	public String getUnit(){
		return unit;
	}
	
	public void setIngName(String ingName){
		//endre info i databasen her?
		this.ingName=ingName;
	}
	
	public void setQuantity(int quantity){
		//endre info i databasen her? 
		this.storageQuantity = quantity;
	}
	
	public String toString(){
		return "Ingredient: "+ingName+". Quantity: "+storageQuantity +" "+unit;
	}
}
