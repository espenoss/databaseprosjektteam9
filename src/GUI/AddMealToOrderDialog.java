package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;

import controller.*;
import database.Database;

/**
 * The Class AddMealToOrderDialog.
 * 
 */
public class AddMealToOrderDialog extends JFrame{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/** The sales. */
	private Sales sales = null;
	
	/** The order. */
	private Order order = null;

	/**
	 * Instantiates a new adds the meal to order dialog.
	 *
	 * @param sales User Object
	 * @param order Order to add meal to
	 */
	public AddMealToOrderDialog(Sales sales, Order order){
		this.sales = sales;
		this.order = order;
		// Add dialog		
		DialogContent dialog = new DialogContent(this);
		pack();
		// Set window location in middle of screen		
		dialog.setLocationRelativeTo(null);
		// Display window
		dialog.setVisible(true);
	}

	/**
	 * The Class DialogContent.
	 */
	private class DialogContent extends MyDialog{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The meal list. 
		 * List of meals available to aadd*/
		private ArrayList<Meal> mealList = null;
		
		/** The meal selection combobox*/
		private JComboBox mealSelect;
		
		/** The date select model. */
		private SpinnerDateModel dateSelectModel = new SpinnerDateModel();
		
		/** The date selection spinner. */
		private JSpinner dateSelect = new JSpinner(dateSelectModel);
		
		/** The quantity select model. */
		private SpinnerNumberModel quantitySelectModel = new SpinnerNumberModel();
		
		/** The quantity selection spinner. */
		private JSpinner quantitySelect = new JSpinner(quantitySelectModel);		

		/**
		 * Instantiates new dialog.
		 *
		 * @param parent Parent frame
		 */
		public DialogContent(JFrame parent){
			super(parent, "Add meal to order");
			add(new JPanel(), BorderLayout.NORTH);
			add(new DataPanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,600);
		}

		/**
		 * The Class DataPanel.
		 */
		private class DataPanel extends JPanel{
			
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * Instantiates a new data panel.
			 */
			public DataPanel(){

				GridLayout superGrid = new GridLayout(6,1);
				setLayout(superGrid);

				mealList = sales.viewAvailableMeals();

				ArrayList<String> mealNames = new ArrayList<>();

				for(Meal m:mealList){
					mealNames.add(m.getMealName());
				}
				mealSelect = new JComboBox<>(mealNames.toArray());

				Font bigText = new Font("SansSerif", Font.PLAIN, 25);
				
				add(new JLabel("Meal: ", JLabel.LEFT)).setFont(bigText);
				add(mealSelect).setFont(bigText);
				add(new JLabel("Delivery date: ", JLabel.LEFT)).setFont(bigText);
				add(dateSelect).setFont(bigText);
				add(new JLabel("Quantity: ", JLabel.LEFT)).setFont(bigText);
				add(quantitySelect).setFont(bigText);
			}
		}


		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){
			// Get selected meal
			int mealIndex = mealSelect.getSelectedIndex();
			Meal currMeal= mealList.get(mealIndex);	

			// Format date from date spinner
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String deliveryDate = s.format(dateSelect.getValue());

			// Get quantity
			int quantity = (int)quantitySelect.getValue();
			if(quantity < 1){
				JOptionPane.showMessageDialog(null, "Quantity must be greater than zero");
				return false;
			}

			// Add connection to database
			return sales.addMealToOrder(order.getOrderID(), currMeal.getMealID(), deliveryDate, quantity);
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){

		String username = "espenme";
		String passingword = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
		Database database = new Database("com.mysql.jdbc.Driver", databasename);
		new AddMealToOrderDialog(new Sales("","", database), new Order(10010, "", 10005, "", ""));

	}
}
