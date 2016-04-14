package databaseguiii;
import controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.Customer;
import controller.LogIn;
import databasePackage.Database;
import databasePackage.QueryMethods;
import databaseguiii.CustomerDialog;
//import databaseguiii.Parentwindow.ButtonListener;
//import databaseguiii.Parentwindow.ButtonListener2;

class Parentwindow2 extends JFrame {
	  private Database database = new Database ("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/mariashc?user=mariashc&password=rGBlmJ91");
	  Sales sales = new Sales("", 0, "", "", database);
	 
	  
	  public Parentwindow2() {
		  CustomerDialog dialog2 = new CustomerDialog(this);
		  dialog2.setVisible(true);
		  
	/*	  String surName, String firstName, String phoneNumber, String email, String adress, 
			int zip_code, int zone_nr, String preferences, boolean active, Database database  */
		  
		  
		  try {
			sales.RegisterCustomer(dialog2.getSurNameText(), dialog2.getFirstNameText(), dialog2.getPhoneNrText(), dialog2.getEmailText(), dialog2.getAdressText(), dialog2.getZip_codeText(),
					  dialog2.getZone_nrText(), dialog2.getPrefencesText(), dialog2.getActive(), database);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  setTitle("Registrer customer");
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  setLayout(new FlowLayout());
		  setLocation(300, 300); // plasserer foreldrevinduet..
		  dialog2.setLocation(350, 350);  // plasserer dialogen
	 } 
}  


	class MainCustomerGui {
	  static public void main(String[] args) {
		Parentwindow2 test = new Parentwindow2();
	    test.setSize(300, 200);  //For � f� litt st�rrelse p� vinduet
	    test.setVisible(true);
	  }   
	} 

