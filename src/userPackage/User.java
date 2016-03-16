package userPackage;

public abstract class User{

	private String name;
	private String pword;
	
	User(String name, String pword){
		this.name = name;
		this.pword = pword;
	}
	
	public String getPword() {
		return pword;
	}

	public void setPword(String pword) {
		this.pword = pword;
	}

	public String getName() {
		return name;
	}

	public String viewFoodOrders(){
		// Skal sp�rre database
		return "Food orders:\n";
	}
	//view customer list
	public String viewCustomerList(){
		String customerList = null;
		
		return customerList;
	}
	
	public String viewOrderIngredients(){
		String ingredients = null;
		
		// hent ingredienser fra database
		
		return ingredients;
	}
	//toString
	public String toString(){
		return "Employee info:\n" + name;
	}
	
}
