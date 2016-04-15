package testController;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import controller.Meal;
import controller.SubPlan;
import databasePackage.Database;

public class TestSubPlan {
	private static Database database;
	private static SubPlan instance;
	private static Meal meal;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		
		instance = new SubPlan(1, "name"); //subPlanID, name
		meal = new Meal(1, "spaghetti", "none", true, 100);
	
	}
	
	@Before
	public void setUp(){
	}
	
	
	@Test
	public void testShouldAddMealToSubPlan(){
		System.out.println("Test: add meal to subplan");
		
		boolean res = instance.addMealToSubPlan(meal, 2);
		boolean expRes = true;
		
		assertEquals(res, expRes);
		
	}
	@Test
	public void testShouldFailAddMealToSubPlan(){
		System.out.println("Test: fail add meal to subplan");
		
		boolean res = instance.addMealToSubPlan(meal,9);
		boolean expRes = false;
		
		assertEquals(res, expRes);
	}
	
	@Test
	public void testShouldRemoveMealFromPlan(){
		System.out.println("Test: remove meal from subplan");
		
		boolean res = instance.removeMealFromPlan(2);
		boolean expRes = true;
		
		assertEquals(res, expRes);
		
	}
	@Test
	public void testShouldFailRemoveMealFromPlan(){
		System.out.println("Test: fail to remove meal from plan");
		
		boolean res = instance.removeMealFromPlan(9);
		boolean expRes = false;
		
		assertEquals(res, expRes);
	}
	
	@Test
	public void testShouldUpdateSupPlan() throws Exception{
		System.out.println("Test: update subplan");
		
		boolean res = instance.updateSubPlan(database);
		boolean expRes = true;
		
		assertEquals(res, expRes);
		
	}
}
