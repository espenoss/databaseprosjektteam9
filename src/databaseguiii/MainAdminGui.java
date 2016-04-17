package databaseguiii;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.*;
import databasePackage.Database;

class AdminGui extends JFrame {
	  private MainCustomerGui mainCustomerGui;
	  private static final String [] CHOICES =
	    {"Register new user","Change user information", "View all users", "Register new customer","Register new company",
	    		"Change customer information", "View private customers", "View company customers","Remove customer","Register new food order","Change food order", "Retrieve statistics"};
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
		  private final Database database = null;
		  private Admin admin = new Admin("", 1, "", "", database);
		  private Sales sales = new Sales("", 1, "", "", database);
		  
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
				admin.viewUserList();
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
	    	 try {
				admin.viewCustomerList();
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
	      else if(choices==7){
	    	  try {
				//admin.ViewAllCompaniesList(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
	      }else if(choices==8){
	    	 
	    	  //metoden remove customer
	      }
	      else if(choices==9){
	    	  MainOrderGui.main(null);
	      }else if(choices==10){
	    	  try {
				ChangeOrderGui.main(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
	      }
	      else if(choices==11){
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