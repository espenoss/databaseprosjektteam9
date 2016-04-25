package controller;

import database.*;

/**
 * The Class LogIn.
 * Contains the login method
 */
public class LogIn {
	
	/**
	 * Log in.
	 *
	 * @param userID User id
	 * @param password Password
	 * @param database Database connection
	 * @return String[] of user information
	 */
	public String[] logIn(String userID, char[] password, Database database) {
		return QMUser.logIn(userID, password, database);
	}
}


