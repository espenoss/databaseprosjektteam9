package databaseguiii;


public class Customer implements java.io.Serializable{
	private String firstName;
	private String surName;
	private String email;
	private String adress;
	private int zip_code;
	private int zone_nr;
	private String preferences;
	
	public User(String firstName, String surName, String email, String adress, int zip_code, int zone_nr, String preferences){
		this.firstName=firstName;
		this.surName=surName;
		this.email=email;
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
		return (username==p.getUsername()&&password==p.getPassword()&&role==p.getRole());
	}
	public String toString(){
		return username+" "+ role +" "+password;
	}
}