package databaseguiii;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;
import databasePackage.Database;

class ViewSingleCustomerGui extends JFrame{
	
	private Sales sales = null; 
	
	public ViewSingleCustomerGui(Sales sales) {
		this.sales = sales;
		ViewSingleCustomerDialog dialog = new ViewSingleCustomerDialog(this);
		dialog.setVisible(true);
		dialog.setLocation(350, 350);
		setTitle("View information about a customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocation(300, 300);  
	} 
	
	private class ViewSingleCustomerDialog extends MyDialog{
		
		private ArrayList<Customer> customerList = new ArrayList<>();
		private JComboBox customerSelect;
		private JTextField company_name_field = new JTextField(50);
		
		public ViewSingleCustomerDialog(JFrame parent){
			
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
				 s=sales.viewSingleCustomer(currCust.getCustomerID()).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, s, "Information about the choosen customer: ", JOptionPane.INFORMATION_MESSAGE );
			
			return true;
			
		}

	}
	
}

  

