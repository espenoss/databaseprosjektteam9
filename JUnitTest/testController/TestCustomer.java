package testController;

import databasePackage.QueryMethods;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databasePackage.Database;

public class TestCustomer {
	private static Database database;
	private Customer customer;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		customer = new Customer(1, "Jens", "Svendsen", "12345678", "", String adress, int zipCode, int zoneNr, String preferences, boolean active){
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShouldUpdateCustomer() {
		//boolean updateCustomer(Database database)
	}

}
