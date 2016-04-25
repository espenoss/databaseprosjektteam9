package GUI;
import controller.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

class ChangeCustomerInfoDialog extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Sales sales = null; 
	Customer customer = null;

	public ChangeCustomerInfoDialog(Sales sales) {
		this.sales = sales;
		CustomerDialog dialog = new CustomerDialog(this);
		setTitle("Change customer info");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	} 

	private class CustomerDialog extends MyDialog implements ActionListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

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


		private final String status[] = {"active", "inactive"}; 
		private JComboBox status_list = new JComboBox(status);



		public CustomerDialog(JFrame parent){
			super(parent, "Fill in new information about a customer");
			add(new JPanel(), BorderLayout.NORTH);
			add(new CustomerDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			customerSelect.addActionListener(this);

			setSize(650,700);
			setLocationRelativeTo(null);
		}

		private class CustomerDatapanel extends JPanel{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public CustomerDatapanel(){
				setLayout(new GridLayout(20,1));

				customerList = sales.viewCustomerList();
				zoneList = sales.viewZones();

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

			boolean nameOk = !firstName.equals("") && editor.isAlpha(firstName) 
					&& !surName.equals("") && editor.isAlpha(surName);
			boolean emailAndAdressOk = !email.equals("") && !adress.equals("");
			boolean zipAndZoneOk = zip_code != -1 && zone_nr != -1;
			boolean phoneOk = !preferences.equals("") && !phoneNumber.equals("") && editor.isNumeric(phoneNumber);

			if(!nameOk) JOptionPane.showMessageDialog(null, "Name cannot be empty or contain numbers");
			if(!emailAndAdressOk) JOptionPane.showMessageDialog(null, "Email or adress cannot be empty or contain numbers");
			if(!phoneOk) JOptionPane.showMessageDialog(null, "Phone number cannot be empty or contain letters");

			boolean dataCheck = nameOk && emailAndAdressOk && zipAndZoneOk && phoneOk;

			boolean active;
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
				currCust.setActive(active);
				return currCust.updateCustomer(sales.getDatabase());					
			}else{
				return false;
			}
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




