package databasePackage;

public class QueryMethods {
	
	// User types
	public static final int U_ADMIN = 0;
	public static final int U_COOK = 1;
	public static final int U_DRIVER = 2;
	public static final int U_SALES = 3;
	public static final int U_STORAGE = 4;
	
	// Puts a ' on either side and a comma at the end  of a string 
	public static String aq(String s){
		return "'" + s + "', ";
	}
	
	// Method for registering new user in database
	public static boolean registerUser(String userID, int userType, String name, String password, Database database) throws Exception{
		
		String statement = "INSERT INTO user VALUES(" 
				+ aq(userID) 
				+ userType + ", '" 
				+ name + "', '" 
				+ password 
				+ "');";

		return database.makeSingleStatement(statement);
	}

	// Update user information
	public static boolean updateUser(String userID, int userType, String name, String password, Database database) throws Exception{
		String statement = "UPDATE user SET "
				+ "user_id =" + aq(userID)
				+ "user_type =" + userType + ","
				+ "name = " + aq(name)
				+ "password = '" + password + "' " 
				+ "WHERE user_id = '" + userID + "';";
		return database.makeSingleStatement(statement);
	}
	
	// Delete user entry from database
	public static boolean removeUser(String userID, Database database) throws Exception{
		String statement = "DELETE FROM user WHERE user_id = '" + userID + "';";
		return database.makeSingleStatement(statement);
	}

	// View single user
	// Returns information on user in database in a String array
	// Columns by second index:
	// 0 : user_id - String
	// 1 : user_type - int
	// 2 : name - String
	// 3 : password - String
	public static String[] viewUser(String userID, Database database) throws Exception{
		String statement = "SELECT user_id, user_type, name FROM user WHERE user_id = '" + userID + "';";
		System.out.println(statement);		
		database.makeSingleStatement(statement);
		return database.getLastResult()[0];
	}	
	
	// View list of all users. 
	// Returns a list of all users in database in a two-dimensional String array
	// First index is row, second is column
	// Columns by second index:
	// 0 : user_id - String
	// 1 : user_type - int
	// 2 : name - String
	// 3 : password - String
	public static String[][] viewAllUsers(Database database) throws Exception{		
		database.makeSingleStatement("SELECT user_id, user_type, name FROM user");
		
		return database.getLastResult();		
	}	

	// Confirms user details
	public static int logIn(String userID, char[] password, Database database) throws Exception{
		
		String[][] userType = null;
		
		database.makeSingleStatement("SELECT user_type FROM user WHERE user_id = '" + userID + "' AND password ='" + password.toString() + "'");	
		
		userType = database.getLastResult();
		
		if(userType == null){ // If no such user or password is incorrect
			return -1;
		}else{
			return Integer.parseInt(userType[0][0]);
		}
	}

	// Register new customer in database
	public static int registerCustomer(String surName, String firstName, String phoneNumber, String email, String adress, 
			int zip_code, int zone_nr, String preferences, boolean active, Database database) throws Exception{
		
		// Attempt to generate new ID five times
		for(int i=0; i<5; i++){
			// Fetch highest currrent ID
			String statement = "SELECT MAX(customer_id) FROM customer;";
			database.makeSingleStatement(statement);

			// If no current entries, ID will be 10000			
			String highestID = database.getLastResult()[0][0];
			int customerID = 10000;
			if(highestID != null){
				customerID = Integer.parseInt(highestID) + 1;
			}

			// Insert entry into database, return generated ID
			statement = "INSERT INTO customer VALUES(" 
					+ customerID + ", " 
					+ aq(surName) 
					+ aq(firstName) 
					+ aq(phoneNumber) 
					+ aq(email) 
					+ aq(adress)
					+ zip_code + ", " 
					+ zone_nr + ", " 
					+ aq(preferences) 
					+ active + ");";
			if(database.makeSingleStatement(statement)) return customerID;
		}
				
		return -1;
	}

	// Update customer information
	public static boolean updateCustomer(int customerID, String surName, String firstName, String phoneNumber, String email, String adress, 
			int zipCode, int zoneNr, String preferences, boolean active, Database database) throws Exception{
			
		String statement = "UPDATE customer SET "
				+ "surname =" + aq(surName)
				+ "firstname =" + aq(firstName)
				+ "phone_number =" + aq(phoneNumber)
				+ "email =" + aq(email)
				+ "adress =" + aq(adress)
				+ "zip_code =" + zipCode + ","
				+ "zone_nr =" + zoneNr + ","
				+ "preferences =" + aq(preferences)
				+ "active =" + active
				+ " WHERE customer_id =" + customerID
				+ ";";
		
		return database.makeSingleStatement(statement);
	}	
	
