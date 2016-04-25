package testPackageDatabase;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import database.Database;
import database.QMFood;

public class TestSubMeal {
	public Database database;
	
	@Before
	public void beforeTest(){
		String username = "espenme";
		String password = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	@Test
	public void subscriptionMealTest() throws Exception{

		boolean result = true; // QueryMethods.addMealToPlan(1, 1, 1, "Mandag", database);
		boolean expRes = true;
		assertEquals(expRes, result);
		
		result = QMFood.updateMealInPlan(1, 1, 2, "Tirsdag", database);
		expRes = true;
		assertEquals(expRes, result);
		
		String[][] view = QMFood.viewMealsInPlan(1, database);
		for(int x=0;x<view.length;x++){
			for(int y=0;y<view[x].length;y++){
				System.out.print(view[x][y] + " ");
			}
			System.out.println("");
		}
		
		result = QMFood.removeMealFromPlan(1, 1, 2, database);
		expRes = true;
		assertEquals(expRes, result);		
	}
}
