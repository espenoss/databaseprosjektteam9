package controller;

import databasePackage.*;

public class User implements java.io.Serializable{
	private String userID;
	private String name;
	private String pword;
	private int userType;
	Database database;
	
	public User(String userID,int userType, String name,String pword, Database database){
		this.userID=userID;
		this.userType=userType;
		this.name=name;
		this.pword=pword;
		this.database = database;
	}	
	public void setUserID(String userID){
		this.userID=userID;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setUserType(int userType){
		this.userType = userType;
	}
	public void setPassword(String pword){
		this.pword=pword;
	}
	public String getUserID(){
		return userID;
	}
	
	public String getName(){
		return name;
	}
	public int getUserType(){
		return userType;
	}
	public String getPword(){
		return pword;
	}
	
	public String viewFoodOrders(){
		// Skal sporre database
		return "Food orders:\n";
	}
	
	public Customer viewCustomerList() throws Exception{
		
		String[][] tempList = QueryMethods.viewAllCustomers(database);
		
		String res = "";
		
		for (int i=0;i<tempList.length;i++){
			for(int j=0;j<tempList[i].length;j++){
				res+=tempList[i][j];
			}
		}
		System.out.println(res);
		
		return null;
	}
	
	public String viewOrderIngredients(){
		String ingredients = null;
		
		// hent ingredienser fra database
		
		return ingredients;
	}
	
	public boolean equals(Object obj){
		if(!(obj instanceof User)){
			return false;
		}
		if(this==obj){
			return true;
		}
		User p=(User)obj;
		return (userID==p.getUserID() && name==p.getName()&&userType==p.getUserType()&&pword==p.getPword());
	}
	public String toString(){
		return userID+""+ name+" "+ userType +" "+pword;
	}
	
	
	public static void main(String[] args){
		String username = "marith1";
		String password = "";
		String databaseName = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		String databaseDriver = "com.mysql.jdbc.Driver";
		Database database = new Database(databaseDriver, databaseName);
		
		User user = new User("Hanne",1, "Hanne","1234", database);
	}
}