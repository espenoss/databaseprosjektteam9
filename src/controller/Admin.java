package controller;
import java.util.ArrayList;

import databasePackage.*;

public class Admin extends User {
	
	public Admin(String userID,int userType, String name,String pword, Database database) {
		super(userID, userType, name, pword, database);
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
		
		// hent statistikk fra databasen
		
		return statistics;
	}
	
	//returns an arraylist with customer objects with all active users
	public ArrayList<User> viewUserList() throws Exception{
		
		String[][] list = QMUser.viewAllUsers(database);
		User tempUser;
		ArrayList<User> userList = new ArrayList<User>();
		
		for(int i=0; i<list.length; i++){
			int userType = Integer.parseInt(list[i][1]); 
			
			tempUser = new User(list[i][0],userType,list[i][2], null, database);
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
