package testController;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.*;

import controller.Admin;
import controller.User;
import database.Database;

public class TestAdmin {
	private static Database database;
	private static Admin instance;
	private static ArrayList<User> userList = new ArrayList<User>();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "marith1";
		String password = "tgp8sBZA";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		
		instance = new Admin("Per","Per",database);
		
		String insertUser1 = "INSERT INTO user VALUES('hanneh', 1, 'Hanne Hansen', 123456)";
		String insertUser2 = "INSERT INTO user VALUES('olen', 2, 'Ole Normann', 123456)";
		String insertUser3 = "INSERT INTO user VALUES('marie', 3, 'Marie', 1234)";
		
		userList.add(new User("hanneh", 1, "Hanne Hansen", database));
		userList.add(new User("olen", 2, "Ole Normann", database));

		
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

		//order_id, meal_id, delivery_date, quantity, ready_delivery, delivered		
		String insert7  = "INSERT INTO ordered_meal VALUES(10000, 1, '2016-03-01', 1, 1, 1)";
		String insert8  = "INSERT INTO ordered_meal VALUES(10001, 2, '2016-03-31', 1, 1, 1)";
		String insert9  = "INSERT INTO ordered_meal VALUES(10002, 3, '2016-03-05', 1, 1, 1)";
		String insert10 = "INSERT INTO ordered_meal VALUES(10003, 1, '2016-03-04', 2, 1, 1)";
		String insert11 = "INSERT INTO ordered_meal VALUES(10004, 2, '2016-03-07', 3, 1, 1)";
		
		String insert12 = "INSERT INTO ordered_meal VALUES(10000, 3, '2016-04-05', 1, 1, 1)";
		String insert13 = "INSERT INTO ordered_meal VALUES(10001, 1, '2016-04-30', 1, 1, 1)";
		String insert14 = "INSERT INTO ordered_meal VALUES(10002, 2, '2016-04-01', 1, 1, 1)";
		String insert15 = "INSERT INTO ordered_meal VALUES(10003, 3, '2016-04-05', 1, 1, 1)";
		
		database.makeSingleStatement(insert7);
		database.makeSingleStatement(insert8);
		database.makeSingleStatement(insert9);
		database.makeSingleStatement(insert10);
		database.makeSingleStatement(insert11);
		database.makeSingleStatement(insert12);
		database.makeSingleStatement(insert13);
		database.makeSingleStatement(insert14);
		database.makeSingleStatement(insert15);	
	}
	
	@Test
	public void testRegisterUser() throws Exception{
		System.out.println("Admin test 1: registerUser");
		
		instance.registerUser("marit", 1, "Marit", "1234");
		String res = instance.viewUser("marit");
		String expRes = "Username: marit, user type: 1, name: Marit";
		
		assertEquals(expRes, res);
	}
	
	@Test
	public void testUpdateUser() throws Exception{
		System.out.println("Admin test 2: updateUser");
		String res = null;
		
		if (instance.updateUser("marie", 3, "Marie Hansen", "1234")){
			res = instance.viewUser("marie");
		}
		String expRes = "Username: marie, user type: 3, name: Marie Hansen";
		assertEquals(expRes, res);
	}
	
	@Test
	public void testGetStatisticsForYear() throws Exception {
		System.out.println("Admin test 3: getStatisticsForYear");
		String expRes = "Income overview for 2016\n\n"+
				"Income in January is: 0 kr.\n"+
				"Income in February is: 0 kr.\n"+
				"Income in March is: 1050 kr.\n"+
				"Income in April is: 650 kr.\n\n"+
				
				"Total income this year: 1700 kr.";
		
		String res = instance.getStatisticsForYear(2016);
		assertEquals(expRes, res);
	}
	
	@Test
	public void testViewUserList() throws Exception {
		System.out.println("Admin test 4: viewUserList");
		
		ArrayList<User> testList = instance.viewUserList();

		assertEquals(userList.get(0), testList.get(0));
	}
}
