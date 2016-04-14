package controller;

import databasePackage.Database;
import databasePackage.QueryMethods;

public class Customer implements java.io.Serializable{
	private final int customerID;
	private String firstName;
	private String surName;
	private String email;
	private String adress;
	private int zipCode;
	private int zoneNr;
	private String preferences;
	private String phoneNumber;
	private boolean active;
	private boolean isCompany;
	private String companyName;
	
	
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
	
	public boolean getIsCompany(){
		return isCompany;
	}
	
	public String getCompanyName(){
		return companyName;
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
		this.zipCode=zip_code;
	}
	public void setZoneNr(int zone_nr){
		this.zoneNr=zone_nr;
	}
	public void setPreferences(String preferences){
		this.preferences=preferences;
	}
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber=phoneNumber;
	}
	public void setActive(boolean active){
		this.active = active;
	}
	
	public void setIsCompany(boolean isCompany){
		this.isCompany = isCompany;
	}
	
	public void setCompanyName(String compName){
		this.companyName=compName;
	}

	
	public boolean updateCustomer(Database database) throws Exception{
		if(QueryMethods.updateCustomer(customerID, surName, firstName, phoneNumber, email, adress, zipCode, zoneNr, preferences, active, database)){
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