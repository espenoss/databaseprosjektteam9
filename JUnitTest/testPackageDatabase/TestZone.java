package testPackageDatabase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import databasePackage.Database;
import databasePackage.QueryMethods;

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
		String[][] zones = QueryMethods.viewZones(database);
		// select last entry
		String[] zone = zones[2];
				
		// attempt to register new info about existing entry
		boolean exp = QueryMethods.updateZone(Integer.parseInt(zone[0]), "Ny info", database);
		assertEquals(true, exp);		
		
		// attempt to remove entry		
		exp = QueryMethods.removeZone(Integer.parseInt(zone[0]), database);
		assertEquals(true, exp);

		// attempt to reregister removed entry	
		int iexp = QueryMethods.registerZone(zone[1], database);
		assertNotEquals(-1, iexp);		
	}
}
