package databasePackage;

import java.util.Arrays;

//QueryMethods for orders and deliveryplan

public class QMOrder {
	
	// Register new order in database
	public static int registerOrder(String order_date, int customer_id, String info, 
			String user_id, Database database) throws Exception{
	
		for(int i=0; i<5; i++){
			String statement = "SELECT MAX(order_id) FROM food_order;";
			database.makeSingleStatement(statement);

			String highestID = database.getLastResult()[0][0];
			int orderID = 1;			
			if(highestID != null){
				orderID = Integer.parseInt(highestID) + 1;
			}			
			
			statement = "INSERT INTO food_order VALUES(" 
					+ orderID + ", "
					+ aq(order_date) 
					+ customer_id + ", " 
					+ aq(info) + "'" 
					+ user_id 
					+ "');";
			if(database.makeSingleStatement(statement)) return orderID;
		}
		return -1;
	}
	
	
	// Update order info
	public static boolean updateOrder(int orderID, String orderDate, int customerID, String info, 
			String userID, Database database) throws Exception{
		
		String statement = "UPDATE food_order SET "
				+ "order_date = '" + orderDate + "', "
				+ "customer_id = " + customerID + ", "
				+ "info = " + aq(info)
				+ "user_id = '" + userID + "'"
				+ " WHERE order_id = " + orderID;
		
		return database.makeSingleStatement(statement);
	}
	
	// Remove order from database
	public static boolean removeOrder(int orderID, Database database) throws Exception{
		
		String statement = "DELETE FROM food_order WHERE order_id = '" + orderID + "';";
		
		return database.makeSingleStatement(statement);
	}
	
	// View list of a food order.
	// Returns food order in a String array
	// Columns by index:
	// 0 : order_id - int
	// 1 : order_date - String
	// 2 : customer_id - int
	// 3 : info - String
	// 4 : user_id - String
	public static String[] viewOrder(int orderID, Database database) throws Exception{
		
		String statement = "SELECT * FROM food_order WHERE order_id = " + orderID + ";";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult()[0];
	}

	// View list of all food orders.
	// Returns a list of all food orders in database in a two-dimensional String array
	// First index is row, second is column
	// Columns by second index:
	// 0 : order_id - int
	// 1 : order_date - String
	// 2 : customer_id - int
	// 3 : info - String
	// 4 : user_id - String
	public static String[][] viewAllOrders(Database database) throws Exception{
		
		String statement = "SELECT * FROM food_order;";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}	
	
	public static String[][] viewAllOrdersFromCustomer(int customerID, Database database) throws Exception{
		
		String statement = "SELECT * FROM food_order WHERE customer_id = " + customerID + ";";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}
	
	// **** IKKE TESTET
	// Connect meal to order. Both must exisst in database to succeed
	public static boolean addMealToOrder(int orderID, int mealID, String deliveryDate, int quantity, 
			boolean readyDelivery, boolean delivered, Database database) throws Exception{
		
		String statement = "INSERT INTO ordered_meal VALUES("
				+ orderID + "," 
				+ mealID + "," 
				+ aq(deliveryDate) 
				+ quantity + "," 
				+ readyDelivery + "," 
				+ delivered + ");";
		
		return database.makeSingleStatement(statement);
	}

	
	// **** IKKE TESTET
	// Update info about meal in order
	public static boolean updateMealInOrder(int orderID, int mealID, String deliveryDate, int quantity, 
			boolean readyDelivery, boolean delivered, Database database) throws Exception{
		 
		String statement = "UPDATE ordered_meal SET "
				+ "order_id = " + orderID + "," 
				+ "meal_id = " + mealID + "," 
				+ "delivery_date = '" + deliveryDate+"'," 
				+ "quantity = " + quantity + "," 
				+ "ready_delivery = " + readyDelivery + "," 
				+ "delivered = " + delivered 
				+ "WHERE order_id =" + orderID
				+ " AND meal_id = " + mealID
				+ " AND delivery_date = '" + deliveryDate + "'"
				+";";
		
		return database.makeSingleStatement(statement);
	}
	 
	
	// Remove meal from order
	public static boolean removeMealFromOrder(int orderID, int mealID, String deliveryDate, Database database) throws Exception{

		String statement = "DELETE FROM ordered_meal WHERE "
				+ "order_id =" + orderID
				+ " AND meal_id = " + mealID
				+ " AND delivery_date = '" + deliveryDate + "'"
				+ ";";
		return database.makeSingleStatement(statement);
	}
	
	
	// Mark meal in order as ready for delivery
	public static boolean markMealOrderAsReadyForDelivery(int orderID, int mealID, String deliveryDate,Database database) throws Exception{
		String statement = "UPDATE ordered_meal SET ready_delivery=true "
				+ "WHERE order_id = " + orderID 
				+ " AND meal_id = " + mealID 
				+ " AND delivery_date = '" + deliveryDate + "'"
				+ ";";
		
		return database.makeSingleStatement(statement);
	}
	
	
	// Mark meal in order as delivered
	public static boolean markMealOrderAsDelivered(int orderID, int mealID, String deliveryDate,Database database) throws Exception{
		String statement = "UPDATE ordered_meal SET delivered=true "
				+ "WHERE order_id = " + orderID 
				+ " AND meal_id = " + mealID 
				+ " AND delivery_date = '" + deliveryDate + "'"
				+ ";";
		
		return database.makeSingleStatement(statement);
	}	

	
	// legge til bestillingsinfo?
	// Returns all meals in an order in a two-dimensional String array
	// First index is row, second is column
	// Columns by second index:
	// 0 : meal_id - int
	// 1 : meal_name - String
	// 2 : instructions - String
	// 3 : available - boolean
	// 4 : price - int
	// 5 : order_id - int 
	// 6 : delivery_date - String
	// 7 : quantity - int 
	// 8 : ready_delivery
	// 9 : delivered
	
