package databasePackage;

//All query methods related to users and login

public class QMUser {
	
	// User types
	public static final int U_ADMIN = 0;
	public static final int U_COOK = 1;
	public static final int U_DRIVER = 2;
	public static final int U_SALES = 3;
	public static final int U_STORAGE = 4;
	

	
	// Method for registering new user in database
	public static boolean registerUser(String userID, int userType, String name, String password, Database database) throws Exception{
		
		String statement = "INSERT INTO user VALUES(" 
				+ aq(userID) 
				+ userType + ", '" 
				+ name + "', '" 
				+ password 
				+ "');";

		return database.makeSingleStatement(statement);
	}

	// Update user information
	public static boolean updateUser(String userID, int userType, String name, String password, Database database) throws Exception{
		String statement = "UPDATE user SET "
				+ "user_id =" + aq(userID)
				+ "user_type =" + userType + ","
				+ "name = " + aq(name)
				+ "password = '" + password + "' " 
				+ "WHERE user_id = '" + userID + "';";
		return database.makeSingleStatement(statement);
	}
	
	// Delete user entry from database
	public static boolean removeUser(String userID, Database database) throws Exception{
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
	public static String[] viewUser(String userID, Database database) throws Exception{
		String statement = "SELECT user_id, user_type, name FROM user WHERE user_id = '" + userID + "';";
		System.out.println(statement);		
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
	public static String[][] viewAllUsers(Database database) throws Exception{		
		database.makeSingleStatement("SELECT user_id, user_type, name FROM user");
		
		return database.getLastResult();		
	}	

	// Confirms user details
	public static int logIn(String userID, char[] password, Database database) throws Exception{
		
		String[][] userType = null;
		
		String statement = "SELECT user_type FROM user WHERE user_id = '" + userID + "' AND password ='" + String.valueOf(password) + "'";
		
		database.makeSingleStatement(statement);	
		
		userType = database.getLastResult();
		
		if(userType.length == 0){ // If no such user or password is incorrect
			return -1;
		}else{
			return Integer.parseInt(userType[0][0]);
		}
	}
	
	
	// Puts a ' on either side and a comma at the end  of a string 
	public static String aq(String s){
		return "'" + s + "', ";
	}
}