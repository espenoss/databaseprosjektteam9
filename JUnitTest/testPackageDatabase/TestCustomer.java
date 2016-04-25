package testPackageDatabase;

import org.junit.*;

import database.*;

import static org.junit.Assert.*;


public class TestCustomer {

	public Database database;
	
	@Before
	public void beforeTest(){
		String username = "espenme";
		String password = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	@Test
	public void customerTest() throws Exception{
		System.out.println("Test register new customer");

		String[][] customerList = QMCustomer.viewAllCustomers(database);
		String[] customer = customerList[customerList.length - 1];
		String[] customerCopy = QMCustomer.viewCustomer(Integer.parseInt(customer[0]), database);

		for(int i=0;i<customer.length;i++){
			assertEquals(customer[i], customerCopy[i]);
		}
		
		boolean expResult = true;
		boolean bResult = QMCustomer.updateCustomer(Integer.parseInt(customer[0]), customer[1], customer[2], customer[3], customer[4], customer[5], 1234, 1, customer[8], true, database);
		assertEquals(bResult, expResult);		

		customerCopy = QMCustomer.viewCustomer(Integer.parseInt(customer[0]), database);		
		
		assertNotEquals(customer[6], customerCopy[6]);
		
				
	}
}
