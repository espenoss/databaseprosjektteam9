package controller;

import databasePackage.*;

public class LogIn {
	public String[] logIn(String userID, char[] password, Database database) throws Exception{
		return QMUser.logIn(userID, password, database);
	}
}


