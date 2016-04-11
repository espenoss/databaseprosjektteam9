package controller;

import databasePackage.*;

public class ViewAllCustomers {

	String username = "espenme";
	String passingword = "16Sossosem06";
	String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
	Database database = null;
	public ViewAllCustomers(){
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	public String[][] ViewAllCustomersList() throws Exception{
		return QueryMethods.viewAllCustomers(database);
	}
}