	public static boolean removeCustomer(int customerID, Database database) throws Exception{
		
		String statement ="DELETE FROM customer WHERE customer_id ='" + customerID + "';";
		
		return database.makeSingleStatement(statement);
	}
	
	// View single customer information. 
	// Returns a customer information in a String array
	// Columns by index:
	// 0 : customer_id - int
	// 1 : surname - String
	// 2 : firstname - String
	// 3 : phone_number - String
	// 4 : email - String
	// 5 : adress - String
	// 6 : zip_code - int
	// 7 : zone_nr - int
	// 8 : preferences - String
	// 9 : active - boolean
	public static String[] viewCustomer(int customerID, Database database) throws Exception{		
		String statement = "SELECT * FROM customer WHERE customer_id ="
				+ customerID + ";";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult()[0];
	}	

	// View list of all customer information. 
	// Returns a list of all customers in database in a two-dimensional String array
	// First index is row, second is column
	// Columns by second index:
	// 0 : customer_id - int
	// 1 : surname - String
	// 2 : firstname - String
	// 3 : phone_number - String
	// 4 : email - String
	// 5 : adress - String
	// 6 : zip_code - int
	// 7 : zone_nr - int
	// 8 : preferences - String
	// 9 : active - boolean
	public static String[][] viewAllCustomers(Database database) throws Exception{		
		database.makeSingleStatement("SELECT * FROM customer");
		
		return database.getLastResult();  //Denne metoden fungerer ikke som den skal
	}

	// Register company in database. Needs ID of exissting customer to succeed
	public static boolean registerCompanyToCustomer(int customerID, String companyName, Database database) throws Exception{				
		String statement = "INSERT INTO company VALUES("
				+ customerID + ",'" 
				+ companyName 
				+ "');";		
		
		return database.makeSingleStatement(statement);
	}

	// Update customer info in database
	public static boolean updateCompany(int customerID, String companyName, Database database) throws Exception{
		String statement = "UPDATE company SET "
				+ "company_name ='" + companyName + "' "
				+ "WHERE customer_id =" + customerID + ";";		
		
		return database.makeSingleStatement(statement);
	}	
	
	// Delete customer entry from database
	public static boolean removeCompany(int customerID, Database database) throws Exception{

		String statement ="DELETE FROM company WHERE customer_id =" + customerID + ";";
		
		return database.makeSingleStatement(statement);
	}

	// View list of single company together with customer information. 
	// Returns information about company in String array
	// Columns by index:
	// 0 : customer_id - int
	// 1 : company_name - String
	// 2 : surname - String
	// 3 : firstname - String
	// 4 : phone_number - String
	// 5 : email - String
	// 6 : adress - String
	// 7 : zip_code - int
	// 8 : zone_nr - int
	// 9 : preferences - String
	// 10 : active - boolean
	public static String[] viewCompany(int customerID, Database database) throws Exception{		
		String statement = "SELECT *  FROM company NATURAL JOIN customer "
				+ "WHERE company.customer_id = customer.customer_id AND company.customer_id = " + customerID;
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult()[0];	
	}
	
	// View list of all companies together with customer information. 
	// Returns a list of all companies in database in a two-dimensional String array
	// First index is row, second is column
	// Columns by second index:
	// 0 : customer_id - int
	// 1 : company_name - String
	// 2 : surname - String
	// 3 : firstname - String
	// 4 : phone_number - String
	// 5 : email - String
	// 6 : adress - String
	// 7 : zip_code - int
	// 8 : zone_nr - int
	// 9 : preferences - String
	// 10 : active - boolean
	public static String[][] viewAllCompanies(Database database) throws Exception{		
		database.makeSingleStatement("SELECT *  FROM company NATURAL JOIN customer "
				+ "WHERE company.customer_id = customer.customer_id");
		
		return database.getLastResult();
	}
	
	// Register new order in database
	public static int registerOrder(String order_date, int customer_id, String info, 
			String user_id, Database database) throws Exception{
	
		for(int i=0; i<5; i++){
			String statement = "SELECT MAX(order_id) FROM food_order;";
			database.makeSingleStatement(statement);

			String highestID = database.getLastResult()[0][0];
			int orderID = 1;			
			if(highestID != null){
				orderID = Integer.parseInt(highestID) + 1;
			}			
			
			statement = "INSERT INTO food_order VALUES(" 
					+ orderID + ", "
					+ aq(order_date) 
					+ customer_id + ", " 
					+ aq(info) + "'" 
					+ user_id 
					+ "');";
			if(database.makeSingleStatement(statement)) return orderID;
		}
		
		return -1;
	}
	
