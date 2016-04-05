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
	public static void registerUser(int userType, String name, String password, Database database) throws Exception{
		
		String statement = "INSERT INTO user VALUES(DEFAULT, " 
				+ userType + ", '" + name + "', '" + password + "');";

		database.makeSingleStatement(statement);
		
		// Sjekk setning, fjern etter testing
		System.out.println(statement);
	}
	
	public static int logIn(String name, String password, Database database) throws Exception{
		
		String[][] userType = null;
		
		userType = database.makeSingleStatement("SELECT user_type FROM user WHERE name = '" + name + "' AND password ='" + password + "'");	
		
		if(userType.length == 0){ // If no such user or password is incorrect
			return -1;
		}else{
			return Integer.parseInt(userType[0][0]);
		}
	}

	public static void registerCustomer(String firstName, String surName, String email, String adress, 
			int zip_code, int zone_nr, String preferences, int active, Database database) throws Exception{
		
		String statement = "INSERT INTO customer VALUES(DEFAULT, " 
				+ aq(firstName) + aq(surName) + aq(email) 
				+ aq(email) + aq(adress)
				+ zip_code + ", " + zone_nr + ", " 
				+ aq(preferences) + "" + active + ");";

		// Sjekk setning, fjern etter testing
		System.out.println(statement);
		
		database.makeSingleStatement(statement);
	}
	
	public static String[][] viewAllCustomers(Database database) throws Exception{
		
		return database.makeSingleStatement("SELECT user_id, user_type, name FROM user");
		
	}
	
	public static void registerCompany(String surName, String firstName, String email, String adress, 
			int zip_code, int zone_nr, String preferences, int active, String companyName, Database database) throws Exception{
	
		registerCustomer(firstName, surName, email, adress, zip_code, zone_nr, preferences, active, database);
		
		String[][] customerID = database.makeSingleStatement("SELECT customer_id FROM customer WHERE firstname = '" + firstName 
				+ "' AND surname = '" + surName + "'");

		if(customerID.length > 1){ // If customer name is not unique
			// Avbryte her?
			System.out.println("Not a unique name");								
		}else{
			// Test
			System.out.println(customerID[0][0]);					
		}
				
		String statement = "INSERT INTO company VALUES("
				+ aq(customerID[0][0]) + "'" + companyName + "');";		

		// Sjekk setning, fjern etter testing
		System.out.println(statement);		
		
		database.makeSingleStatement(statement);
	}
	
	
/*
	public boolean registerOrder(String delivery_date, int quantity, String mealName);
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
		
		//UserMethods.registerCustomer(testStr, testStr, testStr, testStr, 1111, 1111, testStr, 1, database);
		//UserMethods.registerCompany("Meland", "Espen", "asd@asd", "Haga", 123, 5, "Vegetarisk", 1, "Hask", database);		
		
/*		String[][] resultat = UserMethods.viewAllCustomers(database);
		
		for(int x=0;x<resultat.length; x++){
			for(int y=0;y<resultat[x].length;y++){
				System.out.print(resultat[x][y] + " ");
			}
			System.out.println();
		}
*/
//		System.out.println(UserMethods.logIn("Espen", "asd", database));
	}
}

