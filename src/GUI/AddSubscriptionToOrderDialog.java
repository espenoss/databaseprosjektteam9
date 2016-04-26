package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import controller.SubPlan;
import controller.Order;
import controller.Sales;

/**
 * The Class AddSubscriptionToOrderDialog.<br>
 * Used to add subscription to order
 */
public  class AddSubscriptionToOrderDialog extends JFrame{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sales user object. */
	private Sales sales = null;
	
	/** The order to add subscription to. */
	private Order order = null;

	/**
	 * Instantiates a new frame.
	 *
	 * @param sales User object
	 * @param order Order to add subscription to
	 */
	AddSubscriptionToOrderDialog(Sales sales, Order order){
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
		
		/** The subscripiton list. */
		private ArrayList<SubPlan> subList = null;
		
		/** The subscription selection spinner. */
		private JComboBox subSelect;
		
		/** The from date select model. */
		private SpinnerDateModel fromDateSelectModel = new SpinnerDateModel();
		
		/** The to date select model. */
		private SpinnerDateModel toDateSelectModel = new SpinnerDateModel();
		
		/** The from date selection spinner. */
		private JSpinner fromDateSelect = new JSpinner(fromDateSelectModel);
		
		/** The to date selection spinner. */
		private JSpinner toDateSelect = new JSpinner(toDateSelectModel);
		
		/** The quantity select model. */
		private SpinnerNumberModel quantitySelectModel = new SpinnerNumberModel();
		
		/** The quantity selection spinner. */
		private JSpinner quantitySelect = new JSpinner(quantitySelectModel);		

		/**
		 * Instantiates a new dialog content.
		 *
		 * @param parent the parent
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

				GridLayout superGrid = new GridLayout(8,1);
				setLayout(superGrid);

				// Fetch all subscription plans from database
				subList = sales.viewAllSubPlans();

				// Convert to string arraylist
				ArrayList<String> subNames = new ArrayList<>();
				for(SubPlan s:subList){
					subNames.add(s.getName());
				}
				
				// Set up subscription selection combobox
				subSelect = new JComboBox<>(subNames.toArray());

				Font bigText = new Font("SansSerif", Font.PLAIN, 25);				
				
				// Add component
				add(new JLabel("Plan: ", JLabel.LEFT)).setFont(bigText);
				add(subSelect).setFont(bigText);
				add(new JLabel("From date: ", JLabel.LEFT)).setFont(bigText);
				add(fromDateSelect).setFont(bigText);
				add(new JLabel("To date: ", JLabel.LEFT)).setFont(bigText);
				add(toDateSelect).setFont(bigText);				
				add(new JLabel("Quantity: ", JLabel.LEFT)).setFont(bigText);
				add(quantitySelect).setFont(bigText);
			}
		}


		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){
			// Get selected subscription
			int subIndex = subSelect.getSelectedIndex();
			SubPlan currSub= subList.get(subIndex);	

			// Format date selected
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String fromDate = s.format(fromDateSelect.getValue());
			String toDate = s.format(toDateSelect.getValue());
			
			// Get subscription quantity
			int quantity = (int)quantitySelect.getValue();
			if(quantity < 1){
				JOptionPane.showMessageDialog(null, "Quantity must be greater than zero");
				return false;
			}

			// Register in database, return true if OK
			return sales.registerSubscriptionOrder(order, quantity, fromDate, toDate, currSub.getSubPlanID());
		}
	}
}

