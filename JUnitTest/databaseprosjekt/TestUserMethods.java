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
		boolean result = userM.registerUser("Hanne", 3, "Hanne Hansen", "1234", instance);
		assertEquals(expResult, result);
		
		//(String userID, int userType, String name, String password, Database database)
		
		//Try to register same user again
		expResult = false; 
		result = userM.registerUser("Hanne", 3, "Hanne H", "1234", instance);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testLogIn() throws Exception{
		System.out.println("Test logIn");
		UserMethods userM = new UserMethods();
		
		//try to log in
		userM.registerUser("Marie", 3, "Marie M", "1234", instance);
		int result = userM.logIn("Marie", "1234", instance);
		int expResult = 3;
		assertEquals(expResult, result);
		
		//login with wrong password
		expResult = -1; 
		result = userM.logIn("Marie", "  ", instance);
		assertEquals(expResult, result);
		
		//login with nonexcisting user
		expResult = -1; 
		result = userM.logIn("Marit", "1234", instance);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testRegisterCustomer() throws Exception{
		System.out.println("Test register customer");
		UserMethods userM = new UserMethods();
		
		boolean expResult = true;
		boolean result = userM.registerCustomer("Larsen", "Geir", "73123456", "geir@larsen.no", "Erling Skakkes gate 66", 7012, 1, "none", 1, instance);
		
		assertEquals(expResult, result);
		
	}
	
	@Test
	public void testRegisterCompany() throws Exception{
		System.out.println("Test: register company");
		UserMethods userM = new UserMethods();
		
		boolean expResult = true;
		boolean result = userM.registerCompany("Hansen", "Abraham", "73309090", "ab.hansen@franksblomster.com", "Bakkegata 123", 7014, 3, "none", 1, "Franks blomster", instance);
		
		assertEquals(expResult, result);
		
	}
	
	@Ignore
	public void registerSingleOrder() {
		System.out.println("Test: Regiser single order");
		UserMethods userM = new UserMethods();
		
		boolean expResult = true;
		boolean result = userM.registerSingleOrder("2016-01-03", customer_id, info, user_id, mealID, deliveryDate, quantity, database);
		
		assertEquals(expResult, result);
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
