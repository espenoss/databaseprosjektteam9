package testController;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import controller.Customer;
import controller.Meal;
import controller.Order;
import databasePackage.Database;
import databaseguiii.MealsInOrder;

// IKKE FERDIG
public class TestOrder {
	private static Database database;
	private static Order order;
	private static ArrayList<Meal> meals = new ArrayList<Meal>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		
		String insert = "INSERT INTO user VALUES('trym123', 1, 'Trym Larsen', '1234')";
		String insert1 = "INSERT INTO zone VALUES(4, 'Heimdal')";
		String insert2 = "INSERT INTO customer VALUES(10, 'Henrik', 'Hansen', '73909090', 'henrik@hansen.no', 'Gateveien 1', 7098, 4, 'none', true)";
		String insert3 = "INSERT INTO food_order VALUES(1,'2016-01-01', 10, 'none', 'trym123')";

		database.makeSingleStatement(insert);
		database.makeSingleStatement(insert1);
		database.makeSingleStatement(insert2);
		database.makeSingleStatement(insert3);
		
		Meal pizza = new Meal(2, "pizza", "none", true, 140);
		meals.add(pizza);
		order = new Order(1, "2016-01-01", 10, "none", "trym123");
		
		order.addMeal(pizza);
	}

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testShouldMarkMealAsReady() throws Exception{
		System.out.println("Test: Mark meal as ready");
		boolean res = order.markMealAsReady(0, "2016-01-03", database);
		boolean expRes = true;
		
		assertEquals(res, expRes);
	}
	
	@Test
	public void testShouldMarkMealAsDelivered() throws Exception{
		System.out.println("Test: Mark meal as delivered");
		boolean res = order.markMealAsDelivered(0, "2016-01-01", database);
		boolean expRes = true;
		
		assertEquals(res, expRes);
	}
	 
	@Test
	public void testShouldUploadOrder() throws Exception{
		System.out.println("Test: Update order");
		
		boolean res = order.uploadOrder(database);
		boolean expRes = true;
		
		assertEquals(res, expRes);
	}
	 
	// ***** METHOD NOT FINISHED IN CONTROLLER.ORDER *****
	@Ignore
	public void testShouldFetchMealsByDeliveryDate(){
		System.out.println("Test: Fetch meals by delivery date");
		
		// void fetchMealsByDeliveryDate(Date deliveryDate, Database database)
	}
	

}
