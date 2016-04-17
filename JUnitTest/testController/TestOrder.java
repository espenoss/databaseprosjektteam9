package testController;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import controller.Meal;
import controller.Order;
import databasePackage.Database;
import databaseguiii.MealsInOrder;

// IKKE FERDIG
public class TestOrder {
	private static Database database;
	private Order order;
	private static ArrayList<Meal> meals = new ArrayList<Meal>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		
		String insert = "INSERT INTO meal VALUES(1, 'spaghetti', 'none', true, 100)";
		String insert2 = "INSERT INTO meal VALUES(2, 'pizza', 'none', true, 130)";
		String insert3 = "INSERT INTO meal VALUES(3, 'taco', 'none', true, 90)";
		
		database.makeSingleStatement(insert);
		database.makeSingleStatement(insert2);
		database.makeSingleStatement(insert3);
		
		/*Meal spaghetti = new Meal(1, "spaghetti", "none", true, 100);
		Meal pizza = new Meal(2, "pizza", "none", true, 140);
		Meal taco = new Meal(3, "taco", "none", true, 90);
		
		meals.add(spaghetti);
		meals.add(pizza);
		meals.add(taco);
		*/
		// new user?
	}

	@Before
	public void setUp() throws Exception {
		order = new Order(1, "2016-01-01", 1, "none", "trym123");
	}
	
	@Ignore
	public void test() throws Exception{
		boolean res = true;
		boolean res2 = true;
		assertEquals(res, res2);
	}
	
	@Test
	public void testShouldMarkMealAsReady() throws Exception{
		// boolean markMealAsReady(int index, String deliveryDate, Database database)
		System.out.println("Test: mark meal as ready");
		boolean res = order.markMealAsReady(1, "2016-01-03", database);
		boolean expRes = true;
		
		assertEquals(res, expRes);
	}
	
	@Ignore
	public void testShouldMarkMealAsDelivered(){
		// boolean markMealAsDelivered(int index, String deliveryDate, Database database)
	}
	
	@Ignore
	public void testShouldUploadOrder(){
		// boolean uploadOrder(Database database)
	}
	
	@Ignore
	public void testShouldFetchMealsByDeliveryDate(){
		// void fetchMealsByDeliveryDate(Date deliveryDate, Database database)
	}

}
