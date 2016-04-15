package testController;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.Ingredient;
import databasePackage.Database;

public class TestIngredient {
	private Database database;
	private Ingredient ingredient;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		Database database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws Exception{
		System.out.println("Update storage test");
		ingredient = new Ingredient(1, "potato", 50, "kg");
		
		boolean res = ingredient.updateStorage(database);
		boolean expRes = true;
		
		assertEquals(res, expRes);
	}

}
