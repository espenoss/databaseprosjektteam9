package controller;

import databasePackage.Database;
import databasePackage.QueryMethods;

public class Customer implements java.io.Serializable{
	private int customerID;
	private String firstName;
	private String surName;
	private String email;
	private String adress;
	private int zip_code;
	private int zone_nr;
	private String preferences;
	private String phoneNumber;
	private boolean active;
	
	
	public Customer(int customerID, String firstName, String surName, String phoneNumber, String email, String adress, int zip_code, int zone_nr, String preferences, boolean active){
		this.firstName=firstName;
		this.surName=surName;
		this.email=email;
		this.adress=adress;
		this.zip_code=zip_code;
		this.zone_nr=zone_nr;
		this.preferences=preferences;
		this.phoneNumber=phoneNumber;
		this.customerID=customerID;
		this.active = active;
	}
	public void setCustomerID(int customerID){
		this.customerID=customerID;
	}
	public String setFirstName(String firstName){
		this.firstName=firstName;
		return firstName;
	}
	public String setSurName(String surName){
		this.surName=surName;
		return surName;
	}
	public String setAdress(String adress){
		this.adress=adress;
		return adress;
	}
	public String setEmail(String email){
		this.email=email;
		return email;
	}
	
	public int setZipCode(int zip_code){
		this.zip_code=zip_code;
		return zip_code;
	}
	public int setZoneNr(int zone_nr){
		this.zone_nr=zone_nr;
		return zone_nr;
	}
	public String setPreferences(String preferences){
		this.preferences=preferences;
		return preferences;
	}
	public String setPhoneNumber(String phoneNumber){
		this.phoneNumber=phoneNumber;
		return phoneNumber;
	}
	
	public boolean setActive(boolean active){
		this.active = active;
		return active;
	}

	public int getCustomerID(){
		return customerID;
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
	
	public boolean getActive(){
		return active;
	}
	
	public boolean updateCustomer(int customerID, String newSurName, String newFirstName, String newPhoneNumber, String newEmail, String newAdress, int newZipCode, int newZoneNr, String newPreferences, boolean newActive, Database database) throws Exception{
		if(QueryMethods.updateCustomer(customerID, setSurName(newSurName), setFirstName(newFirstName), setPhoneNumber(newPhoneNumber), setEmail(newEmail), setAdress(newAdress), setZipCode(newZipCode), setZoneNr(newZoneNr), setPreferences(newPreferences), setActive(newActive), database)){
			return true;
		}
		return false;
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
		return "CustomerID: " +customerID+" First name: " + firstName+". Surname: "+ surName +"\nEmail: "+email+". Phonenr: "+phoneNumber+".\nAdress: "+adress+". ZipCode: "+zip_code+". ZoneNr: "+zone_nr+".\nPreferences: "+preferences+"\n\n";
	}
}