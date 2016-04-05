package BareGUItest;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.JOptionPane.*;

public class UserDialog extends MyDialog{
	private JTextField usernameField = new JTextField(20);
	private JTextField passwordField = new JTextField(20);
	
	public UserDialog(JFrame parent){
		super(parent, "Person");
		add(new JPanel(), BorderLayout.NORTH);
		add(new UserDatapanel(),BorderLayout.CENTER);
		add(getButtonPanel(),BorderLayout.SOUTH);
		pack();
	}
	
	private class UserDatapanel extends JPanel{
		public UserDatapanel(){
			setLayout(new GridLayout(2,2));
			add(new JLabel("username: ", JLabel.RIGHT));
			add(usernameField);

			add(new JLabel("password: ", JLabel.RIGHT));
			add(passwordField);
			
			
		}
	}
	public boolean showDialog(User user){
		usernameField.setText(user.getUsername());
		passwordField.setText(user.getPassword());
		setOK(false);
		pack();
		usernameField.requestFocusInWindow();
		setVisible(true);
		if(isOK()){
			user.setUsername(usernameField.getText());
			user.setPassword(passwordField.getText());
			return true;
		}else{
			return false;
		}
	}
	
	protected boolean okData(){
		String username = usernameField.getText().trim();
		String password = passwordField.getText().trim();
		if(username.equals("")|| password.equals("")){
			showMessageDialog(UserDialog.this, "Username and password must be given.");
			if(!username.equals("")){
				usernameField.requestFocusInWindow();
			}else{
				passwordField.requestFocusInWindow();
			}
			return false;
			}else{
				return true;
			}
		}	
	}
	

