package databaseguiii;
import controller.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import databasePackage.Database;

class ParentWindow extends JFrame {
	  private Database database = new Database ("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=espenme&password=16Sossosem06");
	  private Sales sales = new Sales("espenme",0, "Espen","hei", database); 
	  Customer customer = new Customer(1,"", "", "", "", "", 1, 1,"", true);
	  
	  private class CustomerDialog extends MyDialog{
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
			
			private String surName;
			private String firstName;
			private String email;
			private String adress;
			private int zip_code;
			private int zone_nr;
			private String preferences;
			private String phoneNumber;
			private boolean active;
	//		private boolean isCompany;
	//		private String companyName;
			
			
			public CustomerDialog(JFrame parent){
				super(parent, "Fill in new information about a customer");
				add(new JPanel(), BorderLayout.NORTH);
				add(new CustomerDatapanel(),BorderLayout.CENTER);
				add(getButtonPanel(),BorderLayout.SOUTH);
				pack();
			}
			
			private class CustomerDatapanel extends JPanel{
				public CustomerDatapanel(){
					setLayout(new GridLayout(9,2));

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
					
					
				}
			}
			public boolean okData(){
				firstName = firstNameField.getText();	
				surName = surNameField.getText();
				email = emailField.getText();
				adress = adressField.getText();
				
				String zip_codeText  = zip_codeField.getText();
				int myIntZip = editor.stringToInt(zip_codeText);
				zip_code = myIntZip;
				
				String zone_nrText  = zone_nrField.getText();
				int myIntZone = editor.stringToInt(zone_nrText);
				zone_nr = myIntZone;
				
				preferences = preferencesField.getText();	
				phoneNumber = phoneNumberField.getText();
				
				try {
					customer.updateCustomer(database);
				} catch (Exception e) {
					System.out.println(e.toString());
				}
				return true;		
			}		
	
	  }
			
	  	public ParentWindow() {
		  CustomerDialog dialog = new CustomerDialog(this);
		  dialog.setVisible(true);
		  setTitle("Registrer customer");
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  setLayout(new FlowLayout());
		  setLocation(300, 300); 
		  dialog.setLocation(350, 350);  
	 } 
}  


	class ChangeCustomerInfoGui {
	  static public void main(String[] args) {
		  ParentWindow test = new ParentWindow();
	  }   
	} 

