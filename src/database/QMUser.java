package database;

/**
 * The Class QMUser.
 * Contains database query methods related to users and login<br>
 * Assembles statements from parameters and executes the them
 */
public class QMUser {
		
	/**
	 * Register new user.
	 *
	 * @param userID User ID
	 * @param userType User type
	 * @param name Name of user
	 * @param password User password
	 * @param database Database connection
	 * @return true, if successful
	 */
	public static boolean registerUser(String userID, int userType, String name, String password, Database database) {
		
		String statement = "INSERT INTO user VALUES(" 
				+ aq(userID) 
				+ userType + ", '" 
				+ name + "', '" 
				+ password 
				+ "');";

		return database.makeSingleStatement(statement);
	}

	/**
	 * Update user.
	 *
	 * @param userID User ID
	 * @param userType User type
	 * @param name Name of user
	 * @param password User password
	 * @param database Database connection
	 * @return true, if successful
	 */
	public static boolean updateUser(String userID, int userType, String name, String password, Database database) {
		String statement = "UPDATE user SET "
				+ "user_id =" + aq(userID)
				+ "user_type =" + userType + ","
				+ "name = " + aq(name)
				+ "password = '" + password + "' " 
				+ "WHERE user_id = '" + userID + "';";
		return database.makeSingleStatement(statement);
	}

	/**
	 * View user.<br>
	 * Returns information on user in database in a String array<br>
	 * <br>
	 * <i>Columns by index:</i><br>
	 * 0 : user_id - String<br>
	 * 1 : user_type - int<br>
	 * 2 : name - String<br>
	 *
	 * @param userID User ID
	 * @param database Database connection
	 * @return String[] of user info
	 */
	public static String[] viewUser(String userID, Database database) {
		String statement = "SELECT user_id, user_type, name FROM user WHERE user_id = '" + userID + "';";		
		database.makeSingleStatement(statement);

		return database.getLastResult()[0];
	}	
	
	/**
	 * View all users.<br>
	 * Returns a list of all users in database in a two-dimensional String array<br>
	 * <br>
	 * <i>Columns by second index:</i><br>
	 * 0 : user_id - String<br>
	 * 1 : user_type - int<br>
	 * 2 : name - String<br>
	 * @param database Database connection
	 * @return String[][] with user info
	 */
	public static String[][] viewAllUsers(Database database) {		
		database.makeSingleStatement("SELECT user_id, user_type, name FROM user");
		
		return database.getLastResult();		
	}	

	/**
	 * Log in.
	 * Takes username and password, and returns user info if successful
	 *
	 * @param userID User ID
	 * @param password Password
	 * @param database Database connection
	 * @return String[] with user info
	 */
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
	
	
	/**
	 * 'Add quotes'.<br>
	 * Convenience function to make queries easier to read
	 * Adds single quotes at either end and a comma at the end
	 *
	 * @param s String to add quotes to
	 * @return String with quotes around it
	 */
	public static String aq(String s){
		return "'" + s + "', ";
	}
}