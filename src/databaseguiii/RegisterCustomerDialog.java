package databaseguiii;
import controller.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import databasePackage.Database;

class RegisterCustomerDialog extends JFrame {
	  Sales sales = null;
	  
	  public RegisterCustomerDialog(Sales sales) {
		  this.sales = sales;
		  CustomerDialog dialog = new CustomerDialog(this);
		  dialog.setVisible(true);
		  setTitle("Register customer");
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
			public String[][] zoneList = null;
			private JComboBox zoneSelect;
			private JTextField preferencesField = new JTextField(50);
			private JTextField phoneNumberField = new JTextField(12);
			private final String status[] = {"Active", "Inactive"}; 
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
					GridLayout superGrid = new GridLayout(18,1);
					setLayout(superGrid);
			
					try {
						zoneList = sales.viewZones();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					ArrayList<String> zoneNames = new ArrayList<>();
					for(int i=0;i<zoneList.length;i++){
						zoneNames.add(zoneList[i][1]);
					}					
					
					zoneSelect = new JComboBox<>(zoneNames.toArray());						
					
					add(new JLabel("Surname: ", JLabel.LEFT));
					add(surNameField);

					add(new JLabel("First name: ", JLabel.LEFT));
					add(firstNameField);
					
					add(new JLabel("Phone number: ", JLabel.LEFT));
					add(phoneNumberField);
					
					add(new JLabel("Email: ", JLabel.LEFT));
					add(emailField);
					
					add(new JLabel("Adress: ", JLabel.LEFT));
					add(adressField);
					
					add(new JLabel("Zip Code: ", JLabel.LEFT));
					add(zip_codeField);
					
					add(new JLabel("Zone Number: ", JLabel.LEFT));
					add(zoneSelect);
					
					add(new JLabel("Preferences: ", JLabel.LEFT));
					add(preferencesField);
					
					add(new JLabel("Status: ", JLabel.LEFT));
					add(status_list);
					
				}
			}
			public boolean okData(){
				
				boolean dataCheck = true;
				
				firstName = firstNameField.getText().trim();	
				surName = surNameField.getText().trim();
				email = emailField.getText().trim();
				adress = adressField.getText().trim();
				
				String zip_codeText  = zip_codeField.getText().trim();
				int myIntZip = editor.stringToInt(zip_codeText);
				zip_code = myIntZip;
				
				int zoneIndex = zoneSelect.getSelectedIndex();
				String zone_nrText  = zoneList[zoneIndex][0];
				int myIntZone = editor.stringToInt(zone_nrText);
				zone_nr = myIntZone;
				
				preferences = preferencesField.getText().trim();	
				phoneNumber = phoneNumberField.getText().trim();
				
				boolean nameOk = firstName != "" && surName != "";
				boolean emailAndAdressOk = email != "" && adress != "";
				boolean zipAndZoneOk = zip_code != -1 && zone_nr != -1;
				boolean prefAndPhoneOk = preferences != "" && phoneNumber != "";
				
				dataCheck = nameOk && emailAndAdressOk && zipAndZoneOk && prefAndPhoneOk;

				if(status_list.getSelectedIndex() == 0){
					active = true;
				}else{
					active = false;
				}

				if(dataCheck){
					try {
						sales.registerCustomer(surName, firstName, phoneNumber, email, adress, 
							zip_code, zone_nr, preferences, active);
					} catch (Exception e) {
						System.out.println(e.toString());
					}					

				}

				return dataCheck;		

			}
		}
}  

