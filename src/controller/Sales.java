package controller;

public class Sales extends User {
	
	public Sales(String name, String pword) {
		super(name, pword);
	}

	public boolean registerNewOrder(int customerID, int ){
		
		
		
		
		boolean success = false;
		
		
		return success;
	}
	
	public boolean registerSubscription(String orderinfo){
		boolean success = false;
		
		// opprett ny ordre i database
		
		return success;
	}

	public boolean changeFoodOrder(String orderinfo){
		boolean success = false;
		
		// generer meny, hent nye verdier,
		// lagre i database
		
		return success;
	}
	
	public boolean registerNewCustomer(String customerinfo){
		boolean success = false;
		
		// opprett ny kunde i database
		
		return success;		
	}
	
	public boolean changeCustomerInfo(String customerinfo){
		boolean success = false;
		
		// generer meny, hent nye verdier,
		// lagre i database
		
		return success;				
	}
	
	public boolean registerPackageDeal(){
		boolean success = false;
		
		// retrieve package from database
		// select package
		// display package
		// receive customer info
		
		return success;						
	}
		
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Sales";
		
		return s;
	}
	
	
	
}
