package database;

/**
 * The Class QMOrder.<br>
 * Contains database query methods related to order registration and fullfilment.<br>
 * Assembles statements from parameters and executes the them
 */

public class QMOrder {

	/**
	 * Register order.
	 *
	 * @param order_date Order date in YYYY-MM-DD format
	 * @param customer_id Customer ID
	 * @param info Info about customer
	 * @param user_id the User ID
	 * @param database Database connection
	 * @return Order ID
	 */
	public static int registerOrder(String order_date, int customer_id, String info, 
			String user_id, Database database) {

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


	/**
	 * Update order.
	 *
	 * @param orderID Order id
	 * @param order_date Order date in YYYY-MM-DD format
	 * @param customer_id Customer ID
	 * @param info Info about customer
	 * @param user_id the User ID
	 * @param database Database connection
	 * @return true, if successful
	 */
	public static boolean updateOrder(int orderID, String orderDate, int customerID, String info, 
			String userID, Database database) {

		String statement = "UPDATE food_order SET "
				+ "order_date = '" + orderDate + "', "
				+ "customer_id = " + customerID + ", "
				+ "info = " + aq(info)
				+ "user_id = '" + userID + "'"
				+ " WHERE order_id = " + orderID;

		return database.makeSingleStatement(statement);
	}

	/**
	 * View a food order.<br>
	 * Returns food order in a String array<br><br>
	 * <i>Columns by index:</i><br>
	 * 0 : order_id - int<br>
	 * 1 : order_date - String<br>
	 * 2 : customer_id - int<br>
	 * 3 : info - String<br>
	 * 4 : user_id - String<br>
	 *
	 * @param orderID Order ID
	 * @param database Database connection
	 * @return String[] of order information
	 */
	public static String[] viewOrder(int orderID, Database database) {

		String statement = "SELECT * FROM food_order WHERE order_id = " + orderID + ";";

		database.makeSingleStatement(statement);

		return database.getLastResult()[0];
	}

	/**
	 * View all orders.<br>
	 * Returns a list of all food orders in database in a two-dimensional String array<br><br>
	 * <i>Columns by second index:</i><br>
	 * 0 : order_id - int<br>
	 * 1 : order_date - String<br>
	 * 2 : customer_id - int<br>
	 * 3 : info - String<br>
	 * 4 : user_id - String<br>
	 * @param database Database connection
	 * @return String[][] of orders
	 */
	public static String[][] viewAllOrders(Database database){

		String statement = "SELECT * FROM food_order;";

		database.makeSingleStatement(statement);

		return database.getLastResult();
	}	

	/**
	 * View all orders from customer.
	 * Returns a list of all food orders in database in a two-dimensional String array<br><br>
	 * <i>Columns by second index:</i><br>
	 * 0 : order_id - int<br>
	 * 1 : order_date - String<br>
	 * 2 : customer_id - int<br>
	 * 3 : info - String<br>
	 * 4 : user_id - String<br>
	 *
	 * @param customerID Customer ID
	 * @param database Database connection
	 * @return String[][] of orders
	 */
	public static String[][] viewAllOrdersFromCustomer(int customerID, Database database){

		String statement = "SELECT * FROM food_order WHERE customer_id = " + customerID + ";";

		database.makeSingleStatement(statement);

		return database.getLastResult();
	}

	/**
	 * Adds the meal to order.
	 *
	 * @param orderID Order ID
	 * @param mealID Meal ID
	 * @param deliveryDate The delivery date of the meal
	 * @param quantity Quantity
	 * @param readyDelivery Whether the meal is ready for delivery
	 * @param delivered Whether the meal is delivered
	 * @param database Database
	 * @return true, if successful
	 */
	public static boolean addMealToOrder(int orderID, int mealID, String deliveryDate, int quantity, 
			boolean readyDelivery, boolean delivered, Database database){

		String statement = "INSERT INTO ordered_meal VALUES("
				+ orderID + "," 
				+ mealID + "," 
				+ aq(deliveryDate) 
				+ quantity + "," 
				+ readyDelivery + "," 
				+ delivered + ");";

		return database.makeSingleStatement(statement);
	}

	/**
	 * Update meal in order.
	 *
	 * @param orderID Order ID
	 * @param mealID Meal ID
	 * @param deliveryDate The delivery date of the meal
	 * @param quantity Quantity
	 * @param readyDelivery Whether the meal is ready for delivery
	 * @param delivered Whether the meal is delivered
	 * @param database Database
	 * @return true, if successful
	 */
	public static boolean updateMealInOrder(int orderID, int mealID, String deliveryDate, int quantity, 
			boolean readyDelivery, boolean delivered, Database database){

		String statement = "UPDATE ordered_meal SET "
				+ " order_id = " + orderID + "," 
				+ " meal_id = " + mealID + "," 
				+ " delivery_date = '" + deliveryDate+"'," 
				+ " quantity = " + quantity + "," 
				+ " ready_delivery = " + readyDelivery + "," 
				+ " delivered = " + delivered 
				+ " WHERE order_id =" + orderID
				+ " AND meal_id = " + mealID
				+ " AND delivery_date = '" + deliveryDate + "'"
				+";";
		return database.makeSingleStatement(statement);
	}


	/**
	 * Removes the meal from order.
	 *
	 * @param orderID Order ID
	 * @param mealID Meal ID
	 * @param deliveryDate The delivery date
	 * @param database Database
	 * @return true, if successful
	 */
	public static boolean removeMealFromOrder(int orderID, int mealID, String deliveryDate, Database database) {

		String statement = "DELETE FROM ordered_meal WHERE "
				+ "order_id =" + orderID
				+ " AND meal_id = " + mealID
				+ " AND delivery_date = '" + deliveryDate + "'"
				+ ";";
		return database.makeSingleStatement(statement);
	}


	/**
	 * Mark meal in order as ready for delivery.
	 *
	 * @param orderID Order ID
	 * @param mealID Meal ID
	 * @param deliveryDate The delivery date
	 * @param database Database connection
	 * @return true, if successful
	 */
	public static boolean markMealOrderAsReadyForDelivery(int orderID, int mealID, String deliveryDate,Database database) {
		String statement = "UPDATE ordered_meal SET ready_delivery=true "
				+ "WHERE order_id = " + orderID 
				+ " AND meal_id = " + mealID 
				+ " AND delivery_date = '" + deliveryDate + "'"
				+ ";";

		return database.makeSingleStatement(statement);
	}


	/**
	 * Mark meal in order as delivered.
	 *
	 * @param orderID Order ID
	 * @param mealID Meal ID
	 * @param deliveryDate The delivery date
	 * @param database Database connection
	 * @return true, if successful
	 */
	public static boolean markMealOrderAsDelivered(int orderID, int mealID, String deliveryDate,Database database) {
		String statement = "UPDATE ordered_meal SET delivered=true "
				+ "WHERE order_id = " + orderID 
				+ " AND meal_id = " + mealID 
				+ " AND delivery_date = '" + deliveryDate + "'"
				+ ";";

		return database.makeSingleStatement(statement);
	}	

	/**
	 * Returns all meals in an order in a two-dimensional String array<br>
	 * First index is row, second is column<br><br>
	 * <i>Columns by second index:</i><br>
	 * 0 : meal_id - int<br>
	 * 1 : meal_name - String<br>
	 * 2 : instructions - String<br>
	 * 3 : available - boolean<br>
	 * 4 : price - int<br>
	 * 5 : order_id - int <br>
	 * 6 : delivery_date - String<br>
	 * 7 : quantity - int <br>
	 * 8 : ready_delivery<br>
	 * 9 : delivered<br>
	 * @param orderID the order id
	 * @param database the database
	 * @return the string[][]
	 */
	public static String[][] viewMealsInOrder(int orderID, Database database) {

		String statement = "SELECT * FROM meal NATURAL JOIN ordered_meal WHERE order_id= "+ orderID +";";

		database.makeSingleStatement(statement);

		return database.getLastResult();		
	}

	/**
	 * View total price of an order.
	 *
	 * @param orderID Order ID
	 * @param database Database connection
	 * @return Price of the order
	 */
	public static int viewOrderPrice(int orderID, Database database) {

		String statement = "SELECT SUM(quantity*price) FROM "
				+ "(SELECT a.quantity,b.price FROM ordered_meal AS a, meal AS b WHERE a.order_id =  " + orderID
				+ " AND b.meal_id = a.meal_id) AS tab";

		database.makeSingleStatement(statement);

		String[][] result = database.getLastResult();
		if(result[0] == null){
			return 0;
		}else{
			return Integer.parseInt(result[0][0]);
		}
	}

	/**
	 * Returns meals to be delivered at DeliveryDate in a two-dimensional String array<br>
	 * First index is row, second is column<br><br>
	 * <i>Columns by second index:</i><br>
	 * 0 : order_id - int<br>
	 * 1 : order_date - String<br>
	 * 2 : customer_id - int<br>
	 * 3 : info - String<br>
	 * 4 : user_id - String<br>
	 *
	 * @param deliveryDate The delivery date
	 * @param database Database connection
	 * @return String[][] of meal information
	 */
	public static String[][] viewOrdersByDeliveryDate(java.sql.Date deliveryDate, Database database) {

		String statement = "SELECT DISTINCT food_order.* FROM food_order NATURAL JOIN ordered_meal where food_order.order_id = ordered_meal.order_id AND delivery_date = '" + deliveryDate + "';";

		database.makeSingleStatement(statement);

		return database.getLastResult();
	}

	/**
	 * Adds subscription plan to order.
	 *
	 * @param orderID Order ID
	 * @param quantitySub Quantity
	 * @param fromDate From date
	 * @param toDate To date
	 * @param subID Sub ID
	 * @param database Database connection
	 * @return true, if successful
	 */
	public static boolean addSubscriptionToOrder(int orderID, int quantitySub, String fromDate, String toDate, 
			int subID, Database database) {		
		String statement = "INSERT INTO sub_order VALUES(" 
				+ orderID + "," 
				+ quantitySub + "," 
				+ aq(fromDate) 
				+ aq(toDate) + "'" 
				+ subID 
				+ "');";			
		return database.makeSingleStatement(statement);
	}


	/**
	 * Update subscription in order.
	 *
	 * @param orderID Order ID
	 * @param quantitySub Quantity
	 * @param fromDate From date
	 * @param toDate To date
	 * @param subID Sub ID
	 * @param database Database connection
	 * @return true, if successful
	 */
	public static boolean updateSubscriptionInOrder(int orderID, int quantitySub, String fromDate, String toDate, 
			int subID, Database database) {

		String statement = "UPDATE sub_order SET "
				+ "quantity_sub = " + quantitySub + ", "
				+ "from_date = " + aq(fromDate)
				+ "to_date = " + aq(toDate)
				+ "sub_id = " + subID + " "
				+ "WHERE order_id = " + orderID
				+ ";";

		return database.makeSingleStatement(statement);
	}

	/**
	 * View subscription order.<br>
	 * Returns String array of subscription information<br>
	 * <br> 
	 * <i>Columns by index:</i><br>
	 * 0 : order_id - int<br>
	 * 1 : quantity_sub - int<br>
	 * 2 : from_date - String<br>
	 * 3 : to_date - String<br>
	 * 4 : sub_id - int
	 * 
	 * @param orderID Order ID
	 * @param database Database connection
	 * @return the string[] of subscription order
	 */
	public static String[] viewSubscriptionInOrder(int orderID, Database database) {

		String statement = "SELECT * FROM sub_order WHERE order_id = " + orderID + ";";

		database.makeSingleStatement(statement);

		return database.getLastResult()[0];
	}	

	/**
	 * View all subscription orders.<br>
	 * Returns two-dimensional String array of subscription information<br>
	 * <br> 
	 * <i>Columns by second index:</i><br>
	 * 0 : order_id - int<br>
	 * 1 : quantity_sub - int<br>
	 * 2 : from_date - String<br>
	 * 3 : to_date - String<br>	 
	 * 4 : sub_id - int
	 * 
	 * @param orderID Order ID
	 * @param database Database connection
	 * @return String[][] of subscription orders
	 */
	// View all subscription connected to orders
	public static String[][] viewAllSubscriptionsInOrders(Database database) {

		String statement = "SELECT * FROM sub_order;";

		database.makeSingleStatement(statement);

		return database.getLastResult();
	}

	/**
	 * Generate delivery list.<br>
	 * Returns two-diemnsional String array of delivery information<br>
	 * <br> 
	 * <i>Columns by second index:</i><br>
	 * 0 : meal_name - String<br>
	 * 1 : quantity - int<br>
	 * 2 : adress - String<br>
	 * 3 : firstname - String<br>
	 * 4 : surname - String<br>	 	 
	 * 5 : order_id - int<br>
	 * 6 : meal_id - int
	 * 
	 * @param currentDate The current date
	 * @param database Database connection
	 * @return String[][] of delivery information
	 */
	public static String[][] generateDeliveryList(java.sql.Date currentDate, Database database) {

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

	/**
	 * Calculate income for period of fromDate to toDate.
	 *
	 * @param fromDate From date
	 * @param toDate To date
	 * @param database Dataabse connection
	 * @return Income for the period specified
	 */
	public static int calculateIncomeForPeriod(java.sql.Date fromDate, java.sql.Date toDate, Database database) {
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

	/**
	 * 'Add quotes'.<br>
	 * Convenience function to make queries easier to read
	 * Adds single quotes at either end and a comma at the end
	 *
	 * @param s String to add quotes to
	 * @return String with quotes around it
	 */
	private static String aq(String s){
		return "'" + s + "', ";
	}
}

