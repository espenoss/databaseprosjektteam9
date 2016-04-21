package controller;
import java.util.ArrayList;

import databasePackage.*;

public class Admin extends User {
	
	public Admin(String userID, String name, Database database) {
		super(userID, 0, name,  database);
	}

	//Registrerer ny bruker
	public boolean registerUser(String userID, int userType, String name,String pword, Database database) throws Exception{
		return QMUser.registerUser(userID, userType, name, pword, database);
	}
	
	public boolean updateUser(String userID, int userType, String name, String pword, Database database) throws Exception{
		return(QMUser.updateUser(userID, userType, name, pword, database));
	}
	 
	
	
	// MÅ LAGES !! 
	public String getStatistics(){
		String statistics = null;
		
		
		
		QMOrder.calculateIncomeForPeriod(fromDate, toDate, database)
		
		return statistics;
	}
	
	//returns an arraylist with customer objects with all active users
	public ArrayList<User> viewUserList() throws Exception{
		
		String[][] list = QMUser.viewAllUsers(database);
		User tempUser;
		ArrayList<User> userList = new ArrayList<User>();
		
		for(int i=0; i<list.length; i++){
			int userType = Integer.parseInt(list[i][1]); 
			
			tempUser = new User(list[i][0],userType,list[i][2], database);
			userList.add(tempUser);
		}
		return userList;
	}
	
	
	
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Admin";
		
		return s;
	}
	
}
