package testController;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.Ingredient;
import controller.Meal;
import database.Database;
import database.QMFood;

public class TestMeal {
	private static Database database;
	private static Meal soup;
	private ArrayList <Ingredient> ing = new ArrayList<Ingredient>();

	
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
		
		String insert4 = "INSERT INTO meal VALUES(2, 'soup', 'none', true, 90)";
	
		
		database.makeSingleStatement(insert1);
		database.makeSingleStatement(insert2);
		database.makeSingleStatement(insert3);
		
		database.makeSingleStatement(insert4);
		
		
		QMFood.addIngredientToMeal(2, 1, 2, database);
		QMFood.addIngredientToMeal(2, 2, 3, database);
		QMFood.addIngredientToMeal(2, 3, 10, database);
	
		soup = new Meal(2, "soup", "none", true, 90);
	
	}

	@Before
	public void setUp() throws Exception {
	}
	
	
	@Test
	public void testShouldFetchIngredients() throws Exception {
		System.out.println("Meal test 1: Fetch ingredients");
		
		
		soup.fetchIngredients(database);
		ing = soup.getIngredients();
		
		boolean res0 = ing.get(0).getIngID() == 1 && ing.get(0).getIngName().equals("potato");
		boolean expRes0 = true;
		assertEquals(res0, expRes0);
		
		boolean res1 = ing.get(1).getIngID() == 2 && ing.get(1).getIngName().equals("carrot");
		boolean expRes1 = true;
		assertEquals(res1, expRes1);
		
		boolean res2 = ing.get(2).getIngID() == 3 && ing.get(2).getIngName().equals("cheese");
		boolean expRes2 = true;
		assertEquals(res2, expRes2);
	}
	
	
	@Test
	public void testShouldUpdateMealToDatabase() throws Exception{
		System.out.println("Meal test 2: Update meal to database");
		
		boolean res = soup.updateMealToDatabase(database);
		boolean expRes = true;
		
		assertEquals(res, expRes);	
	}

}
