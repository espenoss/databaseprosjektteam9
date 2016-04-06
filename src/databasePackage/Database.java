package databasePackage;
import java.sql.*;
import java.util.*;

public class Database {		
		private final String dbDriver; 
		private final String dbName;
		private String[][] lastResult = null;
		
		public Database(String dbDriver, String dbName){
			this.dbDriver = dbDriver;
			this.dbName = dbName;
	}

	public String[][] getLastResult() {
		return lastResult;
	}	
		
	// Executes a statement and returns any results as a two dimensional String array
	public boolean makeSingleStatement(String statement) throws Exception{

		boolean success = true;
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
			success = false;
		}finally{
			// close database connections
			Cleaner.closeResSet(result);
			Cleaner.closeStatement(sqlStatement);
			Cleaner.closeConnection(connection);
		}

		// Convert from arraylist to array
		lastResult= new String[values.size()][];
		values.toArray(lastResult);
		
		return success;
	}
	
	// Executes a transaction from list of update statements
	public boolean makeUpdateTransaction(String[] statements) throws Exception{
		
		boolean success = true;
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
			success = false;
		}finally{
			// close database connections
			connection.setAutoCommit(true);
			Cleaner.closeStatement(sqlStatement);
			Cleaner.closeConnection(connection);
		}
		
		return success;
	}
	
	
	public boolean initiateDb(String table, String createTable){
        boolean ok = false;
    try {    
        Class.forName(dbDriver);        
        try (Connection connection = DriverManager.getConnection(dbName)) {                    
            try (Statement statement = connection.createStatement()) {
                String sqlSlettgmltabell = "DROP TABLE IF EXISTS "+table;
                String sqlOpprettTabell = createTable;
                statement.executeUpdate(sqlSlettgmltabell);
                statement.executeUpdate(sqlOpprettTabell);
            }
           ok = true; // tom tabell opprettet
        } catch (ClassNotFoundException e){
            Cleaner.printMessage(e, "Database.initiateDb(): driver not found");
        }catch (SQLException e){
            Cleaner.printMessage(e, "Database.initiateDb(): SQL-error.");
        } catch (Exception e){
            Cleaner.printMessage(e, "Database.initiateDb(): general error");
    	} finally{
		// close database connections
		Cleaner.closeStatement(Statement);
		Cleaner.closeConnection(connection);
    	}
        return ok;
    }
	
}
