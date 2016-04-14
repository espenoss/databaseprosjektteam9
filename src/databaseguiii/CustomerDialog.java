package databaseguiii;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;

import static javax.swing.JOptionPane.*;

public class CustomerDialog extends MyDialog{
	private TextEditor editor = new TextEditor();
	
	private JTextField firstName = new JTextField(20);
	String firstNameText = firstName.getText();
	
	private JTextField surName = new JTextField(20);
	String surNameText = surName.getText();
	
	private JTextField email = new JTextField(20);
	String emailText = email.getText();
	
	private JTextField adress = new JTextField(20);
	String adressText = adress.getText();
	
	private JTextField zip_code = new JTextField(6);
	String zip_codeText  = zip_code.getText();
	int myIntZip = editor.StringToInt(zip_codeText);
	
	private JTextField zone_nr = new JTextField(6);
	String zone_codeText  = zone_nr.getText();
	int myIntZone = editor.StringToInt(zone_codeText);
	
	private JTextField preferences = new JTextField(100);
	String preferencesText = preferences.getText();
	
	private JTextField phoneNumber = new JTextField(12);
	String phoneNumberText = phoneNumber.getText();
	
	public String getFirstNameText(){
		return firstNameText;
	}
	
	public String getSurNameText(){
		return surNameText;
	}
	
	public String getEmailText(){
		return emailText;
	}
	
	public String getAdressText(){
		return adressText;
	}
	
	public int getZip_codeText(){
		return myIntZip;
	}
	
	public int getZone_nrText(){
		return myIntZone;
	}
	
	public String getPrefencesText(){
		return preferences.getText();
	}
	
	public String getPhoneNrText(){
		return phoneNumber.getText();
	}
	
	public boolean getActive(){
		return true;
	}
	
	
	
	public CustomerDialog(JFrame parent){
		super(parent, "Customer");
		add(new JPanel(), BorderLayout.NORTH);
		add(new CustomerDatapanel(),BorderLayout.CENTER);
		add(getButtonPanel(),BorderLayout.SOUTH);
		pack();
	}
	
	private class CustomerDatapanel extends JPanel{
		public CustomerDatapanel(){
			setLayout(new GridLayout(8,2));
	
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
}
	

