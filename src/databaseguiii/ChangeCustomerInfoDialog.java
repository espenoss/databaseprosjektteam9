package databaseguiii;
import controller.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import org.hamcrest.core.IsInstanceOf;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

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
			public String[][] zoneList = null;
			private JComboBox customerSelect;
			private JTextField firstNameField = new JTextField(20);
			private JTextField surNameField = new JTextField(20);
			private JTextField emailField = new JTextField(20);
			private JTextField adressField = new JTextField(20);
			private JTextField zip_codeField = new JTextField(6);
			private JComboBox zoneSelect;
			private JTextField preferencesField = new JTextField(50);
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
					setLayout(new GridLayout(20,1));

					try {
						customerList = sales.viewCustomerList();
						zoneList = sales.viewZones();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					ArrayList<String> nameList = new ArrayList<>();
					for(Customer c: customerList){
						nameList.add(c.getCustomerID() + " " + c.getFirstName() + " " + c.getSurName());
					}

					ArrayList<String> zoneNames = new ArrayList<>();
					for(int i=0;i<zoneList.length;i++){
						zoneNames.add(zoneList[i][1]);
					}

					customerSelect = new JComboBox<>(nameList.toArray());
					zoneSelect = new JComboBox<>(zoneNames.toArray());	
				
					// Initialize fields
					Customer currCust = customerList.get(0);
					surNameField.setText(currCust.getSurName());
					firstNameField.setText(currCust.getFirstName());
					phoneNumberField.setText(currCust.getPhoneNumber());
					emailField.setText(currCust.getEmail());
					adressField.setText(currCust.getAdress());
					zip_codeField.setText(Integer.toString(currCust.getZipCode()));
					for(int i=0;i<zoneList.length;i++){
						if(currCust.getZoneNr() == Integer.parseInt(zoneList[i][0])){
							zoneSelect.setSelectedIndex(i);
						}
					}
					preferencesField.setText(currCust.getPreferences());
									

					add(new JLabel("Customer: ", JLabel.LEFT));
					add(customerSelect);
			
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
					
					add(new JLabel("Zone: ", JLabel.LEFT));
					add(zoneSelect);
					
					add(new JLabel("Preferences: ", JLabel.LEFT));
					add(preferencesField);
					
					add(new JLabel("Status: ", JLabel.LEFT));
					add(status_list);
					
					
				}
			}
			public boolean okData(){
								
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
				
				boolean nameOk = firstName != "" && editor.isAlpha(firstName) 
						&& surName != "" && editor.isAlpha(surName);
				if(!nameOk) JOptionPane.showMessageDialog(null, "Name cannot contain numbers");
				
				boolean emailAndAdressOk = email != "" && adress != "";
				boolean zipAndZoneOk = zip_code != -1 && zone_nr != -1;
				boolean prefAndPhoneOk = preferences != "" && phoneNumber != "";
				
				boolean dataCheck = nameOk && emailAndAdressOk && zipAndZoneOk && prefAndPhoneOk;

				if(status_list.getSelectedIndex() == 0){
					active = true;
				}else{
					active = false;
				}
				
				if(dataCheck){
					int customerIndex = customerSelect.getSelectedIndex();
					Customer currCust = customerList.get(customerIndex);
					currCust.setFirstName(firstName);
					currCust.setSurName(surName);
					currCust.setEmail(email);
					currCust.setAdress(adress);
					currCust.setZipCode(zip_code);
					currCust.setZoneNr(zone_nr);
					currCust.setPreferences(preferences);
					currCust.setPhoneNumber(phoneNumber);
					try {
						currCust.updateCustomer(sales.getDatabase());					
					} catch (Exception e) {
						dataCheck = false;
						System.out.println(e.toString());
					}					
				}

				return dataCheck;		
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
				for(int i=0;i<zoneList.length;i++){
					if(currCust.getZoneNr() == Integer.parseInt(zoneList[i][0])){
						zoneSelect.setSelectedIndex(i);
					}
				}
				preferencesField.setText(currCust.getPreferences());
			
			}
	
	  }
}  




