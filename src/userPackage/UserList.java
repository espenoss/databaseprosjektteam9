package userPackage;

import java.util.*;

public class UserList {

	private ArrayList<User> userList;
	
	UserList(){
		// Hent brukerliste fra database
		userList.add(new Admin("admin", "admin", "Admin"));
	}
	
	public User login(String name, String pword){

		for(User user: userList){		
			if(name == user.getName() && pword == user.getPword()){
				return user;				
			}			
		}
		return null;
	}
	
	public static void main(String[] args) {		

		// testmetode
		
	}

}
