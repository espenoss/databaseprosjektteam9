package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import databasePackage.*;

public class User implements java.io.Serializable{
	private String userID;
	private int userType;
	private String name;
	private String pword;
	Database database;
	
	public User(String userID, int userType, String name,String pword, Database database){
		this.userID=userID;
		this.userType=userType;
		this.name=name;
		this.pword=pword;
		this.database = database;
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
	
	public Database getDatabase(){
		return database;
		
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