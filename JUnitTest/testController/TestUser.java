package testController;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import controller.Customer;
import controller.Ingredient;
import controller.Meal;
import controller.User;
import databasePackage.Database;

public class TestUser {
	private static Database database;
	
	private static ArrayList<Meal> mealList = new ArrayList<Meal>();
	private static Meal soup;
	private static Meal hamburger;
	private static Meal sandwich;
	private static User user;
	
	private static ArrayList<Customer> customerList = new ArrayList<Customer>();
	ArrayList<Customer> customers = new ArrayList<Customer>();
	private static Customer geir;
	private static Customer jensine;
	private static Customer lise;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
	
		String insert1 = "INSERT INTO meal VALUES(1, 'soup', 'none', true, 90)";
		String insert2 = "INSERT INTO meal VALUES(2, 'hamburger', 'none', true, 100)";
		String insert3 = "INSERT INTO meal VALUES(3, 'sandwich', 'none', true, 50)";
		
		database.makeSingleStatement(insert1);
		database.makeSingleStatement(insert2);
		database.makeSingleStatement(insert3);
		
		user = new User("bob", 2, "Bob Smidt", database);
		
		// MEAL INSERT
		soup = new Meal(1, "soup","none", true, 90);
		hamburger = new Meal(2, "hamburger", "none", true, 100);
		sandwich = new Meal(3, "sandwich", "none", true, 50);
		
		mealList.add(soup);
		mealList.add(hamburger);
		mealList.add(sandwich);
		

		// CUSTOMER INSERT
		String insert4 = "INSERT INTO customer VALUES(10000, 'Hansen', 'Geir', '73329090', 'geir@hansen.com', 'Nedre Bakklandet 61', 7014, 1, 'Allergisk mot sopp', true);";
		String insert5 = "INSERT INTO customer VALUES(10001, 'Tvedt', 'Jensine', '73254090', 'jensinetvedt@mail.com', 'Byåsvegen 64', 7021, 5, NULL, true);";
		String insert6 = "INSERT INTO customer VALUES(10002, 'Andersen', 'Lise Carina', '93081295', 'lise@andersen.com', 'Moltmyra 111', 7091, 4, 'Liker ikke skalldyr', true);";
	
		database.makeSingleStatement(insert4);
		database.makeSingleStatement(insert5);
		database.makeSingleStatement(insert6);
		
		geir = new Customer(10000, "Hansen", "Geir", "73329090", "geir@hansen.com", "Nedre Bakklandet 61", 7014, 1, "Allergisk mot sopp", true);
		jensine = new Customer(10001, "Tvedt","Jensine", "73254090", "jensinetvedt@mail.com", "Byåsvegen 64", 7021, 5, null, true);
		lise = new Customer(10002, "Andersen", "Lise Carina", "93081295", "lise@andersen.com", "Moltmyra 111", 7091, 4, "Liker ikke skalldyr", true);
		
		customerList.add(geir);
		customerList.add(jensine);
		customerList.add(lise);
	}

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testViewAvailableMeals() throws Exception {
		System.out.println("Test: View available meals");

		mealList = user.viewAvailableMeals();
		
		Meal res0 = mealList.get(0);
		Meal expRes0 = soup;
		assertEquals(res0, expRes0);
		
		Meal res1 = mealList.get(1);
		Meal expRes1 = hamburger;
		assertEquals(res1, expRes1);
		
		Meal res2 = mealList.get(2);
		Meal expRes2 = sandwich;
		assertEquals(res2, expRes2);
	}
	
	
	@Test
	public void testViewCustomerList() throws Exception{
		System.out.println("Test: View customer list");
	
		Customer res0 = customerList.get(0);
		Customer expRes0 = geir;
		assertEquals(res0, expRes0);
		
		Customer res1 = user.viewCustomerList().get(1);
		Customer expRes1 = jensine;
		assertEquals(res1, expRes1);
		
		Customer res2 = user.viewCustomerList().get(2);
		Customer expRes2 = lise;
		assertEquals(res2, expRes2);
	}
	
	
	@Test
	public void testViewCompanyList(){
		System.out.println("Test: View company list");
//		ArrayList<Customer> viewCompanyList()
	}
	
	@Test
	public void testViewSingleCustomer(){
		System.out.println("Test: view single customer");
//		Customer viewSingleCustomer(int customerId)
	}
	
	@Test
	public void testViewIngredients(){
		System.out.println("Test: View ingredients");
//		ArrayList<Ingredient> viewIngredients()
	}

}
