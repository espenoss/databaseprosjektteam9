package GUI;
import controller.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


class RegisterCompanyToCustomerDialog extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Sales sales = null; 

	public RegisterCompanyToCustomerDialog(Sales sales) {
		this.sales = sales;
		RegCompanytoCustomerDialog dialog = new RegCompanytoCustomerDialog(this);
		setTitle("Register customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		pack();
		dialog.setLocationRelativeTo(null); 
		dialog.setVisible(true);
	} 

	private class RegCompanytoCustomerDialog extends MyDialog{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private ArrayList<Customer> customerList = new ArrayList<>();
		private JComboBox customerSelect;
		private JTextField company_name_field = new JTextField(50);

		public RegCompanytoCustomerDialog(JFrame parent){
			super(parent, "Fill in company name");
			add(new JPanel(), BorderLayout.NORTH);
			add(new CompanyDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			customerSelect.addActionListener(new listListener());
			setSize(500,200);
			setLocationRelativeTo(null);
		}

		private class listListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				int custIndex = customerSelect.getSelectedIndex();
				Customer currCust = customerList.get(custIndex);

			}				
		}

		private class CompanyDatapanel extends JPanel{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public CompanyDatapanel(){
				GridLayout superGrid = new GridLayout(4,1);
				setLayout(superGrid);

				customerList = sales.viewCustomerList();

				ArrayList<String> nameList = new ArrayList<>();
				for(Customer c: customerList){
					nameList.add(c.getCustomerID() + " " + c.getFirstName() + " " + c.getSurName());
				}
				customerSelect = new JComboBox<>(nameList.toArray());
				add(new JLabel("Customer: ", JLabel.LEFT));
				add(customerSelect);

				add(new JLabel("Company name: ", JLabel.LEFT));
				add(company_name_field);
			}
		}
		public boolean okData(){
			int custIndex = customerSelect.getSelectedIndex();
			Customer currCust = customerList.get(custIndex);	
			String company_name = company_name_field.getText();
			if(company_name.equals("")){
				JOptionPane.showMessageDialog(null, "Company name must be given");
				return false;
			}
			return sales.registerCompanyToCustomer(currCust, company_name);
		}

	}

}  
