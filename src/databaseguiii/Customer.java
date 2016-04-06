package databaseguiii;


public class Customer implements java.io.Serializable{
	private String firstName;
	private String surName;
	private String email;
	private String adress;
	private int zip_code;
	private int zone_nr;
	private String preferences;
	private String phoneNumber;
	
	public Customer(String firstName, String surName, String phoneNumber, String email, String adress, int zip_code, int zone_nr, String preferences){
		this.firstName=firstName;
		this.surName=surName;
		this.email=email;
		this.adress=adress;
		this.zip_code=zip_code;
		this.zone_nr=zone_nr;
		this.preferences=preferences;
		this.phoneNumber=phoneNumber;
	}
	public void setFirstName(String firstName){
		this.firstName=firstName;
	}
	public void setSurName(String surName){
		this.surName=surName;
	}
	public void setAdress(String adress){
		this.adress=adress;
	}
	public void setEmail(String email){
		this.email=email;
	}
	
	public void setZipCode(int zip_code){
		this.zip_code=zip_code;
	}
	public void setZoneNr(int zone_nr){
		this.zone_nr=zone_nr;
	}
	public void setPreferences(String preferences){
		this.preferences=preferences;
	}
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber=phoneNumber;
	}
	public String getFirstName(){
		return firstName;
	}
	public String getSurName(){
		return surName;
	}
	public String getEmail(){
		return email;
	}
	public String getAdress(){
		return adress;
	}
	public int getZipCode(){
		return zip_code;
	}
	public int getZoneNr(){
		return zone_nr;
	}
	public String getPreferences(){
		return preferences;
	}
	public String getPhoneNumber(){
		return phoneNumber;
	}
	
	
	public boolean equals(Object obj){
		if(!(obj instanceof Customer)){
			return false;
		}
		if(this==obj){
			return true;
		}
		Customer c=(Customer)obj;
		return (firstName==c.getFirstName()&&surName==c.getSurName()&&email==c.getEmail()
				&&adress==c.getAdress()&&zip_code==c.getZipCode()&&zone_nr==c.zone_nr&&preferences==c.getPreferences()&&phoneNumber==c.getPhoneNumber());
	}
	public String toString(){
		return firstName+" "+ surName +" "+email+" "+adress+" "+zip_code+" "+zone_nr+" "+preferences + "" + phoneNumber;
	}
}