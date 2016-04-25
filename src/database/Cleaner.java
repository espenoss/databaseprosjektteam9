package database;
import java.sql.*;

/**
 * The Class Cleaner.
 * Contains convenience methods for database interactions
 */
public class Cleaner {
	
	/**
	 * Close result set.
	 *
	 * @param res Reult set to be closed
	 */
	public static void closeResSet(ResultSet res){
	    try {
	        if (res != null) {
	          res.close();
	        }
	      }catch (SQLException e) {
	        printMessage(e, "closeResSet()");
	      }		
	}
	
	/**
	 * Close statement.
	 *
	 * @param stm The statement to be closed
	 */
	public static void closeStatement(Statement stm){
	    try {
	        if (stm != null) {
	          stm.close();
	        }
	      } catch (SQLException e) {
	        printMessage(e, "closeStatement()");
	      }
	}
	
	/**
	 * Close connection.
	 *
	 * @param con The connection to be closed
	 */
	public static void closeConnection(Connection con){
	    try {
	        if (con != null) {
	          con.close();
	        }
	      } catch (SQLException e) {
	        printMessage(e, "closeConnection()");
	      }		
	}
	
	/**
	 * Rollback.
	 * Rolls back transaction.
	 *
	 * @param con Database connection
	 */
	public static void rollback(Connection con){
	    try {
	        if (con != null && !con.getAutoCommit()) {
	          con.rollback();
	        }
	      } catch (SQLException e) {
	        printMessage(e, "rollback()");
	      }		
	}
		
	/**
	 * Prints the message.
	 *
	 * @param e the e
	 * @param message the message
	 */
	public static void printMessage(Exception e, String message){
	    System.err.println("*** Error occured: " + message + ". ***");
	    e.printStackTrace(System.err);		
	}
}
