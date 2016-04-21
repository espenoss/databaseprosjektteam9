package databaseguiii;
import databasePackage.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import controller.*;
import databasePackage.Database;

class MainSalesPersonGui extends JFrame {

	public static final int REGISTER_NEW_CUSTOMER = 0;		
	public static final int REGISTER_NEW_COMPANY = 1;
	public static final int VIEW_PRIVATE_CUSTOMERS = 2;		
	public static final int VIEW_COMPANY_CUSTOMERS = 3;
	public static final int CHANGE_CUSTOMER_INFO = 4;	
	public static final int REGISTER_NEW_ORDER = 5;
	public static final int CHANGE_ORDER = 6;
	private JList list = new JList();
	private static final String [] CHOICES =
		{"Register new customer","Register new company", "View private customers", "View company customers","Change customer information", 
    		"Register new food order", "Change food order"};
	private JList<String> choice_list = new JList<String>(CHOICES);
	private Sales sales = null;

	public MainSalesPersonGui(Sales sales) {
		this.sales = sales;
		setTitle("Sales control panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font bigText = new Font("SansSerif", Font.PLAIN, 30);

		JLabel ledetekst = new JLabel("Choose one of the following options.");
		add(ledetekst, BorderLayout.NORTH);

  
		JScrollPane rullefeltMedListe = new JScrollPane(choice_list);
		add(rullefeltMedListe, BorderLayout.CENTER);

		ListeboksLytter lytter = new ListeboksLytter();
		choice_list.addListSelectionListener(lytter);
	  	choice_list.setFont(bigText);
	  	setSize(700, 700);
	}

	private class ListeboksLytter implements ListSelectionListener {
		private Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=espenme&password=16Sossosem06");
	  
		public void valueChanged(ListSelectionEvent hendelse) {
		  
			Object[] values = choice_list.getSelectedValuesList().toArray();
			int choices = choice_list.getSelectedIndex();
			if(choices == REGISTER_NEW_CUSTOMER){
				try {
					new RegisterCustomerDialog(sales);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(choices==REGISTER_NEW_COMPANY){
				try {
					new RegisterCompanyToCustomerDialog(sales);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(choices==VIEW_PRIVATE_CUSTOMERS){
				ArrayList<Customer> c = null;
				try {
					c = sales.viewCustomerList();
				}catch (Exception e) {
					e.printStackTrace();
				}
				String[] s = new String[c.size()];
				for( int i = 0; i < c.size(); i++ ){
					s[i] = c.get(i).toString() + " ";
				}
	    	 
				JScrollPane scrollpane = new JScrollPane(); 
    			JList list = new JList(s);
    			scrollpane = new JScrollPane(list);
    			JPanel panel = new JPanel(); 
    			panel.add(scrollpane);
    			scrollpane.getViewport().add(list);		    	 
    			JOptionPane.showMessageDialog(null, scrollpane, "All customers: ", JOptionPane.INFORMATION_MESSAGE );

			}else if(choices == VIEW_COMPANY_CUSTOMERS){
				ArrayList<Customer> c = null;
				try {
					c= sales.viewCompanyList(); 
				}catch (Exception e) {
					e.printStackTrace();
				}
				String[] s = new String[c.size()];
    				for( int i = 0; i < c.size(); i++ ){
    					Customer t = c.get(i);
    					s[i] = t.getCustomerID() + ", " + t.getCompanyName() + ": " + t.getSurName() + ", " + t.getFirstName() + "\n";
    				}
	    	 
    				JScrollPane scrollpane = new JScrollPane(); 
    				JList list = new JList(s);
    				scrollpane = new JScrollPane(list);
    				JPanel panel = new JPanel(); 
    				panel.add(scrollpane);
    				scrollpane.getViewport().add(list);		    	 
    				JOptionPane.showMessageDialog(null, scrollpane, "All customers: ", JOptionPane.INFORMATION_MESSAGE );
    	
			}else if(choices==CHANGE_CUSTOMER_INFO){
				new ChangeCustomerInfoDialog(sales);      
    
			}else if(choices==REGISTER_NEW_ORDER){
				new RegisterOrderDialog(sales);
			}

			else if(choices== CHANGE_ORDER){
				try {
					new ChangeOrderDialog(sales);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	 }
 }