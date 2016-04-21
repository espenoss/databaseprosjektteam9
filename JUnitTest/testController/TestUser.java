package testController;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

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

public class TestUser {
	private static Database database;
	
	private static ArrayList<Meal> mealList = new ArrayList<Meal>();
	private static Meal soup;
	private static Meal hamburger;
	private static Meal sandwich;
	private static User user;
	
	private static ArrayList<Customer> customerList = new ArrayList<Customer>();
	private static Customer geir;
	private static Customer jensine;
	private static Customer lise;
	
	private static ArrayList<Customer> companyList = new ArrayList<Customer>();
	private static Customer skatteetaten;
	private static Customer deloitte;
	
	private static ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
	private static Ingredient potato;
	private static Ingredient carrot;
	private static Ingredient cheese;
	
	private static ArrayList<Order> orderList = new ArrayList<Order>();
	private static Order order1;
	private static Order order2;
	
	private static ArrayList<SubPlan> subPlanList = new ArrayList<SubPlan>();
	private static SubPlan subPlan1;
	private static SubPlan subPlan2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String username = "anitakau";
		String password = "e82L3Dat";
		
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		database = new Database("com.mysql.jdbc.Driver", databasename);
		database.initiateDb();
	
	
		
		// *** USER ***
		String userInsert = "INSERT INTO user VALUES('bob', 2, 'Bob Smidt', '1234');";
		user = new User("bob", 2, "Bob Smidt", database);
		database.makeSingleStatement(userInsert);
		
		
		// *** MEAL INSERT ***
		String insert1 = "INSERT INTO meal VALUES(1, 'soup', 'none', true, 90)";
		String insert2 = "INSERT INTO meal VALUES(2, 'hamburger', 'none', true, 100)";
		String insert3 = "INSERT INTO meal VALUES(3, 'sandwich', 'none', true, 50)";
		
		database.makeSingleStatement(insert1);
		database.makeSingleStatement(insert2);
		database.makeSingleStatement(insert3);
		
		soup = new Meal(1, "soup","none", true, 90);
		hamburger = new Meal(2, "hamburger", "none", true, 100);
		sandwich = new Meal(3, "sandwich", "none", true, 50);
		
		mealList.add(soup);
		mealList.add(hamburger);
		mealList.add(sandwich);
		

		// *** CUSTOMER INSERT ***
		String insert4 = "INSERT INTO customer VALUES(10000, 'Hansen', 'Geir', '73329090', 'geir@hansen.com', 'Nedre Bakklandet 61', 7014, 1, 'Allergisk mot sopp', true);";
		String insert5 = "INSERT INTO customer VALUES(10001, 'Tvedt', 'Jensine', '73254090', 'jensinetvedt@mail.com', 'Byåsvegen 64', 7021, 5, NULL, true);";
		String insert6 = "INSERT INTO customer VALUES(10002, 'Andersen', 'Lise Carina', '93081295', 'lise@andersen.com', 'Moltmyra 111', 7091, 4, 'Liker ikke skalldyr', true);";
	
		database.makeSingleStatement(insert4);
		database.makeSingleStatement(insert5);
		database.makeSingleStatement(insert6);
		
		geir = new Customer(10000, "Geir", "Hansen", "73329090", "geir@hansen.com", "Nedre Bakklandet 61", 7014, 1, "Allergisk mot sopp", true);
		jensine = new Customer(10001, "Jensine","Tvedt", "73254090", "jensinetvedt@mail.com", "Byåsvegen 64", 7021, 5, null, true);
		lise = new Customer(10002, "Lise Carina", "Andersen", "93081295", "lise@andersen.com", "Moltmyra 111", 7091, 4, "Liker ikke skalldyr", true);
		
		customerList.add(geir);
		customerList.add(jensine);
		customerList.add(lise);
		
		
		// *** COMPANY INSERT ***
		String insert7 = "INSERT INTO customer VALUES(10004, 'Gjertsen', 'Laila', '22123456', 'skatteetaten@trondheim.no', 'Erling Skakkes gate 50', 7012, 1, NULL, true);";
		String insert8 = "INSERT INTO company VALUES(10004, 'Skatteetaten');";
		String insert9 = "INSERT INTO customer VALUES(10005, 'Smith', 'Gordon', '20203030', 'deloitte@trondheim.no', 'Dyre Halses gate 1A', 7042, 1, NULL, true);";
		String insert10 = "INSERT INTO company VALUES(10005, 'Deloitte');";
		
		database.makeSingleStatement(insert7);
		database.makeSingleStatement(insert8);
		database.makeSingleStatement(insert9);
		database.makeSingleStatement(insert10);
		
		skatteetaten = new Customer(10004, "Gjertsen", "Laila", "22123456", "skatteetaten@trondheim.no", "Erling Skakkes gate 50", 7012, 1, null, true, "Skatteetaten");
		deloitte = new Customer(10005, "Smith", "Gordon", "20203030", "deloitte@trondheim.no", "Dyre Halses gate 1A", 7042, 1, null, true, "Deloitte");
		
		companyList.add(skatteetaten);
		companyList.add(deloitte);
		
		
		// *** INGREDIENTS INSERT ***
		String insert11 = "INSERT INTO ingredient VALUES(1, 'potato', 50, 'kg')";
		String insert12 = "INSERT INTO ingredient VALUES(2, 'carrot', 30, 'kg')";
		String insert13 = "INSERT INTO ingredient VALUES(3, 'cheese', 10, 'kg')";
		database.makeSingleStatement(insert11);
		database.makeSingleStatement(insert12);
		database.makeSingleStatement(insert13);
		
