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
	public static boolean registerUser(int userType, String name, String password, Database database) throws Exception{
		
		String statement = "INSERT INTO user VALUES(DEFAULT, " 
				+ userType + ", '" + name + "', '" + password + "');";

		return database.makeSingleStatement(statement);
	}

	public static String[][] viewAllUsers(Database database) throws Exception{
		
		database.makeSingleStatement("SELECT user_id, user_type, name FROM user");
		
		return database.getLastResult();
		
	}	
	
	public static int logIn(String name, String password, Database database) throws Exception{
		
		String[][] userType = null;
		
		database.makeSingleStatement("SELECT user_type FROM user WHERE name = '" + name + "' AND password ='" + password + "'");	
		
		userType = database.getLastResult();
		
		if(userType.length == 0){ // If no such user or password is incorrect
			return -1;
		}else{
			return Integer.parseInt(userType[0][0]);
		}
	}

	public static boolean registerCustomer(String surName, String firstName, String phoneNumber, String email, String adress, 
			int zip_code, int zone_nr, String preferences, int active, Database database) throws Exception{
		
		String statement = "INSERT INTO customer VALUES(DEFAULT, " 
				+ aq(surName) + aq(firstName) + aq(phoneNumber) 
				+ aq(email) + aq(adress)
			/*	+ zip_code + ", " + zone_nr + ", " */
				+ aq(preferences) + "" + active + ");";
		
		return database.makeSingleStatement(statement);
	}
	
	public static boolean registerCompany(String surName, String firstName, String phoneNumber,String email, String adress, 
			int zip_code, int zone_nr, String preferences, int active, String companyName, Database database) throws Exception{
	
		registerCustomer(firstName, surName, phoneNumber, email, adress, zip_code, zone_nr, preferences, active, database);
		
		database.makeSingleStatement("SELECT MAX(customer_id) FROM customer");
		
		String[][] customerID = database.getLastResult();
				
		String statement = "INSERT INTO company VALUES("
				+ aq(customerID[0][0]) + "'" + companyName + "');";		
		
		return database.makeSingleStatement(statement);
	}
	
	public static String[][] viewAllCustomers(Database database) throws Exception{		
		database.makeSingleStatement("SELECT * FROM customer");
		
		return database.getLastResult();
	}

	public static boolean registerSingleOrder(String order_date, int customer_id, String info, int user_id, int mealID, String deliveryDate, int quantity, Database database) throws Exception{
		
		String statement = "INSERT INTO food_order VALUES(DEFAULT, "
				+ aq(order_date) + customer_id + "," + aq(info) + user_id + ");";
		if(!database.makeSingleStatement(statement)) return false;

		if(!database.makeSingleStatement("SELECT MAX(order_id) FROM food_order")) return false;
		
		String orderID = database.getLastResult()[0][0];
		
		statement = "INSERT INTO ordered_meal VALUES("
				+ aq(orderID) + mealID + "," + aq(deliveryDate) + quantity + "," + 0 + "," + 0 + ");";
		if(!database.makeSingleStatement(statement)) return false;		
		
		return true;
	}

	/*
	public boolean registerSubscription(String delivery_date, int quantity, String fromDate, String toDate, String subName);
	
	public boolean updateOrder(String delivery_date, int quantity, String mealName);
	
	
	public boolean updateCompany(String customerId, String companyName, String firstName, String surName, String email, String adress, String zip_code, String zone_nr, String preferences, String active);
	public boolean updateCustomer(String customerId, String surName, String email, String adress, String zip_code, String zone_nr, String preferences, String active);
	*/

	
	
	public static void main(String[] args) throws Exception{
		// testkode
		
		String username = "";
		String password = "";
		Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=" + username + "&password=" + password);
		
		String testStr = "test";
		
		UserMethods.registerCustomer("surname", "firstname", "aabbccdd", "email", "veigata", 1111, 1, "None", 1, database);
		UserMethods.registerCompany("Meland", "Espen", "aabbccdd", "asd@asd", "Haga", 1234, 1, "Vegetarisk", 1, "Hask", database);		
		
		
		/*
		UserMethods.viewAllCustomers(database);
		
		String[][] resultat = database.getLastResult();
		
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

