package databasePackage;

public class UserMethods {
	
	// User types
	public static final int U_ADMIN = 0;
	public static final int U_COOK = 1;
	public static final int U_DRIVER = 2;
	public static final int U_SALES = 3;
	public static final int U_STORAGE = 4;
	
	// Method for registering new user in database
	public static void registerUser(int userType, String name, String password, Database database) throws Exception{
		
		String statement = "INSERT INTO user VALUES(DEFAULT, " 
				+ userType + ", '" + name + "', '" + password + "');";

		database.makeSingleStatement(statement);
		
		// Sjekk setning, fjern etter testing
		System.out.println(statement);
	}
	
	/*
	public boolean registerCompany(String companyName, String firstName, String surName, String email, String adress, String zip_code, String zone_nr, String preferences, String active);
	public boolean registerCustomer(String firstName, String surName, String email, String adress, String zip_code, String zone_nr, String preferences, String active);

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
		
		UserMethods.registerUser(U_ADMIN, "Espen", "asd", database);
	}

}
