package databaseguiii;
//noe
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.User;

import static javax.swing.JOptionPane.*;

public class UserDialog extends MyDialog{
	private JTextField userIDfield = new JTextField(10);
	private JTextField userTypeField = new JTextField(10);
	private JTextField usernameField = new JTextField(20);
	private JTextField passwordField = new JTextField(20);
	
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
			add(userTypeField);
			
			add(new JLabel("Name: ", JLabel.RIGHT));
			add(usernameField);
			

			add(new JLabel("Password: ", JLabel.RIGHT));
			add(passwordField);
			
			
		}
	}
	public boolean showDialog(User user){
		userIDfield.setText(user.getUserID());
	//	userTypeField.setText(user.getUserType());
		usernameField.setText(user.getName());
		passwordField.setText(user.getPword());
		setOK(false);
		pack();
		userIDfield.requestFocusInWindow();
		setVisible(true);
		if(isOK()){
			user.setName(usernameField.getText());
//			user.setUserType(userTypeField.getText());
	
			user.setPassword(passwordField.getText());
			user.setUserID(userIDfield.getText());
			return true;
		}else{
			return false;
		}
	}
	
	protected boolean okData(){
		String name = usernameField.getText().trim();
		String password = passwordField.getText().trim();
		if(name.equals("")|| password.equals("")){
			showMessageDialog(UserDialog.this, "Username, role and password must be given.");
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
	

