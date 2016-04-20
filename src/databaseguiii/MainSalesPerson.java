package databaseguiii;
import databasePackage.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import controller.*;
import databasePackage.Database;

class SalesPersonGui extends JFrame {
  private MainCustomerGui mainCustomerGui;
  private JList list = new JList();
  private static final String [] CHOICES =
    {"Register new customer","Register new company", "View private customers", "View company customers","Change customer information", 
    		"Register new food order", "Change food order"};
  private JList<String> choice_list = new JList<String>(CHOICES);  // Naa er listen laget!

  public SalesPersonGui(String tittel) {
	  setTitle(tittel);
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  Font bigText = new Font("SansSerif", Font.PLAIN, 30);

	  JLabel ledetekst = new JLabel("Choose one of the following options.");
	  add(ledetekst, BorderLayout.NORTH);

  
	  JScrollPane rullefeltMedListe = new JScrollPane(choice_list);
	  add(rullefeltMedListe, BorderLayout.CENTER);

	  ListeboksLytter lytter = new ListeboksLytter();
	  choice_list.addListSelectionListener(lytter);
	  choice_list.setFont(bigText);
	 setSize(700, 700);
  }

  private class ListeboksLytter implements ListSelectionListener {
	  private Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=espenme&password=16Sossosem06");
	  private Admin admin = new Admin("", 1, "", "", database);
	  private User user = new Sales("", 1, "", "", database);
	  
	  public void valueChanged(ListSelectionEvent hendelse) {
		  
      Object[] values = choice_list.getSelectedValuesList().toArray();
      int choices = choice_list.getSelectedIndex();
      if(choices == 0){
    	  try {
			MainCustomerGui.main(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
      }else if(choices==1){
    	  try {
			RegisterCompanyToCustomer.main(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
      }else if(choices==2){
    		ArrayList<Customer> c = null;
  	 try {
  		 	c = user.viewCustomerList();
			}
  	 	catch (Exception e) {
  	 		e.printStackTrace();
  	 	}
  		 String[] s = new String[c.size()];
	    	 for( int i = 0; i < c.size(); i++ ){
	    		 s[i] = c.get(i).toString() + " ";
	    	 }
	    	 
	    	 JScrollPane scrollpane = new JScrollPane(); 
	         JList list = new JList(s);
	         scrollpane = new JScrollPane(list);
	         JPanel panel = new JPanel(); 
	         panel.add(scrollpane);
	         scrollpane.getViewport().add(list);		    	 
	    	 JOptionPane.showMessageDialog(null, scrollpane, "All customers: ", JOptionPane.INFORMATION_MESSAGE );

    	}else if(choices == 3){
      		ArrayList<Customer> c = null;
    	  try {
    		  c=user.viewCompanyList(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
    	  String[] s = new String[c.size()];
	    	 for( int i = 0; i < c.size(); i++ ){
	    		 Customer t = c.get(i);
	    		 s[i] = t.getCustomerID() + ", " + t.getCompanyName() + ": " + t.getSurName() + ", " + t.getFirstName() + "\n";
	    	 }
	    	 
	    	 JScrollPane scrollpane = new JScrollPane(); 
	         JList list = new JList(s);
	         scrollpane = new JScrollPane(list);
	         JPanel panel = new JPanel(); 
	         panel.add(scrollpane);
	         scrollpane.getViewport().add(list);		    	 
	    	 JOptionPane.showMessageDialog(null, scrollpane, "All customers: ", JOptionPane.INFORMATION_MESSAGE );
      }else if(choices==4){
    	  ChangeCustomerInfoGui.main(null);      
    	  }else if(choices==4){
    		  MainOrderGui.main(null);
      }
      	else if(choices==5){
      		MainOrderGui.main(null);
      	}
      	else if(choices==6){
	    	  try {
				ChangeOrderGui.main(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
	      }
      }
	 }
 }

class MainSalesPerson {
  public static void main(String[] args) {
	SalesPersonGui aWindow = new SalesPersonGui("Choose an option");
	aWindow.setVisible(true);
  }
 }