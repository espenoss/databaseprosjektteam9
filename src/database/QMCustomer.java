package database;

/**
 * The Class QMCustomer.<br>
 * Contains database query methods related to customer information<br>
 * Assembles statements from parameters and executes the them
 */
public class QMCustomer {


	/**
	 * Register new customer.
	 *
	 * @param surName Customer surname
	 * @param firstName Customer first name
	 * @param phoneNumber Customer  phone number
	 * @param email Customer  email
	 * @param adress Customer  adress
	 * @param zip_code Customer zip_code
	 * @param zone_nr Customer zone_nr, must exist in database table 'zone'
	 * @param preferences Customer preferences
	 * @param active Whether customer is active
	 * @param database Database connection
	 * @return Customer ID
	 */
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


	/**
	 * Update customer information.
	 *
	 * @param customerID Customer id
	 * @param surName Customer surname
	 * @param firstName Customer first name
	 * @param phoneNumber Customer  phone number
	 * @param email Customer  email
	 * @param adress Customer  adress
	 * @param zip_code Customer zip_code
	 * @param zone_nr Customer zone_nr, must exist in databasse table 'zone'
	 * @param preferences Customer preferences
	 * @param active Whether customer is active
	 * @param database Database connection	 
	 * @return true, if successful
	 */
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

	/**
	 * View customer information.<br>
	 * Columns by index:<br>
	 * 0 : customer_id - int<br>
	 * 1 : surname - String<br>
	 * 2 : firstname - String<br>
	 * 3 : phone_number - String<br>
	 * 4 : email - String<br>
	 * 5 : adress - String<br>
	 * 6 : zip_code - int<br>
	 * 7 : zone_nr - int<br>
	 * 8 : preferences - String<br>
	 * 9 : active - boolean<br>
	 * 
	 * @param customerID the customer id
	 * @param database Database connection
	 * @return String[] of customer information
	 * 
	 */
	public static String[] viewCustomer(int customerID, Database database) {		
		String statement = "SELECT * FROM customer WHERE customer_id ="
				+ customerID + ";";

		database.makeSingleStatement(statement);

		return database.getLastResult()[0];
	}	


	/**
	 * View all customers.
	 * Returns a list of all customers in database in a two-dimensional String array<br>
	 * Columns by second index:<br>
	 * 0 : customer_id - int<br>
	 * 1 : surname - String<br>
	 * 2 : firstname - String<br>
	 * 3 : phone_number - String<br>
	 * 4 : email - String<br>
	 * 5 : adress - String<br>
	 * 6 : zip_code - int<br>
	 * 7 : zone_nr - int<br>
	 * 8 : preferences - String<br>
	 * 9 : active - boolean
	 * 
	 * @param database Database connection
	 * @return String[][] of customer information
	 */
	public static String[][] viewAllCustomers(Database database) {		
		database.makeSingleStatement("SELECT * FROM customer");

		return database.getLastResult(); 
	}


	/**
	 * Register company to customer.
	 * Customer ID needs to exist in 'customer' table
	 *
	 * @param customerID Customer id
	 * @param companyName Company name
	 * @param database Database connection
	 * @return true, if successful
	 */
	public static boolean registerCompanyToCustomer(int customerID, String companyName, Database database) {				
		String statement = "INSERT INTO company VALUES("
				+ customerID + ",'" 
				+ companyName 
				+ "');";		

		return database.makeSingleStatement(statement);
	}


	/**
	 * Update company name.
	 *
	 * @param customerID Customer id
	 * @param companyName New company name
	 * @param database Database connection
	 * @return true, if successful
	 */
	public static boolean updateCompany(int customerID, String companyName, Database database) {
		String statement = "UPDATE company SET "
				+ "company_name ='" + companyName + "' "
				+ "WHERE customer_id =" + customerID + ";";		

		return database.makeSingleStatement(statement);
	}	

	/**
	 * View company and customer information.
	 * Returns information about company in String array<br>
	 * Columns by index:<br>
	 *
	 * 0 : customer_id - int<br>
	 * 1 : company_name - String<br>
	 * 2 : surname - String<br>
	 * 3 : firstname - String<br>
	 * 4 : phone_number - String<br>
	 * 5 : email - String<br>
	 * 6 : adress - String<br>
	 * 7 : zip_code - int<br>
	 * 8 : zone_nr - int<br>
	 * 9 : preferences - String<br>
	 * 10 : active - boolean<br>
	 *
	 * @param customerID Customer id
	 * @param database Database connection
	 * @return String[] of compant information
	 */
	public static String[] viewCompany(int customerID, Database database) {		
		String statement = "SELECT *  FROM company NATURAL JOIN customer "
				+ "WHERE company.customer_id = customer.customer_id AND company.customer_id = " + customerID;

		database.makeSingleStatement(statement);

		return database.getLastResult()[0];	
	}

	/**
	 * View all company and customer information.
	 * Returns a list of all companies in database in a two-dimensional String array<br>
	 * Columns by second index:<br>
	 *
	 * 0 : customer_id - int<br>
	 * 1 : company_name - String<br>
	 * 2 : surname - String<br>
	 * 3 : firstname - String<br>
	 * 4 : phone_number - String<br>
	 * 5 : email - String<br>
	 * 6 : adress - String<br>
	 * 7 : zip_code - int<br>
	 * 8 : zone_nr - int<br>
	 * 9 : preferences - String<br>
	 * 10 : active - boolean<br>
	 *
	 * @param customerID Customer id
	 * @param database Database connection
	 * @return String[] of company information
	 */
	public static String[][] viewAllCompanies(Database database) {		
		database.makeSingleStatement("SELECT *  FROM company NATURAL JOIN customer "
				+ "WHERE company.customer_id = customer.customer_id");

		return database.getLastResult();
	}

	/**
	 * View zones.
	 * First index is row, second is column<br>
	 * Columns by second index:<br>
	 * 0 : zone_nr - int<br>
	 * 1 : zone_name - String<br>
	 *
	 * @param database Database connection
	 * @return String[][] of zone information
	 */
	public static String[][] viewZones(Database database) {

		String statement = "SELECT * FROM zone";

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
