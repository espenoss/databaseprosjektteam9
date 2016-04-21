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
import databasePackage.Database;
import databaseguiii.MealsInOrder;

public class TestOrder {
	private static Database database;
	private static Order order;
	private static ArrayList<MealOrdered> meals = new ArrayList<MealOrdered>();
	private static ArrayList<Meal> mealList = new ArrayList<Meal>();

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
		String customer2 = "INSERT INTO customer VALUES(60001, 'Andersen', 'Lise Carina', '93081295', 'lise@andersen.com', 'Moltmyra 111', 7091, 4, 'Liker ikke skalldyr', true);";
		String customer3 = "INSERT INTO customer VALUES(60002, 'Andersen', 'Lise Carina', '93081295', 'lise@andersen.com', 'Moltmyra 111', 7091, 4, 'Liker ikke skalldyr', true);";
		database.makeSingleStatement(customer1);
		database.makeSingleStatement(customer2);
		database.makeSingleStatement(customer3);
		
// order_id, order_date, customer_id, info, user_id
		String insert4 = "INSERT INTO food_order VALUES(1,'2016-02-02',60000,NULL,'trym123')";
		String insert5 = "INSERT INTO food_order VALUES(2,'2016-02-02',60001,NULL,'trym123')";
		String insert6 = "INSERT INTO food_order VALUES(3,'2016-02-02',60002,NULL,'trym123')";
		
		database.makeSingleStatement(insert4);
		database.makeSingleStatement(insert5);
		database.makeSingleStatement(insert6);
		
//		meal_id, meal_name, instructions, available, price
		String insert1 = "INSERT INTO meal VALUES(1, 'pizza', NULL, true, 120)";
		String insert2 = "INSERT INTO meal VALUES(2, 'taco', NULL, true, 100)";
		String insert3 = "INSERT INTO meal VALUES(3, 'steak',NULL, true, 200)";
		
		database.makeSingleStatement(insert1);
		database.makeSingleStatement(insert2);
		database.makeSingleStatement(insert3);
		

		// order_id, meal_id, delivery_date, quantity, ready_delivery, delivered		
		String insert7 = "INSERT INTO ordered_meal VALUES(1,1, '2016-05-04',3,0,0)";
		String insert8 = "INSERT INTO ordered_meal VALUES(2,2, '2016-04-05',3,0,0)";
		String insert9 = "INSERT INTO ordered_meal VALUES(3,3, '2016-05-05',2,0,0)";
		
		database.makeSingleStatement(insert7);
		database.makeSingleStatement(insert8);
		database.makeSingleStatement(insert9);
		
		//int mealID, String mealName, String instructions, boolean available, int price
		Meal pizza1 = new Meal(1,"pizza",null, true, 100);
		Meal taco1 = new Meal(2,"taco", null, true, 90);
		Meal steak1 = new Meal(3, "steak", null, true,200);
		mealList.add(pizza1);
		mealList.add(taco1);
		mealList.add(steak1);
		
		MealOrdered pizza2 = new MealOrdered(1,"pizza", null, 120, "2016-05-04",1,1,false,false);
		MealOrdered taco2 = new MealOrdered(2, "taco", null, 100, "2016-04-05",2,2,false, false);
		MealOrdered steak2 = new MealOrdered(3, "steak", null, 200, "2016-05-05",2,3,false,false);
		meals.add(pizza2);
		meals.add(taco2);
		meals.add(steak2);
		
		/*
		String insert = "INSERT INTO user VALUES('trym123', 1, 'Trym Larsen', '1234')";
		String insert1 = "INSERT INTO customer VALUES(10, 'Henrik', 'Hansen', '73909090', 'henrik@hansen.no', 'Gateveien 1', 7098, 4, 'none', true)";
		String insert2 = "INSERT INTO food_order VALUES(1,'2016-01-01', 10, 'none', 'trym123')";

		database.makeSingleStatement(insert);
		database.makeSingleStatement(insert1);
		database.makeSingleStatement(insert2);
		
	
		order = new Order(1, "2016-01-01", 10, "none", "trym123");
		
		order.addMeal(pizza, "2016-01-01");
		*/
	}


	@Test
	public void testShouldMarkMealAsReadyByIndex() throws Exception{
//		boolean markMealAsReadyByIndex(int index, Database database)
		System.out.println("Test 1: Mark meal as ready by index");
		
		boolean res = order.markMealAsReadyByIndex(0, database);
		boolean expRes = true;
		assertEquals(res, expRes);
		
	}
	
	@Ignore
	public void testShouldMarkMealAsDelivered() throws Exception{
//		boolean markMealAsDelivered(int index, Database database)
	}
	
	@Ignore
	public void testShouldMarkAllMealsAsReadyByDate() throws Exception{
//		int markAllMealsAsReadyByDate(java.sql.Date date, Database database)
	}
	
	@Ignore
	public void testShouldUploadOrderToDatabase() throws Exception{
//		boolean uploadOrder(Database database)
	}
	
	@Ignore
	public void testShouldViewMealsInOrderByDate() throws Exception{
//		ArrayList<MealOrdered> viewMealsInOrderByDate(java.sql.Date date, Database database)
	}
	
	@Ignore
	public void testShouldFetchMealsInOrder() throws Exception{
//		void fetchMealsInOrder(Database database)
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
