package controller;

import databasePackage.Database;
import databasePackage.QueryMethods;

public class Customer implements java.io.Serializable{
	private int customerID;
	private String firstName;
	private String surName;
	private String email;
	private String adress;
	private int zipCode;
	private int zoneNr;
	private String preferences;
	private String phoneNumber;
	private boolean active;
	
	
	public Customer(int customerID, String firstName, String surName, String phoneNumber, String email, String adress, int zipCode, int zoneNr, String preferences, boolean active){
		this.firstName=firstName;
		this.surName=surName;
		this.email=email;
		this.adress=adress;
		this.zipCode=zipCode;
		this.zoneNr=zoneNr;
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
		this.zipCode=zip_code;
		return zip_code;
	}
	public int setZoneNr(int zone_nr){
		this.zoneNr=zone_nr;
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
		return zipCode;
	}
	public int getZoneNr(){
		return zoneNr;
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
	
	public boolean updateCustomer(int customerID, String surName, String firstName, String phoneNumber, String email, String adress, int zipCode, int zoneNr, String preferences, boolean active, Database database) throws Exception{
		if(QueryMethods.updateCustomer(customerID, setSurName(surName), setFirstName(firstName), setPhoneNumber(phoneNumber), setEmail(email), setAdress(adress), setZipCode(zipCode), setZoneNr(zoneNr), setPreferences(preferences), setActive(active), database)){
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
				&&adress==c.getAdress()&&zipCode==c.getZipCode()&&zoneNr==c.zoneNr&&preferences==c.getPreferences()&&phoneNumber==c.getPhoneNumber());
	}
	public String toString(){
		return "CustomerID: " +customerID+" First name: " + firstName+". Surname: "+ surName +"\nEmail: "+email+". Phonenr: "+phoneNumber+".\nAdress: "+adress+". ZipCode: "+zipCode+". ZoneNr: "+zoneNr+".\nPreferences: "+preferences+"\n\n";
	}
}