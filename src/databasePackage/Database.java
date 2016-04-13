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
	
	
	public boolean initiateDb(String table, String createTable){
        boolean ok = false;
        Connection connection = null;                  
    	Statement statement = null;
    	
        try {    
        	Class.forName(dbDriver);        
        	connection = DriverManager.getConnection(dbName);                    
        	statement = connection.createStatement();
        	String sqlDeleteTable = "DROP TABLE IF EXISTS "+table;
	        String sqlCreateTable = createTable;
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
