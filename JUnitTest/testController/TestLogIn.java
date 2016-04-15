package testController;

import databasePackage.QMUser;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import controller.LogIn;
import controller.User;
import databasePackage.Database;
import databasePackage.QMUser;

public class TestLogIn {
	private static Database database;
	LogIn login;
	private static char[] pword = {'1','2','3','4'};

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
	}

	@Test
	public void testShouldLogIn() throws Exception{
		System.out.println("Test: log in");
		
		int res = login.logIn("trym123", pword);
		int expRes = 1;
		
		assertEquals(res, expRes);
		
	}
	@Test
	public void testShouldFailLogIn() throws Exception{
		System.out.println("Test: fail log in");
		
		int res = login.logIn("trine43", pword);
		int expRes = -1;
		
		assertEquals(res, expRes);
	}

}
