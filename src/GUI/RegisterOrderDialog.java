package GUI;

import controller.*;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The Class RegisterOrderDialog.<br>
 * Used to register new dialog.
 */
class RegisterOrderDialog extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sales user object. */
	private Sales sales = null; 

	/**
	 * Instantiates a new register order dialog.
	 *
	 * @param sales User object
	 */
	public RegisterOrderDialog(Sales sales) {
		this.sales = sales;
		DialogWindow dialog = new DialogWindow(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		pack();
		// Set window location in middle of screen
		dialog.setLocationRelativeTo(null);
		// Display window
		dialog.setVisible(true);
	}


	/**
	 * The Class DialogWindow.
	 */
	private class DialogWindow extends MyDialog{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The customer list. */
		private ArrayList<Customer> customerList = new ArrayList<>();
		
		/** The customer selection box. */
		private JComboBox customerSelect;
				
		/** The info field. */
		private JTextField infoField = new JTextField(10);

		/**
		 * Instantiates a new dialog window.
		 *
		 * @param parent the parent
		 */
		public DialogWindow(JFrame parent){
			super(parent, "New order");
			add(new JPanel(), BorderLayout.NORTH);
			add(new OrderDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,300);
		}

		/**
		 * The Class OrderDatapanel.
		 */
		private class OrderDatapanel extends JPanel{
			
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * Instantiates a new order datapanel.
			 */
			public OrderDatapanel(){
				setLayout(new GridLayout(8,1));

				// Fetch list of customers from database
				customerList = sales.viewCustomerList();

				// Convert to list of strings
				ArrayList<String> nameList = new ArrayList<>();
				for(Customer c: customerList){
					nameList.add(c.getCustomerID() + " " + c.getFirstName() + " " + c.getSurName());
				}
				
				// Set up combobox
				customerSelect = new JComboBox<>(nameList.toArray());

				// Add components
				add(new JLabel("Customer: ", JLabel.LEFT));
				add(customerSelect);

				add(new JLabel("Information about the order: ", JLabel.LEFT));
				add(infoField);

			}
		}

		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){

			// Get customer selected
			int customerIndex = customerSelect.getSelectedIndex();
			int customerID = customerList.get(customerIndex).getCustomerID();

			String info = infoField.getText();

			// Register order in database
			return sales.registerNewOrder(customerID, info, sales.getUserID()) != null;
		}	
	}
}