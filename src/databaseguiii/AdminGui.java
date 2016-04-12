package databaseguiii;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import controller.*;
import databasePackage.Database;

class Admin extends JFrame {
  private Parentwindow parentwindow;
  private MainCustomerGui mainCustomerGui;
  private static final String [] CHOICES =
    {"Register new user", "Register new customer", "View private customers", "View company customers"};
  private JList<String> choice_list = new JList<String>(CHOICES);  // Naa er listen laget!

  public Admin(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel ledetekst = new JLabel("Choose one of the following options.");
    add(ledetekst, BorderLayout.NORTH);

    /* Legger paa rullefelt */
    JScrollPane rullefeltMedListe = new JScrollPane(choice_list);
    add(rullefeltMedListe, BorderLayout.CENTER);

    ListeboksLytter lytter = new ListeboksLytter();
    choice_list.addListSelectionListener(lytter);
    pack();
  }

  /* Lytteren fanger opp alle klikk paa linjer i listeboksen */
  private class ListeboksLytter implements ListSelectionListener {
	private final Database database = null;
	private ViewAllCustomers viewAllCustomers = new ViewAllCustomers();
	 private User viewAllUsers = new User("", 1, "", "", database);
    public void valueChanged(ListSelectionEvent hendelse) {
      Object[] values = choice_list.getSelectedValuesList().toArray();
      int choices = choice_list.getSelectedIndex();
      if(choices == 0){
    	  TestUserDialog.main(null);
      } else if(choices == 1){
    	  MainCustomerGui.main(null);
      }else if(choices==2){
    	  viewAllCustomers.ViewAllCustomersList();
		}else if(choices==3){
	    	  try {
				viewAllUsers.viewUserList();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
  }
}

 class AdminGui {
  public static void main(String[] args) {
	  Admin etVindu = new Admin("Choose an option");
    etVindu.setVisible(true);
  }
}
