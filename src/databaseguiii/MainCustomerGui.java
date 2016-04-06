package databaseguiii;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import databasePackage.Database;
import databasePackage.UserMethods;
import databaseguiii.Customer;
import databaseguiii.CustomerDialog;
//import databaseguiii.Parentwindow.ButtonListener;
//import databaseguiii.Parentwindow.ButtonListener2;


class Parentwindow2 extends JFrame {
	  private Customer customer = new Customer("", "", "", "", "", 0, 0, "");
	  private CustomerDialog dialog = new CustomerDialog(this);
	  private String firstName = "";
	  private String surName = "";
	  private String email = "";
	  private String adress = "";
	  private String phoneNumber = "";
	  private String preferences = "";
	  private Database database = new Database ("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/ninameed?user=ninameed&password=1Le5YPPr");
	  
	  public Parentwindow2() {
	    setTitle("Registrer customer");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new FlowLayout());
	    JButton button = new JButton("Register new customer");
	    add(button);
	    button.addActionListener(new ButtonListener());
	    JButton button2 = new JButton("List customers");
	    add(button2);
	    button2.addActionListener(new ButtonListener2());   
	    setLocation(300, 300); // plasserer foreldrevinduet..
	    dialog.setLocation(350, 350);  // plasserer dialogen
	  }

	  private class ButtonListener implements ActionListener {
	    public void actionPerformed(ActionEvent action) {
	      if (dialog.showDialog(customer)) {
	          try {
	      		UserMethods.registerCustomer(customer.getFirstName(), customer.getSurName(), customer.getEmail(), customer.getAdress(), customer.getPreferences(), customer.getZipCode(), customer.getZoneNr(), customer.getPhoneNumber(), 1, database);
	            } catch (Exception e) {
	      		e.printStackTrace();
	            }
	      } else {
	        System.out.println("Cancelled");
	      }
	      System.out.println(customer); //bruker toString().
	    }
	  }
	  
	  private class ButtonListener2 implements ActionListener {
		  public void actionPerformed(ActionEvent action) {
			  
			  String text = "Customer List:\n";

			  String[][] dbData = null;
			  
			  try {
				  dbData = UserMethods.viewAllUsers(database);
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
		  }
	  }	    
	}

	class MainCustomerGui {
	  static public void main(String[] args) {
		Parentwindow2 test = new Parentwindow2();
	    test.setSize(300, 200);  //For å få litt størrelse på vinduet
	    test.setVisible(true);
	  }   
	} 

