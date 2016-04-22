package testController;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import controller.Meal;
import controller.SubPlan;
import controller.TextEditor;
import databasePackage.Database;
import databasePackage.QMFood;

public class TestSubPlan {
	private static Database database;
	private static SubPlan instance;
	private static Meal[] meals = new Meal[7];
	private static Meal meal1;
	private static Meal meal3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		
		
		String insert1 = "INSERT INTO meal VALUES(1, 'spaghetti', NULL, true, 100)";
		String insert2 = "INSERT INTO meal VALUES(2, 'hamburger', NULL, true, 100)";
		
		String insert3 = "INSERT INTO subscription_plan VALUES(1, 'Dinner plan')";
		String insert4 = "INSERT INTO sub_meals_day VALUES(1,1,1,'monday')";
		String insert5 = "INSERT INTO sub_meals_day VALUES(1,2,3,'wednesday')";
		
		database.makeSingleStatement(insert1);
		database.makeSingleStatement(insert2);
		database.makeSingleStatement(insert3);
		database.makeSingleStatement(insert4);
		database.makeSingleStatement(insert5);
		
		
		instance = new SubPlan(1, "Dinner plan"); //subPlanID, name
		meal1 = new Meal(1, "spaghetti", "none", true, 100);
		meal3 = new Meal(2, "hamburger", "none", true, 100);
	
		meals[0] = meal1;
		meals[2] = meal3;
		
	}
	
	@Before
	public void setUp(){
	}
	
	
	@Test
	public void testShouldAddMealToSubPlan(){
		System.out.println("SubPlan test 1: add meal to subplan");
		
		boolean res = instance.addMealToSubPlan(meal1, 2);
		boolean expRes = true;
		
		assertEquals(res, expRes);
		
	}
	@Test
	public void testShouldFailAddMealToSubPlan(){
		System.out.println("SubPlan test 2: fail add meal to subplan");
		
		boolean res = instance.addMealToSubPlan(meal1,9);
		boolean expRes = false;
		
		assertEquals(res, expRes);
	}
	
	@Test
	public void testShouldRemoveMealFromPlan(){
		System.out.println("SubPlan test 3: remove meal from subplan");
		
		boolean res = instance.removeMealFromPlan(2);
		boolean expRes = true;
		
		assertEquals(res, expRes);
		
	}
	@Test
	public void testShouldFailRemoveMealFromPlan(){
		System.out.println("SubPlan test 4: fail to remove meal from plan");
		
		boolean res = instance.removeMealFromPlan(9);
		boolean expRes = false;
		
		assertEquals(res, expRes);
	}
	
	@Test
	public void testShouldUpdateSupPlan() throws Exception{
		System.out.println("SubPlan test 5: update subplan");
		
		boolean res = instance.updateSubPlan(database);
		boolean expRes = true;
		
		assertEquals(res, expRes);	
	}

	@Test
	public void testShouldFetchMeals() throws Exception{
		System.out.println("SubPlan test 6: Fetch meals in plan from database");
	
		instance.fetchMealsInPlan(database);
		meals = instance.getMeals();
		
		boolean res = meals[0].equals(meal1);
		boolean expRes = true;
		assertEquals(res, expRes);
		
		boolean res2 = meals[2].equals(meal3);
		boolean expRes2 = true;
		assertEquals(res2, expRes2);
	}
}
