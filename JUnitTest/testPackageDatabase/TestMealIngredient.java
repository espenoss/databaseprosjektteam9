package testPackageDatabase;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import database.Database;
import database.QMFood;

public class TestMealIngredient {
	public Database database;
	
	@Before
	public void beforeTest(){
		String username = "espenme";
		String password = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	@Test
	public void MealIngredientTest() throws Exception{

		boolean result = QMFood.addIngredientToMeal(4, 1, 5, database);
		boolean expRes = true;
		assertEquals(expRes, result);
		
		result = QMFood.updateIngredientInMeal(4, 1, 3, database);
		expRes = true;
		assertEquals(expRes, result);
		
		String[][] view = QMFood.viewIngredientsInMeal(4, database);
		for(int x=0;x<view.length;x++){
			for(int y=0;y<view[x].length;y++){
				System.out.print(view[x][y] + " ");
			}
			System.out.println("");
		}
	}
}
