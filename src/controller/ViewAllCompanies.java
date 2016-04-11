package controller;

import databasePackage.*;

public class ViewAllCompanies{

	String username = "espenme";
	String passingword = "16Sossosem06";
	String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
	Database database = null;
	public ViewAllCompanies(){
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	public String[][] ViewAllCompaniesList() throws Exception{
		return QueryMethods.viewAllCompanies(database);
	}
}