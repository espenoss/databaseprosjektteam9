package testController;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.*;

import controller.*;
import database.Database;

public class testDriver {
private static Database database;
private static Driver instance;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "marith1";
		String password = "tgp8sBZA";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		
		instance = new Driver("Per", "Per Pettersen", database);
		
		String insertUser1 = "INSERT INTO user VALUES('hanneh', 1, 'Hanne Hansen', 123456)";
		String insertUser2 = "INSERT INTO user VALUES('olen', 2, 'Ole Normann', 123456)";
		String insertUser3 = "INSERT INTO user VALUES('marie', 3, 'Marie', 1234)";
		
		
		
		database.makeSingleStatement(insertUser1);
		database.makeSingleStatement(insertUser2);
		database.makeSingleStatement(insertUser3);

		
		String customer1 = "INSERT INTO customer VALUES(10000, 'Hansen', 'Geir', '73329090', 'geir@hansen.com', 'Nedre Bakklandet 61', '7014', 1, 'Allergisk mot sopp', true)";
		String customer2 = "INSERT INTO customer VALUES(10001, 'Tvedt', 'Jensine', '73254090', 'jensinetvedt@mail.com', 'Byåsvegen 64', '7021', 5, NULL, true)";
		String customer3 = "INSERT INTO customer VALUES(10002, 'Andersen', 'Lise Carina', '93081295', 'geir@hansen.com', 'Moltmyra 111', '7091', 4, 'Liker ikke skalldyr', true)";
		String customer4 = "INSERT INTO customer VALUES(10003, 'Hellemark', 'Kåre', '90123482', 'kårehellemark@gmail.com', 'Holtermanns veg 20', '7031', 1, NULL, true)";
		
		database.makeSingleStatement(customer1);
		database.makeSingleStatement(customer2);
		database.makeSingleStatement(customer3);
		database.makeSingleStatement(customer4);
		
		// order_id, order_date, customer_id, info, user_id
		String insertOrder1 = "INSERT INTO food_order VALUES(10000, '2016-03-01', 10000, 'Noen info', 'hanneh')";
		String insertOrder2 = "INSERT INTO food_order VALUES(10001, '2016-03-02', 10001, 'Annen info', 'olen')";
		String insertOrder3 = "INSERT INTO food_order VALUES(10002, '2016-03-05', 10001, 'Mer info', 'hanneh')";
		String insertOrder4 = "INSERT INTO food_order VALUES(10003, '2016-03-07', 10002, 'Blabla', 'hanneh')";
		String insertOrder5 = "INSERT INTO food_order VALUES(10004, '2016-03-07', 10003, 'Noe', 'olen')";
		String insertOrder6 = "INSERT INTO food_order VALUES(10005, '2016-03-09', 10002, 'En ting', 'olen')";
		
		database.makeSingleStatement(insertOrder1);
		database.makeSingleStatement(insertOrder2);
		database.makeSingleStatement(insertOrder3);
		database.makeSingleStatement(insertOrder4);
		database.makeSingleStatement(insertOrder5);
		database.makeSingleStatement(insertOrder6);
		
		String insertIng1 = "INSERT INTO ingredient VALUES(1, 'pasta', 20, 'g');";
		String insertIng2 = "INSERT INTO ingredient VALUES(2, 'kylling', 100, 'kg');";
		String insertIng3 = "INSERT INTO ingredient VALUES(3, 'ost', 50, 'kg');";
		String insertIng4 = "INSERT INTO ingredient VALUES(4, 'salat', 50, 'g');";
		String insertIng5 = "INSERT INTO ingredient VALUES(5, 'melk', 70, 'l');";
		
		database.makeSingleStatement(insertIng1);
		database.makeSingleStatement(insertIng2);
		database.makeSingleStatement(insertIng3);
		database.makeSingleStatement(insertIng5);
		database.makeSingleStatement(insertIng4);
		
		//meal_id, meal_name, instructions, available, price
		String insert1 = "INSERT INTO meal VALUES(1, 'pizza', 'info', true, 150)";
		String insert2 = "INSERT INTO meal VALUES(2, 'taco','info', true, 100)";
		String insert3 = "INSERT INTO meal VALUES(3, 'steak','info', true, 200)";
		
