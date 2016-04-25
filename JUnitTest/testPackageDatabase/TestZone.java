package testPackageDatabase;

import org.junit.Before;
import org.junit.Test;

import database.*;

public class TestZone {
	public Database database;
	
	@Before
	public void beforeTest(){
		String username = "espenme";
		String password = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	@Test
	public void zoneTest() throws Exception{

		// view all entries
		String[][] zones = QMCustomer.viewZones(database);
		// select last entry
		String[] zone = zones[2];
				
	}
}
