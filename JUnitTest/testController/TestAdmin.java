package testController;

import static org.junit.Assert.*;

import org.junit.*;

import controller.Admin;
import databasePackage.Database;

public class TestAdmin {
	private static Database database;
	private static Admin instance;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "marith1";
		String password = "tgp8sBZA";
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
		
		
	}
	
	
	@Test
	public void testGetStatisticsForYear() {
		System.out.println("Admin test 1: getStatisticsForYear");
		
		fail("Not yet implemented");
	}
	
	@Test
	public void testViewUserList() {
		System.out.println("Admin test 2: viewUserList");
		
		fail("Not yet implemented");
	}
	

}
