package databasePackage;


public abstract class Database {
		private final String dbDriver;
		private final String dbName;
		
		public Database(String dbDriver, String dbName){
			this.dbDriver = dbDriver;
			this.dbName = dbName;
	}
}
