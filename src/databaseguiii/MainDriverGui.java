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

class MainDriverGui extends JFrame {
	private static final String [] CHOICES =
		{"Generate delivery plan", "Mark order as delivered"};
	private JList<String> choice_list = new JList<String>(CHOICES);  // Naa er listen laget!
	private Driver driver = null;

	public MainDriverGui(Driver driver) {
		this.driver = driver;
		setTitle("Driver control panel");
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
		private int orderIndex;
		
		public void valueChanged(ListSelectionEvent hendelse) {
			
			Object[] values = choice_list.getSelectedValuesList().toArray();
			int choices = choice_list.getSelectedIndex();
			if(choices == 0){
				new ViewDeliveryList(driver).setVisible(true);
			}else if(choices == 1){
				// 
			}
		} 
	}
	
	public static void main(String[] args){
 		String username = "espenme";
 		String passingword = "16Sossosem06";
 		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
 		Database database = new Database("com.mysql.jdbc.Driver", databasename);		
		Driver driver = new Driver("","", database);
		MainDriverGui d = new MainDriverGui(driver);
		d.setVisible(true);
	}
}