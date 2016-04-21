package controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import databasePackage.*;

public class Admin extends User {
	
	public Admin(String userID, String name, Database database) {
		super(userID, 0, name,  database);
	}

	//Registrerer ny bruker
	public boolean registerUser(String userID, int userType, String name,String pword, Database database) throws Exception{
		return QMUser.registerUser(userID, userType, name, pword, database);
	}
	
	public boolean updateUser(String userID, int userType, String name, String pword, Database database) throws Exception{
		return(QMUser.updateUser(userID, userType, name, pword, database));
	}
	 
	
	
	// MÃ… LAGES !! 
	public String getStatisticsForYear(int year) throws Exception{
		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		
		cal.set(Calendar.YEAR, year);		
		int monthCount = cal.get(Calendar.MONTH);

		int res;
	    int sum=0;
	    
	    java.sql.Date sDate;
	    java.sql.Date eDate;
	    String text = "Income overview for "+year+"\n\n";
	    
	    
	    String[] mNames = {"January", "February", "March", "April",
	    					"May", "June", "July", };
	    
	    for (int i=0; i<monthCount+1;i++){
	    	cal.set(Calendar.MONTH, i);
	    	cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));

	    	sDate = java.sql.Date.valueOf(format1.format(cal.getTime()));
	    	cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

	    	eDate = java.sql.Date.valueOf(format1.format(cal.getTime()));
		    
	    	System.out.println("måned: "+i+", start: "+sDate+", slutt: "+eDate);
		    
	    	res = QMOrder.calculateIncomeForPeriod(sDate, eDate, database);
	    	text += "Income in "+mNames[i]+" is: "+res+" kr.\n";
	    	sum+=res;
		    
	    }
	    
	    System.out.println("Total: "+ QMOrder.calculateIncomeForPeriod(java.sql.Date.valueOf("2016-01-01"), java.sql.Date.valueOf("2016-04-30"), database));
	    
	    text+="\nTotal income this year: "+sum+" kr.";
	    return text;
	}
	
	
	
	//returns an arraylist with customer objects with all active users
	public ArrayList<User> viewUserList() throws Exception{
		
		String[][] list = QMUser.viewAllUsers(database);
		User tempUser;
		ArrayList<User> userList = new ArrayList<User>();
		
		for(int i=0; i<list.length; i++){
			int userType = Integer.parseInt(list[i][1]); 
			
			tempUser = new User(list[i][0],userType,list[i][2], database);
			userList.add(tempUser);
		}
		return userList;
	}
	
	
	
	@Override
	public String toString() {
		String s = super.toString() + "\n";
	
		s += "Admin";
		
		return s;
	}
	
	public static void main(String[] args) throws Exception{
		String username = "marith1";
		String password = "tgp8sBZA";
		String databaseName = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
		String databaseDriver = "com.mysql.jdbc.Driver";
		Database database = new Database(databaseDriver, databaseName);
		
		Admin admin = new Admin("Per","Per",database);
		
		System.out.println(admin.getStatisticsForYear(2016));
	}
	
}
