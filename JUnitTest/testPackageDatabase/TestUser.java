package testPackageDatabase;

import databasePackage.*;
import org.junit.*;
import static org.junit.Assert.*;


public class TestUser {

	public Database database;
	
	@Before
	public void beforeTest(){
		String username = "espenme";
		String password = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	@Test
	public void testUser() throws Exception{
		System.out.println("Test register new user");

		QueryMethods.removeUser("Nils", database);		
		
		//Register new user
		boolean expResult = true;
		boolean result = QueryMethods.registerUser("Nils", 3, "Nils Hansen", "1234", database);
		assertEquals(expResult, result);
/*		
		//Try to register same user again
		expResult = false; 
		result = QueryMethods.registerUser("Nils", 3, "Nils Hansen", "1234", database);
		assertEquals(expResult, result);
*/		
		expResult = true;
		result = QueryMethods.updateUser("Nils", 3, "Nils Veem", "1234", database);
		assertEquals(expResult, result);

		String[] resultString = QueryMethods.viewUser("Nils", database);
		
		assertEquals(resultString[0],"Nils");
		assertEquals(resultString[1],"3");
		assertEquals(resultString[2],"Nils Veem");		
				
		expResult = true;
		result = QueryMethods.removeUser("Nils", database);
		assertEquals(expResult, result);
		
	}
	
}
