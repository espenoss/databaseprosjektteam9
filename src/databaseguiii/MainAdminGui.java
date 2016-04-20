package databaseguiii;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.*;
import databasePackage.Database;

class AdminGui extends JFrame {
	  private MainCustomerGui mainCustomerGui;
	  private JList list = new JList();
	  private static final String [] CHOICES =
	    {"Register new user","Change user information", "View all users", "Register new customer","Register new company",
	    		"Change customer information", "View private customers", "View company customers","Register new food order","Change food order", "Retrieve statistics"};
	  private JList<String> choice_list = new JList<String>(CHOICES);  // Naa er listen laget!

	  public AdminGui(String tittel) {
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
				MainUserDialog.main(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
	      }else if(choices==1){
	    	  try {
				ChangeUserInfoGui.main(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
	      }
	      else if(choices==2){
	    	  try {
	    		  String s = "";
	    		  ArrayList<User> userList = admin.viewUserList();
	    		  for(int i = 0; i < userList.size(); i++ ){
	    	          s +=  userList.get(i).toString() + " \n";
	    	      }
	    		  JOptionPane.showMessageDialog(null, s, "All users: ", JOptionPane.INFORMATION_MESSAGE );
			} catch (Exception e) {
				e.printStackTrace();
				}
	      	} else if(choices == 3){
	    	  MainCustomerGui.main(null);
	      }else if(choices==4){
	    	  RegisterCompanyToCustomer.main(null);
	      }else if(choices==5){
	    	  ChangeCustomerInfoGui.main(null);
	      }
	      	else if(choices==6){
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

	      	}
	      	else if(choices==7){
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

	      }
	      else if(choices==8){
	    	  MainOrderGui.main(null);
	      }else if(choices==9){
	    	  try {
				ChangeOrderGui.main(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
	      }
	      else if(choices==10){
	    	  //metoden for a hente ut statistikk
	    }
	  }
	 }
    }

	 class MainAdminGui {
	  public static void main(String[] args) {
		  AdminGui window = new AdminGui("Choose an option");
		  window.setVisible(true);
	  }
	 }