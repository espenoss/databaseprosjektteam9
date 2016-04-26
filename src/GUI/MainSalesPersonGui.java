package GUI;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import controller.*;

/**
 * The Class MainSalesPersonGui.<br>
 * The main menu for Sales users
 */
class MainSalesPersonGui extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The menu selection list. */
	private static final String [] CHOICES =
		{
				"Register new customer",
				"View information about a single customer",
				"Register new company", 
				"View private customers", 
				"View company customers",
				"Change customer information", 
				"View food orders",
				"Register new food order", 
				"Change food order",
				"Add meal or subscription to order",
				"View all subscription plans",
				"View available meals", 
		"View ingredient in meal"};

	/** The menu list. */
	private JList<String> choice_list = new JList<String>(CHOICES);

	/** The sales user object. */
	private Sales sales = null;

	/** User menu selection constants. */
	private static final int REGISTER_NEW_CUSTOMER = 0;
	private static final int VIEW_INFO_ABOUT_SINGLE_CUST = 1;		
	private static final int REGISTER_NEW_COMPANY = 2;
	private static final int VIEW_PRIVATE_CUSTOMERS = 3;		
	private static final int VIEW_COMPANY_CUSTOMERS = 4;
	private static final int CHANGE_CUSTOMER_INFO = 5;
	private static final int VIEW_FOOD_ORDERS = 6;
	private static final int REGISTER_NEW_ORDER = 7;
	private static final int CHANGE_ORDER = 8;
	private static final int VIEW_FOOD_ORDER_OF_SINGLE_CUSTOMER = 9;
	private static final int VIEW_ALL_SUBPLANS = 10;
	private static final int VIEW_MEALS = 11;
	private static final int VIEW_INGREDIENTS_IN_MEAL = 12;

	/**
	 * 
	 * Instantiates a new main sales gui.
	 * 
	 * @param sales User object
	 */
	public MainSalesPersonGui(Sales sales) {
		this.sales = sales;
		setTitle("Sales control panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font bigText = new Font("SansSerif", Font.PLAIN, 25);

		JLabel ledetekst = new JLabel("Choose one of the following options.");
		add(ledetekst, BorderLayout.NORTH);

		JScrollPane rullefeltMedListe = new JScrollPane(choice_list);
		add(rullefeltMedListe, BorderLayout.CENTER);

		MenuListListener listener = new MenuListListener();
		choice_list.addListSelectionListener(listener);
		choice_list.setFont(bigText);
		setSize(700, 700);
		setLocationRelativeTo(null);
	}

	/**
	 * The Class MenuListListener.<br>
	 * Listens to menu choice and creates new windows based on selection.
	 */
	private class MenuListListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent hendelse) {

			int choices = choice_list.getSelectedIndex();
			if(choices == REGISTER_NEW_CUSTOMER){
				new RegisterCustomerDialog(sales);

			}else if(choices==VIEW_INFO_ABOUT_SINGLE_CUST){
				new ViewSingleCustomerGui(new Sales(sales.getUserID(), sales.getName(), sales.getDatabase()));

			}
			else if(choices==REGISTER_NEW_COMPANY){
				new RegisterCompanyToCustomerDialog(sales);
			}else if(choices==VIEW_PRIVATE_CUSTOMERS){
				ArrayList<Customer> c = null;
				c = sales.viewCustomerList();
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
				JOptionPane.showMessageDialog(null, scrollpane, "Private customers: ", JOptionPane.INFORMATION_MESSAGE );

			}else if(choices == VIEW_COMPANY_CUSTOMERS){
				ArrayList<Customer> c = null;
				c= sales.viewCompanyList(); 
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
				JOptionPane.showMessageDialog(null, scrollpane, "Companies: ", JOptionPane.INFORMATION_MESSAGE );

			}else if(choices==CHANGE_CUSTOMER_INFO){
				new ChangeCustomerInfoDialog(sales);      

			}else if(choices==VIEW_FOOD_ORDERS){
				new ViewFoodOrders (new Sales (sales.getUserID(), sales.getName(), sales.getDatabase()));
			}
			else if(choices==REGISTER_NEW_ORDER){
				new RegisterOrderDialog(sales);
			}
			else if(choices== CHANGE_ORDER){
				new ChangeOrderDialog(sales);
			}else if(choices==VIEW_FOOD_ORDER_OF_SINGLE_CUSTOMER){
				new ViewFoodOrdersByCustomerGui(new Sales(sales.getUserID(), sales.getName(), sales.getDatabase()));
			}else if (choices == VIEW_ALL_SUBPLANS){
				ArrayList<SubPlan> sp = null;
				sp = sales.viewAllSubPlans();
				String[] s = new String[sp.size()];
				for( int i = 0; i < sp.size(); i++ ){
					s[i] = sp.get(i).toString() + " ";
				}
				JScrollPane scrollpane = new JScrollPane(); 
				JList<String> list = new JList<String>(s);
				scrollpane = new JScrollPane(list);
				JPanel panel = new JPanel(); 
				panel.add(scrollpane);
				scrollpane.getViewport().add(list);		    	 
				JOptionPane.showMessageDialog(null, scrollpane, "All sub plans: ", JOptionPane.INFORMATION_MESSAGE );
			}else if(choices==VIEW_MEALS){
				ArrayList<Meal> m = null;
				m= sales.viewAvailableMeals();
				String[] s = new String[m.size()];
				for( int i = 0; i < m.size(); i++ ){
					Meal meal = m.get(i);
					s[i] = meal.getMealName()+ "\n";
				}

				JScrollPane scrollpane = new JScrollPane(); 
				JList list = new JList(s);
				scrollpane = new JScrollPane(list);
				JPanel panel = new JPanel(); 
				panel.add(scrollpane);
				scrollpane.getViewport().add(list);		    	 
				JOptionPane.showMessageDialog(null, scrollpane, "All meals: ", JOptionPane.INFORMATION_MESSAGE );
			}else if(choices==VIEW_INGREDIENTS_IN_MEAL){
				new IngredientsInMealGui(new Cook(sales.getUserID(), sales.getName(), sales.getDatabase()));
			}
		}
	}
}
