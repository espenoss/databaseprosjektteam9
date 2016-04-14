package databasePackage;
import java.sql.*;
import java.util.*;

public class Database {		
		private final String dbDriver; 
		private final String dbName;
		private String[][] lastResult = null;
		private String [][][] lastResults = null;
		
		public Database(String dbDriver, String dbName){
			this.dbDriver = dbDriver;
			this.dbName = dbName;
	}

	public String[][] getLastResult() {
		return lastResult;
	}	

	public String[][][] getLastResults() {
		return lastResults;
	}
	
	// Executes a statement and returns any results as a two dimensional String array
	public boolean makeSingleStatement(String statement) throws Exception{

		boolean success = true;
		ArrayList<String[]> values = new ArrayList<String[]>();		
		Connection connection = null;
		Statement sqlStatement = null;
		ResultSet result = null;
		lastResult = null;
		
		// instantiate driver
		Class.forName(dbDriver);
		
		try{
			// Establish database connection
			connection = DriverManager.getConnection(dbName);
			sqlStatement = connection.createStatement();
			
			// Execute query and check if resultset is returned
			if(sqlStatement.execute(statement)){ 
				
				// if resultset is returned, get results, check number of columns
				result = sqlStatement.getResultSet();					
				ResultSetMetaData resultMeta = result.getMetaData();	
				int noCol = resultMeta.getColumnCount();

				// collect results in arraylist of string arrays				
				int x = 0;
				while(result.next()){
					values.add(new String[noCol]);
					for(int y = 0; y < noCol; y++){
						values.get(x)[y] = result.getString(y+1);	// MySQL column numbering begins at 1
					}
					x++;
				}
				// Convert from arraylist to array
				lastResult= new String[values.size()][];
				values.toArray(lastResult);
			}
		}catch(SQLException e){
			Cleaner.printMessage(e, "makeSingleStatement()");
			success = false;
		}finally{
			// close database connections
			Cleaner.closeResSet(result);
			Cleaner.closeStatement(sqlStatement);
			Cleaner.closeConnection(connection);
		}
		
		return success;
	}
	
