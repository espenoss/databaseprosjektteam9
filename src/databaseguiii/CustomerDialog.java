package databaseguiii;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Customer;

import static javax.swing.JOptionPane.*;

public class CustomerDialog extends MyDialog{

	private JTextField customerID = new JTextField(20);
	private JTextField firstName = new JTextField(20);
	private JTextField surName = new JTextField(20);
	private JTextField email = new JTextField(20);
	private JTextField adress = new JTextField(20);
	
	private JTextField zip_code = new JTextField(6);
	private JTextField zone_nr = new JTextField(6);
/*	String text2 = zone_nr.getText();
	int  zone_nrInt= Integer.parseInt(text2);   */
	
	private JTextField preferences = new JTextField(100);
	private JTextField phoneNumber = new JTextField(12);
	
	
	public CustomerDialog(JFrame parent){
		super(parent, "Person");
		add(new JPanel(), BorderLayout.NORTH);
		add(new CustomerDatapanel(),BorderLayout.CENTER);
		add(getButtonPanel(),BorderLayout.SOUTH);
		pack();
	}
	
	private class CustomerDatapanel extends JPanel{
		public CustomerDatapanel(){
			setLayout(new GridLayout(9,2));
			add(new JLabel("Customer ID: ", JLabel.RIGHT));
			add(customerID);
			
			add(new JLabel("First name: ", JLabel.RIGHT));
			add(firstName);
			
			add(new JLabel("Surname: ", JLabel.RIGHT));
			add(surName);

			add(new JLabel("Email: ", JLabel.RIGHT));
			add(email);
			
			add(new JLabel("Adress: ", JLabel.RIGHT));
			add(adress);
			
			add(new JLabel("Zip Code: ", JLabel.RIGHT));
			add(zip_code);
			
			add(new JLabel("Zone Number: ", JLabel.RIGHT));
			add(zone_nr);
			
			add(new JLabel("Preferences: ", JLabel.RIGHT));
			add(preferences);
			
			add(new JLabel("Phone number: ", JLabel.RIGHT));
			add(phoneNumber);
		}
	}
	public boolean showDialog(Customer customer){
	//	customerID.setText(customer.getCustomerID());
		firstName.setText(customer.getFirstName());
		surName.setText(customer.getSurName());
		email.setText(customer.getEmail());
		adress.setText(customer.getAdress());
//		zip_code.setText(customer.getZipCode());
//		zone_nr.setText(customer.getZoneNr());
		preferences.setText(customer.getPreferences());
		preferences.setText(customer.getPhoneNumber());
		setOK(false);
		pack();
		
		customerID.requestFocusInWindow();
		setVisible(true);
		if(isOK()){
			//customer.setCustomerID(customer.getText());
			customer.setFirstName(firstName.getText());
			customer.setSurName(surName.getText());
			customer.setEmail(email.getText());
			customer.setAdress(adress.getText());
 //	    	customer.setZipCode(zip_code.getText());
//		    customer.setZoneNr(zone_nr.getText());
			customer.setPreferences(preferences.getText());
			return true;
		}else{
			return false;
		}
	}
	
	protected boolean okData(){
		String Firstname = firstName.getText().trim();
		String Surname = surName.getText().trim();
		String Email = email.getText().trim();
		String Adress = adress.getText().trim();

		String text1 = zip_code.getText();
		int  zip_codeInt= Integer.parseInt(text1.trim());
		
		String text2 = zip_code.getText();
		int  zone_nrInt= Integer.parseInt(text2.trim());
		

		String text3 = customerID.getText();
		int  customer_id_Int= Integer.parseInt(text3.trim());
		
		
		String Preferences = preferences.getText().trim();
		
		if(customerID.equals("") || firstName.equals("")|| surName.equals("") || email.equals("") || adress.equals("") || zip_code.equals("") || zone_nr.equals("") || preferences.equals("")){
			showMessageDialog(CustomerDialog.this, "All information must be given.");
			/*if(!username.equals("")){
				usernameField.requestFocusInWindow();
			}else{
				passwordField.requestFocusInWindow();
			}
			return false;
			}else{*/
		//		return true;
			}
		return true;
		}	
	}
	

