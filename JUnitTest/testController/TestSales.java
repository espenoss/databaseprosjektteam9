package testController;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import controller.*;
import controller.*;
import databasePackage.Database;

public class TestSales {
	private static Database database;
	private Sales instance;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "marith1";
		String password = "tgp8sBZA";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		
		String userInsert = "INSERT INTO user VALUES('hanne', 3,'Hanne Hansen', 123456)";
		String zoneInsert1 = "INSERT INTO zone VALUES(1, 'Midtbyen');";
		String zoneInsert2 = "INSERT INTO zone VALUES(2, 'Østbyen');";
		String zoneInsert3 = "INSERT INTO zone VALUES(3, 'Lerkendal');";
		String zoneInsert4 = "INSERT INTO zone VALUES(4, 'Heimdal');";
		String zoneInsert5 = "INSERT INTO zone VALUES(5, 'Byåsen');";
		
		database.makeSingleStatement(userInsert);
		database.makeSingleStatement(zoneInsert1);
		database.makeSingleStatement(zoneInsert2);
		database.makeSingleStatement(zoneInsert3);
		database.makeSingleStatement(zoneInsert4);
		database.makeSingleStatement(zoneInsert5);
		
		
		
	}

	@Before
	public void setUp() throws Exception {
		instance = new Sales("hanne",3,"Hanne Hansen", "123456", database);
		
	}
	
	@Test
	public void testRegisterCustomer() throws Exception {
		System.out.println("test registerCustomer");
		Customer result = instance.registerCustomer("Hansen","Geir","73329090" , "geir@hansen.com", "Nedre Bakklandet 61", 7014, 3,"Allergisk mot sopp", true, database);
		
		Customer expResult = instance.viewSingleCustomer(result.getCustomerID());
		

		assertEquals(result, expResult);
	}
	
	@Ignore
	public void testRegisterCompanyToCustomer() {
		fail("Not yet implemented");
	}
	
	@Ignore
	public void testRegisterNewOrder() {
		fail("Not yet implemented");
	}
	
	@Ignore
	public void testRegisterSubscriptionOrder() {
		fail("Not yet implemented");
	}

}
