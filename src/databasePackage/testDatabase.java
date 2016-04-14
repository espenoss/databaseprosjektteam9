package databasePackage;

public class testDatabase {
	public static void main(String[] args){
		String dbDriver = "com.mysql.jdbc.Driver";
		String username = "anitakau";
		String password = "e82L3Dat";
		String dbName = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		
		Database database = new Database(dbDriver, dbName);
		
		System.out.println(database.initiateDb());
	}
}
