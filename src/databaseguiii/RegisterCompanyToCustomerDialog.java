package databaseguiii;
import controller.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import databasePackage.*;


class RegisterCompanyToCustomerDialog extends JFrame{
	
		private Sales sales = null; 
		
		public RegisterCompanyToCustomerDialog(Sales sales) {
			this.sales = sales;
			RegCompanytoCustomerDialog dialog = new RegCompanytoCustomerDialog(this);
			dialog.setVisible(true);
			setTitle("Register customer");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new FlowLayout());
			setLocation(300, 300); 
			dialog.setLocation(350, 350); 
		} 
		
		private class RegCompanytoCustomerDialog extends MyDialog{
			private ArrayList<Customer> customerList = new ArrayList<>();
			private JComboBox customerSelect;
			private JTextField company_name_field = new JTextField(50);
			
			public RegCompanytoCustomerDialog(JFrame parent){
				super(parent, "Fill in company name");
				add(new JPanel(), BorderLayout.NORTH);
				add(new CompanyDatapanel(),BorderLayout.CENTER);
				add(getButtonPanel(),BorderLayout.SOUTH);
				customerSelect.addActionListener(new listListener());
				pack();
			}
			
			private class listListener implements ActionListener{
				public void actionPerformed(ActionEvent e){
					int custIndex = customerSelect.getSelectedIndex();
					Customer currCust = customerList.get(custIndex);
						
				}				
			}

			private class CompanyDatapanel extends JPanel{
				public CompanyDatapanel(){
					setLayout(new GridLayout(2,2));

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
			
					add(new JLabel("Company name: ", JLabel.RIGHT));
					add(company_name_field);
				}
			}
			public boolean okData(){
				int custIndex = customerSelect.getSelectedIndex();
				Customer currCust = customerList.get(custIndex);	
				String company_name = company_name_field.getText();
				if(company_name.equals(""))JOptionPane.showMessageDialog(null, "Company name must be given");
					try{
						sales.registerCompanyToCustomer(currCust, company_name);
					}catch (Exception e) {
						System.out.println(e.toString());
						return false;
					}
					return true;
				
			
			}

		}

	}  
