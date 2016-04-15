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

	
	//Register information to database
	public boolean updateCustomer(Database database) throws Exception{
		if(QueryMethods.updateCustomer(customerID, surName, firstName, phoneNumber, email, adress, zipCode, zoneNr, preferences, active, database)){
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (customerID != other.customerID)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (isCompany != other.isCompany)
			return false;
		if (surName == null) {
			if (other.surName != null)
				return false;
		} else if (!surName.equals(other.surName))
			return false;
		if (zipCode != other.zipCode)
			return false;
		if (zoneNr != other.zoneNr)
			return false;
		return true;
	}

	public String toString(){
		String res = "CustomerID: " +customerID+" First name: " + firstName+". Surname: "+ surName +"\nEmail: "+email+". Phonenr: "+phoneNumber+".\nAdress: "+adress+". ZipCode: "+zipCode+". ZoneNr: "+zoneNr+".\nPreferences: "+preferences+"\n";
		if (isCompany){
			res+="Company name: "+companyName+"\n";
		}
		res+="\n";
		return res;
	}
}