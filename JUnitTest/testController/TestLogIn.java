package testController;

import static org.junit.Assert.*;
import org.junit.*;
import controller.LogIn;
import databasePackage.Database;

public class TestLogIn {
	private static Database database;
	LogIn login;
	private static char[] pword = {'1','2','3','4'};
	private static String[] trymTabl = new String[3];

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		
		String insert = "INSERT INTO user VALUES('trym123',1,'Trym Hansen',1234)";
		database.makeSingleStatement(insert);
		
	
	}

	@Before
	public void setUp() throws Exception {
		login = new LogIn();
		trymTabl[0]= "trym123";
		trymTabl[1]= "1";
		trymTabl[2]="Trym Hansen";
	}

	@Test
	public void testShouldLogIn() throws Exception{
		System.out.println("Test: log in");
		String[] res = login.logIn("trym123", pword, database);
		
		boolean result = res[0].equals(trymTabl[0]) && res[1].equals(trymTabl[1]) && res[2].equals(trymTabl[2]);
		boolean expRes = true;
		
		assertEquals(expRes, result);
		
	}
	
	@Test
	public void testShouldFailLogIn() throws Exception{
		System.out.println("Test: fail log in");
		
		String[] tabl = login.logIn("trine43", pword, database);
		
		boolean result = !(tabl == null);
		boolean expRes = false;
		
		assertEquals(expRes, result);
	}

}
