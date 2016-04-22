package testController;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.Ingredient;
import databasePackage.Database;

public class TestIngredient {
	private static Database database;
	private Ingredient ingredient;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		String insert1 = "INSERT INTO ingredient VALUES(1, 'potato', 50, 'kg')";
		String insert2 = "INSERT INTO ingredient VALUES(2, 'carrot', 30, 'kg')";
		String insert3 = "INSERT INTO ingredient VALUES(3, 'cheese', 10, 'kg')";
		database.makeSingleStatement(insert1);
		database.makeSingleStatement(insert2);
		database.makeSingleStatement(insert3);
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShouldUpdateStorage() throws Exception{
		System.out.println("Ingredient test 1: Update storage test");
		ingredient = new Ingredient(1, "poteto", 150, "kg");
		
		boolean res = ingredient.updateStorage(database);
		boolean expRes = true;
		
		assertEquals(res, expRes);
	}

}
