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

class ViewSingleCustomerGui extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Sales sales = null; 

	public ViewSingleCustomerGui(Sales sales) {
		this.sales = sales;
		ViewSingleCustomerDialog dialog = new ViewSingleCustomerDialog(this);
		setTitle("View information about a customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);

	} 

	private class ViewSingleCustomerDialog extends MyDialog{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private ArrayList<Customer> customerList = new ArrayList<>();
		private JComboBox customerSelect;

		public ViewSingleCustomerDialog(JFrame parent){

			super(parent, "Choose a customer");
			add(new JPanel(), BorderLayout.NORTH);
			add(new CompanyDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,200);
			setLocationRelativeTo(null);
		}


		private class CompanyDatapanel extends JPanel{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public CompanyDatapanel(){
				setLayout(new GridLayout(1,2));

				customerList = sales.viewCustomerList();

				ArrayList<String> nameList = new ArrayList<>();
				for(Customer c: customerList){
					nameList.add(c.getCustomerID() + " " + c.getFirstName() + " " + c.getSurName());
				}
				customerSelect = new JComboBox<>(nameList.toArray());
				add(new JLabel("Customer: ", JLabel.RIGHT));
				add(customerSelect);


			}
		}
		public boolean okData(){
			int custIndex = customerSelect.getSelectedIndex();
			Customer currCust = customerList.get(custIndex);

			String s="";
			s=sales.viewSingleCustomer(currCust.getCustomerID()).toString();

			JOptionPane.showMessageDialog(null, s, "Information about the choosen customer: ", JOptionPane.INFORMATION_MESSAGE );

			return true;

		}
	}
}



