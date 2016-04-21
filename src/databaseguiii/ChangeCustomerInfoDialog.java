package databaseguiii;
import controller.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import databasePackage.Database;

class ChangeCustomerInfoDialog extends JFrame {
	  private Sales sales = null; 
	  Customer customer = null;
	 
	  public ChangeCustomerInfoDialog(Sales sales) {
		  	this.sales = sales;
	  		CustomerDialog dialog = new CustomerDialog(this);
	  		dialog.setVisible(true);
	  		setTitle("Registrer customer");
	  		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  		setLayout(new FlowLayout());
	  		setLocation(300, 300); 
	  		dialog.setLocation(350, 350); 
	 } 
	  
	  private class CustomerDialog extends MyDialog implements ActionListener{
			private TextEditor editor = new TextEditor();

			private ArrayList<Customer> customerList = new ArrayList<>();
			private JComboBox customerSelect;
			private JTextField firstNameField = new JTextField(20);
			private JTextField surNameField = new JTextField(20);
			private JTextField emailField = new JTextField(20);
			private JTextField adressField = new JTextField(20);
			private JTextField zip_codeField = new JTextField(6);
			private JTextField zone_nrField = new JTextField(6);
			private JTextField preferencesField = new JTextField(100);
			private JTextField phoneNumberField = new JTextField(12);

			private final String status[] = {"active", "inactive"}; 
			private JComboBox status_list = new JComboBox(status);
			
			
			
			public CustomerDialog(JFrame parent){
				super(parent, "Fill in new information about a customer");
				add(new JPanel(), BorderLayout.NORTH);
				add(new CustomerDatapanel(),BorderLayout.CENTER);
				add(getButtonPanel(),BorderLayout.SOUTH);
				customerSelect.addActionListener(this);
				pack();
			}
			
			private class CustomerDatapanel extends JPanel{
				public CustomerDatapanel(){
					setLayout(new GridLayout(10,2));

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
			
					add(new JLabel("Surname: ", JLabel.RIGHT));
					add(surNameField);

					add(new JLabel("First name: ", JLabel.RIGHT));
					add(firstNameField);
					
					add(new JLabel("Phone number: ", JLabel.RIGHT));
					add(phoneNumberField);
					
					add(new JLabel("Email: ", JLabel.RIGHT));
					add(emailField);
					
					add(new JLabel("Adress: ", JLabel.RIGHT));
					add(adressField);
					
					add(new JLabel("Zip Code: ", JLabel.RIGHT));
					add(zip_codeField);
					
					add(new JLabel("Zone Number: ", JLabel.RIGHT));
					add(zone_nrField);
					
					add(new JLabel("Preferences: ", JLabel.RIGHT));
					add(preferencesField);
					
					add(new JLabel("Status: ", JLabel.RIGHT));
					add(status_list);
					
					
				}
			}
			public boolean okData(){
				int custIndex = customerSelect.getSelectedIndex();
				Customer currCust = customerList.get(custIndex);				
				
				currCust.setFirstName(firstNameField.getText());	
				currCust.setSurName(surNameField.getText());
				currCust.setAdress(adressField.getText());
				currCust.setEmail(emailField.getText());
				currCust.setAdress(adressField.getText());
				
				String zip_codeText  = zip_codeField.getText();
				int myIntZip = editor.stringToInt(zip_codeText);
				currCust.setZipCode(myIntZip);
				
				String zone_nrText  = zone_nrField.getText();
				int myIntZone = editor.stringToInt(zone_nrText);
				currCust.setZoneNr(myIntZone);
				
				currCust.setPreferences(preferencesField.getText());
				currCust.setPhoneNumber(phoneNumberField.getText());
			
				if(status_list.getSelectedIndex() == 0){
					currCust.setActive(true);
				}else{
					currCust.setActive(false);
				}
				
				try {
					currCust.updateCustomer(sales.getDatabase());
				} catch (Exception e) {
					System.out.println(e.toString());
					return false;
				}
				return true;		
			}
			
			public void actionPerformed(ActionEvent e){
				int custIndex = customerSelect.getSelectedIndex();
				Customer currCust = customerList.get(custIndex);
				surNameField.setText(currCust.getSurName());
				firstNameField.setText(currCust.getFirstName());
				phoneNumberField.setText(currCust.getPhoneNumber());
				emailField.setText(currCust.getEmail());
				adressField.setText(currCust.getAdress());
				zip_codeField.setText(Integer.toString(currCust.getZipCode()));
				zone_nrField.setText(Integer.toString(currCust.getZoneNr()));
				preferencesField.setText(currCust.getPreferences());
			
			}
	
	  }
}  




