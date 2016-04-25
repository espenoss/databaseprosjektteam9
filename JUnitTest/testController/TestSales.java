package testController;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.*;
import database.Database;

public class TestSales {
	private static Database database;
	private static Sales instance;
	private static Customer customer1;
	private static Order order1;
	private static Order order2;
	private static String dateToday;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "marith1";
		String password = "tgp8sBZA";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		
		String userInsert = "INSERT INTO user VALUES('hanne', 3,'Hanne Hansen', 123456)";
		String customerInsert = "INSERT INTO customer VALUES(10000, 'Henrik', 'Hansen', '73909090', 'henrik@hansen.no', 'Gateveien 1', 7098, 4, 'none', true)";
		String subPlanInsert = "INSERT INTO subscription_plan VALUES(1,'Lunsjmeny')";
		
		String insert1 = "INSERT INTO meal VALUES(1, 'pizza', 'info', true, 150)";
		String insert2 = "INSERT INTO meal VALUES(2, 'taco','info', true, 100)";
		String insert3 = "INSERT INTO meal VALUES(3, 'steak','info', true, 200)";
		
		database.makeSingleStatement(userInsert);
		database.makeSingleStatement(customerInsert);
		database.makeSingleStatement(subPlanInsert);
		database.makeSingleStatement(insert1);
		database.makeSingleStatement(insert2);
		database.makeSingleStatement(insert3);
		
		instance = new Sales("hanne","Hanne Hansen", database);
		customer1 = new Customer(10000,"Geir","Hansen","73329090", "geir@hansen.com",
				"Nedre Bakklandet 61", 7014, 1,"Allergisk mot sopp", true);
		

		
	    java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    dateToday = sqlDate.toString();	

		
		String orderInsert = "INSERT INTO food_order VALUES(1,'"+dateToday+"',10000,'info1','hanne')";
		database.makeSingleStatement(orderInsert);
		
		order1 = new Order(1, dateToday, 10000,"info1", "hanne");
		order2 = new Order(2, dateToday, 10000,"info2", "hanne");
	
	}

	@Before
	public void setUp() throws Exception {

	}
	
	@Test
	public void testRegisterCustomer() throws Exception {
		System.out.println("Sales test 1: registerCustomer");
		Customer result = instance.registerCustomer("Hansen","Geir","73329090" , "geir@hansen.com",
				"Nedre Bakklandet 61", 7014, 1,"Allergisk mot sopp", true);
		
		Customer expResult = instance.viewSingleCustomer(result.getCustomerID());
		

		assertEquals(result, expResult);
	}
	
	@Test
	public void testRegisterCompanyToCustomer() throws Exception {
		System.out.println("Sales test 2: registerCompanyToCustomer");
		
		boolean result = instance.registerCompanyToCustomer(customer1, "Posten");
		boolean expResult = true;
		
		assertEquals(result, expResult);
		
	}
	
	@Test
	public void testRegisterNewOrder() throws Exception {
		System.out.println("Sales test 3: registerNewOrder");
		Order result = instance.registerNewOrder(10000,"Infoinfo", "hanne");
		Order expResult = order2;
		
		assertEquals(result,expResult);
		
	}
	
	@Test
	public void testRegisterSubscriptionOrder() throws Exception {
		System.out.println("Sales test 4: registerSubscriptionOrder");
		boolean result = instance.registerSubscriptionOrder(order1, 3, "2016-04-22", "2016-04-30", 1);
		boolean expResult = true; 
		
		//registerSubscriptionOrder(Order order, int quantitySub, String fromDate, String toDate, int subID) throws Exception{
		
		assertEquals(result,expResult);
	}
	
	@Test
	public void testAddMealToOrder() throws Exception{
		System.out.println("Sales test 5: addMealToOrder");
		instance.addMealToOrder(1, 1, "2016-04-22", 1);
		Order order = instance.viewSingleOrder(1);
		int mealID = order.getMeal(0).getMealID();
		String mealName = order.getMeal(0).getMealName();
		
		boolean res = mealID==1 && mealName.equals("pizza");
		boolean expRes = true;
		
		assertEquals(expRes, res);
	}

}
