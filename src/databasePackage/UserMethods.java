package databasePackage;

public class UserMethods {
	
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
		
		String statement = "INSERT INTO user VALUES(" + aq(userID) 
				+ userType + ", '" + name + "', '" + password + "');";

		return database.makeSingleStatement(statement);
	}

	public static String[][] viewAllUsers(Database database) throws Exception{
		
		database.makeSingleStatement("SELECT user_id, user_type, name FROM user");
		
		return database.getLastResult();
		
	}	
	
	public static int logIn(String userID, String password, Database database) throws Exception{
		
		String[][] userType = null;
		
		database.makeSingleStatement("SELECT user_type FROM user WHERE user_id = '" + userID + "' AND password ='" + password + "'");	
		
		userType = database.getLastResult();
		
		if(userType.length == 0){ // If no such user or password is incorrect
			return -1;
		}else{
			return Integer.parseInt(userType[0][0]);
		}
	}

	public static int registerCustomer(String surName, String firstName, String phoneNumber, String email, String adress, 
			int zip_code, int zone_nr, String preferences, int active, Database database) throws Exception{
		
		for(int i=0; i<5; i++){
			String statement = "SELECT MAX(customer_id) FROM customer;";
			database.makeSingleStatement(statement);
			int customerID = Integer.parseInt(database.getLastResult()[0][0]) + 1;
			statement = "INSERT INTO customer VALUES(" + customerID + ", " 
					+ aq(surName) + aq(firstName) + aq(phoneNumber) 
					+ aq(email) + aq(adress)
					+ zip_code + ", " + zone_nr + ", " 
					+ aq(preferences) + "" + active + ");";
			if(database.makeSingleStatement(statement)) return customerID;
		}
				
		return -1;
	}
	
	public static boolean registerCompanyToCustomer(int customerID, String companyName, Database database) throws Exception{
			
					
		String statement = "INSERT INTO company VALUES("
				+ customerID + ",'" + companyName + "');";		
		
		return database.makeSingleStatement(statement);
	}
	
	public static String[][] viewAllCustomers(Database database) throws Exception{		
		database.makeSingleStatement("SELECT * FROM customer");
		
		return database.getLastResult();
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
			int orderID = Integer.parseInt(database.getLastResult()[0][0]) + 1;
			statement = "INSERT INTO food_order VALUES(" + orderID + ", "
				+ aq(order_date) + customer_id + ", " + aq(info) + "'" + user_id + "');";
			if(database.makeSingleStatement(statement)) return orderID;
		}
		
		return -1;
	}

	public static boolean addMealToOrder(String orderID, String mealID, String deliveryDate, int quantity,boolean readyDelivery, boolean delivered, Database database) throws Exception{
		
		String statement = "INSERT INTO ordered_meal VALUES("
				+ orderID + "," + mealID + "," + aq(deliveryDate) + quantity + "," + 0 + "," + 0 + ");";
		System.out.println(statement);
		if(!database.makeSingleStatement(statement)) return false;		
		
		return true;
	}
	
	public static int registerIngredient(String name, float quantity , String unit,Database database) throws Exception{
		
		for(int i=0; i<5; i++){
			String statement = "SELECT MAX(ingredient_id) FROM ingredient;";
			database.makeSingleStatement(statement);
			int ingredientID = Integer.parseInt(database.getLastResult()[0][0]) + 1;
			statement = "INSERT INTO ingredient VALUES(" + ingredientID + ","
					+ aq(name) + quantity + ", '" + unit + "');";
			if(database.makeSingleStatement(statement)) return ingredientID;
		}
		
		return -1;		

	}
	

	public static int registerMeal(String name, String instructions, int available, int price, int discount, int discountLim, Database database) throws Exception{

		for(int i=0; i<5; i++){
			String statement = "SELECT MAX(meal_id) FROM meal;";
			database.makeSingleStatement(statement);
			int mealID = Integer.parseInt(database.getLastResult()[0][0]) + 1;
			statement = "INSERT INTO meal VALUES(" + mealID
					+ aq(name) + aq(instructions) + available 
					+ ", " + price + ", " + discount + ", " + discountLim + ");";
			if(database.makeSingleStatement(statement)) return mealID;
		}
		
		return -1;		
	}
	
	public static boolean addIngredientToMeal(int mealID, int ingredientID, float ingredientQuantity, Database database) throws Exception{
				
		String statement = "INSERT INTO meal_ingredient VALUES(" 
	+ mealID + ", " + ingredientID + "," + ingredientQuantity + ");";
		return database.makeSingleStatement(statement);
}
	
	public static String[][] viewMeals(Database database) throws Exception{
		
		String statement = "SELECT * FROM meal";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}
	

	public static int registerZone(String zoneName, Database database) throws Exception{
		
		for(int i=0; i<5; i++){
			String statement = "SELECT MAX(zone_nr) FROM zone;";
			database.makeSingleStatement(statement);
			int zoneNr = Integer.parseInt(database.getLastResult()[0][0]) + 1;
			statement = "INSERT INTO zone VALUES("
					+ zoneNr + ", '" + zoneName + "');";
			if(database.makeSingleStatement(statement)) return zoneNr;
		}
		return -1;
	}

	public static int registerSubscription(String subName, Database database) throws Exception{		
		for(int i=0; i<5; i++){
			String statement = "SELECT MAX(sub_id) FROM subscription_plan;";
			database.makeSingleStatement(statement);
			int subID = Integer.parseInt(database.getLastResult()[0][0]) + 1;
			statement = "INSERT INTO subscription_plan VALUES("
					+ subID + ", '" + subName + "')";
			if(database.makeSingleStatement(statement)) return subID;
		}
		
		return -1;
	}
	

	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception{
		// testkode
		
		String username = "espenme";
		String password = "16Sossosem06";
		Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=" + username + "&password=" + password);
		String[][] resultat = null;
		
		UserMethods.registerOrder("2016-01-03", 10000, "none", "Marie", database);
		
//		UserMethods.registerIngredients("Kjøtt", 5, database);
		
//		UserMethods.registerIngredients("Kjï¿½tt", 5, database);
//		UserMethods.registerMeal("Mais", "ingenting", 1, 123, 10, 12, database);
//		UserMethods.viewMeals(database);

/*		resultat = database.getLastResult();		
		
		for(int x=0;x<resultat.length; x++){
			for(int y=0;y<resultat[x].length;y++){
				System.out.print(resultat[x][y] + " ");
			}
			System.out.println();
		}		
*/		
//		UserMethods.registerUser("espenme", 1, "Espen Meland", "asd", database);
		
		
//		UserMethods.registerCustomer(11, "surname", "firstname", "aabbccdd", "email", "veigata", 1111, 1, "None", 1, database);
//		UserMethods.registerCompany(12, "Meland", "Espen", "aabbccdd", "asd@asd", "Haga", 1234, 1, "Vegetarisk", 1, "Hask", database);		
		
		
		
/*		UserMethods.viewAllCompanies(database);
		
		
		for(int x=0;x<resultat.length; x++){
			for(int y=0;y<resultat[x].length;y++){
				System.out.print(resultat[x][y] + " ");
			}
			System.out.println();
		}
*/		
//		System.out.println(UserMethods.logIn("Espen", "asd", database));
		
//		UserMethods.registerSingleOrder("2012-03-02", 10001, "Her er info om bestilingen", 1, 1, "2012-12-12", 1, database);
	}
}

