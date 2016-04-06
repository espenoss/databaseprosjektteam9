package databaseprosjekt;

import databasePackage.*;
import org.junit.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUserMethods {
	
	@BeforeClass
	// opprett databaseforbindelse
	public static void setUpClass(){
		String brukernavn = "marith1";
		String passord = "tgp8sBZA";
		String databasenavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + brukernavn + "?user=" + brukernavn + "&password=" + passord;
		
		Database database = new Database("com.mysql.jdbc.Driver", databasenavn);
	}
	
	@AfterClass
	// lukk databaseforbindelse
	public static void tearDownClass(){
		
	}
	
	@Before
	public void beforeTest(){
	
		
	}
	
	@After
	public void afterTest(){
	
	}

	@Test
	public void testRegisterUser() {
		fail("Not yet implemented");
	}
	
	@Ignore
	public void testLogIn() {
		fail("Not yet implemented");
	}
	
	@Ignore
	public void testRegisterCustomer() {
		fail("Not yet implemented");
	}
	
	@Ignore
	public void testRegisterCompany() {
		fail("Not yet implemented");
	}
	
	@Ignore
	public void testRegisterOrder() {
		fail("Not yet implemented");
	}
	
	@Ignore
	public void testRegisterSubscription() {
		fail("Not yet implemented");
	}
	
	@Ignore
	public void testUpdateOrder() {
		fail("Not yet implemented");
	}
	
	@Ignore
	public void testUpdateCompany() {
		fail("Not yet implemented");
	}
	@Ignore
	public void testUpdateCustomer() {
		fail("Not yet implemented");
	}
	

}
