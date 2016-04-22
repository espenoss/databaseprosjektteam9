package testController;

import static org.junit.Assert.*;

import org.junit.*;

import controller.Admin;
import databasePackage.Database;

public class TestAdmin {
	private static Database database;
	private static Admin instance;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "marith1";
		String password = "tgp8sBZA";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
	}
	
	
	@Test
	public void testGetStatisticsForYear() {
		System.out.println("Admin test 1: getStatisticsForYear");
		
		fail("Not yet implemented");
	}
	
	@Test
	public void testViewUserList() {
		System.out.println("Admin test 2: viewUserList");
		
		fail("Not yet implemented");
	}
	

}
