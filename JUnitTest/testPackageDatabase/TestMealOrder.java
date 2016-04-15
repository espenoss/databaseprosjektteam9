package testPackageDatabase;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import databasePackage.*;

public class TestMealOrder {
	public Database database;
	
	@Before
	public void beforeTest(){
		String username = "espenme";
		String password = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	@Test
	public void mealOrderTest() throws Exception{

		boolean result = true; //= QueryMethods.addMealToOrder(10002, 1, "2012-12-12", 3, false, false, database);
		boolean expRes = true;
		assertEquals(expRes, result);
		
		result = QMOrder.updateMealInOrder(10002, 1, "2012-12-12", 3, true, true, database);
		expRes = true;
		assertEquals(expRes, result);
		
		result = QMOrder.markMealOrderAsReadyForDelivery(10002, 1, "2012-12-12", database);
		expRes = true;
		assertEquals(expRes, result);		

		result = QMOrder.markMealOrderAsDelivered(10002, 1, "2012-12-12", database);
		expRes = true;
		assertEquals(expRes, result);		

		String[][] view = QMOrder.viewMealsInOrder(10002, database);
		for(int x=0;x<view.length;x++){
			for(int y=0;y<view[x].length;y++){
				System.out.print(view[x][y] + " ");
			}
			System.out.println("");
		}
		
		result = QMOrder.removeMealFromOrder(10002, 1, "2012-12-12", database);
		expRes = true;
		assertEquals(expRes, result);		
	}
}
