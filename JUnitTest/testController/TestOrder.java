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
	private static Order order1;
	private static Order order2;
	
	private static MealOrdered pizza;
	private static MealOrdered taco;
	
	private static ArrayList<MealOrdered> mealsOrdered = new ArrayList<MealOrdered>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		
		System.out.println("Total number of tests: 4");
		
		String insert = "INSERT INTO user VALUES('trym123', 1, 'Trym Larsen', '1234');";
		database.makeSingleStatement(insert);
		
		String customer1 = "INSERT INTO customer VALUES(60000, 'Hansen', 'Geir', '73329090', 'geir@hansen.com', 'Nedre Bakklandet 61', 7014, 1, 'Allergisk mot sopp', true);";
		String customer2 = "INSERT INTO customer VALUES(60001, 'Tvedt', 'Jens', '95454565', 'jens@tvedt.com', 'Gateveien 115', 7021, 3, NULL, true);";
		database.makeSingleStatement(customer1);
		database.makeSingleStatement(customer2);
		

		String insert4 = "INSERT INTO food_order VALUES(1,'2016-11-04',60000,NULL,'trym123')";
		String insert5 = "INSERT INTO food_order VALUES(2,'2016-11-04',60001,NULL,'trym123')";
		
		database.makeSingleStatement(insert4);
		database.makeSingleStatement(insert5);

		order1 = new Order(1, "2016-11-04", 60000, null, "trym123");
		order2 = new Order(2, "2016-11-04", 60001, null, "trym123");

		
		String insert3 = "INSERT INTO ingredient VALUES(1, 'cheese', 10, 'kg')";
		String insert6 = "INSERT INTO ingredient VALUES(2, 'meat', 20, 'kg')";
		database.makeSingleStatement(insert3);
		database.makeSingleStatement(insert6);
		
		
		String insert1 = "INSERT INTO meal VALUES(1, 'pizza', NULL, true, 120)";
		String insert2 = "INSERT INTO meal VALUES(2, 'taco', NULL, true, 100)";
		
		database.makeSingleStatement(insert1);
		database.makeSingleStatement(insert2);
		
		
		String insert9= "INSERT INTO meal_ingredient VALUES(1,1,2)";
		String insert10 = "INSERT INTO meal_ingredient VALUES(2,2,1)";
		database.makeSingleStatement(insert9);
		database.makeSingleStatement(insert10);
		

		String insert7 = "INSERT INTO ordered_meal VALUES(1,1, '2016-11-04',3,0,0)";
		String insert8 = "INSERT INTO ordered_meal VALUES(2,2, '2016-11-04',3,0,0)";
		
		database.makeSingleStatement(insert7);
		database.makeSingleStatement(insert8);
	
		
		pizza = new MealOrdered(1,"pizza", null, 120, "2016-11-04",1,1,false,false);
		taco = new MealOrdered(2, "taco", null, 100, "2016-11-04",2,2,false, false);
		mealsOrdered.add(pizza);
		mealsOrdered.add(taco);
		
	
	}
	
	@Test
	public void testShouldFetchMealsInOrder() throws Exception{
		System.out.println("Order test 1: Fetch meals in order");
		
		order1.fetchMealsInOrder(database);
		mealsOrdered = order1.getMeals();
		
		boolean res = mealsOrdered.get(0).equals(pizza);
		boolean expRes = true;
		assertEquals(res, expRes);
	}


	@Test
	public void testShouldRemoveMealFromOrder() throws Exception{
		System.out.print("Order test 2: Remove meal from order");
		
		order2.fetchMealsInOrder(database);
		
		boolean res = order2.removeMealFromOrder(0, database);
		boolean expRes = true;
		assertEquals(res, expRes);
	}
	
	
	@Test
	public void testShouldUploadOrderToDatabase() throws Exception{
		System.out.println("Order test 3: Upload order to database");
		
		order1.setOrderDate("2016-03-02");
		order1.setInfo("Info");
		
		boolean res = order1.uploadOrder(database);
		boolean expRes = true;
		assertEquals(res, expRes);
	}
	
	@Test
	public void testShouldViewMealsInOrderByDate() throws Exception{
		System.out.println("Order test 4: View meals in order by date");
		java.sql.Date date = java.sql.Date.valueOf("2016-11-04");
		
		boolean res = (order1.viewMealsInOrderByDate(date, database).get(0).equals(mealsOrdered.get(0)));
		boolean expRes = true;
		assertEquals(res, expRes);
	}
}
