package databaseguiii;

import databaseguiii.User;

public class User implements java.io.Serializable{
	private String userID;
	private String name;
	private String password;
	private int userType;
	
	public User(String userID,int userType, String name,String password){
		this.userID=userID;
		this.userType=userType;
		this.name=name;
		this.password=password;
	}	
	public void setUseID(String userID){
		this.userID=userID;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setUserType(int userType){
		this.userType = userType;
	}
	public void setPassword(String password){
		this.password=password;
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
	public String getPassword(){
		return password;
	}
	public boolean equals(Object obj){
		if(!(obj instanceof User)){
			return false;
		}
		if(this==obj){
			return true;
		}
		User p=(User)obj;
		return (userID==p.getUserID() && name==p.getName()&&userType==p.getUserType()&&password==p.getPassword());
	}
	public String toString(){
		return userID+""+ name+" "+ userType +" "+password;
	}
}