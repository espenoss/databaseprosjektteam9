package controller;

import databasePackage.*;

public class LogIn {
	public String[] logIn(String userID, char[] password, Database database) {
		return QMUser.logIn(userID, password, database);
	}
}


