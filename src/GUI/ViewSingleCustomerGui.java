package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.*;

/**
 * The Class ViewSingleCustomerGui.<br>
 * Used to view info about single customer
 */
class ViewSingleCustomerGui extends JFrame{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sales user object. */
	private Sales sales = null; 

	/**
	 * Instantiates a new view single customer gui.
	 *
	 * @param sales Uesr object
	 */
	public ViewSingleCustomerGui(Sales sales) {
		this.sales = sales;
		ViewSingleCustomerDialog dialog = new ViewSingleCustomerDialog(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		pack();
		// Set window location in middle of screen
		dialog.setLocationRelativeTo(null);
		// Display window
		dialog.setVisible(true);

	} 

	/**
	 * The Class ViewSingleCustomerDialog.
	 */
	private class ViewSingleCustomerDialog extends MyDialog{

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The customer list. */
		private ArrayList<Customer> customerList = new ArrayList<>();
		
		/** The customer selection box. */
		private JComboBox customerSelect;

		/**
		 * Instantiates a new view single customer dialog.
		 *
		 * @param parent Parent frame
		 */
		public ViewSingleCustomerDialog(JFrame parent){

			super(parent, "Choose a customer");
			add(new JPanel(), BorderLayout.NORTH);
			add(new CompanyDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,200);
		}


		/**
		 * The Class CompanyDatapanel.
		 */
		private class CompanyDatapanel extends JPanel{
			
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * Instantiates a new company datapanel.
			 */
			public CompanyDatapanel(){
				setLayout(new GridLayout(1,2));

				// Fetch customer list from database
				customerList = sales.viewCustomerList();

				// Convert to string list
				ArrayList<String> nameList = new ArrayList<>();
				for(Customer c: customerList){
					nameList.add(c.getCustomerID() + " " + c.getFirstName() + " " + c.getSurName());
				}

				// Set up customer selection box
				customerSelect = new JComboBox<>(nameList.toArray());

				// Add component
				add(new JLabel("Customer: ", JLabel.RIGHT));
				add(customerSelect);
			}
		}
		
		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){
			// Get customer selected
			int custIndex = customerSelect.getSelectedIndex();
			Customer currCust = customerList.get(custIndex);

			// Collect information in string and display
			String s="";
			s=sales.viewSingleCustomer(currCust.getCustomerID()).toString();

			JOptionPane.showMessageDialog(null, s, "Information about the choosen customer: ", JOptionPane.INFORMATION_MESSAGE );

			return true;
		}
	}
}



