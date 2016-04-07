package testPackageDatabase;

import databasePackage.*;
import org.junit.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUserMethods {
	private static Database database;
	
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
		String username = "espenme";
		String password = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	@After
	public void afterTest(){
	}

	@Test
	public void testRegisterUser() throws Exception{
		System.out.println("Test register new user");
		
		//Register new user
		boolean expResult = true;
		boolean result = UserMethods.registerUser("Hanne", 3, "Hanne Hansen", "1234", database);
		assertEquals(expResult, result);
				
		//Try to register same user again
		expResult = false; 
		result = UserMethods.registerUser("Hanne", 3, "Hanne H", "1234", database);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testLogIn() throws Exception{
		System.out.println("Test logIn");
		
		//try to log in
		UserMethods.registerUser("Marie", 3, "Marie M", "1234", database);
		
		int result = UserMethods.logIn("Marie", "1234", database);
		int expResult = 3;
		assertEquals(expResult, result);
		
		
		//login with wrong password
		expResult = -1; 
		result = UserMethods.logIn("Marie", "  ", database);
		assertEquals(expResult, result);
		
		
		//login with nonexcisting user
		expResult = -1; 
		result = UserMethods.logIn("Marit", "1234", database);
		assertEquals(expResult, result);
		
	}
	
	@Test
	public void testRegisterCustomer() throws Exception{
		System.out.println("Test register customer");
		
		int errorResult = -1;
		int result = UserMethods.registerCustomer("Larsen", "Geir", "73123456", "geir@larsen.no", "Erling Skakkes gate 66", 7012, 1, "none", 1, database);
		
		assertNotEquals(errorResult, result);
		
	}
	
	@Test
	public void testRegisterCompanyToCustomer() throws Exception{
		System.out.println("Test: register company");
		
		boolean expResult = true;
		boolean result = UserMethods.registerCompanyToCustomer(1, "Franks blomster", database);
		
		assertEquals(expResult, result);
		
	}
	
	@Test
	public void registerOrder() throws Exception{
		System.out.println("Test: Register order");
		
		
		int errorResult = -1;
		int result = UserMethods.registerOrder("2016-01-03", 10000, "none", "Marie", database);
		
		assertNotEquals(errorResult, result);
	}
	
	@Test 
	public void testRegisterIngredient() throws Exception{
		System.out.println("Test registerIngredient");
		
		int result = UserMethods.registerIngredient("Fish", 3, "kg", database);
		int errorResult = -1;
		
		assertNotEquals(errorResult, result);
		
	}
	
	@Ignore
	public void testRegisterMeal() throws Exception{
		System.out.println("Test registerMeal");
		
		int result = UserMethods.registerMeal("Chicken pasta", "Make it!", 1, 200, 0, 0, database);
		int errorResult = -1;
		assertEquals(errorResult, result);
		
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