	// Update order info
	public static boolean updateOrder(int orderID, String orderDate, int customerID, String info, 
			String userID, Database database) throws Exception{
		
		String statement = "UPDATE food_order SET "
				+ "customer_id = " + customerID + ", "
				+ "info = " + aq(info)
				+ "user_id = '" + userID + "'"
				+ " WHERE order_id = " + orderID;
		
		return database.makeSingleStatement(statement);
	}
	
	// Remove order from database
	public static boolean removeOrder(int orderID, Database database) throws Exception{
		
		String statement = "DELETE FROM food_order WHERE order_id = '" + orderID + "';";
		
		return database.makeSingleStatement(statement);
	}
	
	// View list of a food order.
	// Returns food order in a String array
	// Columns by index:
	// 0 : order_id - int
	// 1 : order_date - String
	// 2 : customer_id - int
	// 3 : info - String
	// 4 : user_id - String
	public static String[] viewOrder(int orderID, Database database) throws Exception{
		
		String statement = "SELECT * FROM food_order WHERE order_id = " + orderID + ";";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult()[0];
	}

	// View list of all food orders.
	// Returns a list of all food orders in database in a two-dimensional String array
	// First index is row, second is column
	// Columns by second index:
	// 0 : order_id - int
	// 1 : order_date - String
	// 2 : customer_id - int
	// 3 : info - String
	// 4 : user_id - String
	public static String[][] viewAllOrders(Database database) throws Exception{
		
		String statement = "SELECT * FROM food_order;";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}	
	
	
	// **** IKKE TESTET
	// Connect meal to order. Both must exisst in database to succeed
	public static boolean addMealToOrder(int orderID, int mealID, String deliveryDate, int quantity, 
			boolean readyDelivery, boolean delivered, Database database) throws Exception{
		
		String statement = "INSERT INTO ordered_meal VALUES("
				+ orderID + "," 
				+ mealID + "," 
				+ aq(deliveryDate) 
				+ quantity + "," 
				+ readyDelivery + "," 
				+ delivered + ");";
		
		return database.makeSingleStatement(statement);
	}

	// **** IKKE TESTET
	// Update info about meal in order
	public static boolean updateMealInOrder(int orderID, int mealID, String deliveryDate, int quantity, 
			boolean readyDelivery, boolean delivered, Database database) throws Exception{
		
		String statement = "UPDATE ordered_meal SET "
				+ "order_id = " + orderID + "," 
				+ "meal_id = " + mealID + "," 
				+ "delivery_date = " + aq(deliveryDate) 
				+ "quantity = " + quantity + "," 
				+ "ready_delivery = " + readyDelivery + "," 
				+ "delivered = " + delivered 
				+ "WHERE order_id =" + orderID
				+ " AND meal_id = " + mealID
				+ " AND delivery_date = '" + deliveryDate + "'"
				+";";
		
		return database.makeSingleStatement(statement);
	}
	 
	// Remove meal from order
	public static boolean removeMealFromOrder(int orderID, int mealID, String deliveryDate, Database database) throws Exception{

		String statement = "DELETE FROM ordered_meal WHERE "
				+ "order_id =" + orderID
				+ " AND meal_id = " + mealID
				+ " AND delivery_date = '" + deliveryDate + "'"
				+ ";";
		return database.makeSingleStatement(statement);
	}
	
	// Mark meal in order as ready for delivery
	public static boolean markMealOrderAsReadyForDelivery(int orderID, int mealID, String deliveryDate,Database database) throws Exception{
		String statement = "UPDATE ordered_meal SET ready_delivery=true "
				+ "WHERE order_id = " + orderID 
				+ " AND meal_id = " + mealID 
				+ " AND delivery_date = '" + deliveryDate + "'"
				+ ";";
		
		return database.makeSingleStatement(statement);
	}
	
	// Mark meal in order as delivered
	public static boolean markMealOrderAsDelivered(int orderID, int mealID, String deliveryDate,Database database) throws Exception{
		String statement = "UPDATE ordered_meal SET delivered=true "
				+ "WHERE order_id = " + orderID 
				+ " AND meal_id = " + mealID 
				+ " AND delivery_date = '" + deliveryDate + "'"
				+ ";";
		
		return database.makeSingleStatement(statement);
	}	

