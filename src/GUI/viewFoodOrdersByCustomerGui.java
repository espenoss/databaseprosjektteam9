package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.*;

/**
 * The Class ViewFoodOrdersByCustomerGui<br>
 * Used to view food orders by a customer.
 */

class ViewFoodOrdersByCustomerGui extends JFrame{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Sales user object. */
	private Sales sales = null; 

	public ViewFoodOrdersByCustomerGui(Sales sales) {
		this.sales = sales;
		ViewFoodOrdersByCustomerDialog dialog = new ViewFoodOrdersByCustomerDialog(this);
		setLayout(new FlowLayout());
		pack();
		// Set window location in middle of screen
		dialog.setLocationRelativeTo(null);
		// Display window
		dialog.setVisible(true);
	} 

	/**
	 * The Class ViewFoodOrdersByCustomerDialog.
	 */
	
	private class ViewFoodOrdersByCustomerDialog extends MyDialog{

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** The customer list. */
		private ArrayList<Customer> customerList = null;
		
		/** The order list. */
		private ArrayList<Order> orderList = null;
		
		/** The customer selection box. */
		private JComboBox customerSelect;

		/** Listmodel for orderlist component. */
		private DefaultListModel<String> orderListContent = new DefaultListModel<String>();

		/** orderlist component. */
		private JList<String> list = new JList<String>(orderListContent);
		
		/**
		 * Instantiates dialog.
		 *
		 * @param parent Parent frame
		 */
		public ViewFoodOrdersByCustomerDialog(JFrame parent){

			super(parent, "Choose a customer");
			add(new JPanel(), BorderLayout.NORTH);
			add(new FoodOrderDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,200);
		}

		/**
		 * The Class FoodOrderDatapanel.
		 */
		private class FoodOrderDatapanel extends JPanel{

			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			public FoodOrderDatapanel(){
				setLayout(new FlowLayout());

				// Fetches customer list from database
				customerList = sales.viewCustomerList();

				// Convert to string list
				ArrayList<String> nameList = new ArrayList<>();
				for(Customer c: customerList){
					nameList.add(c.getCustomerID() + " " + c.getFirstName() + " " + c.getSurName());
				}
				
				// Set up customer select box
				customerSelect = new JComboBox<>(nameList.toArray());
				customerSelect.addActionListener(new comboListener());

				// Fetch orders for first customer in list
				orderList = sales.viewFoodOrdersByCustomer(customerList.get(0).getCustomerID());

				if(orderList != null){
					for(Order o: orderList){
						orderListContent.addElement(o.getOrderID() + "");
					}
				}

				// Add components
				add(new JLabel("Customer: ", JLabel.RIGHT));
				add(customerSelect);
				add(new JLabel("Orders: ", JLabel.RIGHT));
				JScrollPane orderScroller = new JScrollPane(list); 
				list.addListSelectionListener(new listListener());
				add(orderScroller, BorderLayout.CENTER);
				pack();
			}

			private class comboListener implements ActionListener{

				public void actionPerformed(ActionEvent arg0) {

					// Update orderlist when different customer selected
					// Get customer
					int customerIndex = customerSelect.getSelectedIndex();
					if(customerIndex != -1){
						Customer currCust = customerList.get(customerIndex);
						orderListContent.clear();
						orderList = sales.viewFoodOrdersByCustomer(currCust.getCustomerID());

						// Get orders for customer
						if(orderList != null){
							for(Order o: orderList){
								orderListContent.addElement(o.getOrderID() + "");
							}
						}	
					}
				}				
			}

			// Listener for orderlist
			private class listListener implements ListSelectionListener{
				public void valueChanged(ListSelectionEvent arg0) {
					// Get order
					int orderIndex = list.getSelectedIndex();
					if(orderIndex != -1){
						list.clearSelection();
						// Display order menu
						Order selectedOrder = orderList.get(orderIndex);
						new CustomerOrderMenu(sales, selectedOrder);
					}
				}
			}
		}
	}
}


