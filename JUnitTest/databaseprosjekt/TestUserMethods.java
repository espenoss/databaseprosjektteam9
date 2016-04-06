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
		
		Database instance = new Database("com.mysql.jdbc.Driver", databasenavn);
	}
	
	@After
	public void afterTest(){
	}

	@Test
	public void testRegisterUser() throws Exception{
		System.out.println("Test register new user");
		UserMethods userM = new UserMethods();
		
		boolean expResult = true;
		boolean result = userM.registerUser(3, "Hanne", "1234", instance);
		
		assertEquals(expResult, result);
		
		expResult = false; 
		result = userM.registerUser(3, "Hanne", "1234", instance);
		assertEquals(expResult, result);
	}
	
	@Ignore
	public void testLogIn() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testRegisterCustomer() throws Exception{
		System.out.println("Test register customer");
		UserMethods userM = new UserMethods();
		
		boolean expResult = true;
		boolean result = userM.registerCustomer("Geir", "Larsen", "geir@larsen.no", "Erling Skakkes gate 66", 7012, 1, "ingen", 1, instance);
		
		assertEquals(expResult, result);
		
		expResult = false;
		result = userM.registerCustomer("Geir", "Larsen", "geir@larsen.no", "Erling Skakkes gate 66", 7012, 1, "ingen", 1, instance);
		assertEquals(expResult, result);
		
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
