package databaseguiii;

/**
 * Viser en flervalgsliste der brukeren kan velge måltider.
 * Valgene vises under listen.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class ReadyForDelivery2 extends JFrame {
  private static final String [] MEALS =
    {"Spaghetti", "Steak", "Fish", "Salad"};
  private JTextField tekst = new JTextField("You have not chosen any meals yet.     ");
  private JList<String> meals = new JList<String>(MEALS);  // Nå er listen laget!

  public ReadyForDelivery2(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel ledetekst = new JLabel("Choose one or more meals.");
    add(ledetekst, BorderLayout.NORTH);

    /* Legger på rullefelt */
    JScrollPane rullefeltMedListe = new JScrollPane(meals);
    add(rullefeltMedListe, BorderLayout.CENTER);

    ListeboksLytter lytter = new ListeboksLytter();
    meals.addListSelectionListener(lytter);

    tekst.setEditable(false); // brukeren skal ikke kunne redigere i feltet
    add(tekst, BorderLayout.SOUTH);
    pack();
  }

  /* Lytteren fanger opp alle klikk på linjer i listeboksen */
  private class ListeboksLytter implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent hendelse) {
      Object[] verdier = meals.getSelectedValuesList().toArray();
      String nyTekst = "Du har nå valgt " + verdier.length;
      
    //Dersom vi bruker denne metoden med en ArrayList knyttet til viewFoodOrders(), må vi sette verdien lik true her
   /*   nyTekst += (verdier.length == 1) ? " by." :  " byer.";
      tekst.setText(nyTekst);   */ 
    }
  }
}

class MarkAsReadyForDelivery2 {
  public static void main(String[] args) {
	 ReadyForDelivery2 etVindu = new ReadyForDelivery2("You have chosen these meals ");
    etVindu.setVisible(true);
  }
}
