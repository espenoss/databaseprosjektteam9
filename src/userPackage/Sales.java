package userPackage;

public class Sales extends User {
	
	public Sales(String name, String pword) {
		super(name, pword);
	}

	public boolean registerNewOrder(String orderinfo){
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
	
	public String viewCustomerList(){
		String customerList = null;
		
		return customerList;
	}
	
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Sales";
		
		return s;
	}
	
	
	
}
