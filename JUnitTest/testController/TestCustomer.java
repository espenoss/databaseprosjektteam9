package testController;

import databasePackage.Database;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.Customer;

public class TestCustomer {
	private static Database database;
	private static Customer customer;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		customer = new Customer(1, "Jens", "Svendsen", "12345678", "jens@svendsen.com", "Veibakken 12", 7014, 1, "None", true);
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShouldUpdateCustomer() throws Exception{
		//boolean updateCustomer(Database database)
		System.out.println("Customer test 1: updateCustomer");
		
		customer.setAdress("Gateveien 123");
		customer.setEmail("epost@epost.no");
		customer.setFirstName("Hanne");
		customer.setSurName("Byggsen");
		customer.setPhoneNumber("50505050");
		customer.setZipCode(7070);
		customer.setPreferences("Allergi: Fisk");
		customer.setZoneNr(2);
		customer.setCompanyName("Oles blomster");
		customer.setIsCompany(true);
		customer.setActive(false);
		
		boolean res = customer.updateCustomer(database);
		boolean expRes = true;
		
		assertEquals(res, expRes);
	
	}

}
