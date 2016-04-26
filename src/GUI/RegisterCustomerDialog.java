package GUI;
import controller.*;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * The Class RegisterCustomerDialog.<br>
 * Used to register new customer
 */
class RegisterCustomerDialog extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sales user object. */
	private Sales sales = null;

	/**
	 * Instantiates a new register customer dialog.
	 *
	 * @param sales User object
	 */
	public RegisterCustomerDialog(Sales sales) {
		this.sales = sales;
		CustomerDialog dialog = new CustomerDialog(this);
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
	private class CustomerDialog extends MyDialog{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** Convenience string handling methods*/
		private TextEditor editor = new TextEditor();

		/** The first name field. */
		private JTextField firstNameField = new JTextField(20);
		
		/** The surname field. */
		private JTextField surNameField = new JTextField(20);
		
		/** The email field. */
		private JTextField emailField = new JTextField(20);
		
		/** The adress field. */
		private JTextField adressField = new JTextField(20);
		
		/** The zip code field. */
		private JTextField zip_codeField = new JTextField(6);
		
		/** The zone list. */
		private String[][] zoneList = null;
		
		/** The zone select box. */
		private JComboBox zoneSelect;
		
		/** The preferences field. */
		private JTextField preferencesField = new JTextField(50);
		
		/** The phone number field. */
		private JTextField phoneNumberField = new JTextField(12);
		
		/** The status. */
		private final String status[] = {"Active", "Inactive"}; 
		
		/** The status select box. */
		private JComboBox status_list = new JComboBox(status);
		
		/** The surname. */
		private String surName;
		
		/** The firstname. */
		private String firstName;
		
		/** The email. */
		private String email;
		
		/** The adress. */
		private String adress;
		
		/** The zip code. */
		private int zip_code;
		
		/** The zone nr. */
		private int zone_nr;
		
		/** The preferences. */
		private String preferences;
		
		/** The phone number. */
		private String phoneNumber;
		
		/** The active status. */
		private boolean active;

		/**
		 * Instantiates a new customer dialog.
		 *
		 * @param parent Parent frame
		 */
		public CustomerDialog(JFrame parent){
			super(parent, "New customer");
			add(new JPanel(), BorderLayout.NORTH);
			add(new CustomerDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);

			setSize(600,700);
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
				setLayout(new GridLayout(18,1));

				// Fetch zone list from database
				zoneList = sales.viewZones();

				// Convert to string list
				ArrayList<String> zoneNames = new ArrayList<>();
				for(int i=0;i<zoneList.length;i++){
					zoneNames.add(zoneList[i][1]);
				}					

				// Set up combo box
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

		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){
			// Get data from fields
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
			boolean phoneOk = !phoneNumber.equals("") && editor.isNumeric(phoneNumber);

			if(!nameOk) JOptionPane.showMessageDialog(null, "Name cannot be empty or contain numbers");
			if(!emailAndAdressOk) JOptionPane.showMessageDialog(null, "Email or adress cannot be empty or contain numbers");
			if(!phoneOk) JOptionPane.showMessageDialog(null, "Phone number cannot be empty or contain letters");

			boolean dataCheck = nameOk && emailAndAdressOk && zipAndZoneOk && phoneOk;

			if(status_list.getSelectedIndex() == 0){
				active = true;
			}else{
				active = false;
			}

			// Register data to database if valid
			if(dataCheck){
				return sales.registerCustomer(surName, firstName, phoneNumber, email, adress, 
						zip_code, zone_nr, preferences, active) != null;
			}else{
				return false;
			}

		}
	}
}  

