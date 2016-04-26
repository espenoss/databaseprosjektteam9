package GUI;
import controller.*;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * The Class RegisterCompanyToCustomerDialog.<br>
 * Used to add company name to customer
 */
class RegisterCompanyToCustomerDialog extends JFrame{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sales  user object. */
	private Sales sales = null; 

	/**
	 * Instantiates a new register company to customer dialog.
	 *
	 * @param sales User object
	 */
	public RegisterCompanyToCustomerDialog(Sales sales) {
		this.sales = sales;
		RegCompanytoCustomerDialog dialog = new RegCompanytoCustomerDialog(this);
		setLayout(new FlowLayout());
		pack();
		// Set window location in middle of screen
		dialog.setLocationRelativeTo(null);
		// Display window
		dialog.setVisible(true);
	} 

	/**
	 * The Class RegCompanytoCustomerDialog.
	 */
	private class RegCompanytoCustomerDialog extends MyDialog{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The customer list. */
		private ArrayList<Customer> customerList = new ArrayList<>();
		
		/** The customer selection box. */
		private JComboBox customerSelect;
		
		/** The company name field. */
		private JTextField company_name_field = new JTextField(50);

		/**
		 * Instantiates a new reg companyto customer dialog.
		 *
		 * @param parent the parent
		 */
		public RegCompanytoCustomerDialog(JFrame parent){
			super(parent, "Fill in company name");
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
				setLayout(new GridLayout(4,1));

				// Fetch customer list from database
				customerList = sales.viewCustomerList();

				// Convert to string list
				ArrayList<String> nameList = new ArrayList<>();
				for(Customer c: customerList){
					if(!c.getIsCompany()){
						nameList.add(c.getCustomerID() + " " + c.getFirstName() + " " + c.getSurName());						
					}
				}
				// Set up customer selection combo box
				customerSelect = new JComboBox<>(nameList.toArray());
				add(new JLabel("Customer: ", JLabel.LEFT));
				add(customerSelect);

				add(new JLabel("Company name: ", JLabel.LEFT));
				add(company_name_field);
			}
		}
		
		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){
			// Get customer
			int custIndex = customerSelect.getSelectedIndex();
			Customer currCust = customerList.get(custIndex);	
			String company_name = company_name_field.getText();
			
			// Check if name valid
			if(company_name.equals("")){
				JOptionPane.showMessageDialog(null, "Company name must be given");
				return false;
			}
			
			// Register name to customer in database
			return sales.registerCompanyToCustomer(currCust, company_name);
		}

	}

}  
