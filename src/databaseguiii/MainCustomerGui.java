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
	  private Customer customer = new Customer("", "", "", "", "", 0, 0, "");
	  private CustomerDialog dialog = new CustomerDialog(this);
//	  private String customerID = ""; //M� tas bort, er kun for bedriftskunder. Trenger company name i bedriftskunde klassenkkk
	  private String firstName = "";
	  private String surName = "";
	  private String email = "";
	  private String adress = "";
	  private String phoneNumber = "";
	  private String preferences = "";
	  private Database database = new Database ("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/mariashc?user=mariashc&password=rGBlmJ91");
	  
	  public Parentwindow2() {
		  QueryMethodsController queryMethodsController = new QueryMethodsController();
	      if (dialog.showDialog(customer)) {
	          try {
	        	  queryMethodsController.RegisterCustomer(customer.getFirstName(), customer.getSurName(), 
	        	  customer.getEmail(), customer.getAdress(), customer.getPreferences(), customer.getZipCode(), 
	      		  customer.getZoneNr(), customer.getPhoneNumber(), true, database);
	            } catch (Exception e) {
	      		e.printStackTrace();
	            }
	      } else {
	        System.out.println("Cancelled");
	      }
	      System.out.println(customer); //bruker toString().
	    
	    setTitle("Registrer customer");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new FlowLayout());
	    
	   
	    setLocation(300, 300); // plasserer foreldrevinduet..
	    dialog.setLocation(350, 350);  // plasserer dialogen
	  }  
}

	class MainCustomerGui {
	  static public void main(String[] args) {
		Parentwindow2 test = new Parentwindow2();
	    test.setSize(300, 200);  //For � f� litt st�rrelse p� vinduet
	    test.setVisible(true);
	  }   
	} 

