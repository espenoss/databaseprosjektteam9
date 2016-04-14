package controller;
import java.util.ArrayList;

import databasePackage.*;

public class Admin extends User {
	

	public Admin(String userID,int userType, String name,String pword, Database database) {
		super(userID, userType, name, pword, database);
	}


	//Registrerer ny bruker
	public boolean registerUser(String userID, int userType, String name,String pword, Database database) throws Exception{
		return QueryMethods.registerUser(userID, userType, name, pword, database);
	}
	
	public boolean updateUser(String userID, int userType, String name, String pword, Database database) throws Exception{
		return(QueryMethods.updateUser(userID, userType, name, pword, database));
	}
	
	public String getStatistics(){
		String statistics = null;
		
		// hent statistikk fra databasen
		
		return statistics;
	}
	
	//returns an arraylist with customer objects with all active customers
	public ArrayList<User> viewUserList() throws Exception{
		
		String[][] list = QueryMethods.viewAllUsers(database);
		User tempUser;
		ArrayList<User> userList = new ArrayList<User>();
		
		for(int i=0; i<list.length; i++){
			if (Integer.parseInt(list[i][1])==1){
		//		int userID = Integer.parseInt(list[i][0]); //0
				int userType = Integer.parseInt(list[i][1]); 
				
				tempUser = new User(list[i][0],userType,list[i][2], null, database);
				userList.add(tempUser);
			}
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
