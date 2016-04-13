package databaseguiii;
import databasePackage.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import controller.*;
import databasePackage.Database;

class SalesPersonGui extends JFrame {
  private Parentwindow parentwindow;
  private MainCustomerGui mainCustomerGui;
  private static final String [] CHOICES =
    {"Register new user","Change user information", "View all users", "Register new customer","Register new company",
    		"Change customer information", "View private customers", "View company customers","Remove customer","Register new food order","Change food order"};
  private JList<String> choice_list = new JList<String>(CHOICES);  // Naa er listen laget!

  public SalesPersonGui(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Font bigText = new Font("SansSerif", Font.PLAIN, 30);

    JLabel ledetekst = new JLabel("Choose one of the following options.");
    add(ledetekst, BorderLayout.NORTH);

    /* Legger paa rullefelt */
    JScrollPane rullefeltMedListe = new JScrollPane(choice_list);
    add(rullefeltMedListe, BorderLayout.CENTER);

    ListeboksLytter lytter = new ListeboksLytter();
    choice_list.addListSelectionListener(lytter);
    choice_list.setFont(bigText);
    setSize(700, 700);
  }

  /* Lytteren fanger opp alle klikk paa linjer i listeboksen */ 
  private class ListeboksLytter implements ListSelectionListener {
	private final Database database = null;
	private QueryMethodsController queryMethodsController = new QueryMethodsController();
	 private User viewAllUsers = new User("", 1, "", "", database);
    public void valueChanged(ListSelectionEvent hendelse) {
      Object[] values = choice_list.getSelectedValuesList().toArray();
      int choices = choice_list.getSelectedIndex();
      if(choices == 0){
    	  TestUserDialog.main(null);
      }else if(choices==1){
    	  ////metoden for å endre info til en bruker
      }
      else if(choices==2){
    	  try {
			queryMethodsController.viewUserList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	} else if(choices == 3){
    	  MainCustomerGui.main(null);
      }else if(choices==4){
    	  //metoden for a registrere en bedrift
      }else if(choices==5){
    	  //metoden for a endre info til en kundre
      }
      	else if(choices==6){
    	  queryMethodsController.ViewAllCustomersList();
		}
      else if(choices==7){
    	  try {
			queryMethodsController.ViewAllCompaniesList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }else if(choices==8){
    	 
    	  //metoden remove customer
      }
      else if(choices==9){
    	  //metoden for a registrere en ny bestilling
      }else if(choices==10){
    	  //motoden for a endre bestilling
      }
    }
  }
}

 class MainSalesPerson {
  public static void main(String[] args) {
	  SalesPersonGui etVindu = new SalesPersonGui("Choose an option");
    etVindu.setVisible(true);
  }
 }