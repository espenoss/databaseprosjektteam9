package databaseguiii;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class Admin extends JFrame {
  private Parentwindow parentwindow;
  private MainCustomerGui mainCustomerGui;
//  private TestUserDialog testUserDialog;
  private static final String [] CHOICES =
    {"Register new user", "Register new customer", "View private customers", "View company customers"};
  private JTextField text = new JTextField("Du har ennå ikke valgt byer.     ");
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

    text.setEditable(false); // brukeren skal ikke kunne redigere i feltet
    add(text, BorderLayout.SOUTH);
    pack();
  }

  /* Lytteren fanger opp alle klikk paa linjer i listeboksen */
  private class ListeboksLytter implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent hendelse) {
      // Object[] verdier = byliste.getSelectedValues();  - deprecated i Java 7
      Object[] values = choice_list.getSelectedValuesList().toArray();
      int choices = choice_list.getSelectedIndex();
      if(choices == 0){
    	  TestUserDialog.main(null);
      }
      
      if(choices == 1){
    	  MainCustomerGui.main(null);
      }
      /*
      String nyTekst = "You have now chosen " + values.length;
      nyTekst += (values.length == 1) ? " by." :  " byer.";
      text.setText(nyTekst);   */
    }
  }
}

class AdminGui {
  public static void main(String[] args) {
	  Admin etVindu = new Admin("Choose an option");
    etVindu.setVisible(true);
  }
}