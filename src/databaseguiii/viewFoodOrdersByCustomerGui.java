package databaseguiii;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;
import databasePackage.Database;

class ViewFoodOrdersByCustomerGui extends JFrame{
	
	private Sales sales = null; 
	
	public ViewFoodOrdersByCustomerGui(Sales sales) {
		this.sales = sales;
		ViewFoodOrdersByCustomerDialog dialog = new ViewFoodOrdersByCustomerDialog(this);
		dialog.setVisible(true);
		dialog.setLocation(350, 350);
		setTitle("View food orders of a customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocation(300, 300);  
	} 
	
	private class ViewFoodOrdersByCustomerDialog extends MyDialog{
		
		private ArrayList<Customer> customerList = new ArrayList<>();
		private JComboBox customerSelect;
		private JTextField company_name_field = new JTextField(50);
		
		public ViewFoodOrdersByCustomerDialog(JFrame parent){
			
			super(parent, "Choose a customer");
			add(new JPanel(), BorderLayout.NORTH);
			add(new CompanyDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			pack();
		}
		

		private class CompanyDatapanel extends JPanel{
			public CompanyDatapanel(){
				setLayout(new GridLayout(1,2));

				try {
					customerList = sales.viewCustomerList();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
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
			
			try {
				s = sales.viewFoodOrdersByCustomer(currCust.getCustomerID()).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, s, "Orders of the chosen customer "+ currCust.getFirstName(), JOptionPane.INFORMATION_MESSAGE );
			
			return true;
			
		}

	}
	
}

	
