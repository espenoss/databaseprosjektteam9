package testPackageDatabase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import databasePackage.Database;
import databasePackage.QueryMethods;

public class TestIngredient {
	public Database database;
	
	@Before
	public void beforeTest(){
		String username = "espenme";
		String password = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	@Test
	public void ingredientTest() throws Exception{

		// view all entries
		String[][] ingredients = QueryMethods.viewIngredients(database);
		String[] ingredient = ingredients[ingredients.length-1];
				
		// attempt to register new info about existing entry
		boolean exp = QueryMethods.updateIngredient(Integer.parseInt(ingredient[0]), ingredient[1], Float.parseFloat(ingredient[2]), "Ny info", database);
		assertEquals(true, exp);		
		
		// attempt to remove entry		
		exp = QueryMethods.removeIngredient(Integer.parseInt(ingredient[0]), database);
		assertEquals(true, exp);

		// attempt to reregister removed entry	
		int iexp = QueryMethods.registerIngredient(ingredient[1], Float.parseFloat(ingredient[2]), ingredient[3], database);
		assertNotEquals(-1, iexp);		
	}
}
