package controller;
import databasePackage.Database;
import databasePackage.QueryMethods;

public class RegisterCustomer {
	
		String username = "espenme";
		String passingword = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
		Database database = null;
		public RegisterCustomer(){
			database = new Database("com.mysql.jdbc.Driver", databasename);
		}
		
		public int RegisterCustomer(String surName, String firstName, String phoneNumber, String email, String adress, 
				int zip_code, int zone_nr, String preferences, boolean active, Database database) throws Exception{
			return QueryMethods.registerCustomer(surName, firstName, phoneNumber, email, adress, zip_code, zone_nr, preferences, active, database);
		}
	}



