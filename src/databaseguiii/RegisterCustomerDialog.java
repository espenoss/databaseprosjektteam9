package databaseguiii;
import controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import databasePackage.Database;

class RegisterCustomerDialog extends JFrame {
	  Sales sales = null;
	  
	  public RegisterCustomerDialog(Sales sales) {
		  this.sales = sales;
		  CustomerDialog dialog = new CustomerDialog(this);
		  dialog.setVisible(true);
		  setTitle("Registrer customer");
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  setLayout(new FlowLayout());
		  setLocation(300, 300); 
		  dialog.setLocation(350, 350);  
	 } 
	  
	  private class CustomerDialog extends MyDialog{
			private TextEditor editor = new TextEditor();
			
			private JTextField firstNameField = new JTextField(20);
			private JTextField surNameField = new JTextField(20);
			private JTextField emailField = new JTextField(20);
			private JTextField adressField = new JTextField(20);
			private JTextField zip_codeField = new JTextField(6);
			private JTextField zone_nrField = new JTextField(6);
			private JTextField preferencesField = new JTextField(100);
			private JTextField phoneNumberField = new JTextField(12);
			private final String status[] = {"active", "unactive"}; 
			private JComboBox status_list = new JComboBox(status);
			private String surName;
			private String firstName;
			private String email;
			private String adress;
			private int zip_code;
			private int zone_nr;
			private String preferences;
			private String phoneNumber;
			private boolean active;
			
			public CustomerDialog(JFrame parent){
				super(parent, "New customer");
				add(new JPanel(), BorderLayout.NORTH);
				add(new CustomerDatapanel(),BorderLayout.CENTER);
				add(getButtonPanel(),BorderLayout.SOUTH);
				pack();
			}
			
			private class CustomerDatapanel extends JPanel{
				public CustomerDatapanel(){
					setLayout(new GridLayout(9,2));
			
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
				

				if(status_list.getSelectedIndex() == 0){
					active = true;
				}else{
					active = false;
				}
				
				try {
					sales.registerCustomer(surName, firstName, phoneNumber, email, adress, 
						zip_code, zone_nr, preferences, active);
				} catch (Exception e) {
					System.out.println(e.toString());
				}
				return true;		
			}
		}
}  

