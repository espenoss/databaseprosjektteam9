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

class DriverGui extends JFrame {
	private static final String [] CHOICES =
		{"Generate delivery plan", "Mark order as delivered"};
	private JList<String> choice_list = new JList<String>(CHOICES);  // Naa er listen laget!
	

	public DriverGui(String tittel) {
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
	//	private Driver driver = new  Driver (userID, userType, name, pword, database);
		private Driver driver = new Driver("", 1, "", "", database);
		
		
		public void valueChanged(ListSelectionEvent hendelse) {
			
			Object[] values = choice_list.getSelectedValuesList().toArray();
			int choices = choice_list.getSelectedIndex();
			if(choices == 0){
				driver.generateDeliveryPlan();
  
			} else if(choices==1){
				driver.markDelivered();
				  
			} 
		} 
	}
}
  
  
 class MainDriver {
  public static void main(String[] args) {
	  DriverGui window = new DriverGui("Choose an option");
	  window.setVisible(true);
  }
}