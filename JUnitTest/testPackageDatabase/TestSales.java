package testPackageDatabase;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databasePackage.Database;

public class TestSales {
	private static Database database;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "marith1";
		String password = "";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}

	@Before
	public void setUp() throws Exception {
		Sales instance = new Sales
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
