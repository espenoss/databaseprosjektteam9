package controller;
import database.*;

/**
 * The Class Ingredient.
 * Represents entry in 'ingredient' database table
 */
public class Ingredient {
	
	/** The ingredient id. */
	private final int ingID; 
	
	/** The ingredient name. */
	public String ingName;
	
	/** The storage quantity. */
	private float storageQuantity;
	
	/** Measuring unit (kg, l, ...). */
	private String unit;

	/**
	 * Instantiates ingredient.
	 *
	 * @param ingID The ingredient id
	 * @param ingName The ingredient name
	 * @param quantity Quantity
	 * @param unit Measuring unit (kg, l, ...)
	 */
	public Ingredient(int ingID, String ingName, float quantity, String unit){
		this.ingID = ingID;
		this.ingName = ingName;
		this.storageQuantity = quantity;
		this.unit = unit;
	}

	/**
	 * Gets the ingredient id.
	 *
	 * @return The ingredient id
	 */
	public int getIngID(){
		return ingID;
	}

	/**
	 * Gets the ingredient name.
	 *
	 * @return The ingredient name
	 */
	public String getIngName(){
		return ingName;
	}

	/**
	 * Gets the storage quantity.
	 *
	 * @return The storage quantity
	 */
	public float getStorageQuantity(){
		return storageQuantity;
	}

	/**
	 * Gets measuring unit.
	 *
	 * @return Unit
	 */
	public String getUnit(){
		return unit;
	}

	/**
	 * Sets the ingredient name.
	 *
	 * @param ingName The new ingredient name
	 */
	public void setIngName(String ingName){
		this.ingName=ingName;
	}

	/**
	 * Sets the storage quantity.
	 *
	 * @param quantity The new storage quantity
	 */
	public void setStorageQuantity(float quantity){ 
		this.storageQuantity = quantity;
	}

	/**
	 * Update storage.<br>
	 * Register object information to database.
	 * @param database the database
	 * @return true, if successful
	 */
	public boolean updateStorage(Database database) {
		return(QMFood.updateIngredient(ingID, ingName, storageQuantity, unit, database));
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "Ingredient: "+ingName+". Quantity: "+storageQuantity +" "+unit;
	}

}
