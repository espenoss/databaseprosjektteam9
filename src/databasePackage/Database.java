package databasePackage;


public abstract class Database {
		private final String dbDriver;
		private final String dbName;
		
		public Database(String dbDriver, String dbName){
			this.dbDriver = dbDriver;
			this.dbName = dbName;
	}
	
	public abstract boolean registerUser(String userType, String name, String password);
	
	public abstract boolean registerCompany(String companyName, String firstName, String surName, String email, String adress, String zip_code, String zone_nr, String preferences, String active);
	public abstract boolean registerCustomer(String firstName, String surName, String email, String adress, String zip_code, String zone_nr, String preferences, String active);

	public abstract boolean registerOrder(String delivery_date, int quantity, String mealName);
	public abstract boolean registerSubscription(String delivery_date, int quantity, String fromDate, String toDate, String subName);
	
	public abstract boolean updateOrder(String delivery_date, int quantity, String mealName);
	
	
	
	public abstract boolean updateCompany(String customerId, String companyName, String firstName, String surName, String email, String adress, String zip_code, String zone_nr, String preferences, String active);
	public abstract boolean updateCustomer(String customerId, String surName, String email, String adress, String zip_code, String zone_nr, String preferences, String active);
	
	
}