	public static String[][] viewMealsInOrder(int orderID, Database database) throws Exception{

		String statement = "SELECT * FROM meal NATURAL JOIN ordered_meal WHERE order_id= "+ orderID +";";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();		
	}
	
	// Calculates the total price of an order by summing the prices of the individual meals multiplied by quantity
	public static int viewOrderPrice(int orderID, Database database) throws Exception{

		String statement = "SELECT SUM(quantity*price) FROM "
				+ "(SELECT a.quantity,b.price FROM ordered_meal AS a, meal AS b WHERE a.order_id =  " + orderID
				+ " AND b.meal_id = a.meal_id) AS tab";
		
		database.makeSingleStatement(statement);
		
		return Integer.parseInt(database.getLastResult()[0][0]);
	}

	// Returns order list of orders together with any orders to be delivered at deliveryDate
	public static String[][] viewOrdersByDeliveryDate(java.sql.Date deliveryDate, Database database) throws Exception{
		
		String statement = "SELECT DISTINCT food_order.* FROM food_order NATURAL JOIN ordered_meal where food_order.order_id = ordered_meal.order_id AND delivery_date = '" + deliveryDate + "';";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}
	
	
	public static String[][] viewMealsInOrderByDeliveryDate(int orderID, java.sql.Date deliveryDate, Database database) throws Exception{
			
		String statement = "SELECT a.*, b.adress "
				+ "FROM meal AS a, customer AS b, (SELECT a.order_id, a.meal_id, b.customer_id FROM "
				+ "ordered_meal AS a, "
				+ "food_order AS b WHERE a.delivery_date = '" + deliveryDate + "' "
				+ "AND a.order_id = b.order_id AND a.order_id = " + orderID + ") AS c "
				+ "WHERE a.meal_id = c.meal_id AND b.customer_id = c.customer_id";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}

	
	// Connect subscription plan to order
	public static boolean addSubscriptionToOrder(int orderID, int quantitySub, String fromDate, String toDate, 
			int subID, Database database) throws Exception{		
		String statement = "INSERT INTO sub_order VALUES(" 
				+ orderID + "," 
				+ quantitySub + "," 
				+ aq(fromDate) 
				+ aq(toDate) + "'" 
				+ subID 
				+ "');";			
		return database.makeSingleStatement(statement);
	}
	
	
	// Update subscription information in order
	public static boolean updateSubscriptionInOrder(int orderID, int quantitySub, String fromDate, String toDate, 
			int subID, Database database) throws Exception{
		
		String statement = "UPDATE sub_order SET "
				+ "quantity_sub = " + quantitySub + ", "
				+ "from_date = " + aq(fromDate)
				+ "to_date = " + aq(toDate)
				+ "sub_id = " + subID + " "
				+ "WHERE order_id = " + orderID
				+ ";";
		
		return database.makeSingleStatement(statement);
	}
	
	
	// Remove subscription from order
	public static boolean removeSubscriptionFromOrder(int orderID, Database database) throws Exception{
		
		String statement = "DELETE FROM sub_order WHERE order_id = " + orderID + ";";
		
		return database.makeSingleStatement(statement);
	}
	
	
	// View subcription information for order
	public static String[] viewSubscriptionInOrder(int orderID, Database database) throws Exception{

		String statement = "SELECT * FROM sub_order WHERE order_id = " + orderID + ";";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult()[0];
	}	
	
	// View all subscription connected to orders
	public static String[][] viewAllSubscriptionsInOrders(Database database) throws Exception{

		String statement = "SELECT * FROM sub_order;";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}
	
	public static String[][] generateDeliveryList(java.sql.Date currentDate, Database database) throws Exception{
		
		String statement = "SELECT a.meal_name, c.quantity, b.adress, b.firstname, b.surname, c.order_id, a.meal_id "
				+ "FROM meal AS a, customer AS b, (SELECT a.meal_id, a.quantity, b.customer_id, b.order_id FROM "
				+ "ordered_meal AS a, "
				+ "food_order AS b WHERE a.delivery_date = '" + currentDate + "' "
				+ "AND a.order_id = b.order_id "
				+ "AND a.ready_delivery = true "
				+ "AND a.delivered = false) AS c "
				+ "WHERE a.meal_id = c.meal_id AND b.customer_id = c.customer_id";
		
		database.makeSingleStatement(statement);
		
		return database.getLastResult();
	}
	
	public static int calculateIncomeForPeriod(java.sql.Date fromDate, java.sql.Date toDate, Database database) throws Exception{
		String statement = "SELECT SUM(price*quantity) FROM meal NATURAL JOIN ordered_meal "
				+ "WHERE delivery_date >= '" + fromDate + "' "
				+ "AND delivery_date <= '" + toDate + "' "
				+ "AND delivered = true";

		database.makeSingleStatement(statement);
		
		String[][] result = database.getLastResult();
		
		if(result.length == 0 || result[0][0]== null){
			return 0;			
		}else{
			return Integer.parseInt(result[0][0]);			
		}

	}
	
	// Puts a ' on either side and a comma at the end of a string 
	public static String aq(String s){
		return "'" + s + "', ";
	}
}

