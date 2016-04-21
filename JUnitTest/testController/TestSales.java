package testController;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import controller.*;
import databasePackage.Database;

public class TestSales {
	private static Database database;
	private Sales instance;
	private Customer customer1;
	private String dateToday;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "marith1";
		String password = "tgp8sBZA";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		
		String userInsert = "INSERT INTO user VALUES('hanne', 3,'Hanne Hansen', 123456)";

		database.makeSingleStatement(userInsert);
	
	}

	@Before
	public void setUp() throws Exception {
		instance = new Sales("hanne","Hanne Hansen", database);
		customer1 = new Customer(10000,"Geir","Hansen","73329090", "geir@hansen.com",
				"Nedre Bakklandet 61", 7014, 1,"Allergisk mot sopp", true);
		
	    java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    dateToday = sqlDate.toString();	
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
		Order expResult = new Order(1, dateToday, 10000,"Infoinfo", "hanne");
		
		assertEquals(result,expResult);
		
	}
	
	@Ignore
	public void testRegisterSubscriptionOrder() {
		System.out.println("Sales test 4: registerSubscriptionOrder");
		//instance.registerSubscriptionOrder(order, quantitySub, fromDate, toDate, subID);
		fail("Not yet implemented");
	}

}
