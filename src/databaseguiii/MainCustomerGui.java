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

class Parentwindow extends JFrame {
  private Customer customer = new Customer("", "","", "", "");
  private CustomerDialog dialog = new CustomerDialog(this);

  public Parentwindow() {
    setTitle("New customer registration");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());
    JButton button = new JButton("Register new customer ");
    add(button);
    button.addActionListener(new ButtonListener());
    setLocation(300, 300); // plasserer foreldrevinduet
    dialog.setLocation(350, 350);  // plasserer dialogen
  }

  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent action) {
      if (dialog.showDialog(customer)) {
        System.out.println("OK is pressed ...");
      } else {
        System.out.println("Cancel is pressed...");
      }
      System.out.println(customer); // bruker toString()
    }
  }
}

class MainCustomerGui {
  static public void main(String[] args) {
	Parentwindow test = new Parentwindow();
    test.setSize(500, 300);  // for å få litt størrelse på vinduet
    test.setSize(900, 600);  // for å få litt størrelse på vinduet
    test.setVisible(true);
  }   
}  

class Parentwindow2 extends JFrame {
	  private Customer customer = new Customer("", "", "", "", "");
	  private CustomerDialog dialog = new CustomerDialog(this);
	  private String firstName = "";
	  private String surName = "";
	  private String email = "";
	  private String adress = "";
	  private String preferences = "";
	  private Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=" +  name +"&password=" + pword);
	  
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
	      		UserMethods.registerCustomer(customer.getFirstName(), customer.getSurName(), customer.getEmail(), customer.getAdress(), customer.getPreferences(), int active, database);
	            } catch (Exception e) {
	      		e.printStackTrace();
	            }
	      } else {
	        System.out.println("Cancelled");
	      }
	      System.out.println(customer); //bruker toString()
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

	class TestUserDialog {
	  static public void main(String[] args) {
		Parentwindow test = new Parentwindow();
	    test.setSize(300, 200);  //For � f� litt st�rrelse p� vinduet
	    test.setVisible(true);
	  }   
	}  

