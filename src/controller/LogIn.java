package controller;

import databasePackage.*;

public class LogIn {

	String username = "espenme";
	char[] password = {'1', '6', 'S', 'o', 's', 's', 'o', 's', 'e', 'm', '0', '6'};
	String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;	
	Database database = null;
	public LogIn(){
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	public int logIn(String userID, char[] password) throws Exception{
		return QueryMethods.logIn(userID, password, database);
	}
}


