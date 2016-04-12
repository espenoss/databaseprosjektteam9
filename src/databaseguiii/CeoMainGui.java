package databaseguiii;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import controller.*;
import databasePackage.Database;

class CeoGui extends JFrame {
  private Parentwindow parentwindow;
  private MainCustomerGui mainCustomerGui;
  private static final String [] CHOICES =
    {"Retrieve statistics"};
  private JList<String> choice_list = new JList<String>(CHOICES);  // Naa er listen laget!

  public CeoGui(String tittel) {
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
	private QueryMethodsController queryMethodsController = new QueryMethodsController();
    public void valueChanged(ListSelectionEvent hendelse) {
      Object[] values = choice_list.getSelectedValuesList().toArray();
      int choices = choice_list.getSelectedIndex();
      if(choices == 0){
    	  //mangler metoden "retrieve statistics" i kontrolleren
    	  //queryMethodsController.;
      }
    }
  }
}

 class CeoMainGui {
  public static void main(String[] args) {
	  CeoGui etVindu = new CeoGui("Choose an option");
    etVindu.setVisible(true);
  }
 }