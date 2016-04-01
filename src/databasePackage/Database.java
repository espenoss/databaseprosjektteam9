package databasePackage;
import java.sql.*;

public class Database {
		
		// User types
		public final int U_ADMIN = 0;
		public final int U_COOK = 1;
		public final int U_DRIVER = 2;
		public final int U_SALES = 3;
		public final int U_STORAGE = 4;
		
		private final String dbDriver; 
		private final String dbName;    
		
		public Database(String dbDriver, String dbName){
			this.dbDriver = dbDriver;
			this.dbName = dbName;
	}
	
	// Executes a statement and returns any results as a String array
	public String[] makeSingleStatement(String statement) throws Exception{

		String[] values = null;
		Connection connection = null;
		Statement sqlStatement = null;
		ResultSet result = null;

		// instantiate driver
		Class.forName(dbDriver);
		
		try{
			connection = DriverManager.getConnection(dbName);
			sqlStatement = connection.createStatement();
			
			// Execute query and check if resultset is returned
			if(sqlStatement.execute(statement)){ 
				
				// if resultset is returned, get results, check number of columns
				result = sqlStatement.getResultSet();					
				ResultSetMetaData resultMeta = result.getMetaData();	

				// collect results in string array
				int noCol = resultMeta.getColumnCount();
				values = new String[noCol];
				while(result.next()){
					for(int i = 0; i < noCol; i++){
						values[i] = result.getString(i+1);	// MySQL column numbering begins at 1
					}
				}
			}
		}catch(SQLException e){
			Cleaner.printMessage(e, "makeSingleStatement()");
		}finally{
			// close database connections
			Cleaner.closeResSet(result);
			Cleaner.closeStatement(sqlStatement);
			Cleaner.closeConnection(connection);
		}
		
		return values;
	}
	
	public ResultSet makeTransaction(String[] statements){
		
		ResultSet result = null;
		
		return result;
	}
		
	public boolean registerUser(int userType, String name, String password){
	
		return false;
	}
	// INSERT INTO user VALUES (userType, name, password)
	
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
		
		String username = "espenme";
		String password = "";
		Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=" + username + "&password=" + password);
		
		String statement = "SELECT * FROM byer WHERE bynr = '1'";
		
		String[] result = database.makeSingleStatement(statement);
		
		for(int i = 0; i < result.length; i++){
			System.out.println(result[i]);
		}
		
	}
	
}