		potato = new Ingredient(1, "potato", 50, "kg");
		carrot = new Ingredient(2, "carrot", 30, "kg");
		cheese = new Ingredient(3, "cheese", 10,"kg");
		
		ingredientList.add(potato);
		ingredientList.add(carrot);
		ingredientList.add(cheese);
		
		
		// *** ORDER/FOOD_ORDER INSERT ***
		String insert14 = "INSERT INTO food_order VALUES(1, '2016-03-03', 10001, NULL, 'bob');";
		String insert15 = "INSERT INTO food_order VALUES(2, '2016-04-03', 10002, NULL, 'bob')";
		database.makeSingleStatement(insert14);
		database.makeSingleStatement(insert15);
		
		String insert16 = "INSERT INTO ordered_meal VALUES(1, 1, '2016-03-03', 2, 0, 0);";
		String insert17 = "INSERT INTO ordered_meal VALUES(2, 2, '2016-04-03', 1, 0, 0);";
		database.makeSingleStatement(insert16);
		database.makeSingleStatement(insert17);
		
		order1 = new Order(1, "2016-03-03", 10001, null, "bob");
		order2 = new Order(2, "2016-04-03", 10002, null, "bob");
		
		orderList.add(order1);
		orderList.add(order2);
		
		
		// *** SUBPLAN INSERT ***
		String insert18 = "INSERT INTO subscription_plan VALUES(1, 'Lunch plan');";
		String insert19 = "INSERT INTO subscription_plan VALUES(2, 'Dinner plan');";
		database.makeSingleStatement(insert18);
		database.makeSingleStatement(insert19);
		
		subPlan1 = new SubPlan(1, "Lunch plan");
		subPlan2 = new SubPlan(2, "Dinner plan");
		
		subPlanList.add(subPlan1);
		subPlanList.add(subPlan2);
	}

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testViewAvailableMeals() throws Exception {
		System.out.println("Test: View available meals");

		Meal res0 = user.viewAvailableMeals().get(0);
		Meal expRes0 = mealList.get(0);
		assertEquals(res0, expRes0);
		
		Meal res1 = user.viewAvailableMeals().get(1);
		Meal expRes1 = mealList.get(1);
		assertEquals(res1, expRes1);
		
		Meal res2 = user.viewAvailableMeals().get(2);
		Meal expRes2 = mealList.get(2);
		assertEquals(res2, expRes2);
	}
	
	
	@Test
	public void testViewCustomerList() throws Exception{
		System.out.println("Test: View customer list");
	
		Customer res0 = user.viewCustomerList().get(0);
		Customer expRes0 = customerList.get(0);
		assertEquals(res0, expRes0);
		
		Customer res1 = user.viewCustomerList().get(1);
		Customer expRes1 = customerList.get(1);
		assertEquals(res1, expRes1);
		
		Customer res2 = user.viewCustomerList().get(2);
		Customer expRes2 = customerList.get(2);
		assertEquals(res2, expRes2);
	}
	
	
	@Test
	public void testViewCompanyList() throws Exception{
		System.out.println("Test: View company list");
		
		Customer res0 = user.viewCompanyList().get(0);
		Customer expRes0 = companyList.get(0);
		assertEquals(res0, expRes0);
		
		Customer res1 = user.viewCompanyList().get(1);
		Customer expRes1 = companyList.get(1);
		assertEquals(res1, expRes1);

	}
	
	@Test
	public void testViewSingleCustomer() throws Exception{
		System.out.println("Test: view single customer");
		
		int id1 = 10001; // jensine
		int id2 = 10002; // lise
		int id3 = 19921; // no one
		
		Customer res1 = user.viewSingleCustomer(id1);
		Customer expRes1 = jensine;
		assertEquals(res1, expRes1);
	
		Customer res2 = user.viewSingleCustomer(id2);
		Customer expRes2 = lise;
		assertEquals(res2, expRes2);
		
		Customer res3 = user.viewSingleCustomer(id3);
		Customer expRes3 = null;
		assertEquals(res3,expRes3);
		
	}
	
	@Test
	public void testViewIngredients() throws Exception{
		System.out.println("Test: View ingredients");
		
		Ingredient res0 = user.viewIngredients().get(0);
		Ingredient expRes0 = ingredientList.get(0);
		assertEquals(res0, expRes0);
		
		Ingredient res1 = user.viewIngredients().get(1);
		Ingredient expRes1 = ingredientList.get(1);
		assertEquals(res1, expRes1);
		
		Ingredient res2 = user.viewIngredients().get(2);
		Ingredient expRes2 = ingredientList.get(2);
		assertEquals(res2, expRes2);

	}
	@Test
	public void testViewFoodOrders() throws Exception {
		System.out.println("Test: View Food orders");
		Date date0 = java.sql.Date.valueOf("2016-03-03");
		Date date1 = java.sql.Date.valueOf("2016-04-03");
		
		boolean res0 = user.viewFoodOrders(date0).get(0).equals(orderList.get(0));
		boolean expRes0 = true;
		assertEquals(res0, expRes0);
		
		boolean res1 = user.viewFoodOrders(date1).get(1).equals(orderList.get(1));
		boolean expRes1 = true;
		assertEquals(res1, expRes1);
	 		
	}
	
	@Test
	public void testViewAllSubPlans() throws Exception{
		System.out.println("Test: View all subscrition plans");
		
		SubPlan res0 = user.viewAllSubPlans().get(0);
		SubPlan expRes0 = subPlanList.get(0);
		assertEquals(res0, expRes0);
		
		boolean res1 = user.viewAllSubPlans().get(1).equals(subPlanList.get(1));
		boolean expRes1 = true;
		assertEquals(res1, expRes1);
		
//	ArrayList<SubPlan> viewAllSubPlans()
	}	

}
