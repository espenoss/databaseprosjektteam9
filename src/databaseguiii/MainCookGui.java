package databaseguiii;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import controller.*;
import databasePackage.Database;

class MainCookGui extends JFrame {
	private static final String [] CHOICES =
		{"View food orders","Mark order as ready for delivery","Create new meal","View menu ingredients"};
	private JList<String> choice_list = new JList<String>(CHOICES);
	Cook cook = null;

	public MainCookGui(Cook cook) {
		this.cook = cook;
		setTitle("Cook control panel");
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
		public void valueChanged(ListSelectionEvent hendelse) {
			int choices = choice_list.getSelectedIndex();
			try {
			if(choices == 0){
				// View food orders
			}else if(choices==1){
				// Mark as ready gui
			}else if(choices==2){
				new CreateMealDialog(cook);
					// Create new meal
			}else if(choices == 3){
				// View menu ingredients
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		} 
	}
}