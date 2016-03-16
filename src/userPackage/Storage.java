package userPackage;

public class Storage extends User {

	public Storage(String name, String pword) {
		super(name, pword);
	}
	
	public boolean registerDelivery(String orderinfo){
		boolean success = false;
		
		// oppdater database med ny info
		
		return success;
	}
	
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Storage";
		
		return s;
	}

}