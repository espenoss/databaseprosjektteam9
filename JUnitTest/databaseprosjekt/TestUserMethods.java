package databaseprosjekt;

import databasePackage.*;
import org.junit.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUserMethods {
	private static Database instance;
	
	public TestUserMethods(){
	}

	
	@BeforeClass
	// opprett databaseforbindelse
	public static void setUpClass(){

	}
	
	@AfterClass
	// lukk databaseforbindelse
	public static void tearDownClass(){
		
	}
	
	@Before
	public void beforeTest(){
		String brukernavn = "marith1";
		String passord = "tgp8sBZA";
		String databasenavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + brukernavn + "?user=" + brukernavn + "&password=" + passord;
		
		instance = new Database("com.mysql.jdbc.Driver", databasenavn);
	}
	
	@After
	public void afterTest(){
	}

	@Test
	public void testRegisterUser() throws Exception{
		System.out.println("Test registrer new user");
		UserMethods userM = new UserMethods();
		
		//Register new user
		boolean expResult = true;
		boolean result = userM.registerUser(3, "Hanne", "1234", instance);
		assertEquals(expResult, result);
		
		//Try to register same user again
		expResult = false; 
		result = userM.registerUser(3, "Hanne", "1234", instance);
		assertEquals(expResult, result);
	}
	
	@Ignore
	public void testLogIn() {
		System.out.println("Test logIn");
		UserMethods userM = new UserMethods();
		
		userM.registerUser(3, "Marie", "1234", instance);
		int result = userM.logIn("Marie", "1234", instance);
		int expResult = 3;
		assertEquals(expResult, result);
		
		//Try to register same user again
		expResult = -1; 
		result = userM.logIn(name, password, database)
		assertEquals(expResult, result);
		
		
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
	public void registerSingleOrder() {
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
