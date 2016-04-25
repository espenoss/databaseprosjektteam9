package testPackageDatabase;

import org.junit.*;

import database.*;

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

		QMUser.removeUser("Nils", database);		
		
		//Register new user
		boolean expResult = true;
		boolean result = QMUser.registerUser("Nils", 3, "Nils Hansen", "1234", database);
		assertEquals(expResult, result);
/*		
		//Try to register same user again
		expResult = false; 
		result = QueryMethods.registerUser("Nils", 3, "Nils Hansen", "1234", database);
		assertEquals(expResult, result);
*/		
		expResult = true;
		result = QMUser.updateUser("Nils", 3, "Nils Veem", "1234", database);
		assertEquals(expResult, result);

		String[] resultString = QMUser.viewUser("Nils", database);
		
		assertEquals(resultString[0],"Nils");
		assertEquals(resultString[1],"3");
		assertEquals(resultString[2],"Nils Veem");		
				
		expResult = true;
		result = QMUser.removeUser("Nils", database);
		assertEquals(expResult, result);
		
	}
	
}