	// legge til bestillingsinfo?
	// Returns all meals in an order in a two-dimensional String array
	// First index is row, second is column
	// Columns by second index:
	// 0 : meal_id - int
	// 1 : meal_name - String
	// 2 : instructions - String
	// 3 : available - boolean
	// 4 : price - int
	public static String[][] viewMealsInOrder(int orderID, Database database) throws Exception{

		String statement = "SELECT * FROM meal WHERE meal_id IN (SELECT meal_id FROM ordered_meal WHERE order_id = " + orderID + ")";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();		
	}	
	
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
	public static int registerZone(String zoneName, Database database) throws Exception{
		
		for(int i=0; i<5; i++){
			String statement = "SELECT MAX(zone_nr) FROM zone;";
			database.makeSingleStatement(statement);

			String highestID = database.getLastResult()[0][0];
			int zoneID = 1;			
			if(highestID != null){
				zoneID = Integer.parseInt(highestID) + 1;
			}

			statement = "INSERT INTO zone VALUES("
					+ zoneID + ", '" 
					+ zoneName 
					+ "');";
			if(database.makeSingleStatement(statement)) return zoneID;
		}
		return -1;
	}
	
	// Update zone entry in database
	public static boolean updateZone(int zoneID, String zoneName, Database database) throws Exception{
		
		String statement = "UPDATE zone SET "
				+ "zone_name = '" + zoneName + "' "
				+ "WHERE zone_nr = " + zoneID
				+ ";";
		
		return database.makeSingleStatement(statement);
	}

	// Delete zone entry in database
	public static boolean removeZone(int zoneID, Database database) throws Exception{
		
		String statement = "DELETE FROM zone WHERE zone_nr = " + zoneID + ";";
		
		return database.makeSingleStatement(statement);
	}	
	
	// View zones in database
	// Returns list zones in a two-dimensional String array
	// First index is row, second is column
	// Columns by second index:
	// 0 : zone_nr - int
	// 1 : zone_name - String
	public static String[][] viewZones(Database database) throws Exception{

		String statement = "SELECT * FROM zone";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}
	
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
	
	// Connect subscription plan to order
	public static boolean addSubscriptionToOrder(int orderID, int quantitySub, String fromDate, String toDate, 
			int subID, Database database) throws Exception{		
		String statement = "INSERT INTO sub_order VALUES(" 
				+ orderID + "," 
				+ quantitySub + "," 
				+ aq(fromDate) 
				+ aq(toDate) + "'" 
				+ subID 
				+ "');";			
		return database.makeSingleStatement(statement);
	}
	
	// Update subscription information in order
	public static boolean updateSubscriptionInOrder(int orderID, int quantitySub, String fromDate, String toDate, 
			int subID, Database database) throws Exception{
		
		String statement = "UPDATE sub_order SET "
				+ "quantity_sub = " + quantitySub + ", "
				+ "from_date = " + aq(fromDate)
				+ "to_date = " + aq(toDate)
				+ "sub_id = " + subID + " "
				+ "WHERE order_id = " + orderID
				+ ";";
		
		return database.makeSingleStatement(statement);
	}
	
	// Remove subscription from order
	public static boolean removeSubscriptionFromOrder(int orderID, Database database) throws Exception{
		
		String statement = "DELETE FROM sub_order WHERE order_id = " + orderID + ";";
		
		return database.makeSingleStatement(statement);
	}
	
	// View subcription information for order
	public static String[] viewSubscription(int orderID, Database database) throws Exception{

		String statement = "SELECT * FROM sub_order WHERE order_id = " + orderID + ";";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult()[0];
	}	
	
	// View all subscription connected to orders
	public static String[][] viewSubscriptions(Database database) throws Exception{

		String statement = "SELECT * FROM sub_order;";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}		
	
	// **** IKKE TESTET
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
	
	// **** IKKE TESTET
	public static String[][] viewOrdersBydeliveryDate(java.sql.Date deliveryDate, Database database) throws Exception{
		
		String statement = "SELECT * FROM food_order NATURAL JOIN ordered_meal where food_order.order_id = ordered_meal.order_id AND delivery_date = '" + deliveryDate + "';";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
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
	
	public static String[][] viewMealsInPlan(int subID, Database database) throws Exception{

		String statement = "SELECT meal_name FROM meal WHERE meal_id IN (SELECT meal_id FROM sub_meals_day WHERE sub_id = " + subID + ")";		
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}	
	
}

