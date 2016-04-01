package databasePackage;
import java.sql.*;

public class Cleaner {
	
	public static void closeResSet(ResultSet res){
	    try {
	        if (res != null) {
	          res.close();
	        }
	      }catch (SQLException e) {
	        printMessage(e, "closeResSet()");
	      }		
	}
	
	public static void closeStatement(Statement stm){
	    try {
	        if (stm != null) {
	          stm.close();
	        }
	      } catch (SQLException e) {
	        printMessage(e, "closeStatement()");
	      }
	}
	
	public static void closeConnection(Connection con){
	    try {
	        if (con != null) {
	          con.close();
	        }
	      } catch (SQLException e) {
	        printMessage(e, "closeConnection()");
	      }		
	}
	
	public static void rollback(Connection con){
	    try {
	        if (con != null && !con.getAutoCommit()) {
	          con.rollback();
	        }
	      } catch (SQLException e) {
	        printMessage(e, "rollback()");
	      }		
	}
	
	public static void setAutoCommit(Connection con){
	    try {
	        if (con != null && !con.getAutoCommit()) {
	          con.setAutoCommit(true);
	        }
	      } catch (SQLException e) {
	        printMessage(e, "setAutoCommit()");
	      }		
	}
	
	public static void printMessage(Exception e, String message){
	    System.err.println("*** Error occured: " + message + ". ***");
	    e.printStackTrace(System.err);		
	}
}
