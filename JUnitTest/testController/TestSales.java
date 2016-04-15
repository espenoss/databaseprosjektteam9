package testController;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import controller.Sales;
import databasePackage.Database;

public class TestSales {
	private static Database database;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "marith1";
		String password = "";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		
		String userInsert = "INSERT INTO user VALUES('hanne', 3,'Hanne Hansen', 123456)";
		String zoneInsert1 = "INSERT INTO zone VALUES(1, 'Midtbyen');";
		String zoneInsert2 = "INSERT INTO zone VALUES(2, 'Østbyen');";
		String zoneInsert3 = "INSERT INTO zone VALUES(3, 'Lerkendal');";
		String zoneInsert4 = "INSERT INTO zone VALUES(4, 'Heimdal');";
		String zoneInsert5 = "INSERT INTO zone VALUES(5, 'Byåsen');";
		
		
	}

	@Before
	public void setUp() throws Exception {
		Sales instance = new Sales("hanne",3,"Hanne Hansen", "123456", database);
		
	}
	
	@Ignore
	public void testRegisterCustomer() {
		fail("Not yet implemented");
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
