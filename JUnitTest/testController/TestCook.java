package testController;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import controller.Cook;
import controller.Ingredient;
import controller.Meal;
import controller.SubPlan;
import databasePackage.Database;

public class TestCook {
	private static Database database;
	private Cook cook;
	private SubPlan instance;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
	
	}

	@Before
	public void setUp() throws Exception {
		cook = new Cook("bob", 1,"Bob","qwerty", database);
		instance = new SubPlan(1, "name");
		
	}
	
	@Test
	public void testShouldCreateMeal() throws Exception {
		System.out.println("Test: Create meal");
		// try to register new meal
		Meal result = cook.createMeal("Spaghetti", "Boil pasta", true, 100);
		Meal expResult = new Meal(1,"Spaghetti", "None", true, 100);
		
		assertEquals(result, expResult);
		
	}
		
	@Ignore("Missing method")
	public void testShoulRegisterSubPlan() throws Exception{
		System.out.println("Test: Register subscriptionplan");
		SubPlan result = cook.registerSubPlan("name", database);
		SubPlan expResult = instance.viewSubPlan(); 	
		// ****ViewSubPlan must be created in class User ****
		
		assertEquals(result, expResult);
	
	}

}
