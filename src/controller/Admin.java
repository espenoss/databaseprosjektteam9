package controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import database.*;

/**
 * The Class Admin.
 * Contains all methods exclusive to admin user
 */
public class Admin extends User {

	TextEditor t = new TextEditor();

	/**
	 * Instantiates a new admin user.
	 *
	 * @param userID User id
	 * @param name Name of user
	 * @param database Database connection
	 */
	public Admin(String userID, String name, Database database) {
		super(userID, 0, name,  database);
	}

	/**
	 * Register new user.
	 *
	 * @param userID User ID
	 * @param userType User type
	 * @param name Name of user
	 * @param pword Password
	 * @return true, if successful
	 */
	public boolean registerUser(String userID, int userType, String name,String pword) {
		return QMUser.registerUser(userID, userType, name, pword, database);
	}

	/**
	 * Update user.
	 *
	 * @param userID User id
	 * @param userType User type
	 * @param name Name of user
	 * @param pword Password
	 * @return true, if successful
	 */
	public boolean updateUser(String userID, int userType, String name, String pword) {
		return(QMUser.updateUser(userID, userType, name, pword, database));
	}

	/**
	 * View user.
	 *
	 * @param username Username
	 * @return String of user info
	 */
	public String viewUser(String username) {
		String[] userTabl = QMUser.viewUser(username, database);
		if (userTabl.length==0){
			return null;
		}
		return "Username: "+userTabl[0]+", user type: "+userTabl[1]+", name: "+userTabl[2];
	}

	//  
	/**
	 * Returns String with statistics for given year, month by month.<br>
	 * Calculated with prices of meals that have been delivered
	 * 
	 * @param year The year to retrieve statistics for
	 * @return Statistics for the year
	 */
	public String getStatisticsForYear(int year) {
		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		// Get number of months if current year is requested, 12 otherwise
		int thisYear = cal.get(Calendar.YEAR);
		int monthCount = 12;	
		if(thisYear==year){
			monthCount = cal.get(Calendar.MONTH) + 1;
		}
		cal.set(Calendar.YEAR, year);

		int res;
		int sum=0;

		java.sql.Date sDate;
		java.sql.Date eDate;
		String text = "Income overview for "+year+"\n\n";


		String[] mNames = {"January", "February", "March", "April",
				"May", "June", "July", "August",
				"September", "October","November", "December"};

		// Retrieve income for each month in the year
		for (int i=0; i<monthCount;i++){
			cal.set(Calendar.MONTH, i);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));

			sDate = java.sql.Date.valueOf(format1.format(cal.getTime()));
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

			eDate = java.sql.Date.valueOf(format1.format(cal.getTime()));

			res = QMOrder.calculateIncomeForPeriod(sDate, eDate, database);
			text += "Income in "+mNames[i]+" is: "+res+" kr.\n";
			sum+=res;

		}

		text+="\nTotal income this year: "+sum+" kr.";
		return text;
	}



	/**
	 * View list of active users.
	 *
	 * @return ArrayList of User objects
	 */
	public ArrayList<User> viewUserList() {
		// Get user list from database
		String[][] list = QMUser.viewAllUsers(database);

		// If no users found
		if (list.length== 0){
			return null;
		}
		User tempUser;
		ArrayList<User> userList = new ArrayList<User>();

		// Convert string array to user object and add to arraylist
		for(int i=0; i<list.length; i++){
			int userType = t.stringToInt(list[i][1]); 

			tempUser = new User(list[i][0],userType,list[i][2], database);
			userList.add(tempUser);
		}
		return userList;
	}


	/* (non-Javadoc)
	 * @see controller.User#toString()
	 */
	@Override
	public String toString() {
		String s = super.toString() + "\n";

		s += "Admin";

		return s;
	}	
}
