package testPackageDatabase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import database.*;

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
		String[][] orders = QMOrder.viewAllOrders(database);
		// view single entry
		int lastOrder = Integer.parseInt(orders[orders.length-1][0]);
		String[] order = QMOrder.viewOrder(lastOrder, database);
		
		System.out.println(lastOrder);
		
		// check to see if they contain the same info
		for(int i=0;i<order.length;i++){
			assertEquals(order[i], orders[orders.length-1][i]);
		}
		
		java.text.SimpleDateFormat s = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date tDate = s.parse("2016-04-13");
		java.sql.Date date = new java.sql.Date(tDate.getTime());
		
		String[][] delivery = QMOrder.generateDeliveryList(date, database);
		
		for(int x=0;x<delivery.length;x++){
			for(int y=0;y<delivery[x].length;y++){
				System.out.print(delivery[x][y] + " ");
			}
			System.out.println("");
		}
				
		delivery = QMOrder.viewAllOrdersFromCustomer(10002, database);
		
		for(int x=0;x<delivery.length;x++){
			for(int y=0;y<delivery[x].length;y++){
				System.out.print(delivery[x][y] + " ");
			}
			System.out.println("");
		}
		
		delivery = QMOrder.viewOrdersByDeliveryDate(date, database);
		
		for(int x=0;x<delivery.length;x++){
			for(int y=0;y<delivery[x].length;y++){
				System.out.print(delivery[x][y] + " ");
			}
			System.out.println("");
		}

		tDate = s.parse("2016-04-20");
		java.sql.Date fromDate = new java.sql.Date(tDate.getTime());
		
		tDate = s.parse("2016-04-22");
		java.sql.Date toDate = new java.sql.Date(tDate.getTime());
		
		QMOrder.calculateIncomeForPeriod(fromDate, toDate, database);
		
		QMOrder.viewOrderPrice(Integer.parseInt(order[0]), database);
		
		// attempt to register new info about existing entry
		boolean exp = QMOrder.updateOrder(Integer.parseInt(order[0]), order[1], Integer.parseInt(order[2]), "Ny info", order[4], database);
		assertEquals(true, exp);		
		
		// attempt to reregister removed entry	
		int iexp = QMOrder.registerOrder(order[1], Integer.parseInt(order[2]), order[3], order[4], database);
		assertNotEquals(-1, iexp);		
	}
}
