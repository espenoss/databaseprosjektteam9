package BareGUItest;

import BareGUItest.User;

public class User implements java.io.Serializable{
	private String username;
	private String password;
	
	public User(String username, String password){
		this.username=username;
		this.password=password;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getUsername(){
		return username;
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
		return (username==p.getUsername()&&password==p.getPassword());
	}
	public String toString(){
		return username+" "+password;
	}
	

}