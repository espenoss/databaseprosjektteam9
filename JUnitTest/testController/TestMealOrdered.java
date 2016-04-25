package testController;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import controller.Meal;
import controller.MealOrdered;
import database.Database;

public class TestMealOrdered{
	private static Database db;
	private static MealOrdered instance;
	private static MealOrdered instance2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		db = new Database("com.mysql.jdbc.Driver", databasename);
		db.initiateDb(); 
		
		
//		int mealID, String mealName, String instructions, boolean available, int price
		instance = new MealOrdered(1,"pizza", null, 120, "2016-11-04",1,1,false,false);
		
		String insert = "INSERT INTO user VALUES('trym123', 1, 'Trym Larsen', '1234');";
		db.makeSingleStatement(insert);
		
		String customer1 = "INSERT INTO customer VALUES(60000, 'Hansen', 'Geir', '73329090', 'geir@hansen.com', 'Nedre Bakklandet 61', 7014, 1, 'Allergisk mot sopp', true);";
		String customer2 = "INSERT INTO customer VALUES(60001, 'Tvedt', 'Jens', '95454565', 'jens@tvedt.com', 'Gateveien 115', 7021, 3, NULL, true);";
		db.makeSingleStatement(customer1);
		db.makeSingleStatement(customer2);
		

		String insert4 = "INSERT INTO food_order VALUES(1,'2016-11-04',60000,NULL,'trym123')";
		String insert5 = "INSERT INTO food_order VALUES(2,'2016-11-04',60001,NULL,'trym123')";
		
		db.makeSingleStatement(insert4);
		db.makeSingleStatement(insert5);

		
		String insert3 = "INSERT INTO ingredient VALUES(1, 'cheese', 10, 'kg')";
		String insert6 = "INSERT INTO ingredient VALUES(2, 'meat', 20, 'kg')";
		db.makeSingleStatement(insert3);
		db.makeSingleStatement(insert6);
		
		
		String insert1 = "INSERT INTO meal VALUES(1, 'pizza', NULL, 1, 120)";
		String insert2 = "INSERT INTO meal VALUES(2, 'taco', NULL, 1, 100)";
		
		db.makeSingleStatement(insert1);
		db.makeSingleStatement(insert2);
		
		String insert9= "INSERT INTO meal_ingredient VALUES(1,1,2)";
		String insert10 = "INSERT INTO meal_ingredient VALUES(2,2,1)";
		db.makeSingleStatement(insert9);
		db.makeSingleStatement(insert10);
		

		String insert7 = "INSERT INTO ordered_meal VALUES(1,1, '2016-11-04',3,0,0)";
		String insert8 = "INSERT INTO ordered_meal VALUES(2,2, '2016-11-04',3,0,0)";
		
		db.makeSingleStatement(insert7);
		db.makeSingleStatement(insert8);
		
		Meal pizza = new Meal(1,"pizza", null, true,100);
		instance2 = new MealOrdered(pizza,"2016-03-03",2,1, false, false);
		
		
		
	}
	@Test
	public void testShouldMarkMealAsReady() throws Exception{
		System.out.println("MealOrder test 1: Mark meal as ready");
		
		boolean res = instance.markMealAsReady(db);
		boolean expRes = true;
		assertEquals(res, expRes);
		
		boolean res2 = instance2.markMealAsReady(db);
		boolean expRes2 = true;
		assertEquals(res2, expRes2);
	}
	
	@Test
	public void testShouldMarkMealAsDelivered() throws Exception{
		System.out.println("MealOrder test 2: Mark meal as delivered");
		
		boolean res = instance.markMealAsDelivered(db);
		boolean expRes = true;
		assertEquals(res,expRes);
		
		boolean res2 = instance2.markMealAsDelivered(db);
		boolean expRes2 = true;
		assertEquals(res2, expRes2);
	}
	
	@Test
	public void testShouldUploadMealOrdered() throws Exception{
		System.out.println("MealOrdered test 3: Upload meal ordered to database");
		
		instance.setQuantity(4);
		instance.setMealName("salad");
		
		boolean res = instance.uploadMealOrdered(db);
		boolean expRes = true;
		assertEquals(res, expRes);
		
		instance2.setInstructions("Something");
		instance2.setDeliverydate("2016-05-05");
		boolean res2 = instance2.uploadMealOrdered(db);
		boolean expRes2 = true;
		assertEquals(res2, expRes2);
	}

}
