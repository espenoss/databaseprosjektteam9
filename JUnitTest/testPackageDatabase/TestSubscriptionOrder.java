package testPackageDatabase;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import databasePackage.Database;
import databasePackage.QMFood;

public class TestSubscriptionOrder {
	public Database database;
	
	@Before
	public void beforeTest(){
		String username = "espenme";
		String password = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	@Test
	public void subscriptionOrderTest() throws Exception{

		boolean result = QMFood.addSubscriptionToOrder(10003, 1, "2012-12-12", "2012-12-23", 1, database);
		boolean expRes = true;
		assertEquals(expRes, result);
		
		result = QMFood.updateSubscriptionInOrder(10003, 2, "2012-12-12", "2012-12-23", 1, database);
		expRes = true;
		assertEquals(expRes, result);
		
		String[][] view = QMFood.viewSubscriptions(database);
		for(int x=0;x<view.length;x++){
			for(int y=0;y<view[x].length;y++){
				System.out.print(view[x][y] + " ");
			}
			System.out.println("");
		}
		
		view = QMFood.viewActiveSubscriptions("2016-04-02", database);
		for(int x=0;x<view.length;x++){
			for(int y=0;y<view[x].length;y++){
				System.out.print(view[x][y] + " ");
			}
			System.out.println("");
		}
				
		result = QMFood.removeSubscriptionFromOrder(10003 ,database);
		expRes = true;
		assertEquals(expRes, result);		
	}
}
