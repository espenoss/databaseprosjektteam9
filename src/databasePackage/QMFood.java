package databasePackage;

//Query Methods for the food classes, ingredients and meals, and subscription plans (NOT connections between subPlan and  order)

public class QMFood {
	
	public static int registerIngredient(String name, float quantity, 
			String unit, Database database) throws Exception{
		
		for(int i=0; i<5; i++){
			String statement = "SELECT MAX(ingredient_id) FROM ingredient;";
			database.makeSingleStatement(statement);
			
			String highestID = database.getLastResult()[0][0];
			int ingredientID = 1;			
			if(highestID != null){
				ingredientID = Integer.parseInt(highestID) + 1;
			}
			
			statement = "INSERT INTO ingredient VALUES(" 
					+ ingredientID + ","
					+ aq(name) 
					+ quantity + ", "
					+ "'" + unit 
					+ "');";
			if(database.makeSingleStatement(statement)) return ingredientID;
		}
		
		return -1;		

	}
	
	public static boolean updateIngredient(int ingredientID, String name, float quantity, 
			String unit, Database database) throws Exception{
		
		String statement = "UPDATE ingredient SET "
				+ "name = " + aq(name)
				+ "quantity = " + quantity + ", "
				+ "unit = '" + unit + "' " 
				+ "WHERE ingredient_id = " + ingredientID
				+ ";";
		
		return database.makeSingleStatement(statement);
	}
	
	public static boolean removeIngredient(int ingredientID, Database database) throws Exception{
		
		String statement = "DELETE FROM ingredient WHERE ingredient_id = " + ingredientID + ";";
		
		return database.makeSingleStatement(statement);
	}
	
	// View list of ingredients. Returns a list of all ingredients in database in a two-dimensional String array
	// First index is row, second is column
	// Columns:
	// 0 : ingredient_id - int
	// 1 : name - String
	// 2 : quantity - int
	// 3 : unit - String
	public static String[][] viewIngredients(Database database) throws Exception{
		
		String statement = "SELECT * FROM ingredient";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();				
	}

	public static int registerMeal(String mealName, String instructions, boolean available, int price, 
			Database database) throws Exception{

		for(int i=0; i<5; i++){
			String statement = "SELECT MAX(meal_id) FROM meal;";
			database.makeSingleStatement(statement);

			String highestID = database.getLastResult()[0][0];
			int mealID = 1;			
			if(highestID != null){
				mealID = Integer.parseInt(highestID) + 1;
			}			
			
			statement = "INSERT INTO meal VALUES(" 
					+ mealID + ", "
					+ aq(mealName) 
					+ aq(instructions) 
					+ available + ", " 
					+ price 					
					+ ");";
			
			if(database.makeSingleStatement(statement)) return mealID;
		}
		
		return -1;		
	}
	
	public static boolean updateMeal(int mealID, String mealName, String instructions, boolean available, int price, 
			Database database) throws Exception{
		
		String statement = "UPDATE meal SET "
				+ "meal_name = " + aq(mealName)
				+ "instructions = " + aq(instructions)
				+ "available = " + available + ", "
				+ "price = " + price + " "
				+ "WHERE meal_id =" + mealID 
				+ ";";
		
		return database.makeSingleStatement(statement);
	}

	public static boolean removeMeal(int mealID, Database database) throws Exception{
		
		String statement = "DELETE FROM meal WHERE meal_id = " + mealID + ";";
		
		return database.makeSingleStatement(statement);
	}

	// View list of meals. Returns a list of all meals in database in a two-dimensional String array
	// First index is row, second is column
	// Columns:
	// 0 : meal_id - int
	// 1 : meal_name - String
	// 2 : instructions - String
	// 3 : available - boolean
	// 4 : price - int
	// 5 : discount - int
	// 6 : discount_lim - int
	public static String[][] viewMeals(Database database) throws Exception{
		
		String statement = "SELECT * FROM meal";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}	
	
	public static boolean addIngredientToMeal(int mealID, int ingredientID, float ingredientQuantity, 
			Database database) throws Exception{
				
		String statement = "INSERT INTO meal_ingredient VALUES(" 
				+ mealID + ", " 
				+ ingredientID + "," 
				+ ingredientQuantity 
				+ ");";
		return database.makeSingleStatement(statement);
	}
	
	public static boolean updateIngredientInMeal(int mealID, int ingredientID, float quantity, 
			Database database) throws Exception{
		
		String statement = "UPDATE meal_ingredient SET "
				+ "quantity = " + quantity + " "
				+ "WHERE meal_id =" + mealID + " "
				+ "AND ingredient_id = " + ingredientID
				+ ";";
		
		return database.makeSingleStatement(statement);
	}
	
	public static boolean removeIngredientFromMeal(int mealID, int ingredientID, Database database) throws Exception{
		
		String statement = "DELETE FROM meal_ingredient WHERE "
				+ "meal_id =" + mealID + " "
				+ "AND ingredient_id = " + ingredientID
				+ ";";
		
		return database.makeSingleStatement(statement);
	}