		database.makeSingleStatement(insert1);
		database.makeSingleStatement(insert2);
		database.makeSingleStatement(insert3);
		
		//meal_id, ing_id, quantity
		String insertMeal1 = "INSERT INTO meal_ingredient VALUES(1, 1, 1)";
		String insertMeal2 = "INSERT INTO meal_ingredient VALUES(2, 2, 1)";
		String insertMeal3 = "INSERT INTO meal_ingredient VALUES(3, 3, 1)";
		String insertMeal4 = "INSERT INTO meal_ingredient VALUES(1, 4 ,1)";
		String insertMeal5 = "INSERT INTO meal_ingredient VALUES(2, 1, 1)";
		String insertMeal6 = "INSERT INTO meal_ingredient VALUES(3, 2, 1)";
		
		database.makeSingleStatement(insertMeal1);
		database.makeSingleStatement(insertMeal2);
		database.makeSingleStatement(insertMeal3);
		database.makeSingleStatement(insertMeal4);
		database.makeSingleStatement(insertMeal5);
		database.makeSingleStatement(insertMeal6);

	    java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		//order_id, meal_id, delivery_date, quantity, ready_delivery, delivered		
		String insert7  = "INSERT INTO ordered_meal VALUES(10000, 1, '2016-03-01', 1, 1, 1)";
		String insert8  = "INSERT INTO ordered_meal VALUES(10001, 2, '2016-03-31', 1, 1, 1)";
		String insert9  = "INSERT INTO ordered_meal VALUES(10002, 3, '2016-03-05', 1, 1, 1)";
		String insert10 = "INSERT INTO ordered_meal VALUES(10003, 1, '2016-03-04', 2, 1, 1)";
		String insert11 = "INSERT INTO ordered_meal VALUES(10004, 2, '2016-03-07', 3, 1, 1)";
		
		String insert12 = "INSERT INTO ordered_meal VALUES(10000, 3, '"+sqlDate+"', 1, 1, 0)";
		String insert13 = "INSERT INTO ordered_meal VALUES(10001, 1, '"+sqlDate+"', 1, 1, 0)";
		String insert14 = "INSERT INTO ordered_meal VALUES(10002, 2, '"+sqlDate+"', 1, 1, 0)";
		
		
		//[steak, 1, Nedre Bakklandet 61, Geir, Hansen, 10000, 3]
		//meal_name, quan, adresse, fornavn, etternavn, ordreID, mealID
		
		database.makeSingleStatement(insert7);
		database.makeSingleStatement(insert8);
		database.makeSingleStatement(insert9);
		database.makeSingleStatement(insert10);
		database.makeSingleStatement(insert11);
		database.makeSingleStatement(insert12);
		database.makeSingleStatement(insert13);
		database.makeSingleStatement(insert14);	
	}
		
	@Test
	public void testMarkDelivered() throws Exception {
		System.out.println("Driver test 1: markDelivered");
		java.sql.Date date = java.sql.Date.valueOf("2016-03-01");
		instance.markDelivered(10000, 1, date);
		
		Order order = instance.viewSingleOrder(10000);
		ArrayList<MealOrdered> meals = order.getMeals();
		MealOrdered tempMeal=null;
		for (int i=0;i<meals.size();i++){
			if (meals.get(i).getMealID()==1 && meals.get(i).getDeliverydate().equals(date)){
				tempMeal =meals.get(i);
			}
		}
		boolean res = tempMeal.isDelivered();
		boolean expRes = true;
		assertEquals(expRes, res);
	}
	
	@Test
	public void testGenerateDeliveryPlan() throws Exception {
		System.out.println("Driver test 1: generateDeliveryPlan");
		
		String[][] plan = instance.generateDeliveryPlan();

		String[] list1 = {"steak", "1", "Nedre Bakklandet 61", "Geir", "Hansen", "10000", "3"};
		String[] list2 = {"pizza", "1", "Byåsvegen 64", "Jensine", "Tvedt", "10001", "1"};
		String[] list3 = {"taco", "1", "Byåsvegen 64", "Jensine", "Tvedt", "10002", "2"};

		assertEquals(Arrays.toString(plan[0]), Arrays.toString(list1));
		assertEquals(Arrays.toString(plan[1]), Arrays.toString(list2));
		assertEquals(Arrays.toString(plan[2]), Arrays.toString(list3));
	}

}