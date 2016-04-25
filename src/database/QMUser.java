package database;

import java.util.Arrays;

//All query methods related to users and login

public class QMUser {
	
	// User types
	public static final int U_ADMIN = 0;
	public static final int U_COOK = 1;
	public static final int U_DRIVER = 2;
	public static final int U_SALES = 3;
	

	
	// Method for registering new user in database
	public static boolean registerUser(String userID, int userType, String name, String password, Database database) {
		
		String statement = "INSERT INTO user VALUES(" 
				+ aq(userID) 
				+ userType + ", '" 
				+ name + "', '" 
				+ password 
				+ "');";

		return database.makeSingleStatement(statement);
	}

	// Update user information
	public static boolean updateUser(String userID, int userType, String name, String password, Database database) {
		String statement = "UPDATE user SET "
				+ "user_id =" + aq(userID)
				+ "user_type =" + userType + ","
				+ "name = " + aq(name)
				+ "password = '" + password + "' " 
				+ "WHERE user_id = '" + userID + "';";
		return database.makeSingleStatement(statement);
	}
	
	// Delete user entry from database
	public static boolean removeUser(String userID, Database database) {
		String statement = "DELETE FROM user WHERE user_id = '" + userID + "';";
		return database.makeSingleStatement(statement);
	}

	// View single user
	// Returns information on user in database in a String array
	// Columns by second index:
	// 0 : user_id - String
	// 1 : user_type - int
	// 2 : name - String
	// 3 : password - String
	public static String[] viewUser(String userID, Database database) {
		String statement = "SELECT user_id, user_type, name FROM user WHERE user_id = '" + userID + "';";		
		database.makeSingleStatement(statement);

		return database.getLastResult()[0];
	}	
	
	// View list of all users. 
	// Returns a list of all users in database in a two-dimensional String array
	// First index is row, second is column
	// Columns by second index:
	// 0 : user_id - String
	// 1 : user_type - int
	// 2 : name - String
	// 3 : password - String
	public static String[][] viewAllUsers(Database database) {		
		database.makeSingleStatement("SELECT user_id, user_type, name FROM user");
		
		return database.getLastResult();		
	}	

	// Confirms user details
	public static String[] logIn(String userID, char[] password, Database database) {
		
		String[][] userInfo = null;
		
		String statement = "SELECT user_id, user_type, name FROM user WHERE user_id = '" + userID + "' AND password ='" + String.valueOf(password) + "'";
		
		database.makeSingleStatement(statement);	
		
		userInfo = database.getLastResult();
		
		if(userInfo.length  == 0){
			return null;
		}else{
			return userInfo[0];
		}
	}
	
	
	// Puts a ' on either side and a comma at the end  of a string 
	public static String aq(String s){
		return "'" + s + "', ";
	}
}