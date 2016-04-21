package testController;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import controller.Customer;
import controller.Ingredient;
import controller.Meal;
import controller.Order;
import controller.SubPlan;
import controller.User;
import databasePackage.Database;

public class TestUserNull {
	private static Database database;
	private static User user;
	
	// Tests user methods with empty database
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
		
		System.out.println("Total number of tests: 7");
		
		user = new User("bobdy", 1, "Bob Dylan", database);
	}
	
		
	@Test
	public void testNullViewFoodOrders() throws Exception{
		System.out.println("Test 1: View food orders with empty database");
		java.sql.Date date = java.sql.Date.valueOf("2016-02-03");
		
		ArrayList<Order> res = user.viewFoodOrders(date);
		ArrayList<Order> expRes = null;
		assertEquals(res, expRes);		
	}
	
	@Test
	public void testNullViewAvailableMeals() throws Exception{
		System.out.println("Test 2: View available meals with empty database");
		
		ArrayList<Meal> res = user.viewAvailableMeals();
		ArrayList<Meal> expRes = null;
		assertEquals(res, expRes);
	}
	
	@Test
	public void testNullViewCustomerList() throws Exception{
		System.out.println("Test 3: View customer list with empty database");
		
		ArrayList<Customer> res = user.viewCustomerList();
		ArrayList<Customer> expRes = null;
		assertEquals(res, expRes);
	}
	
	@Test
	public void testNullViewCompanyList() throws Exception{
		System.out.println("Test 4: View company list with empty database");
		
		ArrayList<Customer> res = user.viewCompanyList();
		ArrayList<Customer> expRes = null;
		assertEquals(res, expRes);
	}
	
	@Test
	public void testNullViewSingleCustomer() throws Exception{
		System.out.println("Test 5: View customer with empty database");
		
		Customer res = user.viewSingleCustomer(2342);
		Customer expRes = null;
		assertEquals(res, expRes);
	}
	
	@Test
	public void testNullViewIngredients() throws Exception{
		System.out.println("Test 6: View ingredients with empty database");
		
		ArrayList<Ingredient> res = user.viewIngredients();
		ArrayList<Ingredient> expRes = null;
		assertEquals(res, expRes);
	}
	
	@Test
	public void testNullViewSubPlans() throws Exception{
		System.out.println("Test 7: View subscription plans with empty database");
		
		ArrayList<SubPlan> res = user.viewAllSubPlans();
		ArrayList<SubPlan> expRes = null;
		assertEquals(res, expRes);
	}

}
