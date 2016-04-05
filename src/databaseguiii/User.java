package databaseguiii;

import databaseguiii.User;

public class User implements java.io.Serializable{
	private String username;
	private String password;
	private String role;
	
	public User(String username, String role, String password){
		this.username=username;
		this.role=role;
		this.password=password;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public void setRole(String role){
		this.role = role;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getUsername(){
		return username;
	}
	public String getRole(){
		return role;
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
		return username+" "+ role +" "+password;
	}
}