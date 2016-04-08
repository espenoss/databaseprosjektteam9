package controller;

public class Cook extends User {

	public Cook(String userID,int userType, String name,String pword) {
		super(userID, userType, name, pword);
	}
	
	public boolean markAsReady(){
		boolean success = false;
		
		// oppdater ordre i database
		
		return success;
	}
	
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Cook";
		
		return s;
	}
	
}
