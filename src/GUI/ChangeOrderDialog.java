package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import controller.*;

/**
 * The Class ChangeOrderDialog.<br>
 * Used to change order information
 */
class ChangeOrderDialog extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sales. */
	private Sales sales = null;

	/**
	 * Instantiates a new change order dialog.
	 *
	 * @param sales User object
	 */
	public ChangeOrderDialog(Sales sales) {
		this.sales = sales;
		// Add dialog
		DialogWindow dialog = new DialogWindow(this);
		setTitle("Change order");
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

		/** The orders. */
		private ArrayList<Order> orders = new ArrayList<>();
		
		/** The customer list. */
		private ArrayList<Customer> customerList = new ArrayList<>();
		
		/** The customer selection combobox. */
		private JComboBox customerSelect;
		
		/** The order selection combobox. */
		private JComboBox orderSelect;		
		
		/** The date select model. */
		private SpinnerDateModel dateSelectModel = new SpinnerDateModel();
		
		/** The date selection spinner. */
		private JSpinner dateSelect = new JSpinner(dateSelectModel);
		
		/** The info field. */
		private JTextField infoField = new JTextField(10);
		
		/** The delivery date string. */
		private String deliveryDateStr;
		
		/** The info. */
		private String info;

		/**
		 * Instantiates a new dialog window.
		 *
		 * @param parent Parent frame
		 */
		public DialogWindow(JFrame parent){
			super(parent, "Change order information");
			add(new JPanel(), BorderLayout.NORTH);
			add(new ChangeOrderDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);

			setSize(500,300);
		}
		
		/**
		 * The Class ChangeOrderDatapanel.
		 */
		private class ChangeOrderDatapanel extends JPanel{
			
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * Instantiates a new change order datapanel.
			 */
			public ChangeOrderDatapanel(){
				setLayout(new GridLayout(8,1));
				
				// Fetch customer list from database
				customerList = sales.viewCustomerList();

				// Convert to string list
				ArrayList<String> nameList = new ArrayList<>();
				for(Customer c: customerList){
					nameList.add(c.getCustomerID() + " " + c.getFirstName() + " " + c.getSurName());
				}

				// Initialize order list
				orders = sales.viewFoodOrdersByCustomer(customerList.get(0).getCustomerID());

				// Convert to string list
				ArrayList<String> orderNameList = new ArrayList<>();
				for(Order o: orders){
					orderNameList.add(String.valueOf(o.getOrderID()));
				}

				// Set up comboboxes
				customerSelect = new JComboBox<>(nameList.toArray());
				orderSelect = new JComboBox<>(orderNameList.toArray());

				// Add components
				add(new JLabel("Customer: ", JLabel.LEFT));
				add(customerSelect);
				customerSelect.addActionListener(new customerListener());
				add(new JLabel("Order: ", JLabel.LEFT));				
				add(orderSelect);
				add(new JLabel("New order date: ", JLabel.LEFT));
				add(dateSelect);
				add(new JLabel("Info: ", JLabel.LEFT));
				add(infoField);
			}
		}
		
		/**
		 * Listens to customer selection combobox	
		 * 
		 */
		private class customerListener implements ActionListener{
			
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				// Get orders for selected
				int customerSelected = customerSelect.getSelectedIndex();
				orders = sales.viewFoodOrdersByCustomer(customerList.get(customerSelected).getCustomerID());

				// Empty order selection box
				orderSelect.removeAllItems();
				if(orders != null){
					// Add orders for customer to box
					for(Order o: orders){
						orderSelect.addItem(String.valueOf(o.getOrderID()));
					}
				}			
			}		
		}
		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){
			// Get selected order
			int orderIndex = orderSelect.getSelectedIndex();
			Order currOrder = orders.get(orderIndex);

			// Format date 
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			deliveryDateStr = s.format(dateSelect.getValue());
			
			info = infoField.getText();
			
			// Set new info in object
			currOrder.setOrderDate(deliveryDateStr);
			currOrder.setInfo(info);

			// Update to database
			return currOrder.uploadOrder(sales.getDatabase());
		}

	} 
}