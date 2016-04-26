package GUI;
import controller.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * The Class ChangeCustomerInfoDialog.<br>
 * Used to change customer info
 */
public class ChangeCustomerInfoDialog extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sales user object. */
	private Sales sales = null; 
	
	/**
	 * Instantiates a new change customer info dialog.
	 *
	 * @param sales User object
	 */
	public ChangeCustomerInfoDialog(Sales sales) {
		this.sales = sales;
		// Add dialog
		CustomerDialog dialog = new CustomerDialog(this);
		setTitle("Change customer info");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		pack();
		// Set window location in middle of screen
		dialog.setLocationRelativeTo(null);
		// Display window
		dialog.setVisible(true);
	} 

	/**
	 * The Class CustomerDialog.
	 */
	private class CustomerDialog extends MyDialog implements ActionListener{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** Convenience string handling methods*/
		private TextEditor editor = new TextEditor();

		/** The customer list. */
		private ArrayList<Customer> customerList = new ArrayList<>();
		
		/** The zone list. */
		public String[][] zoneList = null;
		
		/** The customer selection combobox. */
		private JComboBox customerSelect;
		
		/** The first name entry field. */
		private JTextField firstNameField = new JTextField(20);
		
		/** The sur name entry field. */
		private JTextField surNameField = new JTextField(20);
		
		/** The email entry field. */
		private JTextField emailField = new JTextField(20);
		
		/** The adress entry field. */
		private JTextField adressField = new JTextField(20);
		
		/** The zip code entry field. */
		private JTextField zip_codeField = new JTextField(6);
		
		/** The zone selection combobox. */
		private JComboBox zoneSelect;
		
		/** The preferences entry field. */
		private JTextField preferencesField = new JTextField(50);
		
		/** The phone number entry field. */
		private JTextField phoneNumberField = new JTextField(12);
		
		/** The surname. */
		private String surName;
		
		/** The first name. */
		private String firstName;
		
		/** The email. */
		private String email;
		
		/** The adress. */
		private String adress;
		
		/** The zip_code. */
		private int zip_code;
		
		/** The zone_nr. */
		private int zone_nr;
		
		/** The preferences. */
		private String preferences;
		
		/** The phone number. */
		private String phoneNumber;

		/** The status selection states. */
		private final String status[] = {"active", "inactive"}; 
		
		/** The status selection combobox. */
		private JComboBox status_list = new JComboBox(status);



		/**
		 * Instantiates a new customer dialog.
		 *
		 * @param parent Parent frame
		 */
		public CustomerDialog(JFrame parent){
			super(parent, "Fill in new information about a customer");
			add(new JPanel(), BorderLayout.NORTH);
			add(new CustomerDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			customerSelect.addActionListener(this);

			setSize(650,700);
		}

		/**
		 * The Class CustomerDatapanel.
		 */
		private class CustomerDatapanel extends JPanel{
			
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * Instantiates a new customer datapanel.
			 */
			public CustomerDatapanel(){
				setLayout(new GridLayout(20,1));

				// Fetch customer list from database
				customerList = sales.viewCustomerList();
				// Fetch zone list from database
				zoneList = sales.viewZones();

				// Convert customer objects to string list
				ArrayList<String> nameList = new ArrayList<>();
				for(Customer c: customerList){
					nameList.add(c.getCustomerID() + " " + c.getFirstName() + " " + c.getSurName());
				}

				// Get zone names
				ArrayList<String> zoneNames = new ArrayList<>();
				for(int i=0;i<zoneList.length;i++){
					zoneNames.add(zoneList[i][1]);
				}

				// Set up comboboxes
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

				// Add components
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
		
		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){

			// Get field information
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

			// Check if data is valid
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

			// If data is valid, update customer info in database
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
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 * Listen to customer selection combobox and loads current customer information into fields
		 */
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
					break;
				}
			}
			preferencesField.setText(currCust.getPreferences());

		}

	}
}  




