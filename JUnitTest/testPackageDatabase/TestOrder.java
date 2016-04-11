package testPackageDatabase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import databasePackage.Database;
import databasePackage.QueryMethods;

public class TestOrder {
	public Database database;
	
	@Before
	public void beforeTest(){
		String username = "espenme";
		String password = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	@Test
	public void companyTest() throws Exception{

		// view all entries
		String[][] orders = QueryMethods.viewAllOrders(database);
		// view single entry
		String[] order = QueryMethods.viewOrder(Integer.parseInt(orders[0][0]), database);
		
		// check to see if they contain the same info
		for(int i=0;i<order.length;i++){
			assertEquals(order[i], orders[0][i]);
		}
		
		// attempt to register new info about existing entry
		boolean exp = QueryMethods.updateOrder(Integer.parseInt(order[0]), order[1], Integer.parseInt(order[2]), "Ny info", order[4], database);
		assertEquals(true, exp);		
		
		// attempt to remove entry		
		exp = QueryMethods.removeOrder(Integer.parseInt(order[0]), database);
		assertEquals(true, exp);

		// attempt to reregister removed entry	
		int iexp = QueryMethods.registerOrder(order[1], Integer.parseInt(order[2]), order[3], order[4], database);
		assertNotEquals(-1, iexp);		
	}
}
