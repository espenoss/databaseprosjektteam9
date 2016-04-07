package testPackageDatabase;

import databasePackage.*;
import org.junit.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUserMethods {
	private static Database database;
	private static UserMethods instance;
	
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
		String username = "marith1";
		String password = "tgp8sBZA";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
		instance = new UserMethods();
	}
	
	@After
	public void afterTest(){
	}

	@Test
	public void testRegisterUser() throws Exception{
		System.out.println("Test registrer new user");
		
		//Register new user
		boolean expResult = true;
		boolean result = instance.registerUser("Hanne", 3, "Hanne Hansen", "1234", database);
		assertEquals(expResult, result);
		
		//(String userID, int userType, String name, String password, Database database)
		
		//Try to register same user again
		expResult = false; 
		result = instance.registerUser("Hanne", 3, "Hanne H", "1234", database);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testLogIn() throws Exception{
		System.out.println("Test logIn");
		
		//try to log in
		instance.registerUser("Marie", 3, "Marie M", "1234", database);
		
		int result = instance.logIn("Marie", "1234", database);
		int expResult = 3;
		assertEquals(expResult, result);
		
		
		//login with wrong password
		expResult = -1; 
		result = instance.logIn("Marie", "  ", database);
		assertEquals(expResult, result);
		
		
		//login with nonexcisting user
		expResult = -1; 
		result = instance.logIn("Marit", "1234", database);
		assertEquals(expResult, result);
		
	}
	
	@Test
	public void testRegisterCustomer() throws Exception{
		System.out.println("Test register customer");
		
		boolean expResult = true;
		boolean result = instance.registerCustomer(1, "Larsen", "Geir", "73123456", "geir@larsen.no", "Erling Skakkes gate 66", 7012, 1, "none", 1, database);
		
		assertEquals(expResult, result);
		
	}
	
	@Test
	public void testRegisterCompany() throws Exception{
		System.out.println("Test: register company");
		
		boolean expResult = true;
		boolean result = instance.registerCompany(2, "Hansen", "Abraham", "73309090", "ab.hansen@franksblomster.com", "Bakkegata 123", 7014, 3, "none", 1, "Franks blomster", database);
		
		assertEquals(expResult, result);
		
	}
	
	@Test
	public void registerSingleOrder() throws Exception{
		System.out.println("Test: Regiser single order");
		
		
		boolean expResult = true;
		boolean result = instance.registerSingleOrder(1,"2016-01-03", 1, "none", "Marie", 1, "2016-01-04", 4, database);
		
		assertEquals(expResult, result);
	}
	
	@Test 
	public void testRegisterIngredient() throws Exception{
		System.out.println("Test registerIngredient");
		
		boolean result = instance.registerIngredient("Fish", 3, database);
		boolean expResult = true;
		
		assertEquals(expResult, result);
		
	}
	
	@Ignore
	public void testRegisterMeal() throws Exception{
		System.out.println("Test registerMeal");
		
		int[] ingredients = {1, 2, 3};
		int[] quantity = {1, 10, 15};
		
		boolean result = instance.registerMeal("Chicken pasta", "Make it!", 1, 200, 0, 0, ingredients, quantity, database);
		boolean expResult = true;
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
