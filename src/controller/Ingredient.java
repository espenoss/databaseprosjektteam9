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
	
	public void setIngName(String ingName){
		this.ingName=ingName;
	}
	
	public void setStorageQuantity(float quantity){ 
		this.storageQuantity = quantity;
	}
	
	//Register information to database
	public boolean updateStorage(Database database) throws Exception{
		return(QueryMethods.updateIngredient(ingID, ingName, storageQuantity, unit, database));
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient other = (Ingredient) obj;
		if (ingID != other.ingID)
			return false;
		if (ingName == null) {
			if (other.ingName != null)
				return false;
		} else if (!ingName.equals(other.ingName))
			return false;
		if (Float.floatToIntBits(storageQuantity) != Float.floatToIntBits(other.storageQuantity))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}

	public String toString(){
		return "Ingredient: "+ingName+". Quantity: "+storageQuantity +" "+unit;
	}
}
