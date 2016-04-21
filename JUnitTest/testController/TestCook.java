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
	private static Cook cook;
	private SubPlan subPlan;	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		
		String insert = "INSERT INTO ingredient VALUES(1,'Mushroom',50,'stk');";
		String insert1 = "INSERT INTO meal VALUES(1,'Pasta carbonara', NULL, true, 120);";
		database.makeSingleStatement(insert);
		database.makeSingleStatement(insert1);
		
		System.out.println("Total number of tests: 7");

	}

	@Before
	public void setUp() throws Exception {
		cook = new Cook("bob","Bob", database);
		subPlan = new SubPlan(1, "name");
		
	} 
	
	@Test
	public void testShouldCreateMeal() throws Exception {
		System.out.println("Test 1: Create meal");
		
		Meal result = cook.createMeal("Spaghetti", "Boil pasta", 100);
		Meal expResult = new Meal(2,"Spaghetti", null, true, 100);
		
		assertEquals(result, expResult);	
	}
	
	
	@Test
	public void testShoulRegisterSubPlan() throws Exception{
		System.out.println("Test 2: Register subscriptionplan");
		boolean result = cook.registerSubPlan("name", database).equals(subPlan);
		boolean expResult = true;
		
		assertEquals(result, expResult);
	}
	
	@Test
	public void testShouldAddIngredientToMeal() throws Exception{
		System.out.println("Test 3: Add ingredient to meal");
			
		boolean res = cook.addIngredientToMeal(1, 1, 2);
		boolean expRes = true;
		
		assertEquals(res, expRes);
	}
	
	@Test
	public void testShouldAddMealToSubPlan() throws Exception{
		System.out.println("Test 4: add meal to subscription plan");
		
		boolean res = cook.addMealToSubPlan(1, 1, 3);
		boolean expRes = true;
		assertEquals(res, expRes);
	}
	@Test 
	public void testShouldRemoveMealFromPlan() throws Exception{
		System.out.println("Test 5: Remove meal from plan");
		cook.addMealToSubPlan(1, 1, 2);
		
		
		boolean res = cook.removeMealFromPlan(1, 1, 3);
		boolean expRes = true;
		assertEquals(res, expRes);
		
	}
	
	@Test
	public void testShouldAddNewIngredient() throws Exception{
		System.out.println("Test 6: add new ingredient to meal");
		
		Ingredient res = cook.addNewIngredient("banana", 40, "kg", database);
		Ingredient expRes = new Ingredient(2, "banana", 40, "kg");
		assertEquals(res, expRes);
	}

	@Ignore("Can not delete ingredient connected to a meal in 'meal_ingredient'")
	public void testShouldDeleteIngredient() throws Exception{
		System.out.println("Test: Delete ingredient");
		
		boolean res = cook.deleteIngredient(1);
		boolean expRes = true;
		assertEquals(res, expRes);
//		deleteIngredient
	}
}
