package testController;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.Cook;
import controller.Ingredient;
import controller.Meal;
import controller.SubPlan;
import databasePackage.Database;

public class TestCook {
	private Database database;
	private Meal meal;
	private Cook cook;
	private Ingredient potato;
	private Ingredient carrot;
	private Ingredient cheese;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//	public static void main(String[] args){
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		Database database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		/*String insert1 = "INSERT INTO ingredient VALUES(1, 'potato', 50, 'kg')";
		String insert2 = "INSERT INTO ingredient VALUES(2, 'carrot', 30, 'kg')";
		String insert3 = "INSERT INTO ingredient VALUES(3, 'cheese', 10, 'kg')";
		database.makeSingleStatement(insert1);
		database.makeSingleStatement(insert2);
		database.makeSingleStatement(insert3);*/
		
		// addIngredients(Ingredient obj, float quantity)
	}



	@Before
	public void setUp() throws Exception {
		
	}
	
	// NULLPOINTEREXCEPTION
	@Test
	public void test() throws Exception {
		Cook cook = new Cook("bob", 1,"Bob","qwerty", database);
		Meal meal = new Meal(1, "soup", "Cut vegetables", true, 100);
		
		Ingredient potato = new Ingredient(1, "potato", 50, "kg");
		Ingredient carrot = new Ingredient(2, "carrot", 30, "kg");
		Ingredient cheese = new Ingredient(3, "cheese", 10, "kg");
		
		
		meal.addIngredients(potato, 3);
		meal.addIngredients(carrot, 2);
		meal.addIngredients(cheese, 1);
		
		// try to register new meal
		boolean result = cook.createMeal(meal);
		boolean expResult = true;
		assertEquals(result, expResult);
		
		// try to register same meal twice
	//	boolean result1 = cook.createMeal(meal);
	//	boolean expResult1 = false;
	//	assertEquals(result1, expResult1);
		
		//boolean createMeal(Meal meal) - creates new meal
		//Meal(int mealID, String mealName, String instructions, boolean available, int price)
		
		
		
		//SubPlan registerSubPlan(String name,Database database) - registers new, empty subplan
	}

}
