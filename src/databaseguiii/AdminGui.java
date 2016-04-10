package databaseguiii;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class AdminGUI extends JFrame {
  private static final String [] CHOICES =
    {"register new user", "register new customer", "view private customers", "view company customers"};
  private JTextField text = new JTextField("Du har ennå ikke valgt byer.     ");
  private JList<String> choice_list = new JList<String>(CHOICES);  // Nå er listen laget!

  public AdminGUI(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel ledetekst = new JLabel("Choose one of the following options.");
    add(ledetekst, BorderLayout.NORTH);

    /* Legger på rullefelt */
    JScrollPane rullefeltMedListe = new JScrollPane(choice_list);
    add(rullefeltMedListe, BorderLayout.CENTER);

    ListeboksLytter lytter = new ListeboksLytter();
    choice_list.addListSelectionListener(lytter);

    text.setEditable(false); // brukeren skal ikke kunne redigere i feltet
    add(text, BorderLayout.SOUTH);
    pack();
  }

  /* Lytteren fanger opp alle klikk på linjer i listeboksen */
  private class ListeboksLytter implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent hendelse) {
      // Object[] verdier = byliste.getSelectedValues();  - deprecated i Java 7
      Object[] values = choice_list.getSelectedValuesList().toArray();
      String nyTekst = "Du har nå valgt " + values.length;
      nyTekst += (values.length == 1) ? " by." :  " byer.";
      text.setText(nyTekst);
    }
  }
}

class AdminGui {
  public static void main(String[] args) {
	  AdminGUI etVindu = new AdminGUI("Choose an option");
    etVindu.setVisible(true);
  }
}