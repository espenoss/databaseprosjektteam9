package testPackageDatabase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import databasePackage.Database;
import databasePackage.QueryMethods;

public class TestSubscription {
	public Database database;
	
	@Before
	public void beforeTest(){
		String username = "espenme";
		String password = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	@Test
	public void subscriptionTest() throws Exception{

		// view all entries
		String[][] subscriptions = QueryMethods.viewSubscriptionPlans(database);
		// select last entry
		String[] sub = subscriptions[subscriptions.length-1];
				
		// attempt to register new info about existing entry
		boolean exp = QueryMethods.updateSubscriptionPlan(Integer.parseInt(sub[0]), sub[1], database);
		assertEquals(true, exp);		
		
		// attempt to remove entry		
		exp = QueryMethods.removeSubscriptionPlan(Integer.parseInt(sub[0]), database);
		assertEquals(true, exp);

		// attempt to reregister removed entry	
		int iexp = QueryMethods.registerSubscriptionPlan(sub[1], database);
		assertNotEquals(-1, iexp);		
	}
}
