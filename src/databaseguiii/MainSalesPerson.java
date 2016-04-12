package databaseguiii;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import controller.*;
import databasePackage.Database;

class SalesPersonGui extends JFrame {
	private Parentwindow parentwindow;
	private MainCustomerGui mainCustomerGui;
	private static final String [] CHOICES =
		{"Register a new customer","Change customer information","View customer list","Register new food order","Change food order"};
	private JList<String> choice_list = new JList<String>(CHOICES);  // Naa er listen laget!

	public SalesPersonGui(String tittel) {
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
		private Sales my_sales = new Sales("", 1, "", "", database);
		private Customer my_customer = new Customer("","","","","",1,1,"");
		private Meal my_meal = new Meal(1, "", "", true,1,1,1);
		private Order my_order = new Order(1, "","", 1, "","");
	
		public void valueChanged(ListSelectionEvent hendelse) {
			Object[] values = choice_list.getSelectedValuesList().toArray();
			int choices = choice_list.getSelectedIndex();
			if(choices == 0){
				
    	  //my_cook.();
			}else if(choices==1){
				try {
				//	my_cook.markAsReadyForDelivery(my_meal, my_order);
				} catch (Exception e) {
					e.printStackTrace();
				}
      
			}else if(choices==2){
				try {
				//	my_cook.createMeal(my_meal);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} 
	}
}
  
  
 class MainSalesPerson{
  public static void main(String[] args) {
	  SalesPersonGui window = new SalesPersonGui("Choose an option");
	  window.setVisible(true);
  }
}