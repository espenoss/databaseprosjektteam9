package controller;

import controller.User;

public class User implements java.io.Serializable{
	private String userID;
	private String name;
	private String pword;
	private int userType;
	
	public User(String userID,int userType, String name,String pword){
		this.userID=userID;
		this.userType=userType;
		this.name=name;
		this.pword=pword;
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
	
	public String viewCustomerList(){
		String customerList = null;
		
		return customerList;
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
}