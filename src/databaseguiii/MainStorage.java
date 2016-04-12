package databaseguiii;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import controller.*;
import databasePackage.Database;

class StorageGui extends JFrame {
	private static final String [] CHOICES =
		{"Register delivery"};
	private JList<String> choice_list = new JList<String>(CHOICES);  // Naa er listen laget!
	

	public StorageGui(String tittel) {
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
		private Storage storage = new Storage("", 1, "", "", database);
		
		
		public void valueChanged(ListSelectionEvent hendelse) {
			Object[] values = choice_list.getSelectedValuesList().toArray();
			int choices = choice_list.getSelectedIndex();
	/*		if(choices == 0){
				storage. registerDelivery(String orderinfo);  
			}    */
		} 
	}
}
  
  
 class MainStorage{
  public static void main(String[] args) {
	  StorageGui window = new StorageGui("Choose an option");
	  window.setVisible(true);
  }
}