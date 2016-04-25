package testController;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.Cook;
import controller.Customer;
import controller.Ingredient;
import controller.Meal;
import controller.Order;
import controller.SubPlan;
import controller.User;
import database.Database;

public class TestWithEmptyDatabase {
	private static Database database;
	private static User user;
	private static Cook cook;
	
	// Tests user (and some cook) methods with empty database
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		
		System.out.println("NULLTESTS --- Total number of tests: 9");
		
		user = new User("bobdy", 1, "Bob Dylan", database);
		cook = new Cook("bob","Bob", database);
	}
	
		
	@Test
	public void testNullViewFoodOrders() throws Exception{
		System.out.println("UserNull test 1: View food orders with empty database");
		java.sql.Date date = java.sql.Date.valueOf("2016-02-03");
		
		ArrayList<Order> res = user.viewFoodOrders(date);
		ArrayList<Order> expRes = null;
		assertEquals(res, expRes);		
	}
	
	@Test
	public void testNullViewAvailableMeals() throws Exception{
		System.out.println("UserNull test 2: View available meals with empty database");
		
		ArrayList<Meal> res = user.viewAvailableMeals();
		ArrayList<Meal> expRes = null;
		assertEquals(res, expRes);
	}
	
	@Test
	public void testNullViewCustomerList() throws Exception{
		System.out.println("UserNull test 3: View customer list with empty database");
		
		ArrayList<Customer> res = user.viewCustomerList();
		ArrayList<Customer> expRes = null;
		assertEquals(res, expRes);
	}
	
	@Test
	public void testNullViewCompanyList() throws Exception{
		System.out.println("UserNull test 4: View company list with empty database");
		
		ArrayList<Customer> res = user.viewCompanyList();
		ArrayList<Customer> expRes = null;
		assertEquals(res, expRes);
	}
	
	@Test
	public void testNullViewSingleCustomer() throws Exception{
		System.out.println("UserNull test 5: View customer with empty database");
		
		Customer res = user.viewSingleCustomer(2342);
		Customer expRes = null;
		assertEquals(res, expRes);
	}
	
	@Test
	public void testNullViewIngredients() throws Exception{
		System.out.println("UserNull test 6: View ingredients with empty database");
		
		ArrayList<Ingredient> res = user.viewIngredients();
		ArrayList<Ingredient> expRes = null;
		assertEquals(res, expRes);
	}
	
	@Test
	public void testNullViewSubPlans() throws Exception{
		System.out.println("UserNull test 7: View subscription plans with empty database");
		
		ArrayList<SubPlan> res = user.viewAllSubPlans();
		ArrayList<SubPlan> expRes = null;
		assertEquals(res, expRes);
	}
	
	@Test
	public void nullTestAddIngredientToMeal() throws Exception{
		// Tests add ingredient to meal with empty database
		database.initiateDb();
		System.out.println("Cook test NULL: Add ingredient to meal with empty databse");
		
		boolean res = cook.addIngredientToMeal(1, 1, 2);
		boolean expRes = false;
		
		assertEquals(res, expRes);
	}

}
