package databaseguiii;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import controller.*;
import databasePackage.*;

class ReadyForDelivery extends JFrame {
	private QueryMethodsController my_controller = new QueryMethodsController();
  /* Knappen merket "kvinne" er trykket inn ved oppstart av programmet. */
  private JRadioButton ready = new JRadioButton("Ready for delivery", true);

  public ReadyForDelivery(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    /* Beholderen har samme oppbygning som i TestAvkrysningsruter.java */
    add(new JPanel(), BorderLayout.NORTH); // litt luft
    add(new JPanel(), BorderLayout.SOUTH); // litt luft
    ValgPanel midten = new ValgPanel();
    add(midten, BorderLayout.CENTER);
    pack();
  }

  /* Beskriver panelet med radioknappene */
  private class ValgPanel extends JPanel {
    public ValgPanel() {
      /*
       * Radioknappene må knyttes til en radioknappgruppe for at de skal
       * virke riktig. Det vil si at bare én knapp kan være trykket inn av gangen.
       */
    	my_controller.viewFoodOrders();
      ButtonGroup gruppe = new ButtonGroup();
      gruppe.add(ready);
      add(ready);

      RadioknappLytter lytter = new RadioknappLytter();
      ready.addActionListener(lytter);
      /* Lager en ramme rundt panelet */
      SoftBevelBorder border = new SoftBevelBorder(BevelBorder.RAISED);
      Border gruppeboks = BorderFactory.createTitledBorder(border, "Orders");
      setBorder(gruppeboks);
    }
  }

  /* Lytterobjekter som lytter til alle endringer i radioknappene */
  private class RadioknappLytter implements ActionListener {
    public void actionPerformed(ActionEvent hendelse) {
      String orders = hendelse.getActionCommand();
      
    }
  }
}

class markAsReadyForDeliveryGui {
  public static void main(String[] args) {
	  ReadyForDelivery etVindu = new ReadyForDelivery("Choose orders ready for delivery");
    etVindu.setVisible(true);
  }
}