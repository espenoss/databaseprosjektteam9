package userPackage;

public class Admin extends User {

	String userTitle;
	
	public Admin(String name, String pword, String userTitle) {
		super(name, pword);
		this.userTitle= userTitle;
	}

	public boolean registerUser(){
		boolean success = true;
		
		// register ny bruker i databasen
		// test
		
		return success;
	}
	
	public String getStatistics(){
		String statistics = null;
		
		// hent statistikk fra databasen
		
		return statistics;
	}
	
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += userTitle;
		
		return s;
	}
	
}
