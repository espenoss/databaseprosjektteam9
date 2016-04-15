package controller;

import databasePackage.*;

public class LogIn {

	String username = "espenme";
	String passingword = "16Sossosem06";
	String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
	Database database = null;
	public LogIn(){
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	public int logIn(String userID, char[] password) throws Exception{
		return QMUser.logIn(userID, password, database);
	}
}