	// Executes a transaction from list of statements
	public boolean makeTransaction(String[] statements) throws Exception{

		boolean success = true;		
		ArrayList<String[][]> valueSet = new ArrayList<String[][]>();		
		Connection connection = null;
		Statement sqlStatement = null;		
		ResultSet result = null;
		lastResults = null;
		
		// instantiate driver
		Class.forName(dbDriver);
		
		try{
			// Establish database connection and disable autocommit
			connection = DriverManager.getConnection(dbName);
			sqlStatement = connection.createStatement();
			connection.setAutoCommit(false);
			
			// Execute statements and collect results
			for(int i=0; i<statements.length; i++){
				if(sqlStatement.execute(statements[i])){
					// if resultset is returned, get results, check number of columns
					ArrayList<String[]> values = new ArrayList<String[]>();
					result = sqlStatement.getResultSet();					
					ResultSetMetaData resultMeta = result.getMetaData();	
					int noCol = resultMeta.getColumnCount();

					// collect results in arraylist of string arrays				
					int x = 0;
					while(result.next()){
						values.add(new String[noCol]);
						for(int y = 0; y < noCol; y++){
							values.get(x)[y] = result.getString(y+1);	// MySQL column numbering begins at 1
						}
						x++;
					}
					// Add results from query to result list
					String[][] temp = new String[values.size()][];
					values.toArray(temp);
					valueSet.add(temp);
					Cleaner.closeResSet(result);
				}				
			}

			// Store results from transaction
			String[][][] temp = new String[valueSet.size()][][];
			valueSet.toArray(temp);
			lastResults = temp;

			// Commit changes if everything went well
			connection.commit();
			
		}catch(SQLException e){
			Cleaner.rollback(connection);
			Cleaner.printMessage(e, "makeSingleStatement()");
			success = false;
		}finally{
			// close database connections
			connection.setAutoCommit(true);
			Cleaner.closeResSet(result);			
			Cleaner.closeStatement(sqlStatement);
			Cleaner.closeConnection(connection);
		}
		
		return success;
	}
	
	
	public boolean initiateDb(){
        boolean ok = false;
        Connection connection = null;                  
    	Statement statement = null;
    	
        try {    
        	Class.forName(dbDriver);        
        	connection = DriverManager.getConnection(dbName);                    
        	statement = connection.createStatement();
        	String sqlDeleteTable = "DROP TABLE IF EXISTS sub_meals_day; "
        			+ "DROP TABLE IF EXISTS ordered_meal; "
        			+ "DROP TABLE IF EXISTS meal_ingredient; "
        			+ "DROP TABLE IF EXISTS ingredient; "
        			+ "DROP TABLE IF EXISTS sub_order; "
        			+ "DROP TABLE IF EXISTS subscription_plan; "
        			+ "DROP TABLE IF EXISTS meal; "
        			+ "DROP TABLE IF EXISTS food_order; "
        			+ "DROP TABLE IF EXISTS company; "
        			+ "DROP TABLE IF EXISTS customer; "
        			+ "DROP TABLE IF EXISTS user; "
        			+ "DROP TABLE IF EXISTS zone;";
	        String sqlCreateTable = 
	        	"CREATE TABLE zone(zone_nr INTEGER NOT NULL, zone_name VARCHAR(30) NOT NULL, CONSTRAINT zone_pk PRIMARY KEY (zone_nr));"
	        	+"CREATE TABLE user(user_id VARCHAR(20) NOT NULL, user_type INTEGER NOT NULL, name VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL, CONSTRAINT user_pk PRIMARY KEY (user_id));"
	        	+"CREATE TABLE customer(customer_id INTEGER NOT NULL, surname VARCHAR(30), firstname VARCHAR(30), phone_number CHAR(8),email VARCHAR(50), adress VARCHAR(100), zip_code  INTEGER NOT NULL, zone_nr  INTEGER NOT NULL, preferences VARCHAR(100), active BOOLEAN, CONSTRAINT customer_pk PRIMARY KEY (customer_id));"
	        	+"CREATE TABLE company(customer_id INTEGER NOT NULL, company_name VARCHAR(30), CONSTRAINT company_pk PRIMARY KEY (customer_id));"
	        	+"CREATE TABLE food_order(order_id INTEGER NOT NULL, order_date DATE NOT NULL, customer_id INTEGER NOT NULL, info VARCHAR(200), user_id VARCHAR(20) NOT NULL, CONSTRAINT order_pk PRIMARY KEY (order_id));"
	        	+"CREATE TABLE sub_order(order_id INTEGER NOT NULL, quantity_sub INTEGER NOT NULL, from_date DATE NOT NULL, to_date DATE, sub_id INTEGER, CONSTRAINT sub_order_pk PRIMARY KEY(order_id));"
	        	+"CREATE TABLE subscription_plan(sub_id INTEGER NOT NULL, sub_name VARCHAR(30) NOT NULL, CONSTRAINT sub_plan_pk PRIMARY KEY(sub_id));"
	        	+"CREATE TABLE meal(meal_id INTEGER NOT NULL, meal_name VARCHAR(30), instructions VARCHAR(200), available BOOLEAN, price INTEGER, CONSTRAINT meal_pk PRIMARY KEY (meal_id));"
	        	+"CREATE TABLE ingredient(ingredient_id INTEGER NOT NULL, name VARCHAR(30), quantity FLOAT, unit VARCHAR(30), CONSTRAINT ingredient_pk PRIMARY KEY (ingredient_id));"
	        	+"CREATE TABLE meal_ingredient(meal_id INTEGER NOT NULL, ingredient_id INTEGER NOT NULL, quantity FLOAT NOT NULL, CONSTRAINT meal_ingredient_pk PRIMARY KEY(meal_id, ingredient_id));"
	        	+"CREATE TABLE ordered_meal(order_id INTEGER NOT NULL, meal_id INTEGER NOT NULL, delivery_date DATE NOT NULL, quantity INTEGER NOT NULL, ready_delivery BOOLEAN NOT NULL, delivered BOOLEAN NOT NULL, CONSTRAINT ordered_meal_pk PRIMARY KEY(order_id, meal_id, delivery_date));"
	        	+"CREATE TABLE sub_meals_day( sub_id INTEGER NOT NULL, meal_id INTEGER NOT NULL, weekday_nr INTEGER NOT NULL, weekday VARCHAR(10), CONSTRAINT sub_meals_day_pk PRIMARY KEY(sub_id, meal_id, weekday_nr));"
	        	+"ALTER TABLE customer ADD CONSTRAINT customer_fk1 FOREIGN KEY(zone_nr) REFERENCES zone (zone_nr);"
	        	+"ALTER TABLE company ADD CONSTRAINT company_fk1 FOREIGN KEY(customer_id) REFERENCES customer(customer_id);"
	        	+"ALTER TABLE food_order ADD CONSTRAINT order_fk1 FOREIGN KEY(user_id) REFERENCES user(user_id);"
	        	+"ALTER TABLE food_order ADD CONSTRAINT order_fk2 FOREIGN KEY(customer_id) REFERENCES customer (customer_id);"
	        	+"ALTER TABLE sub_order ADD CONSTRAINT sub_order_fk1 FOREIGN KEY(order_id) REFERENCES food_order(order_id), ADD CONSTRAINT sub_order_fk2 FOREIGN KEY(sub_id) REFERENCES subscription_plan(sub_id);"
	        	+"ALTER TABLE meal_ingredient ADD CONSTRAINT meal_ingredient_fk1 FOREIGN KEY(meal_id) REFERENCES meal(meal_id), ADD CONSTRAINT meal_ingredient_fk2 FOREIGN KEY(ingredient_id) REFERENCES ingredient(ingredient_id);"
	        	+"ALTER TABLE ordered_meal ADD CONSTRAINT ordered_meal_fk1 FOREIGN KEY(order_id) REFERENCES food_order(order_id), ADD CONSTRAINT ordered_meal_fk2 FOREIGN KEY(meal_id) REFERENCES meal(meal_id);"
	        	+"ALTER TABLE sub_meals_day ADD CONSTRAINT sub_meals_day_fk1 FOREIGN KEY(sub_id) REFERENCES subscription_plan(sub_id), ADD CONSTRAINT sub_meals_day_fk2 FOREIGN KEY(meal_id) REFERENCES meal(meal_id);";
	        statement.executeUpdate(sqlDeleteTable );
	        statement.executeUpdate(sqlCreateTable);
        	ok = true;
        } catch (ClassNotFoundException e){
        	Cleaner.printMessage(e, "Database.initiateDb(): driver not found");
        }catch (SQLException e){
        	Cleaner.printMessage(e, "Database.initiateDb(): SQL-error.");
        } catch (Exception e){
        	Cleaner.printMessage(e, "Database.initiateDb(): general error");
        } finally{
    		// close database connections
    		Cleaner.closeStatement(statement);
    		Cleaner.closeConnection(connection);
        }
        return ok;
    }
	
	public static void main(String[] args) throws Exception{
		String dbDriver = "com.mysql.jdbc.Driver";
		String username = "espenme";
		String password = "16Sossosem06";
		String dbName = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		Database database = new Database(dbDriver, dbName);
		
		String[][][] results = null;
		
		String[] statements = new String[2];
		
		statements[0] = "SELECT * FROM user";
		statements[1] = "SELECT * FROM customer";

		database.makeTransaction(statements);
		
		results = database.getLastResults();
		
		for(int x=0;x<results.length;x++){
			for(int y=0;y<results[x].length;y++){
				for(int z=0;z<results[x][y].length;z++){
					System.out.print(results[x][y][z] + " ");
				}
				System.out.println("");
			}
			System.out.println("");
		}		
	}
}
