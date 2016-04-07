package controller;
import databasePackage.*;

public class Ingredient {
	public final int ingID; 
	public String ingName;
	public int quantity;
	
	public Ingredient(int ingID, String ingName, int quantity){
		this.ingID = ingID;
		this.ingName = ingName;
		this.quantity = quantity;
	}
	
	public int getIngID(){
		return ingID;
	}
	
	public String getIngName(){
		return ingName;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void setIngName(String ingName){
		//endre info i databasen her?
		this.ingName=ingName;
	}
	
	public void setQuantity(int quantity){
		//endre info i databasen her? 
		this.quantity = quantity;
	}
}
