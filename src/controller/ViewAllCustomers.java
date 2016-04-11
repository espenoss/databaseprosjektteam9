package controller;

import javax.swing.JOptionPane;

import databasePackage.*;

public class ViewAllCustomers {

	String username = "espenme";
	String passingword = "16Sossosem06";
	String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
	Database database = null;
	public ViewAllCustomers(){
		database = new Database("com.mysql.jdbc.Driver", databasename);
	}
	
	public String[][] ViewAllCustomersList(){
		String text = "Customer List:\n";

		  String[][] dbData = null;
		  
		  try {
			  dbData = QueryMethods.viewAllCustomers(database);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		
		for(int x=0;x<dbData.length; x++){
			for(int y=0;y<dbData[x].length;y++){
				text += dbData[x][y] + " ";
			}
			text += "\n";
		}
		JOptionPane.showMessageDialog(null, text);
	    return  dbData;
	}
	
}


