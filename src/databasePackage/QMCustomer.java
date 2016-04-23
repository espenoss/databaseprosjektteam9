package databasePackage;


//All query methods related to customers, companies and zones
// MÅ TESTE REGISTER ZONE!


public class QMCustomer {
	
	
	
	// Register new customer in database
	public static int registerCustomer(String surName, String firstName, String phoneNumber, String email, String adress, 
			int zip_code, int zone_nr, String preferences, boolean active, Database database) {
		
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
			int zipCode, int zoneNr, String preferences, boolean active, Database database) {
			
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
	
	public static boolean removeCustomer(int customerID, Database database) {
		
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
	public static String[] viewCustomer(int customerID, Database database) {		
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
	public static String[][] viewAllCustomers(Database database) {		
		database.makeSingleStatement("SELECT * FROM customer");
		
		return database.getLastResult(); 
	}

	
	// Register company in database. Needs ID of exissting customer to succeed
	public static boolean registerCompanyToCustomer(int customerID, String companyName, Database database) {				
		String statement = "INSERT INTO company VALUES("
				+ customerID + ",'" 
				+ companyName 
				+ "');";		
		
		return database.makeSingleStatement(statement);
	}

	
	// Update customer info in database
	public static boolean updateCompany(int customerID, String companyName, Database database) {
		String statement = "UPDATE company SET "
				+ "company_name ='" + companyName + "' "
				+ "WHERE customer_id =" + customerID + ";";		
		
		return database.makeSingleStatement(statement);
	}	
	
	
	// Delete customer entry from database
	public static boolean removeCompany(int customerID, Database database) {

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
	public static String[] viewCompany(int customerID, Database database) {		
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
	public static String[][] viewAllCompanies(Database database) {		
		database.makeSingleStatement("SELECT *  FROM company NATURAL JOIN customer "
				+ "WHERE company.customer_id = customer.customer_id");
		
		return database.getLastResult();
	}
	
	// MÅ TESTES
	public static int registerZone(String zoneName, Database database) {
		
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
	public static boolean updateZone(int zoneID, String zoneName, Database database) {
		
		String statement = "UPDATE zone SET "
				+ "zone_name = '" + zoneName + "' "
				+ "WHERE zone_nr = " + zoneID
				+ ";";
		
		return database.makeSingleStatement(statement);
	}

	// Delete zone entry in database
	public static boolean removeZone(int zoneID, Database database) {
		
		String statement = "DELETE FROM zone WHERE zone_nr = " + zoneID + ";";
		
		return database.makeSingleStatement(statement);
	}	
	
	// View zones in database
	// Returns list zones in a two-dimensional String array
	// First index is row, second is column
	// Columns by second index:
	// 0 : zone_nr - int
	// 1 : zone_name - String
	public static String[][] viewZones(Database database) {

		String statement = "SELECT * FROM zone";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}
	


	
	// Puts a ' on either side and a comma at the end  of a string 
	public static String aq(String s){
		return "'" + s + "', ";
	}

}
