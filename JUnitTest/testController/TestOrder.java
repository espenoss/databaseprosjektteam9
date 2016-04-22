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
import controller.MealOrdered;
import controller.Order;
import controller.User;
import databasePackage.Database;
import databaseguiii.MealsInOrder;

public class TestOrder {
	private static Database database;
	private static Order order;
	private static MealOrdered pizza2;
	private static MealOrdered taco2;
	
	private static ArrayList<MealOrdered> mealsOrdered = new ArrayList<MealOrdered>();
	private static ArrayList<Meal> meals = new ArrayList<Meal>();
	private static ArrayList<Order> orders = new ArrayList<Order>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();

		String insert = "INSERT INTO user VALUES('trym123', 1, 'Trym Larsen', '1234');";
		database.makeSingleStatement(insert);
		
		String customer1 = "INSERT INTO customer VALUES(60000, 'Hansen', 'Geir', '73329090', 'geir@hansen.com', 'Nedre Bakklandet 61', 7014, 1, 'Allergisk mot sopp', true);";
		String customer2 = "INSERT INTO customer VALUES(60001, 'Tvedt', 'Jens', '95454565', 'jens@tvedt.com', 'Gateveien 115', 7021, 3, NULL, true);";
		database.makeSingleStatement(customer1);
		database.makeSingleStatement(customer2);
		
// order_id, order_date, customer_id, info, user_id
		String insert4 = "INSERT INTO food_order VALUES(1,'2016-02-02',60000,NULL,'trym123')";
		String insert5 = "INSERT INTO food_order VALUES(2,'2016-02-02',60001,NULL,'trym123')";
		
		database.makeSingleStatement(insert4);
		database.makeSingleStatement(insert5);
		
		Order order1 = new Order(1, "2016-02-02", 60000, null, "trym123");
		Order order2 = new Order(2, "2016-02-02", 60001, null, "trym123");
		
		orders.add(order1);
		orders.add(order2);
		
//		meal_id, meal_name, instructions, available, price
		String insert1 = "INSERT INTO meal VALUES(1, 'pizza', NULL, true, 120)";
		String insert2 = "INSERT INTO meal VALUES(2, 'taco', NULL, true, 100)";
		
		database.makeSingleStatement(insert1);
		database.makeSingleStatement(insert2);
		

		// order_id, meal_id, delivery_date, quantity, ready_delivery, delivered		
		String insert7 = "INSERT INTO ordered_meal VALUES(1,1, '2016-05-04',3,0,0)";
		String insert8 = "INSERT INTO ordered_meal VALUES(2,2, '2016-04-05',3,0,0)";
		
		database.makeSingleStatement(insert7);
		database.makeSingleStatement(insert8);
		
		//int mealID, String mealName, String instructions, boolean available, int price
		Meal pizza1 = new Meal(1,"pizza",null, true, 100);
		Meal taco1 = new Meal(2,"taco", null, true, 90);
		meals.add(pizza1);
		meals.add(taco1);

		
		pizza2 = new MealOrdered(1,"pizza", null, 120, "2016-05-04",1,1,false,false);
		taco2 = new MealOrdered(2, "taco", null, 100, "2016-04-05",2,2,false, false);
		mealsOrdered.add(pizza2);
		mealsOrdered.add(taco2);
		
	
	}
	
	@Test
	public void testShouldFetchMealsInOrder() throws Exception{
		System.out.println("Order test 1: Fetch meals in order");
		
		System.out.println(meals.size());
		for(int i = 0; i < meals.size(); i++){
			System.out.println(meals.get(i));
		}
		order.fetchMealsInOrder(database);
		mealsOrdered = order.getMeals();
		
		boolean res = mealsOrdered.get(0).equals(pizza2);
		boolean expRes = true;
		assertEquals(res, expRes);
	}

	
	
	@Ignore // Ikke ferdig
	public void testShouldRemoveMealFromOrder() throws Exception{
		System.out.print("Order test 2: Remove meal from order");
		
		order.fetchMealsInOrder(database);
		
		boolean res = order.removeMealFromOrder(0, database);
		boolean expRes = true;
		assertEquals(res, expRes);
//		boolean removeMealFromOrder(int index, Database database)
	}
	
	
	@Ignore
	public void testShouldUploadOrderToDatabase() throws Exception{
//		boolean uploadOrder(Database database) 
	}
	
	@Ignore
	public void testShouldViewMealsInOrderByDate() throws Exception{
//		ArrayList<MealOrdered> viewMealsInOrderByDate(java.sql.Date date, Database database)
	}
	
	
/*	@Test
	public void testShouldMarkMealAsReady() throws Exception{
		System.out.println("Test: Mark meal as ready");
		boolean res = order.markMealAsReadyByDate(0, "2016-01-03", database);
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
	
	@Test
	public void testShouldUploadMealInOrder() throws Exception{
		System.out.println("Test: Upload meal in order to database");
	}
	 
	// ***** METHOD NOT FINISHED IN CONTROLLER.ORDER *****
	@Ignore
	public void testShouldFetchMealsByDeliveryDate(){
		System.out.println("Test: Fetch meals by delivery date");
		
		// void fetchMealsByDeliveryDate(Date deliveryDate, Database database)
	}
*/	

}
