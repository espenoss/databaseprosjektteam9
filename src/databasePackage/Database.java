package databasePackage;
import java.sql.*;
import java.util.*;

public class Database {		
		private final String dbDriver; 
		private final String dbName;    
		
		public Database(String dbDriver, String dbName){
			this.dbDriver = dbDriver;
			this.dbName = dbName;
	}
	
	// Executes a statement and returns any results as a two dimensional String array
	public String[][] makeSingleStatement(String statement) throws Exception{

		ArrayList<String[]> values = new ArrayList<String[]>();		
		Connection connection = null;
		Statement sqlStatement = null;
		ResultSet result = null;

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
			}
		}catch(SQLException e){
			Cleaner.printMessage(e, "makeSingleStatement()");
		}finally{
			// close database connections
			Cleaner.closeResSet(result);
			Cleaner.closeStatement(sqlStatement);
			Cleaner.closeConnection(connection);
		}

		// Convert from arraylist to array
		String[][] r = new String[values.size()][];
		values.toArray(r);
		
		return r;
	}
	
	// Executes a transaction from list of update statements
	public void makeUpdateTransaction(String[] statements) throws Exception{
		
		Connection connection = null;
		Statement sqlStatement = null;

		// instantiate driver
		Class.forName(dbDriver);
		
		try{
			// Establish database connection and disable autocommit
			connection = DriverManager.getConnection(dbName);
			sqlStatement = connection.createStatement();
			connection.setAutoCommit(false);
			
			// Assume only update statements in transaction
			for(int i=0; i<statements.length; i++){
				sqlStatement.executeUpdate(statements[i]); 
			}
			// Commit changes if everything went well
			connection.commit();
		}catch(SQLException e){
			Cleaner.rollback(connection);
			Cleaner.printMessage(e, "makeSingleStatement()");
		}finally{
			// close database connections
			connection.setAutoCommit(true);
			Cleaner.closeStatement(sqlStatement);
			Cleaner.closeConnection(connection);
		}
	}
		
	public static void main(String[] args) throws Exception{

		// testkode
		
		String username = "";
		String password = "";
		Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=" + username + "&password=" + password);
		
		String statement = "SELECT * FROM byer";
		
		String[][] result = database.makeSingleStatement(statement);
		
		for(int i = 0; i < result.length; i++){
			for(int y = 0; y < result[i].length; y++){
				System.out.print(result[i][y] + " ");
			}
			System.out.print("\n");			
		}
		
		
		
//		String statement2 = "INSERT INTO byer VALUES(5, 'Narvik')";		
//		database.makeSingleStatement(statement2);		
	}
	
}
