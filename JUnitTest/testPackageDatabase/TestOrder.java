package testPackageDatabase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import databasePackage.Database;
import databasePackage.QMFood;

public class TestOrder {
	public Database database;
	
	@Before
	public void beforeTest(){
		String username = "espenme";
		String password = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	@Test
	public void companyTest() throws Exception{

		// view all entries
		String[][] orders = QMFood.viewAllOrders(database);
		// view single entry
		String[] order = QMFood.viewOrder(Integer.parseInt(orders[0][0]), database);
		
		// check to see if they contain the same info
		for(int i=0;i<order.length;i++){
			assertEquals(order[i], orders[0][i]);
		}
		
		java.text.SimpleDateFormat s = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date tDate = s.parse("2016-04-13");
		java.sql.Date date = new java.sql.Date(tDate.getTime());
		
		String[][] delivery = QMFood.generateDeliveryList(date, database);
		
		for(int x=0;x<delivery.length;x++){
			for(int y=0;y<delivery[x].length;y++){
				System.out.print(delivery[x][y] + " ");
			}
			System.out.println("");
		}
		
		delivery = QMFood.viewOrdersByDeliveryDate(date, database);
		
		for(int x=0;x<delivery.length;x++){
			for(int y=0;y<delivery[x].length;y++){
				System.out.print(delivery[x][y] + " ");
			}
			System.out.println("");
		}

		delivery = QMFood.viewMealsInOrderByDeliveryDate(Integer.parseInt(order[0]), date, database);
		
		for(int x=0;x<delivery.length;x++){
			for(int y=0;y<delivery[x].length;y++){
				System.out.print(delivery[x][y] + " ");
			}
			System.out.println("");
		}		
		
		// attempt to register new info about existing entry
		boolean exp = QMFood.updateOrder(Integer.parseInt(order[0]), order[1], Integer.parseInt(order[2]), "Ny info", order[4], database);
		assertEquals(true, exp);		
		
		// attempt to remove entry		
		exp = QMFood.removeOrder(Integer.parseInt(order[0]), database);
		assertEquals(true, exp);

		// attempt to reregister removed entry	
		int iexp = QMFood.registerOrder(order[1], Integer.parseInt(order[2]), order[3], order[4], database);
		assertNotEquals(-1, iexp);		
	}
}
