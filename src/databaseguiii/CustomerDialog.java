package databaseguiii;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.JOptionPane.*;

public class CustomerDialog extends MyDialog{
	private JTextField firstName = new JTextField(20);
	private JTextField surName = new JTextField(20);
	private JTextField email = new JTextField(20);
	private JTextField adress = new JTextField(20);
	
	private JTextField zip_code = new JTextField(6);
	String text1 = zip_code.getText();
	int  zip_codeInt= Integer.parseInt(text1);
	
	public CustomerDialog(JFrame parent){
		super(parent, "Person");
		add(new JPanel(), BorderLayout.NORTH);
		add(new CustomerDatapanel(),BorderLayout.CENTER);
		add(getButtonPanel(),BorderLayout.SOUTH);
		pack();
	}
	
	private class CustomerDatapanel extends JPanel{
		public CustomerDatapanel(){
			setLayout(new GridLayout(3,2));
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
		}
	}
	public boolean showDialog(Customer customer){
		firstName.setText(user.getUsername());
		roleField.setText(user.getRole());
		passwordField.setText(user.getPassword());
		setOK(false);
		pack();
		usernameField.requestFocusInWindow();
		setVisible(true);
		if(isOK()){
			user.setUsername(usernameField.getText());
			user.setPassword(roleField.getText());
			user.setPassword(passwordField.getText());
			return true;
		}else{
			return false;
		}
	}
	
	protected boolean okData(){
		String username = usernameField.getText().trim();
		String role = roleField.getText().trim();
		String password = passwordField.getText().trim();
		if(username.equals("")|| password.equals("") || role.equals("")){
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
	

