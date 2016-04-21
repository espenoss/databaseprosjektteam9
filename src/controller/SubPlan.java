package controller;

import databasePackage.Database;
import databasePackage.QMFood;

/* Subscription plan
 * A set plan made by the cook 
 * Can contain different meals Mon - Sun
 * 
 * for example lunch delivery mon - fri
 * or dinner delivery mon/wed/fri/sun 
 * etc
 * 
 * Days set as "null" has no delivery 
 * Customers can subscribe to a plan, but not change it
 */

public class SubPlan {
	private int subPlanID;
	private String name;
	private Meal[] meals = new Meal[7]; // [i] = null if no delivery that day
	
	public SubPlan(int subPlanID, String name){
		this.subPlanID = subPlanID;
		this.name = name;
	}
	
	public int getSubPlanID(){
		return subPlanID;
	}
	
	public String getName(){
		return name;
	}
	
	public Meal[] getMeals(){
		return meals;
	}
	
	public void setName(String newName){
		name = newName;
	}
	
	//Lists subsriction plan 
	public String getMealsAsText(){
		String res = "";
		for(int i = 0; i < meals.length; i++){
			res += meals.toString();
		}
		return res;
	}

	//Adds new meal to subscription plan, given a weekday where mon = 1 and sun = 7
	public boolean addMealToSubPlan(Meal meal, int weekdayNumber){
		if (weekdayNumber<=meals.length){
			meals[weekdayNumber-1]=meal;
			return true;
		}
		return false;
	}
	
	//Removes meal from plan
	public boolean removeMealFromPlan(int weekdayNumber){
		if (weekdayNumber<=meals.length){
			meals[weekdayNumber-1]=null;
			return true;
		}
		return false;
	}
	
	public void fetchMealsInPlan(Database database) throws Exception{
		String[][] mealT = QMFood.viewMealsInPlan(subPlanID, database);
		Meal tempMeal;
		TextEditor t = new TextEditor();
		
		for (int i=0; i<mealT.length; i++){
			tempMeal = new Meal(t.stringToInt(mealT[i][0]), mealT[i][1], mealT[i][2], true, t.stringToInt(mealT[i][4]));
			meals[t.stringToInt(mealT[i][6])-1] = tempMeal;
		}
	}
	
	//Registers information to database
	public boolean updateSubPlan(Database database) throws Exception{
		return QMFood.updateSubscriptionPlan(subPlanID, name, database);
	}
	
	public String toString(){
		String res = "Subscription plan ID: "+ subPlanID+ ", name: "+ name;
		for (int i=0; i<7; i++){
			if(meals[i]!= null){
				res+="\nWeekday nr: "+(i+1)+", meal: "+meals[i].getMealName();
			}
			
		}
		res+="\n";
		return res;
	}
	
	public static void main(String[] args) throws Exception{
		String username = "marith1";
		String password = "tgp8sBZA";
		String databaseName = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		String databaseDriver = "com.mysql.jdbc.Driver";
		Database database = new Database(databaseDriver, databaseName);
		
		
		SubPlan testSub = new SubPlan(1, "Lunsj");
		
		testSub.fetchMealsInPlan(database);
		
		System.out.println(testSub);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubPlan other = (SubPlan) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subPlanID != other.subPlanID)
			return false;
		return true;
	}
}
