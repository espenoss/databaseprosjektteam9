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

	public static boolean updateUser(String userID, int userType, String name, String password, Database database) throws Exception{
		String statement = "UPDATE user SET "
				+ "user_id =" + aq(userID)
				+ "user_type =" + userType + ","
				+ "name = " + aq(name)
				+ "password = '" + password + "' " 
				+ "WHERE user_id = '" + userID + "';";
		return database.makeSingleStatement(statement);
	}
	
	public static boolean removeUser(String userID, Database database) throws Exception{
		String statement = "DELETE FROM user WHERE user_id = '" + userID + "';";
		return database.makeSingleStatement(statement);
	}

	public static String[] viewUser(String userID, Database database) throws Exception{
		String statement = "SELECT user_id, user_type, name FROM user WHERE user_id = '" + userID + "';";
		System.out.println(statement);		
		database.makeSingleStatement(statement);
		return database.getLastResult()[0];
	}	
	
	public static String[][] viewAllUsers(Database database) throws Exception{		
		database.makeSingleStatement("SELECT user_id, user_type, name FROM user");
		
		return database.getLastResult();		
	}	
	
	public static int logIn(String userID, char[] password, Database database) throws Exception{
		
		String[][] userType = null;
		
		database.makeSingleStatement("SELECT user_type FROM user WHERE user_id = '" + userID + "' AND password ='" + password.toString() + "'");	
		
		userType = database.getLastResult();
		
		if(userType.length == 0){ // If no such user or password is incorrect
			return -1;
		}else{
			return Integer.parseInt(userType[0][0]);
		}
	}

	public static int registerCustomer(String surName, String firstName, String phoneNumber, String email, String adress, 
			int zip_code, int zone_nr, String preferences, boolean active, Database database) throws Exception{
		
		for(int i=0; i<5; i++){

			String statement = "SELECT MAX(customer_id) FROM customer;";
			database.makeSingleStatement(statement);
			
			String highestID = database.getLastResult()[0][0];
			int customerID = 1;			
			if(highestID != null){
				customerID = Integer.parseInt(highestID) + 1;
			}

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

	public static boolean updateCustomer(int customerID, String surName, String firstName, String phoneNumber, String email, String adress, 
			int zipCode, int zoneNr, String preferences, boolean active, Database database) throws Exception{
			
		String statement = "UPDATE customer SET "
				+ "customer_id =" + customerID + ","
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
	
	public static String[] viewCustomer(int customerID, Database database) throws Exception{		
		String statement = "SELECT * FROM customer WHERE customer_id ="
				+ customerID + ";";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult()[0];
	}	
	
	public static String[][] viewAllCustomers(Database database) throws Exception{		
		database.makeSingleStatement("SELECT * FROM customer");
		
		return database.getLastResult();  //Denne metoden fungerer ikke som den skal
	}

	public static boolean registerCompanyToCustomer(int customerID, String companyName, Database database) throws Exception{				
		String statement = "INSERT INTO company VALUES("
				+ customerID + ",'" 
				+ companyName 
				+ "');";		
		
		return database.makeSingleStatement(statement);
	}	

	public static boolean updateCompany(int customerID, String companyName, Database database) throws Exception{
		String statement = "UPDATE company SET "
				+ "company_name ='" + companyName + "' "
				+ "WHERE customer_id =" + customerID + ";";		
		
		return database.makeSingleStatement(statement);
	}	
	
	public static boolean removeCompany(int customerID, Database database) throws Exception{

		String statement ="DELETE FROM company WHERE customer_id =" + customerID + ";";
		
		return database.makeSingleStatement(statement);
	}

	public static String[] viewCompany(int customerID, Database database) throws Exception{		
		String statement = "SELECT *  FROM company NATURAL JOIN customer "
				+ "WHERE company.customer_id = customer.customer_id AND company.customer_id = " + customerID;
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult()[0];	
	}
	
	public static String[][] viewAllCompanies(Database database) throws Exception{		
		database.makeSingleStatement("SELECT *  FROM company NATURAL JOIN customer "
				+ "WHERE company.customer_id = customer.customer_id");
		
		return database.getLastResult();
	}
	
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
	
	public static boolean updateOrder(int orderID, String orderDate, int customerID, String info, 
			String userID, Database database) throws Exception{
		
		String statement = "UPDATE food_order SET "
				+ "order_id = " + orderID + ", "
				+ "order_date = " + aq(orderDate)
				+ "customer_id = " + customerID + ", "
				+ "info = " + aq(info)
				+ "user_id = '" + userID + "'"
				+ " WHERE order_id = " + orderID;
		
		return database.makeSingleStatement(statement);
	}
	
	public static boolean removeOrder(int orderID, Database database) throws Exception{
		
		String statement = "DELETE FROM food_order WHERE order_id = '" + orderID + "';";
		
		return database.makeSingleStatement(statement);
	}
	
	public static String[] viewOrder(int orderID, Database database) throws Exception{
		
		String statement = "SELECT * FROM food_order WHERE order_id = " + orderID + ";";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult()[0];
	}
	
	public static String[][] viewAllOrders(Database database) throws Exception{
		
		String statement = "SELECT * FROM food_order;";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}	
	
	
	// **** IKKE TESTET
	public static boolean addMealToOrder(int orderID, int mealID, String deliveryDate, int quantity, 
			boolean readyDelivery, boolean delivered, Database database) throws Exception{
		
		String statement = "INSERT INTO ordered_meal VALUES("
				+ orderID + "," + mealID + "," + aq(deliveryDate) 
				+ quantity + "," + readyDelivery + "," + delivered + ");";
		
		return database.makeSingleStatement(statement);
	}

	public static boolean updateMealInOrder(int orderID, int mealID, String deliveryDate, int quantity, 
			boolean readyDelivery, boolean delivered, Database database) throws Exception{
		
		String statement = "UPDATE ordered_meal SET "
				+ "order_id = " + orderID + "," 
				+ "meal_id = " + mealID + "," 
				+ "delivery_date = " + aq(deliveryDate) 
				+ "quantity = " + quantity + "," 
				+ "ready_delivery = " + readyDelivery + "," 
				+ "delivered = " + delivered + ";";
		
		return database.makeSingleStatement(statement);
	}
	 
	public static boolean removeMealFromOrder(int orderID, int mealID, String deliveryDate, Database database) throws Exception{

		String statement = "DELETE FROM ordered_meal WHERE "
				+ "order_id =" + orderID
				+ " AND meal_id = " + mealID
				+ " AND delivery_date = '" + deliveryDate + "'"
				+ ";";
		return database.makeSingleStatement(statement);
	}
	

	public static boolean markMealOrderAsReadyForDelivery(int orderID, int mealID, String deliveryDate,Database database) throws Exception{
		String statement = "UPDATE ordered_meal SET ready_delivery=true "
				+ "WHERE order_id = " + orderID 
				+ " AND meal_id = " + mealID 
				+ " AND delivery_date = '" + deliveryDate + "'"
				+ ";";
		
		return database.makeSingleStatement(statement);
	}
	
	public static boolean markMealOrderAsDelivered(int orderID, int mealID, String deliveryDate,Database database) throws Exception{
		String statement = "UPDATE ordered_meal SET delivered=true "
				+ "WHERE order_id = " + orderID 
				+ " AND meal_id = " + mealID 
				+ " AND delivery_date = '" + deliveryDate + "'"
				+ ";";
		
		return database.makeSingleStatement(statement);
	}	

	// legge til bestillingsinfo?
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
	
	public static String[][] viewIngredients(Database database) throws Exception{
		
		String statement = "SELECT * FROM ingredient";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();				
	}

	public static int registerMeal(String mealName, String instructions, boolean available, int price, 
			int discount, int discountLim, Database database) throws Exception{

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
					+ price + ", " 
					+ discount + ", " 
					+ discountLim + ");";
			
			if(database.makeSingleStatement(statement)) return mealID;
		}
		
		return -1;		
	}
	
	public static boolean updateMeal(int mealID, String mealName, String instructions, boolean available, int price, 
			int discount, int discountLim, Database database) throws Exception{
		
		String statement = "UPDATE meal SET "
				+ "meal_id = " + mealID + ", "
				+ "meal_name = " + aq(mealName)
				+ "instructions = " + aq(instructions)
				+ "available = " + available + ", "
				+ "price = " + price + ", "
				+ "discount = " + discount + ", "
				+ "discount_lim = " + discountLim + " "
				+ "WHERE meal_id =" + mealID 
				+ ";";
		
		return database.makeSingleStatement(statement);
	}

	public static boolean removeMeal(int mealID, Database database) throws Exception{
		
		String statement = "DELETE FROM meal WHERE meal_id = " + mealID + ";";
		
		return database.makeSingleStatement(statement);
	}

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
				+ "meal_id = " + mealID + ", "
				+ "ingredient_id = " + ingredientID + ", "
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

	public static String[][] viewIngredientsInMeal(int mealID, Database database) throws Exception{
		
		String statement = "SELECT * FROM ingredient WHERE ingredient_id IN (SELECT ingredient_id FROM meal_ingredient WHERE meal_id = " + mealID + ")";;
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();		
	}
			
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
	
	public static boolean updateZone(int zoneID, String zoneName, Database database) throws Exception{
		
		String statement = "UPDATE zone SET "
				+ "zone_nr = " + zoneID + ", "
				+ "zone_name = '" + zoneName + "' "
				+ "WHERE zone_nr = " + zoneID
				+ ";";
		
		return database.makeSingleStatement(statement);
	}

	public static boolean removeZone(int zoneID, Database database) throws Exception{
		
		String statement = "DELETE FROM zone WHERE zone_nr = " + zoneID + ";";
		
		return database.makeSingleStatement(statement);
	}	
	
	public static String[][] viewZones(Database database) throws Exception{

		String statement = "SELECT * FROM zone";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}
	
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

	public static boolean updateSubscriptionPlan(int subID, String subName, Database database) throws Exception{		
		
		String statement = "UPDATE subscription_plan SET "
				+ "sub_id = " + subID + ", "
				+ "sub_name = '" + subName + "' "
				+ "WHERE sub_id = " + subID 
				+";";
		
		return database.makeSingleStatement(statement);
	}
	
	public static boolean removeSubscriptionPlan(int subID, Database database) throws Exception{		
		
		String statement = "DELETE FROM subscription_plan WHERE sub_id = " + subID + ";";
		
		return database.makeSingleStatement(statement);
	}	
	
	public static String[][] viewSubscriptionPlans(Database database) throws Exception{

		String statement = "SELECT * FROM subscription_plan;";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}
	
	// **** IKKE TESTET
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
	
	public static boolean updateSubscriptionInOrder(int orderID, int quantitySub, String fromDate, String toDate, 
			int subID, Database database) throws Exception{
		
		String statement = "UPDATE sub_order SET "
				+ "order_id = " + orderID + ", "
				+ "quantity_sub = " + quantitySub + ", "
				+ "from_date = " + aq(fromDate)
				+ "to_date = " + aq(toDate)
				+ "sub_id = " + subID + " "
				+ "WHERE order_id = " + orderID
				+ ";";
		
		return database.makeSingleStatement(statement);
	}
	
	public static boolean removeSubscriptionFromOrder(int orderID, Database database) throws Exception{
		
		String statement = "DELETE FROM sub_order WHERE order_id = " + orderID + ";";
		
		return database.makeSingleStatement(statement);
	}
	
	public static String[] viewSubscription(int orderID, Database database) throws Exception{

		String statement = "SELECT * FROM sub_order WHERE order_id = " + orderID + ";";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult()[0];
	}	
		public static String[][] viewSubscriptions(Database database) throws Exception{

		String statement = "SELECT * FROM sub_order";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}		
	
	// **** IKKE TESTET
	public static boolean addMealToPlan(int subID, int mealID, int weekdayNr, String weekday, Database database) throws Exception{
		
		String statement = "INSERT INTO sub_meals_day VALUES(" + subID + ", " 
		+ mealID + "," + weekdayNr + ", '" + weekday + "');";
			
		return database.makeSingleStatement(statement);
	}
	
	// **** IKKE TESTET - IKKE IMPLEMENTERT	
	public static boolean updateMealInPlan(int subID, int mealID, int weekdayNr, String weekday, Database database) throws Exception{	
		return false;
	}
	
	// **** IKKE TESTET - IKKE IMPLEMENTERT		
	public static boolean removeMealFromPlan(){
		return false;
	}
	
	// **** IKKE TESTET - IKKE IMPLEMENTERT				
	public static String[][] viewMealsInPlan(int subID, Database database) throws Exception{

		String statement = "";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}	
	
}

