package databaseguiii;
import java.awt.BorderLayout;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import controller.*;

import static javax.swing.JOptionPane.*;

public class UserDialog extends MyDialog{

	private TextEditor editor = new TextEditor();
	
	private JTextField userIDfield = new JTextField(10);
	String user_id_text = userIDfield.getText();
	
	private final String userRoles[] = {"Admin", "Cook", "Driver", "Sales", "Storage"}; 
	JComboBox userList = new JComboBox(userRoles);
	int user_type_int = userList.getSelectedIndex();
	
	private JTextField usernameField = new JTextField(20);
	String user_name_text = usernameField.getText();
	
	private JTextField passwordField = new JTextField(20);
	String password_text = passwordField.getText();
	
	public String getUserId(){
		return user_id_text;
	}
	public int getUserType(){
		return user_type_int;
	}
	
	public String getUserName(){
		return user_name_text;
	}
	public String getPword(){
		return password_text;
	}
	
	
	
	public UserDialog(JFrame parent){
		super(parent, "New user");
		add(new JPanel(), BorderLayout.NORTH);
		add(new UserDatapanel(),BorderLayout.CENTER);
		add(getButtonPanel(),BorderLayout.SOUTH);
		pack();
	}
	
	private class UserDatapanel extends JPanel{
		public UserDatapanel(){
			setLayout(new GridLayout(4,2));
			add(new JLabel("Username: ", JLabel.RIGHT));
			add(userIDfield);
			
			add(new JLabel("User type: ", JLabel.RIGHT));
			add(userList);
			
			add(new JLabel("Name: ", JLabel.RIGHT));
			add(usernameField);
			

			add(new JLabel("Password: ", JLabel.RIGHT));
			add(passwordField);
			
			
		}
	}
}
	

