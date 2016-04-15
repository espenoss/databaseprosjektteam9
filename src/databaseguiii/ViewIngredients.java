package databaseguiii;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import static javax.swing.JOptionPane.*;

class IngredientsList extends JFrame {
  private DefaultListModel<String> listcontent = new DefaultListModel<String>(); // "datamodellen"
  private JList<String> list = new JList<String>(listcontent);

  public IngredientsList(String title) {
    setTitle(title);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(new TextPanel(), BorderLayout.NORTH);
    add(new ListPanel(), BorderLayout.CENTER);
    add(new JButton(), BorderLayout.SOUTH);  // for å få litt luft
    pack();
  }

  /* Beskriver ledetekstene øverst i vinduet */
  private class TextPanel extends JPanel {
    public TextPanel() {
      setLayout(new GridLayout(4, 1, 2, 2));
      add(new JLabel(""));  // for å få inn litt luft
      add(new JLabel("Choose \"New ingredient\" to add ingredient."));
      add(new JLabel("If you click an ingredient it will be removed from the list"));
      add(new JLabel(""));  // for å få inn litt luft
    }
  }

  private class ListPanel extends JPanel {
    public ListPanel() {
      setLayout(new BorderLayout());
      add(new JButton(), BorderLayout.WEST);  // for å fylle opp på venstre side ...
      listcontent.addElement("New ingredient");  // legger linjen inn i datamodellen
      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      JScrollPane rullefeltMedListe = new JScrollPane(list); // rullefelt
      add(rullefeltMedListe, BorderLayout.CENTER);
      list.addListSelectionListener(new ListeboksLytter()); // lytter til listevalg
      add(new JButton(), BorderLayout.EAST);  // ... og på høyre side
    }
  }

  /* Lytter etter valg som gjøres i listen */
  private class ListeboksLytter implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent hendelse) {
      int valg = list.getSelectedIndex();
      if (valg >= 0) {
        list.clearSelection();
        if (valg == 0) {  // nytt navn er valgt
          String ingredient = showInputDialog("Fill in ingredient: ");
          if (ingredient != null) {
            listcontent.addElement(ingredient);
            //her må vi legge inn metoden som oppdaterer databasen finnes ikke enda
          }
        } else {  // skal fjerne eksisterende navn
          String ingredientRemove = (String) listcontent.get(valg);
          listcontent.remove(valg);
          //her må vi legge inn metoden som sletter ingrediensen fra databasen, finnes ikke enda
          showMessageDialog(IngredientsList.this,
                         "Now " + ingredientRemove + " is removed from the database.");
        }
      }
    }
  }
}

class ViewIngredients {
  public static void main(String[] args) {
	IngredientsList window = new IngredientsList("Registered ingredients");
    window.setVisible(true);
  }
}