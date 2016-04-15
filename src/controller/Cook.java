package controller;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import databasePackage.*;

public class Cook extends User {

	public Cook(String userID,int userType, String name,String pword, Database database) {
		super(userID, userType, name, pword, database);
	}
	
	
	//FINISHED is tested
	//Lets the cook register a new meal
	public Meal createMeal(String mealName, String instructions, boolean available, int price) throws Exception{ 
		int mealID = QMFood.registerMeal(mealName, instructions, available, price, database);
		if(mealID < 0){
			return null;
		}
		return new Meal(mealID, mealName, instructions, available, price);
	}
	
	// Finished - lacking method in User before testing
	//Registers new empty subscription plan
	public SubPlan registerSubPlan(String name,Database database) throws Exception{
		int subPlanID = QMFood.registerSubscriptionPlan(name, database);
		
		return new SubPlan(subPlanID, name);
	}
	
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Cook";
		
		return s;
	}
}
