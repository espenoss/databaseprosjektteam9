package database;

/**
 * The Class QMFood.
 * Query Methods for the food classes, ingredients and meals, and subscription plans (NOT connections between subPlan and order)
 */
public class QMFood {

	/**
	 * Register ingredient.
	 *
	 * @param name Ingredient name
	 * @param quantity Quantity
	 * @param unit Unit(kg, l, etc.)
	 * @param database Database connection
	 * @return Ingredient ID
	 */
	public static int registerIngredient(String name, float quantity, 
			String unit, Database database) {

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

	/**
	 * Update ingredient.
	 *
	 * @param ingredientID Ingredient ID
	 * @param name New ingredient name
	 * @param quantity New quantity
	 * @param unit New unit(kg, l, etc.)
	 * @param database Database connection
	 * @return true, if successful
	 */
	public static boolean updateIngredient(int ingredientID, String name, float quantity, 
			String unit, Database database) {

		String statement = "UPDATE ingredient SET "
				+ "name = " + aq(name)
				+ "quantity = " + quantity + ", "
				+ "unit = '" + unit + "' " 
				+ "WHERE ingredient_id = " + ingredientID
				+ ";";

		return database.makeSingleStatement(statement);
	}


	/**
	 * View list of ingredients. Returns a list of all ingredients in database in a two-dimensional String array
	 * First index is row, second is column<br><br>
	 * <i>Columns by second index:</i><br>
	 * 0 : ingredient_id - int<br>
	 * 1 : name - String<br>
	 * 2 : quantity - int<br>
	 * 3 : unit - String<br>
	 * @param database Database connection
	 * @return String[][] of ingredient info
	 */
	public static String[][] viewIngredients(Database database) {

		String statement = "SELECT * FROM ingredient";

		database.makeSingleStatement(statement);

		return database.getLastResult();				
	}

	/**
	 * Register meal.
	 *
	 * @param mealName Meal name
	 * @param instructions Instructions on how to prepare
	 * @param available Whether the meal is available
	 * @param price Price of meal
	 * @param database Database connection
	 * @return Meal ID
	 */
	public static int registerMeal(String mealName, String instructions, boolean available, int price, 
			Database database) {

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

	/**
	 * Update meal.
	 *
	 * @param mealID Meal ID
	 * @param mealName Meal name
	 * @param instructions Instructions on how to prepare
	 * @param available Whether the meal is available
	 * @param price Price of meal
	 * @param database Database connection
	 * @return true, if successful
	 */
	public static boolean updateMeal(int mealID, String mealName, String instructions, boolean available, int price, 
			Database database) {

		String statement = "UPDATE meal SET "
				+ "meal_name = " + aq(mealName)
				+ "instructions = " + aq(instructions)
				+ "available = " + available + ", "
				+ "price = " + price + " "
				+ "WHERE meal_id =" + mealID 
				+ ";";

		return database.makeSingleStatement(statement);
	}

	/**
	 * View meals.
	 * View list of meals. Returns a list of all meals in database in a two-dimensional String array<br>
	 * First index is row, second is column<br><br>
	 * <i>Columns by second index:</i><br>
	 * 0 : meal_id - int<br>
	 * 1 : meal_name - String<br>
	 * 2 : instructions - String<br>
	 * 3 : available - boolean<br>
	 * 4 : price - int<br>
	 *
	 * @param database Database connection
	 * @return String[][] of meal information
	 */
	// 
	public static String[][] viewMeals(Database database) {

		String statement = "SELECT * FROM meal";

		database.makeSingleStatement(statement);

		return database.getLastResult();
	}	

	/**
	 * Add ingredient to a meal.
	 *
	 * @param mealID Meal id
	 * @param ingredientID Ingredient id
	 * @param ingredientQuantity Ingredient quantity
	 * @param database Database connection
	 * @return true, if successful
	 */
	public static boolean addIngredientToMeal(int mealID, int ingredientID, float ingredientQuantity, 
			Database database) {

		String statement = "INSERT INTO meal_ingredient VALUES(" 
				+ mealID + ", " 
				+ ingredientID + "," 
				+ ingredientQuantity 
				+ ");";
		return database.makeSingleStatement(statement);
	}

	/**
	 * Update ingredient in meal.
	 *
	 * @param mealID Meal id
	 * @param ingredientID Ingredient id
	 * @param quantity Ingredient quantity
	 * @param database Database connection
	 * @return true, if successful
	 */
	public static boolean updateIngredientInMeal(int mealID, int ingredientID, float quantity, 
			Database database) {

		String statement = "UPDATE meal_ingredient SET "
				+ "quantity = " + quantity + " "
				+ "WHERE meal_id =" + mealID + " "
				+ "AND ingredient_id = " + ingredientID
				+ ";";

		return database.makeSingleStatement(statement);
	}


	/**
	 * View ingredients in a meal
	 * Returns list of ingredients in a meal in a two-dimensional String array
	 * First index is row, second is column<br><br>
	 * <i>Columns by second index:</i><br>
	 * 0 : ingredient_id - int<br>
	 * 1 : name - String<br>
	 * 2 : quantity - int (quantity in meal)<br>
	 * 3 : quantity - int (quantity in stock)<br>
	 * 4 : unit - String<br>
	 *
	 * @param mealID Meal id
	 * @param database Database connection
	 * @return String[][] of ingredients
	 */
	public static String[][] viewIngredientsInMeal(int mealID, Database database) {

		String statement = "SELECT a.ingredient_id, b.name, a.quantity, b.quantity, b.unit "
				+ "FROM meal_ingredient AS a, ingredient AS b "
				+ "WHERE a.ingredient_id = b.ingredient_id AND meal_id = " + mealID + ";";		

		database.makeSingleStatement(statement);

		return database.getLastResult();		
	}

	/**
	 * Register subscription plan.
	 *
	 * @param subName Subscription name
	 * @param database Database name
	 * @return Subscription name
	 */
	public static int registerSubscriptionPlan(String subName, Database database) {		
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

	/**
	 * Update subscription plan.
	 *
	 * @param subID Subscription id
	 * @param subName Subscription name
	 * @param database Database name
	 * @return true, if successful
	 */
	public static boolean updateSubscriptionPlan(int subID, String subName, Database database) {		

		String statement = "UPDATE subscription_plan SET "
				+ "sub_name = '" + subName + "' "
				+ "WHERE sub_id = " + subID 
				+";";

		return database.makeSingleStatement(statement);
	}	

	/**
	 * View all subscription plans in database<br>
	 * Returns list of subscription plans in a two-dimensional String array<br>
	 * First index is row, second is column<br><br>
	 * <i>Columns by second index:</i><br>
	 * 0 : sub_id - int<br>
	 * 1 : sub_name - String<br>
	 *
	 * @param database Database connection
	 * @return String[][] of subscription plans
	 */
	public static String[][] viewSubscriptionPlans(Database database) {

		String statement = "SELECT * FROM subscription_plan;";

		database.makeSingleStatement(statement);

		return database.getLastResult();
	}		

	/**
	 * View active subscriptions.
	 *
	 * @param currentDate Current date in YYYY-MM-DD format
	 * @param database Database connection
	 * @return the String[][] of active subscriptions
	 */
	public static String[][] viewActiveSubscriptions(String currentDate, Database database) {

		String statement = "SELECT * FROM sub_order WHERE from_date <= '" + currentDate + "' "
				+ "AND to_date >= '" + currentDate + "'";

		database.makeSingleStatement(statement);

		return database.getLastResult();
	}

	/**
	 * Add meal to subscription plan.
	 *
	 * @param subID Subscription ID
	 * @param mealID Meal id
	 * @param weekdayNr Weekday number (1= monday, 2=tuesday ...)
	 * @param weekday Weekday name (Monday, tuesday ...)
	 * @param database Database connections
	 * @return true, if successful
	 */
	public static boolean addMealToPlan(int subID, int mealID, int weekdayNr, String weekday, Database database) {

		String statement = "INSERT INTO sub_meals_day VALUES(" 
				+ subID + ", " 
				+ mealID + "," 
				+ weekdayNr + ", '" 
				+ weekday 
				+ "');";

		return database.makeSingleStatement(statement);
	}


	/**
	 * Update meal in plan.
	 *
	 * @param subID Subscription ID
	 * @param mealID Meal id
	 * @param weekdayNr Weekday number (1= monday, 2=tuesday ...)
	 * @param weekday Weekday name (Monday, tuesday ...)
	 * @param database Database connections
	 * @return true, if successful
	 */
	public static boolean updateMealInPlan(int subID, int mealID, int weekdayNr, String weekday, Database database) {	

		String statement = "UPDATE sub_meals_day SET "
				+ "weekday_nr = " + weekdayNr + ", "
				+ "weekday = '" + weekday + "' "
				+ "WHERE sub_id = " + subID + " "
				+ "AND meal_id = " + mealID + " "
				+ "AND weekday_nr = " + weekdayNr
				+ ";";

		return database.makeSingleStatement(statement);
	}

	/**
	 * Removes the meal from plan.
	 *
	 * @param subID Subscription ID
	 * @param mealID Meal id
	 * @param weekdayNr Weekday number (1= monday, 2=tuesday ...)
	 * @param database Database connections
	 * @return true, if successful
	 */
	public static boolean removeMealFromPlan(int subID, int mealID, int weekdayNr, Database database) {

		String statement = "DELETE FROM sub_meals_day WHERE "
				+ "sub_id = " + subID + " "
				+ "AND meal_id = " + mealID + " "
				+ "AND weekday_nr = " + weekdayNr
				+ ";";

		return database.makeSingleStatement(statement);
	}

	/**
	 * View all meals in subscription plan in database
	 * Returns list of meals in a two-dimensional String array ordered by weekday
	 * First index is row, second is column
	 * Columns by second index:
	 * 0 : meal_id - int
	 * 1 : meal_name - String
	 * 2 : instructions - String
	 * 3 : available - int/boolean
	 * 4 : price - int
	 * 5 : sub_id - int 
	 * 6 : weekday_nr - int
	 * 7 : weekday - String
	 *
	 * @param subID the sub id
	 * @param database the database
	 * @return the string[][]
	 */
	public static String[][] viewMealsInPlan(int subID, Database database) {

		String statement = "SELECT * FROM meal NATURAL JOIN sub_meals_day WHERE sub_id="+subID+" ORDER BY weekday_nr";		

		database.makeSingleStatement(statement);

		return database.getLastResult();
	}

	/**
	 * 'Add quotes'.<br>
	 * Convenience function to make queries easier to read
	 * Adds single quotes at either end and a comma at the end
	 *
	 * @param s String to add quotes to
	 * @return String with quotes around it
	 */
	private static String aq(String s){
		return "'" + s + "', ";
	}
}