	// View ingredients in a meal
	// Returns list of ingredients in a meal in a two-dimensional String array
	// First index is row, second is column
	// Columns by second index:
	// 0 : ingredient_id - int
	// 1 : name - String
	// 2 : quantity - int (quantity in meal)
	// 3 : quantity - int (qunatity in stock)
	// 4 : unit - String
	public static String[][] viewIngredientsInMeal(int mealID, Database database) throws Exception{
		
		String statement = "SELECT a.ingredient_id, b.name, a.quantity, b.quantity, b.unit "
				+ "FROM meal_ingredient AS a, ingredient AS b "
				+ "WHERE a.ingredient_id = b.ingredient_id AND meal_id = " + mealID + ";";		
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();		
	}

	// Register new zone in database

	// Register new subscription plan in database
	public static int registerSubscriptionPlan(String subName, Database database) throws Exception{		
		for(int i=0; i<5; i++){
			String statement = "SELECT MAX(sub_id) FROM subscription_plan;";
			database.makeSingleStatement(statement);
			
			String highestID = database.getLastResult()[0][0];
			int subID = 1;			
			if(highestID != null){
				subID = Integer.parseInt(highestID) + 1;
			}
			
			statement = "INSERT INTO subscription_plan VALUES("
					+ subID + ", '" 
					+ subName 
					+ "')";
			if(database.makeSingleStatement(statement)) return subID;
		}
		
		return -1;
	}

	// Update subscription plan info
	public static boolean updateSubscriptionPlan(int subID, String subName, Database database) throws Exception{		
		
		String statement = "UPDATE subscription_plan SET "
				+ "sub_name = '" + subName + "' "
				+ "WHERE sub_id = " + subID 
				+";";
		
		return database.makeSingleStatement(statement);
	}
	
	// Remove subscription from database
	public static boolean removeSubscriptionPlan(int subID, Database database) throws Exception{		
		
		String statement = "DELETE FROM subscription_plan WHERE sub_id = " + subID + ";";
		
		return database.makeSingleStatement(statement);
	}	
	
	// View all subscription plans in database
	// Returns list of subscription plans in a two-dimensional String array
	// First index is row, second is column
	// Columns by second index:
	// 0 : sub_id - int
	// 1 : sub_name - String
	public static String[][] viewSubscriptionPlans(Database database) throws Exception{

		String statement = "SELECT * FROM subscription_plan;";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}		
	
	public static String[][] viewActiveSubscriptions(String currentDate, Database database) throws Exception{
		
		String statement = "SELECT * FROM sub_order WHERE from_date <= '" + currentDate + "' "
				+ "AND to_date >= '" + currentDate + "'";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}
	
	public static boolean addMealToPlan(int subID, int mealID, int weekdayNr, String weekday, Database database) throws Exception{
		
		String statement = "INSERT INTO sub_meals_day VALUES(" 
				+ subID + ", " 
				+ mealID + "," 
				+ weekdayNr + ", '" 
				+ weekday 
				+ "');";
			
		return database.makeSingleStatement(statement);
	}
	
	
	public static boolean updateMealInPlan(int subID, int mealID, int weekdayNr, String weekday, Database database) throws Exception{	
		
		String statement = "UPDATE sub_meals_day SET "
				+ "weekday_nr = " + weekdayNr + ", "
				+ "weekday = '" + weekday + "' "
				+ "WHERE sub_id = " + subID + " "
				+ "AND meal_id = " + mealID + " "
				+ "AND weekday_nr = " + weekdayNr
				+ ";";
		
		return database.makeSingleStatement(statement);
	}
	
	public static boolean removeMealFromPlan(int subID, int mealID, int weekdayNr, Database database) throws Exception{
		
		String statement = "DELETE FROM sub_meals_day WHERE "
		+ "sub_id = " + subID + " "
		+ "AND meal_id = " + mealID + " "
		+ "AND weekday_nr = " + weekdayNr
		+ ";";
		
		return database.makeSingleStatement(statement);
	}
	
	

	 
	// View all meals in subscription plan in database
	// Returns list of meals in a two-dimensional String array ordered by weekday
	// First index is row, second is column
	// Columns by second index:
	// 0 : meal_id - int
	// 1 : meal_name - String
	// 2 : instructions - String
	// 3 : available - int/boolean
	// 4 : price - int
	// 5 : sub_id - int 
	// 6 : weekday_nr - int
	// 7 : weekday - String
	 
	public static String[][] viewMealsInPlan(int subID, Database database) throws Exception{

		String statement = "SELECT * FROM meal NATURAL JOIN sub_meals_day WHERE sub_id="+subID+" ORDER BY weekday_nr";		
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}
	
	// Puts a ' on either side and a comma at the end  of a string 
		public static String aq(String s){
			return "'" + s + "', ";
		}
	
}

