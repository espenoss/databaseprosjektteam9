package controller;

import database.*;


/**
 * The Class Customer.
 * Contains all methods exclusive to the customer usertype
 */
public class Customer implements java.io.Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The customer id. */
	private final int customerID;
	
	/** Customer first name. */
	private String firstName;
	
	/** Customer surname. */
	private String surName;
	
	/** Email. */
	private String email;
	
	/** Street adress. */
	private String adress;
	
	/** Zip code. */
	private int zipCode;
	
	/** Zone nr. */
	private int zoneNr;
	
	/** Customer preferences. */
	private String preferences;
	
	/** Customer phone number. */
	private String phoneNumber;
	
	/** Whether customer is active. */
	private boolean active;
	
	/** Whether customer is a company. */
	private boolean isCompany= false;
	
	/** Company name. */
	private String companyName = "";
	
	
	/**
	 * Instantiates a new customer.
	 *
	 * @param customerID Customer id
	 * @param firstName First name of customer
	 * @param surName Surname of customer
	 * @param phoneNumber Phone number of customer
	 * @param email Email
	 * @param adress Street adress
	 * @param zipCode Zip code
	 * @param zoneNr Zone number
	 * @param preferences Customer preferences
	 * @param active Whether customer is active
	 */
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
	
	/**
	 * Instantiates a new company customer.
	 *
	 * @param customerID Customer id
	 * @param firstName First name of customer
	 * @param surName Surname of customer
	 * @param phoneNumber Phone number of customer
	 * @param email Email
	 * @param adress Street adress
	 * @param zipCode Zip code
	 * @param zoneNr Zone number
	 * @param preferences Customer preferences
	 * @param active Whether customer is active
	 * @param companyName Company name
	 */
	public Customer(int customerID, String firstName, String surName, String phoneNumber, String email, String adress, int zipCode, int zoneNr, String preferences, boolean active, String companyName){
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
		this.companyName = companyName;
		isCompany = true;
	}
	
	
	/**
	 * Gets the customer id.
	 *
	 * @return Customer id
	 */
	public int getCustomerID(){
		return customerID;
	}
	
	/**
	 * Gets the first name.
	 *
	 * @return The first name
	 */
	public String getFirstName(){
		return firstName;
	}
	
	/**
	 * Gets the sur name.
	 *
	 * @return The surname
	 */
	public String getSurName(){
		return surName;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return The email adress
	 */
	public String getEmail(){
		return email;
	}
	
	/**
	 * Gets the street adress.
	 *
	 * @return The street adress
	 */
	public String getAdress(){
		return adress;
	}
	
	/**
	 * Gets the zip code.
	 *
	 * @return The zip code
	 */
	public int getZipCode(){
		return zipCode;
	}
	
	/**
	 * Gets the zone number.
	 *
	 * @return The zone number
	 */
	public int getZoneNr(){
		return zoneNr;
	}
	
	/**
	 * Gets the preferences.
	 *
	 * @return the preferences
	 */
	public String getPreferences(){
		return preferences;
	}
	
	/**
	 * Gets the phone number.
	 *
	 * @return The phone number
	 */
	public String getPhoneNumber(){
		return phoneNumber;
	}
	
	/**
	 * Check if customer is active.
	 *
	 * @return True if active, false otherwise
	 */
	public boolean getActive(){
		return active;
	}
	
	/**
	 * Checks if customer is company.
	 *
	 * @return True if company, false otherwise
	 */
	public boolean getIsCompany(){
		return isCompany;
	}
	
	/**
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName(){
		return companyName;
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName The new first name
	 */
	public void setFirstName(String firstName){
		this.firstName=firstName;
	}
	
	/**
	 * Sets the surname.
	 *
	 * @param surName The new surname
	 */
	public void setSurName(String surName){
		this.surName=surName;
	}
	
	/**
	 * Sets the adress.
	 *
	 * @param adress The new adress
	 */
	public void setAdress(String adress){
		this.adress=adress;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email The new email
	 */
	public void setEmail(String email){
		this.email=email;
	}
	
	/**
	 * Sets the zip code.
	 *
	 * @param zip_code The new zip code
	 */
	public void setZipCode(int zip_code){
		this.zipCode=zip_code;
	}
	
	/**
	 * Sets the zone number.
	 *
	 * @param zone_nr The new zone number
	 */
	public void setZoneNr(int zone_nr){
		this.zoneNr=zone_nr;
	}
	
	/**
	 * Sets preferences.
	 *
	 * @param preferences The new preferences
	 */
	public void setPreferences(String preferences){
		this.preferences=preferences;
	}
	
	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber The new phone number
	 */
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber=phoneNumber;
	}
	
	/**
	 * Sets whether customer is active.
	 *
	 * @param active The new state
	 */
	public void setActive(boolean active){
		this.active = active;
	}
	
	/**
	 * Sets whether customer is company.
	 *
	 * @param isCompany The new state
	 */
	public void setIsCompany(boolean isCompany){
		this.isCompany = isCompany;
	}
	
	/**
	 * Sets the company name.
	 *
	 * @param compName The new company name
	 */
	public void setCompanyName(String compName){
		this.companyName=compName;
	}

	
	/**
	 * Update customer.<br>
	 * Register object information to database.
	 *
	 * @param database Database connection
	 * @return true, if successful
	 */
	public boolean updateCustomer(Database database) {
		if(QMCustomer.updateCustomer(customerID, surName, firstName, phoneNumber, email, adress, zipCode, zoneNr, preferences, active, database)){
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String res = "CustomerID: " +customerID+" First name: " + firstName+". Surname: "+ surName +"\nEmail: "+email+". Phonenr: "+phoneNumber+".\nAdress: "+adress+". ZipCode: "+zipCode+". ZoneNr: "+zoneNr+".\nPreferences: "+preferences+"\n";
		if (isCompany){
			res+="Company name: "+companyName+"\n";
		}
		res+="\n";
		return res;
	}
}